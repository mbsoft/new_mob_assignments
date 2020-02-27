using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using SkateboardSharingApp.DataRepository;
using SkateboardSharingApp.Models;

namespace SkateboardSharingApp.Controllers
{
    public class SkateboardsController : Controller
    {
        private readonly ICallAPIService _apiService;
        private readonly ILogger<SkateboardsController> _logger;
        private Guid _uniqueLogId;

        public SkateboardsController(ICallAPIService apiService, ILogger<SkateboardsController> logger)
        {
            _apiService = apiService;
            _logger = logger;
        }

        private void LogIt(string msg, bool isError = false)
        {
            if (isError)
            {
                _logger.LogInformation($"{_uniqueLogId}: {msg}");
                return;
            }
            _logger.LogError($"{_uniqueLogId}: {msg}");
        }

        // GET: Skateboards
        public async Task<IActionResult> Index(int id)
        {
            try
            {
                _uniqueLogId = Guid.NewGuid();
                LogIt("Entering index method for owner controller.");
                LogIt($"loging owner id ({id})");

                _uniqueLogId = Guid.NewGuid();

                TempData["owner_id"] = id;

                var skateboards = _apiService.GetOwnerSkateboards(id, _uniqueLogId);

                if (skateboards == null)
                    skateboards = new List<Skateboard>();

                return View(skateboards);
            }
            catch (Exception ex)
            {
                LogIt($"Exception was thrown for owner id {id} - error {ex.Message}");
                return View(new List<Skateboard>());
            }
        }

        // GET: Skateboards/Details/5
        public async Task<IActionResult> Details(int? id)
        {
            try
            {
                _uniqueLogId = Guid.NewGuid();
                LogIt("Entering Details GET method.");
                LogIt($"loging sklateboard id ({id})");

                if (id == null)
                {
                    return NotFound();
                }

                var skateboard = _apiService.GetOwnerSkateboard((int)id, _uniqueLogId);
                if (skateboard == null)
                {
                    return NotFound();
                }

                return View(skateboard);
            }
            catch (Exception ex)
            {
                LogIt($"Exception was thrown for Delete skateboard id {id} - error {ex.Message}");
                return NotFound();
            }
        }

        // GET: Skateboards/Create
        public IActionResult Create()
        {
            return View();
        }

        // POST: Skateboards/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Create([Bind("Name,Description,Color,Length,Wedith,PhotoUrl,OwnerId,IsAvailable,SkateboardId,BorrowerId,IsCheckedOut,CheckedOutDt,ReturnDt")] Skateboard skateboard)
        {
            try
            {
                _uniqueLogId = Guid.NewGuid();
                LogIt("Entering Create POST method.");

                if (ModelState.IsValid)
                {
                    if (!string.IsNullOrEmpty(TempData["owner_id"].ToString()))
                    {
                        int ownerId = (int)TempData["owner_id"];
                        skateboard.OwnerId = ownerId;

                        var success = _apiService.AddSkateboard(skateboard, _uniqueLogId);

                        if (success)
                            return RedirectToAction(nameof(Index), "Skateboards", new { id = ownerId });

                        return View(skateboard);
                    }

                }
                return View(skateboard);
            }
            catch (Exception ex)
            {
                LogIt($"Exception was thrown for Create POST- error {ex.Message}");
                return RedirectToAction(nameof(Index), "Home");
            }
        }

        // GET: Skateboards/Edit/5
        public async Task<IActionResult> Edit(int? id)
        {
            try
            {
                _uniqueLogId = Guid.NewGuid();
                LogIt("Entering Edit GET method.");
                LogIt($"loging skateboard id ({id})");

                if (id == null)
                {
                    return NotFound();
                }

                var skateboard = _apiService.GetOwnerSkateboard((int)id, _uniqueLogId);
                if (skateboard == null)
                {
                    return NotFound();
                }
                return View(skateboard);
            }
            catch (Exception ex)
            {
                LogIt($"Exception was thrown for Edit GET skateboard id {id} - error {ex.Message}");
                return NotFound();
            }
        }

        // POST: Skateboards/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see http://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(int id, [Bind("Name,Description,Color,Length,Wedith,PhotoUrl,OwnerId,IsAvailable,SkateboardId,BorrowerId,IsCheckedOut,CheckedOutDt,ReturnDt")] Skateboard skateboard)
        {
            try
            {
                _uniqueLogId = Guid.NewGuid();
                LogIt("Entering Edit POST method.");

                if (id != skateboard.SkateboardId)
                {
                    return NotFound();
                }

                if (ModelState.IsValid)
                {
                    try
                    {
                        if (!_apiService.UpdateSkateboard(skateboard, _uniqueLogId))
                            return View(skateboard);
                    }
                    catch (DbUpdateConcurrencyException)
                    {
                        return NotFound();
                    }
                }

                if (!string.IsNullOrEmpty(TempData["owner_id"].ToString()))
                {
                    int ownerId = (int)TempData["owner_id"];
                    return RedirectToAction(nameof(Index), "Skateboards", new { id = ownerId });
                }
                return RedirectToAction(nameof(Index), "Home");

            }
            catch (Exception ex)
            {
                LogIt($"Exception was thrown for Edit POST-error {ex.Message}");
                return RedirectToAction(nameof(Index), "Home");
            }
        }

        // GET: Skateboards/Delete/5
        public async Task<IActionResult> Delete(int? id)
        {
            try
            {
                _uniqueLogId = Guid.NewGuid();
                LogIt("Entering Delete GET method.");
                LogIt($"loging skateboard id ({id})");

                if (id == null)
                {
                    return NotFound();
                }

                var skateboard = _apiService.GetOwnerSkateboard((int)id, _uniqueLogId);
                if (skateboard == null)
                {
                    return NotFound();
                }

                return View(skateboard);
            }
            catch (Exception ex)
            {
                LogIt($"Exception was thrown for Delete GET skateboard id {id} - error {ex.Message}");
                return NotFound();
            }


        }

        // POST: Skateboards/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> DeleteConfirmed(int id)
        {
            try
            {
                _uniqueLogId = Guid.NewGuid();
                LogIt("Entering DeleteConfirmed POST method.");
                LogIt($"loging skateboard id ({id})");

                if (_apiService.DeleteSkateboard(id, _uniqueLogId))
                    return RedirectToAction(nameof(Index));

                return View();
            }
            catch (Exception ex)
            {
                LogIt($"Exception was thrown for DeleteConfirmed POST skateboard id {id} - error {ex.Message}");
                return View();
            }
        }
    }
}

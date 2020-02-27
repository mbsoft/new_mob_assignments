using System.Collections.Generic;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using Microsoft.Extensions.Logging;
using SkateboardSharingApp.DataRepository;
using SkateboardSharingApp.Models;
using System.Linq;
using System;

namespace SkateboardSharingApp.Controllers
{
    public class BorrowerController : Controller
    {
        private readonly ICallAPIService _apiService;
        private readonly ILogger<BorrowerController> _logger;
        private Guid _uniqueLogId;

        public BorrowerController(ICallAPIService apiService, ILogger<BorrowerController> logger)
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
                LogIt("Entering index method for borrower controller.");
                LogIt($"loging borrower id ({id})");

                TempData["borrower_id"] = id;
                var skateboards = _apiService.GetAvailableSkateboards(_uniqueLogId);

                if (skateboards == null)
                    skateboards = new List<Skateboard>();

                return View(skateboards);
            }
            catch (Exception ex)
            {
                LogIt($"Exception was thrown for borrower id {id} - error {ex.Message}");
                return View(new List<Skateboard>());
            }
        }


        public async Task<IActionResult> Checkout(int id)
        {
            try
            {
                _uniqueLogId = Guid.NewGuid();
                LogIt("Entering Checkout method for borrower controller.");
                LogIt($"loging skateboard id ({id})");

                if (!string.IsNullOrEmpty(TempData["borrower_id"].ToString()))
                {
                    int borrowerId = (int)TempData["borrower_id"];
                    LogIt($"loging borrower id ({borrowerId})");

                    if (_apiService.CheckoutSkateboard(id, borrowerId, _uniqueLogId))
                        return RedirectToAction(nameof(Index));
                }
                return View();
            }
            catch (Exception ex)
            {
                LogIt($"Exception was thrown for borrower id {id} - error {ex.Message}");
                return View();
            }
        }
    }
}
using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using SkateboardSharingApp.Models;

namespace SkateboardSharingApp.Controllers
{
    public class HomeController : Controller
    {
        private readonly ILogger<HomeController> _logger;

        public HomeController(ILogger<HomeController> logger)
        {
            _logger = logger;
        }

        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Privacy()
        {
            return View();
        }

        public IActionResult UserType(CustomerDetails cd)
        {
            if (!string.IsNullOrEmpty(cd.SelectedUserType) && !string.IsNullOrEmpty(cd.SelectedUserId))
            {
                var success = int.TryParse(cd.SelectedUserId, out int result);
                
                if (!success) result = 0;
                if (cd.SelectedUserType == "Owner")
                {
                    return RedirectToAction(nameof(Index), "Skateboards", new { id = result });
                }
                else
                {
                    return RedirectToAction(nameof(Index), "Borrower", new { id = result });
                }
            }
            var customerDetails = new CustomerDetails();
            return View(customerDetails);

        }

        [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
        public IActionResult Error()
        {
            return View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
        }
    }
}

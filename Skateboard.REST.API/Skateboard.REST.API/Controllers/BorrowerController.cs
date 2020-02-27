using System;
using System.IO;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Logging;
using Skateboard.REST.API.DataRepository;
using Skateboard.REST.API.Requests;
using Newtonsoft.Json;
using Skateboard.REST.API.Responses;

namespace Skateboard.REST.API.Controllers
{
    [Route("api/Skateboard/[controller]")]
    [ApiController]
    public class BorrowerController : ControllerBase
    {

        private readonly ILogger<BorrowerController> _logger;
        private readonly ISkateboardRepositoryService _skateboardRepositoryService;
        private Guid _uniqueLogId;

        public BorrowerController(ISkateboardRepositoryService skateboardRepository, ILogger<BorrowerController> logger)
        {
            _logger = logger;
            _skateboardRepositoryService = skateboardRepository;
        }


        private void LogIt(string msg, bool isError = false)
        {
            if (isError)
            {
                _logger.LogInformation($"{_uniqueLogId}: {msg}");
                Console.WriteLine($"{_uniqueLogId}: {msg}");
                return;
            }

            _logger.LogError($"{_uniqueLogId}: {msg}");
            TextWriter txtWriter = Console.Error;
            try
            {
                txtWriter.WriteLine($"{_uniqueLogId}: {msg}");
            }
            finally
            {
                txtWriter.Dispose();
            }
        }


        // POST: api/Skateboard/borrower
        [HttpPost]
        public BorrowerSkateboardResponse Post([FromBody] BorrowerSkateboardRequest request)
        {
            _uniqueLogId = Guid.NewGuid();
            try
            {
                LogIt("Executing Post([FromBody] BorrowerSkateboardResponse request");

                if (request == null)
                {
                    LogIt("incoming reqest is null!");
                    return new BorrowerSkateboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = $"Request is null!"
                    };
                }

                LogIt("incoming request:");
                LogIt(JsonConvert.SerializeObject(request));

                return _skateboardRepositoryService.ProcessBorrowerRequest(request, _uniqueLogId);
            }
            catch (Exception ex)
            {
                LogIt($"Failure in POST endpoint - error: {ex.Message}");
                return new BorrowerSkateboardResponse()
                {
                    IsOperationSuccess = false,
                    ReturnMessage = $"Failure in POST endpoint due to internale exception!"
                };
            }
        }

    }
}

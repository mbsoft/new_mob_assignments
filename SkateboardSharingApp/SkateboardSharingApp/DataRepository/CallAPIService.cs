using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using SkateboardSharingApp.Models;
using SkateboardSharingApp.Requests;
using SkateboardSharingApp.Responses;
using System;
using System.Linq;


using System.Collections.Generic;
using System.IO;
using System.Net;
using static SkateboardSharingApp.DataRepository.EnumClass;

namespace SkateboardSharingApp.DataRepository
{
    public interface ICallAPIService
    {
        //call borrower
        List<Skateboard> GetAvailableSkateboards(Guid gId);

        bool CheckoutSkateboard(int skateboardId, int borrowerId, Guid gId);


        //call owner
        List<Skateboard> GetOwnerSkateboards(int ownerId, Guid gId);

        Skateboard GetOwnerSkateboard(int id, Guid gId);

        bool AddSkateboard(Skateboard skateboard, Guid gId);

        bool ToggleSkateboardAvailablity(int id, Guid gId);

        bool DeleteSkateboard(int id, Guid gId);

        bool UpdateSkateboard(Skateboard skateboard, Guid gId);
    }
    public class CallAPIService : ICallAPIService
    {
        private readonly ILogger<CallAPIService> _logger;
        private Guid _uniqueLogId;

        public CallAPIService(ILogger<CallAPIService> logger)
        {
            _logger = logger;
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
        
        //call borrower
        //get all available skateboards
        public List<Skateboard> GetAvailableSkateboards(Guid gId)
        {
            try
            {
                _uniqueLogId = gId;
                LogIt($"Running method: CallAPIService.GetAvailableSkateboards");

                var ownerRequest = new OwnerSkateboardRequest()
                {
                    MessageType = (int)OwnerMessageTypeEnum.GetOwnerSkateboards,
                    OwnerId = 0
                };

                var response = CallOwnerApiService(ownerRequest);

                if (response == null || response.OwnerSkateboards == null)
                {
                    return new List<Skateboard>();
                }
                LogIt($"Running method: CallAPIService.GetAvailableSkateboards - successful.");

                return response.OwnerSkateboards.Where(x => x.IsAvailable).ToList();
            }
            catch (Exception ex)
            {
                LogIt($"Methode: GetAvailableSkateboards - throw and exception - error:{ex.Message}");
                return new List<Skateboard>();
            }
        }

        public bool CheckoutSkateboard(int skateboardId, int borrowerId, Guid gId)
        {
            try
            {
                _uniqueLogId = gId;
                LogIt("Running methode: CallAPIService.CheckoutSkateboard.");

                var borrowerRequest = new BorrowerSkateboardRequest()
                {
                    MessageType = (int)BorrowerMessageTypeEnum.ToggleSaketboardBorrowing,
                    SkateboardId = skateboardId,
                    BorrowerId = borrowerId
                };

                var response = CallBorrowerApiService(borrowerRequest);
                LogIt($"Running method: CallAPIService.CheckoutSkateboard - successful.");

                return response.IsOperationSuccess;
            }
            catch (Exception ex)
            {
                LogIt($"Methode: CallAPIService.CheckoutSkateboard - throw and exception - error:{ex.Message}");
                return false;
            }
        }


        //call owner
        //GetList for owner
        public List<Skateboard> GetOwnerSkateboards(int ownerId, Guid gId)
        {
            try
            {
                _uniqueLogId = gId;
                LogIt("Running methode: CallAPIService.GetOwnerSkateboards.");

                var ownerRequest = new OwnerSkateboardRequest()
                {
                    MessageType = (int)OwnerMessageTypeEnum.GetOwnerSkateboards,
                    OwnerId = ownerId
                };

                var response = CallOwnerApiService(ownerRequest);

                if (response == null || response.OwnerSkateboards == null)
                {
                    return new List<Skateboard>();
                }

                LogIt($"Running method: CallAPIService.GetOwnerSkateboards - successful.");

                return response.OwnerSkateboards;
            }
            catch (Exception ex)
            {
                LogIt($"Methode: CallAPIService.GetOwnerSkateboards - throw and exception - error:{ex.Message}");
                return new List<Skateboard>();
            }
        }
        
        //get details 
        public Skateboard GetOwnerSkateboard(int id, Guid gId)
        {
            try
            {
                _uniqueLogId = gId;
                LogIt("Running methode: CallAPIService.GetOwnerSkateboard.");

                var ownerRequest = new OwnerSkateboardRequest()
                {
                    MessageType = (int)OwnerMessageTypeEnum.GetSkateboardDetails,
                    SkateboardId = id
                };

                var response = CallOwnerApiService(ownerRequest);
                if (response == null || response.Skateboard == null)
                {
                    return null;
                }
                LogIt($"Running method: CallAPIService.GetOwnerSkateboard - successful.");

                return response.Skateboard;
            }
            catch (Exception ex)
            {
                LogIt($"Methode: CallAPIService.GetOwnerSkateboard - throw and exception - error:{ex.Message}");
                return null;
            }
        }

        //save new 
        public bool AddSkateboard(Skateboard skateboard, Guid gId)
        {
            try
            {
                _uniqueLogId = gId;

                LogIt("Running methode: CallAPIService.AddSkateboard.");
                var ownerRequest = new OwnerSkateboardRequest()
                {
                    MessageType = (int)OwnerMessageTypeEnum.AddNewSaketborad,
                    AddEditSkateboard = skateboard,
                    OwnerId = skateboard.OwnerId
                };

                var response = CallOwnerApiService(ownerRequest);
                LogIt($"Running method: CallAPIService.AddSkateboard - successful.");
                return response.IsOperationSuccess;
            }
            catch (Exception ex)
            {
                LogIt($"Methode: CallAPIService.AddSkateboard - throw and exception - error:{ex.Message}");
                return false;
            }
        }

        //set active
        public bool ToggleSkateboardAvailablity(int id, Guid gId)
        {
            try
            {
                _uniqueLogId = gId;

                LogIt("Running methode: CallAPIService.ToggleSkateboardAvailablity.");
                var ownerRequest = new OwnerSkateboardRequest()
                {
                    MessageType = (int)OwnerMessageTypeEnum.ToggleSaketboardAvailablity,
                    SkateboardId = id
                };

                var response = CallOwnerApiService(ownerRequest);
                LogIt($"Running method: CallAPIService.ToggleSkateboardAvailablity - successful.");
                return response.IsOperationSuccess;
            }
            catch (Exception ex)
            {
                LogIt($"Methode: CallAPIService.ToggleSkateboardAvailablity - throw and exception - error:{ex.Message}");
                return false;
            }


        }

        //save updates 
        public bool UpdateSkateboard(Skateboard skateboard, Guid gId)
        {
            try
            {
                _uniqueLogId = gId;

                LogIt("Running methode: CallAPIService.UpdateSkateboard.");

                var ownerRequest = new OwnerSkateboardRequest()
                {
                    MessageType = (int)OwnerMessageTypeEnum.EditExistingSakteboard,
                    AddEditSkateboard = skateboard
                };

                var response = CallOwnerApiService(ownerRequest);
                LogIt($"Running method: CallAPIService.UpdateSkateboard - successful.");

                return response.IsOperationSuccess;
            }
            catch (Exception ex)
            {
                LogIt($"Methode: CallAPIService.UpdateSkateboard - throw and exception - error:{ex.Message}");
                return false;
            }

        }

        //delete
        public bool DeleteSkateboard(int id, Guid gId)
        {
            try
            {
                _uniqueLogId = gId;
                LogIt("Running methode: CallAPIService.DeleteSkateboard.");

                var ownerRequest = new OwnerSkateboardRequest()
                {
                    MessageType = (int)OwnerMessageTypeEnum.DeleteExistingSakteboard,
                    SkateboardId = id
                };

                var response = CallOwnerApiService(ownerRequest);
                LogIt($"Running method: CallAPIService.DeleteSkateboard - successful.");

                return response.IsOperationSuccess;
            }
            catch (Exception ex)
            {
                LogIt($"Methode: CallAPIService.DeleteSkateboard - throw an exception - error:{ex.Message}");
                return false;
            }
        }

        private  OwnerStakeboardResponse CallOwnerApiService(OwnerSkateboardRequest ownerRequest)
        {
            Stream responseStream = null;
            StreamReader reader = null;
            StreamWriter writer = null;

            try
            {
                var apiServiceUrl = "http://localhost:33968/api/skateboard/owner";
                var uri = new Uri(apiServiceUrl);
                var request = (HttpWebRequest)WebRequest.Create(uri);

                request.Method = "POST";
                request.ContentType = "application/json";
                request.KeepAlive = true;

                var requestContent = JsonConvert.SerializeObject(ownerRequest);
                LogIt($"sending request:{requestContent.ToString()}");

                writer = new StreamWriter(request.GetRequestStream());
                writer.Write(requestContent);
                writer.Flush();

                var response = (HttpWebResponse)request.GetResponse();

                responseStream = response.GetResponseStream();
                if (responseStream != null)
                {
                    reader = new StreamReader(responseStream);
                }

                var result = (reader == null) ? string.Empty : reader.ReadToEnd();

                LogIt($"Received response:{result}");
                
                return (string.IsNullOrEmpty(result)) ? null : JsonConvert.DeserializeObject<OwnerStakeboardResponse>(result);
            }

            catch (Exception ex)
            {

                LogIt($"Failed to Create api service call- error:{ex.Message}");
                return null;
            }

            finally
            {
                if (writer != null)
                {
                    writer.Close();
                    writer.Dispose();
                }

                if (responseStream != null)
                {
                    responseStream.Close();
                    responseStream.Dispose();
                }

                if (reader != null)
                {
                    reader.Close();
                    reader.Dispose();
                }
            }
        }

        private BorrowerSkateboardResponse CallBorrowerApiService(BorrowerSkateboardRequest borrowerRequest)
        {
            Stream responseStream = null;
            StreamReader reader = null;
            StreamWriter writer = null;

            try
            {
                var apiServiceUrl = "http://localhost:33968/api/skateboard/borrower";
                var uri = new Uri(apiServiceUrl);
                var request = (HttpWebRequest)WebRequest.Create(uri);

                request.Method = "POST";
                request.ContentType = "application/json";
                request.KeepAlive = true;
                ServicePointManager.SecurityProtocol = SecurityProtocolType.Tls12;

                var requestContent = JsonConvert.SerializeObject(borrowerRequest);
                LogIt($"sending request:{requestContent.ToString()}");

                writer = new StreamWriter(request.GetRequestStream());
                writer.Write(requestContent);
                writer.Flush();

                var response = (HttpWebResponse)request.GetResponse();

                responseStream = response.GetResponseStream();
                if (responseStream != null)
                {
                    reader = new StreamReader(responseStream);
                }

                var result = (reader == null) ? string.Empty : reader.ReadToEnd();
                LogIt($"Received response:{result}");

                return (string.IsNullOrEmpty(result)) ? null : JsonConvert.DeserializeObject<BorrowerSkateboardResponse>(result);
            }

            catch (Exception ex)
            {

                LogIt($"Failed to Create api service call- error:{ex.Message}");
                return null;
            }

            finally
            {
                if (writer != null)
                {
                    writer.Close();
                    writer.Dispose();
                }

                if (responseStream != null)
                {
                    responseStream.Close();
                    responseStream.Dispose();
                }

                if (reader != null)
                {
                    reader.Close();
                    reader.Dispose();
                }
            }
        }

    }
}

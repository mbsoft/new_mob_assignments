using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using Microsoft.Extensions.Logging;
using Skateboard.REST.API.Models;
using Skateboard.REST.API.Requests;
using Skateboard.REST.API.Responses;
using static Skateboard.REST.API.DataRepository.EnumClass;

namespace Skateboard.REST.API.DataRepository
{
    public interface ISkateboardRepositoryService
    {
        OwnerStakeboardResponse ProcessOwnerRequest(OwnerSkateboardRequest request, Guid id);

    }
    public class SkateboardRepositoryService : ISkateboardRepositoryService
    {
        private readonly ILogger<SkateboardRepositoryService> _logger;
        private Guid _uniqueLogId;

        public static List<Models.Skateboard> Skateboards;
        public static List<Borrower> Borrowers;
        public static List<Owner> Owners;

        public SkateboardRepositoryService(ILogger<SkateboardRepositoryService> logger)
        {
            _logger = logger;
            Initialize();
        }

        #region initializing data
        private void Initialize()
        {
            Owners = LoadInitialOwnerList();
            Borrowers = LoadInitialBorrowerList();
            Skateboards = LoadInitialSkateboardList(Owners);
        }

        private List<Owner> LoadInitialOwnerList()
        {
            var ownerList = new List<Owner>();
            for (var i = 1; i <= 10; i++)
            {
                var owner = new Owner()
                {
                    OwnerId = i,
                    FirstName = $"Jon{i}",
                    LastName = "Smith"
                };
                ownerList.Add(owner);
            }
            return ownerList;
        }

        private List<Borrower> LoadInitialBorrowerList()
        {
            var borrowerList = new List<Borrower>();
            for (var i = 1; i <= 3; i++)
            {
                var borrower = new Borrower()
                {
                    BorrowerId = i,
                    FirstName = $"Dave{i}",
                    LastName = "Cooper"
                };
                borrowerList.Add(borrower);
            }
            return borrowerList;
        }

        private List<Models.Skateboard> LoadInitialSkateboardList(List<Owner> owners)
        {
            var skateboardList = new List<Models.Skateboard>();
            for (var i = 1; i <= 7; i++)
            {
                var skateboard = new Models.Skateboard()
                {
                    SkateboardId = i,
                    Name = Colors[i],
                    Description = string.Empty,
                    Color = Colors[i],
                    Length = 18 + i * .5,
                    Wedith = 10 + i * .5,
                    OwnerId = owners[i].OwnerId,
                    IsCheckedOut = false,
                    IsAvailable = true,
                    Location = new Location() { Lat = 20 + i * 2, Lng = -20 + i * 2 }
                };
                skateboardList.Add(skateboard);
            }
            return skateboardList;
        }

        private static readonly string[] Colors = new[]
        {
            "Red", "Blue", "Yello", "Green", "Brown", "Pimk", "Purple", "Black", "White"
        };

        #endregion

        #region  functional methods

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

        public OwnerStakeboardResponse ProcessOwnerRequest( OwnerSkateboardRequest request, Guid id)
        {
            //grouping the logs per request
            _uniqueLogId = id;

            LogIt($"Entering ProccessOwnerRequest method");

            var msgType = (MessageTypeEnum)request.MessageType;
            switch (msgType)
            {
                case MessageTypeEnum.AddNewSaketborad:
                    return AddSkateboard(request.AddEditSkateboard);

                case MessageTypeEnum.EditExistingSakteboard:
                    return UpdateSkateboard(request.AddEditSkateboard);

                case MessageTypeEnum.GetSkateboardDetails:
                    return GetSkateboard(request.SkateboardId);
                    
                case MessageTypeEnum.DeleteExistingSakteboard:
                    return DeleteSkateboard(request.SkateboardId);
                    
                case MessageTypeEnum.ToggleSaketboardAvailablity:
                    return ToggelSkateboardAvailability(request.SkateboardId);
                    
                case MessageTypeEnum.GetOwnerSkateboards:
                    return GetOwnerSkateboards(request.OwnerId);
                    
            }

            LogIt($"Unknown message type!");
            return new OwnerStakeboardResponse()
            {
                IsOperationSuccess = false,
                ReturnMessage = $"Unknown message type!"
            };
        }

        private OwnerStakeboardResponse AddSkateboard(Models.Skateboard skateboard)
        {
            try
            {
                if (skateboard == null)
                {
                    LogIt("Failed Add Skateboard, skateboard object was null!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = "Failed Add Skateboard, skateboard object was null!"
                    };
                }

                Skateboards.Add(skateboard);
                LogIt("Successefuly Added Skateboard!");
                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = true,
                    ReturnMessage = "Successefuly Added Skateboard!"
                };
            }
            catch (Exception ex)
            {
                LogIt($"Failure in operation to Add skateboard - error: {ex.Message}");
                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = false,
                    ReturnMessage = $"Failed to Add Skateboard due to internale exception!"
                };
            }
        }

        private OwnerStakeboardResponse DeleteSkateboard(int? id)
        {
            try
            {
                if (id == null)
                {
                    LogIt("Failed to delete Skateboard, skateboard id is null!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = "Failed to delete Skateboard, skateboard id is null!"
                    };

                }

                var board = Skateboards.FirstOrDefault(w => w.SkateboardId == id);

                if (board == null)
                {
                    LogIt($"Failed to delete Skateboard, no skateboard with id {id} exist!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = $"Failed to delete Skateboard, no skateboard with id {id} exist!"
                    };

                }

                Skateboards.Remove(board);

                LogIt($"Successfully deleted skateboard!");

                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = true,
                    ReturnMessage = $"Successfully deleted skateboard!"
                };

            }
            catch (Exception ex)
            {
                LogIt($"Failure in operation deleting skateboard with {id}- error: {ex.Message}");
                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = false,
                    ReturnMessage = $"Failed to delete Skateboard with id {id} due to internale exception!"
                };
            }
        }

        private OwnerStakeboardResponse UpdateSkateboard(Models.Skateboard skateboard)
        {
            try
            {
                if (skateboard == null)
                {
                    LogIt("Failed to update Skateboard, skateboard object was null!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = "Failed to Update Skateboard, skateboard object was null!"
                    };
                }

                var board = Skateboards.FirstOrDefault(w => w.SkateboardId == skateboard.SkateboardId);

                if (board == null)
                {
                    LogIt("Failed to update Skateboard, skateboard does not exist!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = "Failed to update Skateboard, skateboard does not exist!"
                    };
                }

                Skateboards.Remove(board);
                Skateboards.Add(skateboard);


                LogIt("Successefuly updated Skateboard!");
                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = true,
                    ReturnMessage = "Successefuly updated Skateboard!"
                };
            }
            catch (Exception ex)
            {
                LogIt($"Failure in operation to update skateboard - error: {ex.Message}");
                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = false,
                    ReturnMessage = $"Failed to update Skateboard due to internale exception!"
                };
            }
        }

        private bool BarrowSkateboard(int id, int borrowerId)
        {
            try
            {
                var board = Skateboards.FirstOrDefault(w => w.SkateboardId == id);

                if (board == null) return false;

                board.IsCheckedOut = true;
                board.BorrowerId = borrowerId;
                board.CheckedOutDt = DateTime.Now;

                return true;
            }
            catch
            {

                return false;
            }
        }

        private OwnerStakeboardResponse ToggelSkateboardAvailability(int? id)
        {
            try
            {
                if (id == null)
                {
                    LogIt("Failed Toggle Skateboard availablility, skateboard id was null!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = "Failed Toggle Skateboard availablility, skateboard id was null!"
                    };
                }
                var board = Skateboards.FirstOrDefault(w => w.SkateboardId == id);

                if (board == null)
                {
                    LogIt($"Failed to Toggle Skateboard, no skateboard with id {id} exist!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = $"Failed to Toggle Skateboard, no skateboard with id {id} exist!"
                    };

                }

                board.IsAvailable = !board.IsAvailable;
                LogIt($"Successfully Changed skateboard availability!");

                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = true,
                    ReturnMessage = $"Successfully Changed skateboard availability!"
                };
            }
            catch (Exception ex)
            {
                LogIt($"Failure in operation to change Skateboard availability with {id}- error: {ex.Message}");
                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = false,
                    ReturnMessage = $"Failed to change Skateboard availability with id {id} due to internale exception!"
                };
            }
        }

        private OwnerStakeboardResponse GetOwnerSkateboards(int ownerId)
        {
            try
            {
                if (ownerId <= 0)
                {
                    LogIt("Failed to load Skateboards, owner id was null!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = "Failed to load Skateboards, owner id was null!"
                    };
                }

                var boards = Skateboards.Where(w => w.OwnerId == ownerId).ToList();

                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = true,
                    ReturnMessage = $"Successfully loaded skateboard list for owner id {ownerId}!"
                };
            }
            catch (Exception ex)
            {
                LogIt($"Failure in operation to load Skateboards for ownerid  {ownerId}- error: {ex.Message}");
                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = false,
                    ReturnMessage = $"Failure in operation to load Skateboards for ownerid  {ownerId}- due to internale exception!"
                };
            }
        }

        private OwnerStakeboardResponse GetSkateboard(int? skateboardId)
        {
            try
            {
                if (skateboardId == null)
                {
                    LogIt("Failed to load Skateboard, skateboard id was null!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = "Failed to load Skateboard, skateboard id was null!"
                    };
                }

                var board = Skateboards.FirstOrDefault(w => w.SkateboardId == skateboardId);

                if (board == null)
                {
                    LogIt($"Failed to load Skateboard, no skateboard with id {skateboardId} exist!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = $"Failed to load Skateboard, no skateboard with id {skateboardId} exist!"
                    };
                }

                LogIt($"Successfully loaded skateboard!");

                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = true,
                    ReturnMessage = $"Successfully loaded skateboard!"
                };
            }
            catch (Exception ex)
            {
                LogIt($"Failure in operation to load Skateboard with {skateboardId}- error: {ex.Message}");
                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = false,
                    ReturnMessage = $"Failed to load Skateboard with id {skateboardId} due to internale exception!"
                };
            }
        }

        #endregion
    }
}

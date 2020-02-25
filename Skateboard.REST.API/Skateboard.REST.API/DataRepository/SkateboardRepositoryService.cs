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
        BorrowerSkateboardResponse ProcessBorrowerRequest(BorrowerSkateboardRequest request, Guid id);
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

            var msgType = (OwnerMessageTypeEnum)request.MessageType;
            switch (msgType)
            {
                case OwnerMessageTypeEnum.AddNewSaketborad:
                    return AddSkateboard(request.AddEditSkateboard);

                case OwnerMessageTypeEnum.EditExistingSakteboard:
                    return UpdateSkateboard(request.AddEditSkateboard);

                case OwnerMessageTypeEnum.GetSkateboardDetails:
                    return GetSkateboard(request.SkateboardId);
                    
                case OwnerMessageTypeEnum.DeleteExistingSakteboard:
                    return DeleteSkateboard(request.SkateboardId);
                    
                case OwnerMessageTypeEnum.ToggleSaketboardAvailablity:
                    return ToggelSkateboardAvailability(request.SkateboardId);
                    
                case OwnerMessageTypeEnum.GetOwnerSkateboards:
                    return GetOwnerSkateboards(request);
                    
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

                skateboard.SkateboardId = (from x in Skateboards select x.SkateboardId).Max() + 1;
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

        private OwnerStakeboardResponse GetOwnerSkateboards(OwnerSkateboardRequest request)
        {
            try
            {
                if (request.OwnerId <= 0)
                {
                    LogIt("Failed to load Skateboards, owner id was null!");
                    return new OwnerStakeboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = "Failed to load Skateboards, owner id was null!"
                    };
                }

                var boards = Skateboards.Where(w => w.OwnerId == request.OwnerId).ToList();

                var activeFilter = (ActiveSkateboardFilterEnum)request.IsAvailable;
                if(activeFilter == ActiveSkateboardFilterEnum.ShowActive)
                {
                    boards = boards.Where(w => w.IsAvailable).ToList();
                }
                else if (activeFilter == ActiveSkateboardFilterEnum.ShowInActive)
                {
                    boards = boards.Where(w => !w.IsAvailable).ToList();
                }

                var checkedoutFilter = (CheckedoutFilerEnum)request.IsCheckedOut;
                if (checkedoutFilter == CheckedoutFilerEnum.ShowCheckedout)
                {
                    boards = boards.Where(w => w.IsCheckedOut).ToList();
                }
                else if (checkedoutFilter == CheckedoutFilerEnum.ShowNotCheckedout)
                {
                    boards = boards.Where(w => !w.IsCheckedOut).ToList();
                }

                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = true,
                    OwnerSkateboards = boards,
                    ReturnMessage = $"Successfully loaded skateboard list for owner id {request.OwnerId}!"
                };
            }
            catch (Exception ex)
            {
                LogIt($"Failure in operation to load Skateboards for ownerid  {request.OwnerId}- error: {ex.Message}");
                return new OwnerStakeboardResponse()
                {
                    IsOperationSuccess = false,
                    ReturnMessage = $"Failure in operation to load Skateboards for ownerid  {request.OwnerId}- due to internale exception!"
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
                        Skateboard = board,
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


        public BorrowerSkateboardResponse ProcessBorrowerRequest(BorrowerSkateboardRequest request, Guid id)
        {
            //grouping the logs per request
            _uniqueLogId = id;

            LogIt($"Entering ProccessOwnerRequest method");

            var msgType = (BorrowerMessageTypeEnum)request.MessageType;
            switch (msgType)
            {
                case BorrowerMessageTypeEnum.ToggleSaketboardBorrowing:
                    return ToggleBarrowSkateboard(request.SkateboardId, request.BorrowerId);

                case BorrowerMessageTypeEnum.GetSkateboardDetails:
                    var response =  GetSkateboard(request.SkateboardId);
                    return new BorrowerSkateboardResponse
                    {
                        IsOperationSuccess = response.IsOperationSuccess,
                        ReturnMessage = response.ReturnMessage,
                        Skateboard = response.Skateboard,
                    };

                case BorrowerMessageTypeEnum.GetAvailableSkateboards:
                    return GetAvailableSkateboards();
            }

            LogIt($"Unknown message type!");
            return new BorrowerSkateboardResponse()
            {
                IsOperationSuccess = false,
                ReturnMessage = $"Unknown message type!"
            };
        }

        private BorrowerSkateboardResponse ToggleBarrowSkateboard(int? skateboardId, int borrowerId)
        {
            try
            {
                if (skateboardId == null)
                {
                    LogIt("Failed to toggle Skateboard, skateboard id is null!");
                    return new BorrowerSkateboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = "Failed to toggle Skateboard, skateboard id is null!"
                    };
                }

                var board = Skateboards.FirstOrDefault(w => w.SkateboardId == skateboardId);

                if (board == null)
                {
                    LogIt($"Failed to toggle Skateboard, no skateboard with id {skateboardId} exist!");
                    return new BorrowerSkateboardResponse()
                    {
                        IsOperationSuccess = false,
                        ReturnMessage = $"Failed to toggle Skateboard, no skateboard with id {skateboardId} exist!"
                    };
                }

                if(board.IsCheckedOut)
                {
                    board.BorrowerId = null;
                    board.CheckedOutDt = null;
                    board.IsCheckedOut = false;
                }
                else
                {
                    board.BorrowerId = borrowerId;
                    board.CheckedOutDt = DateTime.Now;
                    board.IsCheckedOut = true;
                }

                LogIt($"Successfully toggled skateboard!");

                return new BorrowerSkateboardResponse()
                {
                    IsOperationSuccess = true,
                    ReturnMessage = $"Successfully toggled skateboard!"
                };
            }
            catch (Exception ex)
            {
                LogIt($"Failure in operation toggle skateboard with {skateboardId}- error: {ex.Message}");
                return new BorrowerSkateboardResponse()
                {
                    IsOperationSuccess = false,
                    ReturnMessage = $"Failed to toggle Skateboard with id {skateboardId} due to internale exception!"
                };
            }
        }

        private BorrowerSkateboardResponse GetAvailableSkateboards()
        {
            try
            {
                var boards = Skateboards.Where(w => w.IsAvailable && !w.IsCheckedOut).ToList();

                return new BorrowerSkateboardResponse()
                {
                    IsOperationSuccess = true,
                    AvailableSkateboards = boards,
                    ReturnMessage = $"Successfully loaded available skateboard list."
                };
            }
            catch (Exception ex)
            {
                LogIt($"Failure in operation to load available Skateboards- error: {ex.Message}");
                return new BorrowerSkateboardResponse()
                {
                    IsOperationSuccess = false,
                    ReturnMessage = $"Failure in operation to load available Skateboards- due to internale exception!"
                };
            }
        }

        #endregion
    }
}

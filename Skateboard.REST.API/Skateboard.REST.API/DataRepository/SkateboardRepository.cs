using System;
using System.Collections.Generic;
using System.Linq;
using Skateboard.REST.API.Models;

namespace Skateboard.REST.API.DataRepository
{
    public interface ISkateboardRepositoryService
    {
        bool AddSkateboard(Models.Skateboard skateboard);

        bool DeleteSkateboard(int id);

        bool UpdateSkateboard(Models.Skateboard skateboard);

        bool BarrowSkateboard(int id, int borrowerId);

        bool SetSkateboardAvailability(int id, bool isAvailable);
    }
    public class SkateboardRepository : ISkateboardRepositoryService
    {
        public static List<Models.Skateboard> Skateboards;
        public static List<Borrower> Borrowers;
        public static List<Owner> Owners;
        public SkateboardRepository()
        {
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

        public bool AddSkateboard(Models.Skateboard skateboard)
        {
            try
            {
                Skateboards.Add(skateboard);

                return true;
            }
            catch
            {

                return false;
            }


        }

        public bool DeleteSkateboard(int id)
        {
            try
            {
                var board = Skateboards.FirstOrDefault(w => w.SkateboardId == id);

                if (board == null) return false;

                Skateboards.Remove(board);

                return true;
            }
            catch
            {

                return false;
            }
        }

        public bool UpdateSkateboard(Models.Skateboard skateboard)
        {
            try
            {
                var board = Skateboards.FirstOrDefault(w => w.SkateboardId == skateboard.SkateboardId);

                if (board == null) return false;

                Skateboards.Remove(board);

                Skateboards.Add(skateboard);

                return true;
            }
            catch
            {

                return false;
            }
        }

        public bool BarrowSkateboard(int id, int borrowerId)
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

        public bool SetSkateboardAvailability(int id, bool isAvailable)
        {
            try
            {
                var board = Skateboards.FirstOrDefault(w => w.SkateboardId == id);

                if (board == null) return false;

                board.IsAvailable = isAvailable;
                return true;
            }
            catch
            {

                return false;
            }
        }

        #endregion
    }
}

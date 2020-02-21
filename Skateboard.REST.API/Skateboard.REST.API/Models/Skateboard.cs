using System;

namespace Skateboard.REST.API.Models
{
    public class Skateboard
    {
        public int SkateboardId { get; set; }

        public string Name { get; set; }

        public string Description { get; set; }

        public string Color { get; set; }

        public double Length { get; set; }

        public double Wedith { get; set; }

        public string PhotoUrl { get; set; }

        public int OwnerId { get; set; }

        public int? BorrowerId { get; set; }

        public bool IsCheckedOut { get; set; }

        public bool IsAvailable { get; set; }

        public DateTime? CheckedOutDt { get; set; }

        public DateTime? ReturnDt { get; set; }

        public Location Location { get; set; }
    }
}

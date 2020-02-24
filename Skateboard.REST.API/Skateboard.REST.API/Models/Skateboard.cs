using System;
using System.Runtime.Serialization;

namespace Skateboard.REST.API.Models
{
    public class Skateboard
    {
        [DataMember(Name = "name")]
        public string Name { get; set; }

        [DataMember(Name = "description")]
        public string Description { get; set; }

        [DataMember(Name = "color")]
        public string Color { get; set; }

        [DataMember(Name = "length")]
        public double Length { get; set; }

        [DataMember(Name = "wedith")]
        public double Wedith { get; set; }

        [DataMember(Name = "photo_url")]
        public string PhotoUrl { get; set; }

        [DataMember(Name = "owner_id")]
        public int OwnerId { get; set; }

        [DataMember(Name = "is_avavlable")]
        public bool IsAvailable { get; set; }

        public int SkateboardId { get; set; }

        public int? BorrowerId { get; set; }

        public bool IsCheckedOut { get; set; }

        public DateTime? CheckedOutDt { get; set; }

        public DateTime? ReturnDt { get; set; }

        public Location Location { get; set; }
    }
}

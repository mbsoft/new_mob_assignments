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

        [DataMember(Name = "is_available")]
        public bool IsAvailable { get; set; }

        [DataMember(Name = "skateboardId")]
        public int SkateboardId { get; set; }

        [DataMember(Name = "borrowerId")]
        public int? BorrowerId { get; set; }

        [DataMember(Name = "is_checkedOut")]
        public bool IsCheckedOut { get; set; }

        [DataMember(Name = "checkedOutDt")]
        public DateTime? CheckedOutDt { get; set; }

        [DataMember(Name = "returnDt")]
        public DateTime? ReturnDt { get; set; }

        [DataMember(Name = "location")]
        public Location Location { get; set; }
    }
}

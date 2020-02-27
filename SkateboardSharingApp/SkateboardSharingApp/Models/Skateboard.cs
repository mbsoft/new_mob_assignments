using System;
using System.Runtime.Serialization;

namespace SkateboardSharingApp.Models
{
    public class Skateboard
    {
        [DataMember]
        public string Name { get; set; }

        [DataMember]
        public string Description { get; set; }

        [DataMember]
        public string Color { get; set; }

        [DataMember]
        public double Length { get; set; }

        [DataMember]
        public double Wedith { get; set; }

        [DataMember]
        public string PhotoUrl { get; set; }

        [DataMember]
        public int OwnerId { get; set; }

        [DataMember]
        public bool IsAvailable { get; set; }

        [DataMember]
        public int SkateboardId { get; set; }

        [DataMember]
        public int? BorrowerId { get; set; }

        [DataMember]
        public bool IsCheckedOut { get; set; }

        [DataMember]
        public DateTime? CheckedOutDt { get; set; }

        [DataMember]
        public DateTime? ReturnDt { get; set; }

    }
}

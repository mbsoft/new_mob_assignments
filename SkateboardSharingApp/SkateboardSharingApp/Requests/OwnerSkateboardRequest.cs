using System.Runtime.Serialization;

namespace SkateboardSharingApp.Requests
{
    [DataContract]

    public class OwnerSkateboardRequest
    {
        [DataMember]
        public int MessageType { get; set; }

        [DataMember]
        public int? SkateboardId { get; set; }

        [DataMember]
        public Models.Skateboard AddEditSkateboard { get; set; }

        [DataMember]
        public int OwnerId { get; set; }

        [DataMember]
        public int IsAvailable { get; set; }

        [DataMember]
        public int IsCheckedOut { get; set; }

    }
}

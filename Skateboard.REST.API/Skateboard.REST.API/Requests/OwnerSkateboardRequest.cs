using System.Runtime.Serialization;

namespace Skateboard.REST.API.Requests
{
    [DataContract]

    public class OwnerSkateboardRequest
    {
        [DataMember(Name = "message_type")]
        public int MessageType { get; set; }

        [DataMember(Name = "skateboard_id")]
        public int? SkateboardId { get; set; }

        [DataMember(Name = "add_edit_Skateboard")]
        public Models.Skateboard AddEditSkateboard { get; set; }

        [DataMember(Name = "owner_id")]
        public int OwnerId { get; set; }

        [DataMember(Name = "is_available")]//filter = 0-1-2
        public int IsAvailable { get; set; }

        [DataMember(Name = "is_checkedout")]//filter = 0-1-2
        public int IsCheckedOut { get; set; }

    }
}

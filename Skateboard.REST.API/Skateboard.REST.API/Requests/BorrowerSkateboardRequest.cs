using System.Runtime.Serialization;


namespace Skateboard.REST.API.Requests
{
    [DataContract]
    public class BorrowerSkateboardRequest
    {

        [DataMember(Name = "message_type")]
        public int MessageType { get; set; }

        [DataMember(Name = "skateboard_id")]
        public int? SkateboardId { get; set; }

        [DataMember(Name = "borrower_id")]
        public int BorrowerId { get; set; }
    }
}

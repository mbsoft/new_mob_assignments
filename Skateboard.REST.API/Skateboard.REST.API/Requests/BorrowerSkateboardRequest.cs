using System.Runtime.Serialization;


namespace Skateboard.REST.API.Requests
{
    [DataContract]
    public class BorrowerSkateboardRequest
    {

        [DataMember]
        public int MessageType { get; set; }

        [DataMember]
        public int? SkateboardId { get; set; }

        [DataMember]
        public int BorrowerId { get; set; }
    }
}

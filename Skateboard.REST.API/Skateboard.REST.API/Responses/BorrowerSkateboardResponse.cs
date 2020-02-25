using System.Collections.Generic;
using System.Runtime.Serialization;
namespace Skateboard.REST.API.Responses
{
    [DataContract]
    public class BorrowerSkateboardResponse
    {
        [DataMember(Name = "Skateboard")]
        public Models.Skateboard Skateboard { get; set; }

        [DataMember(Name = "is_operation_success")]
        public bool IsOperationSuccess { get; set; }

        [DataMember(Name = "return_message")]
        public string ReturnMessage { get; set; }

        [DataMember(Name = "available_skateboards")]
        public List<Models.Skateboard> AvailableSkateboards { get; set; }

    }
}

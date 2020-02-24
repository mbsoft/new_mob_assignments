using System.Collections.Generic;
using System.Runtime.Serialization;

namespace Skateboard.REST.API.Responses
{
    [DataContract]
    public class OwnerStakeboardResponse
    {
        [DataMember(Name = "Skateboard")]
        public Models.Skateboard Skateboard { get; set; }

        [DataMember(Name = "is_operation_success")]
        public bool IsOperationSuccess { get; set; }

        [DataMember(Name = "return_message")]
        public string ReturnMessage { get; set; }

        [DataMember(Name = "add_edit_Skateboard")]
        public List<Models.Skateboard> OwnerSkateboards { get; set; }
    }
}

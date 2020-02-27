using System.Collections.Generic;
using System.Runtime.Serialization;

namespace Skateboard.REST.API.Responses
{
    [DataContract]
    public class OwnerStakeboardResponse
    {
        [DataMember]
        public Models.Skateboard Skateboard { get; set; }

        [DataMember]
        public bool IsOperationSuccess { get; set; }

        [DataMember]
        public string ReturnMessage { get; set; }

        [DataMember]
        public List<Models.Skateboard> OwnerSkateboards { get; set; }
    }
}

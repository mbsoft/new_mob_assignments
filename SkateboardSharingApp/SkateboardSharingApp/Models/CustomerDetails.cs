using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace SkateboardSharingApp.Models
{
    public class CustomerDetails
    {
        public List<string> UserTypes 
        {
            get { return new List<string> { "Borrower", "Owner" }; }
        }
        public string SelectedUserType { get; set; }

        public List<int> UserIds
        {
            get { return new List<int> {1,2,3,4,5,6,7,8,9,10 }; }
        }
        public string SelectedUserId { get; set; }

    }


}

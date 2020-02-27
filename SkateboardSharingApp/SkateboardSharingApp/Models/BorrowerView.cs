using System;
using System.Collections.Generic;


namespace SkateboardSharingApp.Models
{
    public class BorrowerView : Skateboard
    {
        public List<int> Borrowers 
        { 
            get { return new List<int> { 1, 2, 3 }; }
        }
    }
}

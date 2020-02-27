using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.EntityFrameworkCore;
using SkateboardSharingApp.Models;

namespace SkateboardSharingApp.Data
{
    public class SkateboardSharingAppContext : DbContext
    {
        public SkateboardSharingAppContext (DbContextOptions<SkateboardSharingAppContext> options)
            : base(options)
        {
        }

        public DbSet<SkateboardSharingApp.Models.Skateboard> Skateboard { get; set; }
    }
}

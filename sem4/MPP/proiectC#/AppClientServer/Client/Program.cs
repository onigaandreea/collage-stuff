using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using Networking;
using services;

namespace Client
{
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            
           
            //IChatServer server=new ChatServerMock();          
            ICompetitionServices server = new CompetitionServerProxy("127.0.0.1", 55556);
            CompetitionController ctrl=new CompetitionController(server);
            Form1 win = new Form1(ctrl);
            Application.Run(win);
        }
    }
}
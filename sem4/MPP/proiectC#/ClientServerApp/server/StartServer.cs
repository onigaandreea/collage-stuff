using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Configuration;
using System.Threading;
using network;
using repository;
using server;
using ServerTemplate;
using services;

namespace chat
{
    class StartServer
    {
        static void Main(string[] args)
        {
            IDictionary<String, string> props = new SortedList<String, String>();
            props.Add("ConnectionString", GetConnectionStringByName("problema5DB"));
            IUserRepository userRepo=new UserDbRepository(props);
            ICompetitionServices serviceImpl = new CompetitionServerImpl(userRepo);

			SerialCompetitionServer server = new SerialCompetitionServer("127.0.0.1", 55556, serviceImpl);
            server.Start();
            Console.WriteLine("Server started ...");
            //Console.WriteLine("Press <enter> to exit...");
            Console.ReadLine();
            
        }
        static string GetConnectionStringByName(string name)
        {
            // Assume failure.
            string returnValue = null;

            // Look for the name in the connectionStrings section.
            ConnectionStringSettings settings = ConfigurationManager.ConnectionStrings[name];

            // If found, return the connection string.
            if (settings != null)
                returnValue = settings.ConnectionString;

            return returnValue;
        }
    }

    public class SerialCompetitionServer: ConcurrentServer 
    {
        private ICompetitionServices server;
        private CompetitionClientWorker worker;
        public SerialCompetitionServer(string host, int port, ICompetitionServices server) : base(host, port)
            {
                this.server = server;
                Console.WriteLine("SerialCompetitionServer...");
        }
        protected override Thread createWorker(TcpClient client)
        {
            worker = new CompetitionClientWorker(server, client);
            return new Thread(new ThreadStart(worker.run));
        }
    }
    
}

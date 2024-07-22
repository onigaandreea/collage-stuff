using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Threading;
using Networking;
using Proto;
using repository;
using server;
using services;

namespace Server
{
    class StartServer
    {
        static void Main(string[] args)
        {
            IUserRepository userRepo = new UserDbRepository();
            ITaskRepository taskRepository = new TaskDbRepository();
            IChildRepository childRepository = new ChildDbRepository();
            IParticipationRepository participationRepository=new ParticipationDbRepository(childRepository, taskRepository);
            
            ICompetitionServices serviceImpl = new CompetitionServerImpl(userRepo, taskRepository, childRepository, participationRepository);

            //SerialServer server = new SerialServer("127.0.0.1", 55556, serviceImpl);
            ProtoServer server = new ProtoServer("127.0.0.1", 55556, serviceImpl);
            server.Start();
            Console.WriteLine("Server started ...");
            //Console.WriteLine("Press <enter> to exit...");
            Console.ReadLine();
            
        }
    }

    public class SerialServer: ConcurrentServer 
    {
        private ICompetitionServices server;
        private CompetitionClientWorker worker;
        public SerialServer(string host, int port, ICompetitionServices server) : base(host, port)
        {
            this.server = server;
            Console.WriteLine("SerialServer...");
        }
        protected override Thread createWorker(TcpClient client)
        {
            worker = new CompetitionClientWorker(server, client);
            return new Thread(new ThreadStart(worker.run));
        }
    }
    
    public class ProtoServer : ConcurrentServer
    {
        private ICompetitionServices server;
        private ProtoWorker worker;
        public ProtoServer(string host, int port, ICompetitionServices server)
            : base(host, port)
        {
            this.server = server;
            Console.WriteLine("ProtoServer...");
        }
        protected override Thread createWorker(TcpClient client)
        {
            worker = new ProtoWorker(server, client);
            return new Thread(new ThreadStart(worker.run));
        }
    }
}

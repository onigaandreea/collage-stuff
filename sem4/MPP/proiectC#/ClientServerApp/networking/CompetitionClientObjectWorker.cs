using System;
using System.Threading;
using System.Net;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using model;
using services;

namespace network
{

	public class CompetitionClientWorker :  ICompetitionObserver //, Runnable
	{
		private ICompetitionServices server;
		private TcpClient connection;

		private NetworkStream stream;
		private IFormatter formatter;
		private volatile bool connected;
		public CompetitionClientWorker(ICompetitionServices server, TcpClient connection)
		{
			this.server = server;
			this.connection = connection;
			try
			{
				
				stream=connection.GetStream();
                formatter = new BinaryFormatter();
				connected=true;
			}
			catch (Exception e)
			{
                Console.WriteLine(e.StackTrace);
			}
		}

		public virtual void run()
		{
			while(connected)
			{
				try
				{
                    object request = formatter.Deserialize(stream);
					object response =handleRequest((Request)request);
					if (response!=null)
					{
					   sendResponse((Response) response);
					}
				}
				catch (Exception e)
				{
                    Console.WriteLine(e.StackTrace);
				}
				
				try
				{
					Thread.Sleep(1000);
				}
				catch (Exception e)
				{
                    Console.WriteLine(e.StackTrace);
				}
			}
			try
			{
				stream.Close();
				connection.Close();
			}
			catch (Exception e)
			{
				Console.WriteLine("Error "+e);
			}
		}
		

		private Response handleRequest(Request request)
		{
			Response response =null;
			if (request is LoginRequest)
			{
				Console.WriteLine("Login request ...");
				LoginRequest logReq =(LoginRequest)request;
				User user =logReq.User;
				try
                {
                    lock (server)
                    {
                        server.login(user, this);
                    }
					return new OkResponse();
				}
				catch (CompetitionException e)
				{
					connected=false;
					return new ErrorResponse(e.Message);
				}
			}
			if (request is LogoutRequest)
			{
				Console.WriteLine("Logout request");
				LogoutRequest logReq =(LogoutRequest)request;
				User user =logReq.User;
				try
				{
                    lock (server)
                    {
	                    server.logout(user, this);
                    }
					connected=false;
					return new OkResponse();

				}
				catch (CompetitionException e)
				{
				   return new ErrorResponse(e.Message);
				}
			}
			
			return response;
		}

	private void sendResponse(Response response)
		{
			Console.WriteLine("sending response "+response);
			lock (stream)
			{
				formatter.Serialize(stream, response);
				stream.Flush();
			}
		}

	public void newParticipant(Participation participation)
	{
		throw new NotImplementedException();
	}
	}

}
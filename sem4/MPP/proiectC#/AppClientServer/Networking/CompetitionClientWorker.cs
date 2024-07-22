using System;
using System.Collections.Generic;
using System.Threading;
using System.Net;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using model;
using services;

namespace Networking
{
	public class CompetitionClientWorker : ICompetitionObserver //, Runnable
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
				stream = connection.GetStream();
				formatter = new BinaryFormatter();
				connected = true;
			}
			catch (Exception e)
			{
				Console.WriteLine(e.StackTrace);
			}
		}
		
		private Response handleRequest(Request request)
		{
			if (request is LoginRequest)
			{
				Console.WriteLine("Login request ...");
				LoginRequest logReq = (LoginRequest)request;
				User user = logReq.User;
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
					connected = false;
					return new ErrorResponse(e.Message);
				}
			}

			if (request is LogoutRequest)
			{
				Console.WriteLine("Logout request");
				LogoutRequest logReq = (LogoutRequest)request;
				User user = logReq.User;
				try
				{
					server.logout(user, this);
					connected = false;
					return new OkResponse();
				}
				catch (CompetitionException e)
				{
					return new ErrorResponse(e.Message);
				}
			}
			
			if (request is ListTasksRequest)
			{
				Console.WriteLine("ListTasks request");
				try
				{
					List<TaskDTO> tasks = server.findAllTasks();
					return new ListTasksResponse(tasks);
				}
				catch (CompetitionException e)
				{
					return new ErrorResponse(e.Message);
				}
			}
			
			if (request is ListParticipantsRequest)
			{
				Console.WriteLine("ListParticipants request");
				ListParticipantsRequest partReq = (ListParticipantsRequest)request;
				Task task = partReq.GetTask;
				try
				{
					List<Child> participants = server.findByTask(task);
					return new ListParticipantsResponse(participants);
				}
				catch (CompetitionException e)
				{
					return new ErrorResponse(e.Message);
				}
			}

			if (request is AddChildRequest)
			{
				Console.WriteLine("AddChild request");
				AddChildRequest chdReq = (AddChildRequest)request;
				Child child = chdReq.getChild;
				try
				{
					server.addChild(child);
					return new OkResponse();
				}
				catch (CompetitionException e)
				{
					return new ErrorResponse(e.Message);
				}
			}
			
			if (request is AddParticipantionRequest)
			{
				Console.WriteLine("AddParticipant request");
				AddParticipantionRequest partReq = (AddParticipantionRequest)request;
				Participation participation = partReq.getPaticipation;
				try
				{
					server.addParticipation(participation);
					return new OkResponse();
				}
				catch (CompetitionException e)
				{
					return new ErrorResponse(e.Message);
				}
			}
			if (request is FindLastChilRequest)
			{
				Console.WriteLine("FindLast request");
				try
				{
					Child child = server.findLastAdded();
					return new FindLastChildResponse(child);
				}
				catch (CompetitionException e)
				{
					return new ErrorResponse(e.Message);
				}
			}
			if (request is UpdateRequest)
			{
				Console.WriteLine("Update request...");
				try
				{
					server.updateEvent();
					return new OkResponse();
				}
				catch (CompetitionException ex)
				{
					return new ErrorResponse(ex.Message);
				}

			}
			return null;
		}

		private void sendResponse(Response response)
		{
			Console.WriteLine("sending response " + response);
			lock (stream)
			{
				formatter.Serialize(stream, response);
				stream.Flush();
			}
		}
		
		public virtual void run()
		{
			while (connected)
			{
				try
				{
					object request = formatter.Deserialize(stream);
					object response = handleRequest((Request)request);
					if (response != null)
					{
						sendResponse((Response)response);
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
				Console.WriteLine("Error " + e);
			}
		}

		public void updateEvents()
		{
			try
			{
				sendResponse(new UpdateResponse());
			}
			catch (Exception e)
			{
				Console.WriteLine(e.StackTrace);
			}
		}
	}

}
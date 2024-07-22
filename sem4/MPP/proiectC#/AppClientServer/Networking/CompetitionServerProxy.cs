using System;
using System.Collections.Generic;
using System.Threading;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using model;
using services;

namespace Networking
{
	public class CompetitionServerProxy : ICompetitionServices
	{
		private string host;
		private int port;

		private ICompetitionObserver client;

		private NetworkStream stream;
		
        private IFormatter formatter;
		private TcpClient connection;

		private Queue<Response> responses;
		private volatile bool finished;
        private EventWaitHandle _waitHandle;
        
		public CompetitionServerProxy(string host, int port)
		{
			this.host = host;
			this.port = port;
			this.responses = new Queue<Response>();
		}

		public virtual void login(User user, ICompetitionObserver client)
		{
			initializeConnection();
			sendRequest(new LoginRequest(user));
			Response response =readResponse();
			if (response is OkResponse)
			{
				this.client=client;
				return;
			}
			if (response is ErrorResponse)
			{
				ErrorResponse err =(ErrorResponse)response;
				closeConnection();
				throw new CompetitionException(err.Message);
			}
		}

		public List<TaskDTO> findAllTasks()
		{
			sendRequest(new ListTasksRequest());
			Response response = readResponse();
			if (response is ErrorResponse)
			{
				ErrorResponse error = (ErrorResponse)response;
				throw new CompetitionException(error.Message);
			}

			ListTasksResponse tasksResponse = (ListTasksResponse)response;
			Console.WriteLine("Proxy: Found tasks");
			return tasksResponse.getTasks;
		}

		public List<Child> findByTask(Task task)
		{
			sendRequest(new ListParticipantsRequest(task));
			Response response = readResponse();
			if (response is ErrorResponse)
			{
				ErrorResponse error = (ErrorResponse)response;
				throw new CompetitionException(error.Message);
			}

			ListParticipantsResponse participantsResponse = (ListParticipantsResponse)response;
			Console.WriteLine("Proxy: Found participants");
			return participantsResponse.participantsList;
		}

		public void addParticipation(Participation participation)
		{
			sendRequest(new AddParticipantionRequest(participation));
			Response response = readResponse();
			if (response is ErrorResponse)
			{
				ErrorResponse error = (ErrorResponse)response;
				throw new CompetitionException(error.Message);
			}
		}
		
		public virtual void addChild(Child child)
		{
			sendRequest(new AddChildRequest(child));
			Response response = readResponse();
			if (response is ErrorResponse)
			{
				ErrorResponse error = (ErrorResponse)response;
				throw new CompetitionException(error.Message);
			}
		}

		public Child findLastAdded()
		{
			sendRequest(new FindLastChilRequest());
			Response response = readResponse();
			if (response is ErrorResponse)
			{
				ErrorResponse error = (ErrorResponse)response;
				throw new CompetitionException(error.Message);
			}

			FindLastChildResponse lastChildResponse = (FindLastChildResponse)response;
			Console.WriteLine("Proxy: Found child");
			return lastChildResponse.getLastChild;
		}

		public virtual void logout(User user, ICompetitionObserver client)
		{
			sendRequest(new LogoutRequest(user));
			Response response =readResponse();
			closeConnection();
			if (response is ErrorResponse)
			{
				ErrorResponse err =(ErrorResponse)response;
				closeConnection();
				throw new CompetitionException(err.Message);
			}
		}

		private void closeConnection()
		{
			finished=true;
			try
			{
				stream.Close();
			
				connection.Close();
                _waitHandle.Close();
				client=null;
			}
			catch (Exception e)
			{
				Console.WriteLine(e.StackTrace);
			}
		}

		private void sendRequest(Request request)
		{
			try
			{
                formatter.Serialize(stream, request);
                stream.Flush();
			}
			catch (Exception e)
			{
				throw new CompetitionException("Error sending object "+e);
			}

		}

		private Response readResponse()
		{
			Response response =null;
			try
			{
                _waitHandle.WaitOne();
				lock (responses)
				{
                    response = responses.Dequeue();
				}
			}
			catch (Exception e)
			{
				Console.WriteLine(e.StackTrace);
			}
			return response;
		}
		private void initializeConnection()
		{ 
			try
			{
				connection = new TcpClient(host,port);
				stream = connection.GetStream();
                formatter = new BinaryFormatter();
				finished = false;
                _waitHandle = new AutoResetEvent(false);
				startReader();
			}
			catch (Exception e) 
			{
                Console.WriteLine(e.StackTrace);
			}
		}
		private void startReader()
		{
			Thread tw = new Thread(run);
			tw.Start();
		}

		public void updateEvent()
		{
			sendRequest(new UpdateRequest());
			Response response = readResponse();
			if (response is ErrorResponse)
			{
				ErrorResponse error = (ErrorResponse)response;
				throw new CompetitionException(error.Message);
			}
		}
		
		private void handleUpdate(Response response)
		{
			if (response is UpdateResponse)
			{
				try
				{
					client.updateEvents();
				}
				catch (CompetitionException e)
				{
					Console.WriteLine(e.StackTrace);
				}
			}
		}
		public virtual void run()
		{
			while(!finished)
			{
				try
				{
					object response = formatter.Deserialize(stream); 
					Console.WriteLine("response received "+response); 
					if (response is UpdateResponse) 
					{ 
						handleUpdate((UpdateResponse)response);
					}
					else 
					{ 
						lock (responses) 
						{
							responses.Enqueue((Response)response);
						} 
						_waitHandle.Set();
					}
				}
				catch (Exception e) 
				{ 
					Console.WriteLine("Reading error "+e);
				}
			}
		}
	}

}
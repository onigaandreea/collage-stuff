using System;
using System.IO;
using System.Net.Sockets;
using System.Threading;
using Google.Protobuf;
using services;

namespace Proto
{
    public class ProtoWorker: ICompetitionObserver
    {
        private ICompetitionServices server;
        private TcpClient connection;
        private NetworkStream stream;
        private volatile bool connected;
        public ProtoWorker(ICompetitionServices server, TcpClient connection)
        {
            this.server = server;
            this.connection = connection;
            try
            {
                stream = connection.GetStream();
                connected = true;
            } catch (Exception ex)
            {
                Console.WriteLine(ex.StackTrace);
            }
        }
        public virtual void run()
        {
            while (connected)
            {
                try
                {
                    Console.WriteLine("Am ajuns la request PARSE");
                    Request request = Request.Parser.ParseDelimitedFrom(stream);
                    Console.WriteLine("Am trecut - cerere primita {0}",request);
                    Response response = handleRequest(request);
                    if (response != null)
                    {
                        sendResponse(response);
                    }
                } catch (Exception ex)
                {
                    Console.WriteLine(ex.StackTrace);
                }
                try
                {
                    Thread.Sleep(1000);
                } catch (Exception ex)
                {
                    Console.WriteLine(ex.StackTrace);
                }
            }
            try
            {
                stream.Close();
                connection.Close();
            } catch (Exception ex)
            {
                Console.WriteLine("Error " + ex);
            }
        }
        public virtual void updateEvents()
        {
            try
            {
                sendResponse(ProtoUtils.createUpdateResponse());
            }
            catch (IOException ex)
            {
                Console.WriteLine(ex.StackTrace);
            }
        }
        private void sendResponse(Response response)
        {
            Console.WriteLine("sending response " + response);
            lock (stream)
            {
                response.WriteDelimitedTo(stream);
                stream.Flush();
            }

        }
        private Response handleRequest(Request request)
        {
            Response response = null;
            Request.Types.Type reqType = request.Type;
            switch (reqType)
            {
                case Request.Types.Type.Login:
                    {
                        Console.WriteLine("Login request...");
                        model.User user = ProtoUtils.getUser(request);
                        try
                        {
                            lock (server)
                            {
                                server.login(user, this);
                            }
                            return ProtoUtils.createLoginResponse(user);
                        }
                        catch (CompetitionException ex)
                        {
                            connected = false;
                            return ProtoUtils.createErrorResponse(ex.Message);
                        }
                    }
                case Request.Types.Type.Logout:
                    {
                        Console.WriteLine("Logout request...");
                        model.User user = ProtoUtils.getUser(request);
                        try
                        {
                            lock (server)
                            {
                                server.logout(user, this);
                            }
                            connected = false;
                            return ProtoUtils.createOkResponse();
                        }
                        catch (CompetitionException ex)
                        {
                            return ProtoUtils.createErrorResponse(ex.Message);
                        }
                    }
                case Request.Types.Type.GetTasks:
                    {
                        Console.WriteLine("GetTasks request...");
                        try
                        {
                            model.TaskDTO[] events;
                            lock (server)
                            {
                                events = server.findAllTasks().ToArray();
                            }
                            return ProtoUtils.createGetEventsResponse(events);
                        }
                        catch (CompetitionException ex)
                        {
                            return ProtoUtils.createErrorResponse(ex.Message);
                        }
                    }
                case Request.Types.Type.GetParticipants:
                    {
                        Console.WriteLine("GetParticipants request...");
                        model.Task e = ProtoUtils.getEvent(request);
                        try
                        {
                            model.Child[] participants;
                            lock (server)
                            {
                                participants = server.findByTask(e).ToArray();
                            }
                            return ProtoUtils.createGetParticipantsResponse(participants);
                        } catch (CompetitionException ex)
                        {
                            return ProtoUtils.createErrorResponse(ex.Message);
                        }
                    }
                case Request.Types.Type.AddParticipation:
                    {
                        Console.WriteLine("AddParticipation request...");
                        model.Participation participation = ProtoUtils.getParticipation(request);
                        try
                        {
                            lock(server)
                            {
                                server.addParticipation(participation);
                            }
                            return ProtoUtils.createAddParticipationResponse();
                        } catch (CompetitionException ex)
                        {
                            return ProtoUtils.createErrorResponse(ex.Message);
                        }
                    }
                case Request.Types.Type.AddChild:
                    {
                        Console.WriteLine("AddChild request...");
                        model.Child child = ProtoUtils.getChild(request);
                        try
                        {
                            lock (server)
                            {
                                server.addChild(child);

                            }
                            return ProtoUtils.createAddChildResponse();
                        }
                        catch (CompetitionException ex)
                        {
                            return ProtoUtils.createErrorResponse(ex.Message);
                        }
                    }
                case Request.Types.Type.FindLastChild:
                    {
                        Console.WriteLine("FindLastChild request...");
                        try
                        {
                            model.Child child;
                            lock (server)
                            {
                                child = server.findLastAdded();
                            }
                            return ProtoUtils.createFindLastChildResponse(child);
                        }
                        catch (CompetitionException ex)
                        {
                            return ProtoUtils.createErrorResponse(ex.Message);
                        }
                    }
                case Request.Types.Type.Update:
                    {
                        Console.WriteLine("Update request...");
                        try
                        {
                            lock(server)
                            {
                                server.updateEvent();
                                
                            }
                            return ProtoUtils.createOkResponse();
                        } catch(CompetitionException ex)
                        {
                            return ProtoUtils.createErrorResponse(ex.Message);
                        }

                    }
            }
            return response;
        }
    }
}
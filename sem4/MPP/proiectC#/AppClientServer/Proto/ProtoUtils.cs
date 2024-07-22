using System;

namespace Proto
{
    public class ProtoUtils
    {
        public static Request createLoginRequest(model.User user)
        {
            Proto.User userDTO = new Proto.User { Id = user.ID, Name = user.name, Username = user.username, Password = user.password, OfficeNo = user.officeNo};
            Request request = new Request { Type = Request.Types.Type.Login, User = userDTO };
            return request;
        }

        public static Request createLogoutRequest(model.User user)
        {
            Proto.User userDTO = new Proto.User { Id = user.ID, Name = user.name, Username = user.username, Password = user.password, OfficeNo = user.officeNo};
            Request request = new Request { Type = Request.Types.Type.Logout, User = userDTO };
            return request;
        }

        public static Request createGetTasksRequest()
        {
            Request request = new Request { Type = Request.Types.Type.GetTasks };
            return request;
        }

        public static Request createGetParticipantsRequest(model.Task e) {
            Proto.Task taskproto = new Proto.Task() { Id = e.ID, AgeCatEnd = e.ageCatEnd, AgeCatStart = e.ageCatStart, Description = e.description};
            Request request = new Request { Type = Request.Types.Type.GetParticipants, Task = taskproto};
            return request;
        }

        public static Request createAddChildRequest(model.Child child)
        {
            Proto.Child  childProto = new Proto.Child() { Id = child.ID, Age = child.age, Name = child.name};
            Request request = new Request { Type = Request.Types.Type.AddChild, Child = childProto};
            return request;
        }

        public static Request createAddParticipationRequest(model.Participation participation)
        {
            Proto.Child  childProto = new Proto.Child() { Id = participation.child.ID, Age = participation.child.age, Name = participation.child.name};
            Proto.Task taskproto = new Proto.Task() { Id = participation.task.ID, AgeCatEnd = participation.task.ageCatEnd, AgeCatStart = participation.task.ageCatStart, Description = participation.task.description};
            Proto.Participation participationProto = new Proto.Participation { Id = participation.ID, Child = childProto, Task = taskproto};
            Request request = new Request { Type = Request.Types.Type.AddParticipation, Participation = participationProto };
            return request;
        }

        public static Request createFindLastChildRequest()
        {
            Request request = new Request { Type = Request.Types.Type.FindLastChild };
            return request;
        }

        public static Request createUpdateRequest()
        {
            Request request = new Request { Type = Request.Types.Type.Update };
            return request;
        }
        public static Response createOkResponse()
        {
            Response response = new Response { Type = Response.Types.Type.Ok };
            return response;
        }

        public static Response createErrorResponse(String text)
        {
            Response response = new Response
            {
                Type = Response.Types.Type.Error,
                Error = text
            };
            return response;
        }

        public static Response createLoginResponse(model.User user)
        {
            Proto.User userDTO = new Proto.User { Id = user.ID, Name = user.name, Username = user.username, Password = user.password, OfficeNo = user.officeNo};
            Response response = new Response { Type = Response.Types.Type.Login };
            return response;
        }

        public static Response createGetEventsResponse(model.TaskDTO[] tasks)
        {
            Response response = new Response { Type = Response.Types.Type.GetTasks };
            foreach (model.TaskDTO e in tasks)
            {
                Proto.TaskDTO eventProto = new Proto.TaskDTO() { Id = e.ID, AgeCatEnd = e.ageCatEnd, AgeCatStart = e.ageCatStart, Description = e.description, Nochildren = e.nrChildren };
                response.Tasks.Add(eventProto);
            }
            return response;
        }

        public static Response createGetParticipantsResponse(model.Child[] participants)
        {
            Response response = new Response { Type = Response.Types.Type.GetParticipants };
            foreach (model.Child participant in participants)
            {
                Proto.Child participantProto = new Proto.Child() { Id = participant.ID, Age = participant.age, Name = participant.name};
                response.Children.Add(participantProto);
            }
            return response;
        }

        public static Response createAddChildResponse()
        {
            Response response = new Response { Type = Response.Types.Type.AddChild };
            return response;
        }

        public static Response createUpdateResponse()
        {
            Response response = new Response { Type = Response.Types.Type.Update };
            return response;
        }

        public static Response createAddParticipationResponse()
        {
            Response response = new Response { Type = Response.Types.Type.AddParticipation };
            return response;
        }

        public static Response createFindLastChildResponse(model.Child child)
        {
            Proto.Child childProto = new Child() { Id = child.ID, Age = child.age, Name = child.name };
            Response response = new Response { Type = Response.Types.Type.FindLastChild, Child = childProto};
            return response;
        }
        public static model.User getUser(Request request)
        {
            model.User user = new model.User();
            user.ID = request.User.Id;
            user.name = request.User.Name;
            user.username = request.User.Username;
            user.password = request.User.Password;
            user.officeNo = request.User.OfficeNo;
            return user;
        }
        public static model.Task getEvent(Request request)
        {
            model.Task t = new model.Task(request.Task.Description, request.Task.AgeCatStart,request.Task.AgeCatEnd);
            t.ID = request.Task.Id;
            return t;
        }
        public static model.Participation getParticipation(Request request)
        {
            model.Child child = new model.Child(request.Participation.Child.Name, request.Participation.Child.Age);
            child.ID = request.Participation.Child.Id;

            model.Task t = new model.Task(request.Participation.Task.Description, request.Participation.Task.AgeCatStart, request.Participation.Task.AgeCatEnd);
            t.ID = request.Participation.Task.Id;

            model.Participation p = new model.Participation(child, t);
            // p.ID = request.Participation.Id;
                
            return p;
        }
        public static model.Child getLastChild(Response response)
        {
            model.Child child = new model.Child(response.Child.Name, response.Child.Age);
            child.ID = response.Child.Id;
            return child;
        }
        public static model.Child getChild(Request request)
        {
            Console.WriteLine("REQUEST ", request.Child);
            model.Child child = new model.Child(request.Child.Name, request.Child.Age);
            child.ID = request.Child.Id;
            return child;
        }

        public static String getError(Response response)
        {
            String errorMessage = response.Error;
            return errorMessage;
        }
        public static model.TaskDTO[] getAllTasks(Response response)
        {
            model.TaskDTO[] events = new model.TaskDTO[response.Tasks.Count];
            for (int i = 0; i < response.Tasks.Count; i++) {
                model.TaskDTO e = new model.TaskDTO(response.Tasks[i].Description, response.Tasks[i].AgeCatStart, response.Tasks[i].AgeCatEnd, response.Tasks[i].Nochildren);
                events[i] = e;
            }
            return events;
        }
        public static model.Child[] getParticipants(Response response)
        {
            model.Child[] participants = new model.Child[response.Children.Count];
            for (int i = 0; i < response.Children.Count; i++)
            {
                model.Child e = new model.Child(response.Children[i].Name, response.Children[i].Age);
                participants[i] = e;
            }
            return participants;
        }
    }
}
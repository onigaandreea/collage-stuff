package org.example.protobuf;

import org.example.model.*;
import org.example.service.CompetitionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ProtoUtils {
    public static CompetitionProtobufs.Request createLoginRequest(User user){
        CompetitionProtobufs.User userDTO = CompetitionProtobufs.User.newBuilder().setUsername(user.getUsername()).setPassword(user.getPassword()).build();
        CompetitionProtobufs.Request request = CompetitionProtobufs.Request.newBuilder().setType(CompetitionProtobufs.Request.Type.Login).setUser(userDTO).build();
        return request;
    }

    public static CompetitionProtobufs.Request createLogoutRequest(User user){
        CompetitionProtobufs.User userDTO = CompetitionProtobufs.User.newBuilder().setUsername(user.getUsername()).setPassword(user.getPassword()).build();
        CompetitionProtobufs.Request request = CompetitionProtobufs.Request.newBuilder().setType(CompetitionProtobufs.Request.Type.Logout).setUser(userDTO).build();
        return request;
    }

    public static CompetitionProtobufs.Request createGetTasksRequest(){
        CompetitionProtobufs.Request request = CompetitionProtobufs.Request.newBuilder().setType(CompetitionProtobufs.Request.Type.Get_tasks).build();
        return request;
    }

    public static CompetitionProtobufs.Request createGetParticipantsRequest(Task task){
        CompetitionProtobufs.Task taskProto = CompetitionProtobufs.Task.newBuilder().setId(task.getId()).setDescription(task.getDescription()).setAgeCatStart(task.getAgeCatStart()).setAgeCatEnd(task.getAgeCatEnd()).build();
        CompetitionProtobufs.Request request = CompetitionProtobufs.Request.newBuilder().setType(CompetitionProtobufs.Request.Type.Get_participants).setTask(taskProto).build();
        return request;
    }

    public static CompetitionProtobufs.Request createAddChildRequest(Child child){
        CompetitionProtobufs.Child childProto = CompetitionProtobufs.Child.newBuilder().setName(child.getName()).setAge(child.getAge()).build();
        CompetitionProtobufs.Request request = CompetitionProtobufs.Request.newBuilder().setType(CompetitionProtobufs.Request.Type.Add_child).setChild(childProto).build();
        return request;
    }

    public static CompetitionProtobufs.Request createAddParticipationRequest(Participation participation){
        CompetitionProtobufs.Child childProto = CompetitionProtobufs.Child.newBuilder().setId(participation.getChild().getId()).setName(participation.getChild().getName()).setAge(participation.getChild().getAge()).build();
        CompetitionProtobufs.Task taskProto = CompetitionProtobufs.Task.newBuilder().setId(participation.getTask().getId()).setDescription(participation.getTask().getDescription()).setAgeCatStart(participation.getTask().getAgeCatStart()).setAgeCatEnd(participation.getTask().getAgeCatEnd()).build();
        CompetitionProtobufs.Participation participationProto = CompetitionProtobufs.Participation.newBuilder().setChild(childProto).setTask(taskProto).build();
        CompetitionProtobufs.Request request = CompetitionProtobufs.Request.newBuilder().setType(CompetitionProtobufs.Request.Type.Add_participation).setParticipation(participationProto).build();
        return request;
    }

    public static CompetitionProtobufs.Request createFindLastSwimmerRequest(){
        CompetitionProtobufs.Request request = CompetitionProtobufs.Request.newBuilder().setType(CompetitionProtobufs.Request.Type.Find_last_child).build();
        return request;
    }

    public static CompetitionProtobufs.Request createUpdateRequest(){
        CompetitionProtobufs.Request request = CompetitionProtobufs.Request.newBuilder().setType(CompetitionProtobufs.Request.Type.Update).build();
        return request;
    }

    public static User getUser(CompetitionProtobufs.Request request){
        User user=new User();
        user.setId(request.getUser().getId());
        user.setName(request.getUser().getName());
        user.setUsername(request.getUser().getUsername());
        user.setPassword(request.getUser().getPassword());
        user.setOfficeNo(request.getUser().getOfficeNo());
        return user;
    }

    public static Child getLastChild(CompetitionProtobufs.Response response){
        Child child = new Child();
        child.setId(response.getChild().getId());
        child.setName(response.getChild().getName());
        child.setAge(response.getChild().getAge());
        return child;
    }

    public static String getError(CompetitionProtobufs.Response response){
        String errorMessage=response.getError();
        return errorMessage;
    }

    public static TaskDTO[] getAllTasks(CompetitionProtobufs.Response response) throws CompetitionException {
        TaskDTO[] tasks = new TaskDTO[response.getTasksCount()];
        for(int i=0; i<response.getTasksCount();i++){
            CompetitionProtobufs.TaskDTO taskDTO=response.getTasks(i);
            TaskDTO task = new TaskDTO();
            task.setId(taskDTO.getId());
            task.setDescription(taskDTO.getDescription());
            task.setAgeCatStart(taskDTO.getAgeCatStart());
            task.setAgeCatEnd(taskDTO.getAgeCatEnd());
            task.setNoChildren(taskDTO.getNochildren());
            tasks[i]=task;
        }
        return tasks;
    }

    public static Child[] getParticipants(CompetitionProtobufs.Response response) throws CompetitionException {
        Child[] children = new Child[response.getChildrenCount()];
        for(int i=0; i<response.getChildrenCount();i++){
            CompetitionProtobufs.Child childDTO=response.getChildren(i);
            Child child = new Child();
            child.setId(childDTO.getId());
            child.setName(childDTO.getName());
            child.setAge(childDTO.getAge());
            children[i]=child;
        }
        return children;
    }

}

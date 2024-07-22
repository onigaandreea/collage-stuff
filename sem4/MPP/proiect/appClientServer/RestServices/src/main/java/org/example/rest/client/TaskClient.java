package org.example.rest.client;

import org.example.model.Task;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Callable;


public class TaskClient {
    public static final String URL = "http://localhost:8080/org/tasks";

    private RestTemplate restTemplate = new RestTemplate();

    private <T> T execute(Callable<T> callable)
    {
        try
        {
            return callable.call();
        }
        catch (Exception exception)
        {
            exception.printStackTrace();
        }

        return null;
    }

    public Task[] findAll()
    {
        return execute(() -> this.restTemplate.getForObject(URL, Task[].class));
    }

    public Task find(Integer id)
    {
        return execute(() -> this.restTemplate.getForObject(String.format("%s/%d", URL, id), Task.class));
    }

    public Task save(Task task)
    {
        return execute(() ->
        {
            Task newTask = this.restTemplate.postForObject(URL, task, Task.class);
            task.setId(newTask.getId());
            return task;
        });
    }

    public Task update(Task tasks)
    {
        return execute(() -> this.restTemplate.exchange(String.format("%s/%d", URL, tasks.getId()), HttpMethod.PUT, new HttpEntity<Task>(tasks), Task.class)).getBody();
    }

    public void remove(Integer id)
    {
        execute(() ->
        {
            this.restTemplate.delete(String.format("%s/%d", URL, id));
            return null;
        });
    }
}

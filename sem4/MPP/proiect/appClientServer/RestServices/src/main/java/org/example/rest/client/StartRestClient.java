package org.example.rest.client;

import org.example.model.Task;

import java.util.Arrays;

public class StartRestClient {
    private final static TaskClient taskClient = new TaskClient();

    public static void main(String[] args)
    {
        Task task = new Task("poezie",6,8);

        show(() -> System.out.println(taskClient.save(task)));
        show(() -> Arrays.stream(taskClient.findAll()).toList().forEach(System.out::println));
        show(() -> System.out.println(taskClient.find(4)));
        show(() ->
        {
            task.setDescription("cautare comori");
            System.out.println(taskClient.update(task));
        });
        show(() -> taskClient.remove(37));
    }

    private static void show(Runnable task)
    {
        task.run();
    }
}

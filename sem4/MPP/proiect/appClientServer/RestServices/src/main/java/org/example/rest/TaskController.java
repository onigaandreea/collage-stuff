package org.example.rest;

import org.example.model.Task;
import org.example.persistence.repository.TaskDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/org/tasks")
@CrossOrigin("http://localhost:5173/")
public class TaskController {
    @Autowired
    private TaskDBRepository tasks;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Task> findAll(){
        System.out.println("Find all tasks...");
        return this.tasks.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id)
    {
        System.out.println("Find one task by id " + id + "...");
        Task task = this.tasks.findById(id);
        if (task == null)
        {
            return new ResponseEntity<String>("Task not found", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public Task save(@RequestBody Task task)
    {
        System.out.println("Save task " + task + "...");
        Integer maximumId = 0;
        Iterable<Task> taskList = this.tasks.findAll();
        for (Task t : taskList)
        {
            if (t.getId() > maximumId)
            {
                maximumId = t.getId();
            }
        }
        task.setId(maximumId + 1);
        this.tasks.add(task);
        return task;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Task update(@RequestBody Task task)
    {
        System.out.println("Update task " + task + "...");
        this.tasks.update(task, task.getId());
        return task;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> remove(@PathVariable Integer id)
    {
        Task task = this.tasks.findById(id);
        System.out.println("Remove task with id " + id + "...");
        this.tasks.delete(task);
        return new ResponseEntity<Task>(task, HttpStatus.OK);
    }

}

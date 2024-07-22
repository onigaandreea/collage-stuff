namespace model;

public class TaskDTO : Entity<int>
{
    public Task task { get; set; }
    public int number { get; set; }

    public TaskDTO(Task task, int number)
    {
        this.task = task;
        this.number = number;
    }
    
    public override bool Equals(object? obj) 
    {
        if (obj is TaskDTO) 
        { 
            return (obj as TaskDTO).ID == ID; 
        } 
        return base.Equals(obj); 
    }
        
    public override string ToString() 
    {
        return ID + " " + task + " " + number;
    }
        
    public static bool operator ==(TaskDTO t1, TaskDTO t2) 
    { 
        return t1.Equals(t2); 
    }
        
    public static bool operator !=(TaskDTO t1, TaskDTO t2) 
    { 
        return !t1.Equals(t2); 
    } 
}
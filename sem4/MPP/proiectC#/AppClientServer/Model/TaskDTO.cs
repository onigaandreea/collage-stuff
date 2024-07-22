using System;

namespace model;
[Serializable]
public class TaskDTO:Entity<int>
{
    public string description { get; set; }
    public int ageCatStart { get; set; }
    public int ageCatEnd { get; set; }
    public int nrChildren { get; set; }

    public TaskDTO(string description, int ageCatStart, int ageCatEnd, int nrKids)
    {
        this.description = description;
        this.ageCatStart = ageCatStart;
        this.ageCatEnd = ageCatEnd;
        this.nrChildren = nrKids;
    }
    public override bool Equals(object? obj) 
    {
        if (obj is Task) 
        {       
            return (obj as Task).ID == ID; 
        } 
        return base.Equals(obj); 
    }
        
    public override string ToString() 
    {
        return ID + " " + description + " " + ageCatStart + " " + ageCatEnd + " " + nrChildren;
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
using System;
using Newtonsoft.Json;

namespace model
{ 
    [Serializable, JsonObject]
    public class Task : Entity<int>
    {
        [JsonProperty("description")]
        public string description { get; set; }
        [JsonProperty("agestart")]
        public int ageCatStart { get; set; }
        [JsonProperty("ageend")]
        public int ageCatEnd { get; set; }

        public Task(string description, int ageCatStart, int ageCatEnd)
        {
            this.description = description;
            this.ageCatStart = ageCatStart;
            this.ageCatEnd = ageCatEnd;
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
            return ID + " " + description + " " + ageCatStart + " " + ageCatEnd;
        }
        
        public static bool operator ==(Task t1, Task t2) 
        { 
            return t1.Equals(t2); 
        }
        
        public static bool operator !=(Task t1, Task t2) 
        { 
            return !t1.Equals(t2); 
        } 
    }
}
using System;

namespace model
{
    [Serializable]
    public class Participation: Entity<int>
    {
        public Child child { get; set; }
        public Task task { get; set; }

        public Participation(Child child, Task task)
        {
            this.child = child;
            this.task = task;
        }
        
        public Participation()
        { }

        public override bool Equals(object? obj)
        {
            if (obj is Participation)
            {
                return (obj as Participation).ID == ID;
            }
            return base.Equals(obj);
        }

        public override string ToString()
        {
            return ID + " " + child + " " + task;
        }
    
        public static bool operator ==(Participation p1, Participation p2)
        {
            return p1.Equals(p2);
        }
    
        public static bool operator !=(Participation p1, Participation p2)
        {
            return !p1.Equals(p2);
        }
    }
}

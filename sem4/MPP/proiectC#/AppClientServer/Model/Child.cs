using System;

namespace model
{
    [Serializable]
    public class Child: Entity<int>
    {
        public string name { get; set; }
        public int age { get; set; }

        public Child(string name, int age)
        {
            this.name = name;
            this.age = age;
        }

        public override bool Equals(object? obj)
        {
            if (obj is Child)
            {
                return (obj as Child).ID == ID;
            }
            return base.Equals(obj);
        }

        public override string ToString()
        {
            return ID + " " + name + " " + age;
        }
    
        public static bool operator ==(Child c1, Child c2)
        {
            return c1.Equals(c2);
        }
    
        public static bool operator !=(Child c1, Child c2)
        {
            return !c1.Equals(c2);
        }  
    }
}
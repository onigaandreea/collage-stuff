using System;

namespace model
{
    [Serializable]
    public class User: Entity<int>
    {
        public string name { get; set; }
        public string username { get; set; }
        public string password { get; set; }
        public int officeNo { get; set; }

        public User(string name, string username, string password, int officeNo)
        {
            this.name = name;
            this.username = username;
            this.password = password;
            this.officeNo = officeNo;
        }
        
        public User() { }

        public override bool Equals(object? obj)
        {
            if (obj is User)
            {
                return (obj as User).ID == ID;
            }
            return base.Equals(obj);
        }

        public override string ToString()
        {
            return ID + " " + name + " " + username + " " + password + " " + officeNo;
        }

        public static bool operator ==(User u1, User u2)
        {
            return u1.Equals(u2);
        }
    
        public static bool operator !=(User u1, User u2)
        {
            return !u1.Equals(u2);
        } 
    }
}


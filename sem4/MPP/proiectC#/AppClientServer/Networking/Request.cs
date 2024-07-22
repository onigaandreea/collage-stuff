using System;
using model;

namespace Networking
{
    public interface Request { }

    [Serializable]
    public class UpdateRequest : Request { }

    [Serializable]
    public class findUserRequest : Request
    {
        private String username;

        public findUserRequest(String username)
        {
            this.username = username;
        }
        
        public virtual String getusername
        {
            get { return this.username; }
        }
    }
}
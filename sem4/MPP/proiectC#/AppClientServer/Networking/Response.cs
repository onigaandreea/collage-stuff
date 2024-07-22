using System;
using System.Collections.Generic;
using model;

namespace Networking
{
    public interface Response { }

    [Serializable]
    public class FindUserResponse : Response
    {
        private User user;

        public FindUserResponse(User user)
        {
            this.user = user;
        }
        
        public virtual User getUser
        {
            get { return this.user; }
        }
    }

    [Serializable]
    public class UpdateResponse : Response { }
}
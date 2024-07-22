using System;
using model;

namespace network
{
	public interface Request 
	{
	}


	[Serializable]
	public class LoginRequest : Request
	{
		private User user;

		public LoginRequest(User user)
		{
			this.user = user;
		}

		public virtual User User
		{
			get
			{
				return user;
			}
		}
	}

	[Serializable]
	public class LogoutRequest : Request
	{
		private User user;

		public LogoutRequest(User user)
		{
			this.user = user;
		}

		public virtual User User
		{
			get
			{
				return user;
			}
		}
	}

}
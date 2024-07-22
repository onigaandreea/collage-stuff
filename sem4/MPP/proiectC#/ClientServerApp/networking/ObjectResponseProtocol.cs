using System;
namespace network
{
	public interface Response 
	{
	}

	[Serializable]
	public class OkResponse : Response
	{
		
	}

    [Serializable]
	public class ErrorResponse : Response
	{
		private string message;

		public ErrorResponse(string message)
		{
			this.message = message;
		}

		public virtual string Message
		{
			get
			{
				return message;
			}
		}
	}
	
	public interface UpdateResponse : Response
	{
	}
	

}
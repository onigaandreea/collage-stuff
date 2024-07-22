using System;

namespace Networking;

[Serializable]
public class ErrorResponse : Response
{
    private string message;

    public ErrorResponse(string message)
    {
        this.message = message;
    }

    public virtual string Message { get { return this.message; } }
}
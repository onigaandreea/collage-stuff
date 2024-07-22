using System;
using model;

namespace Networking;

[Serializable]
public class ListParticipantsRequest : Request
{
    private Task task;

    public ListParticipantsRequest(Task task)
    {
        this.task = task;
    }
        
    public virtual Task GetTask
    {
        get { return this.task; }
    }
}
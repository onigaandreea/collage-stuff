using System;
using System.Collections.Generic;
using model;

namespace Networking;

[Serializable]
public class ListParticipantsResponse : Response
{
    private List<Child> participants;

    public ListParticipantsResponse(List<Child> participants)
    {
        this.participants = participants;
    }
        
    public virtual List<Child> participantsList { get { return this.participants; } }
}
using System;
using model;

namespace Networking;

[Serializable]
public class AddParticipantionRequest : Request
{
    private Participation participation;

    public AddParticipantionRequest(Participation participation)
    {
        this.participation = participation;
    }
        
    public virtual Participation getPaticipation
    {
        get { return this.participation; }
    }
}
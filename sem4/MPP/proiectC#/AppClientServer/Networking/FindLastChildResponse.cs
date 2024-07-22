using System;
using model;

namespace Networking;

[Serializable]
public class FindLastChildResponse : Response
{
    private Child child;

    public FindLastChildResponse(Child child)
    {
        this.child = child;
    }
        
    public virtual Child getLastChild
    {
        get { return this.child; }
    }
}
using System;
using model;

namespace Networking;

[Serializable]
public class AddChildRequest : Request
{
    private Child child;

    public AddChildRequest(Child child)
    {
        this.child = child;
    }
        
    public virtual Child getChild
    {
        get { return this.child; }
    }
}
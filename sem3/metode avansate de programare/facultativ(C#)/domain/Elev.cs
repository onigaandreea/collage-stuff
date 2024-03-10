namespace facultativ.domain;

public class Elev : Entity<int> , ICloneable
{
    public String Nume { get; set; }
    
    public String Scoala { get; set; }
    
    public object Clone()
    {
        throw new NotImplementedException();
    }

    public override bool Equals(object? obj)
    {
        if (obj is Elev)
        {
            return (obj as Elev).ID == ID;
        }
        return base.Equals(obj);
    }

    public override string ToString()
    {
        return ID + " " + Nume + " " + Scoala;
    }

    public static bool operator ==(Elev e1, Elev e2)
    {
        return e1.Equals(e2);
    }
    
    public static bool operator !=(Elev e1, Elev e2)
    {
        return !e1.Equals(e2);
    }
}
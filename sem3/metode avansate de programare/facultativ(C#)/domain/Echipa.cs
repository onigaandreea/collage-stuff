namespace facultativ.domain;

public class Echipa : Entity<int> , ICloneable
{
    public String Nume { get; set; }

    public object Clone()
    {
        throw new NotImplementedException();
    }

    public override string ToString()
    {
        return ID + " " + Nume;
    }

    public override bool Equals(object? obj)
    {
        if (obj is Echipa)
        {
            return (obj as Echipa).ID == ID;
        }
        return base.Equals(obj);
    }

    public static bool operator == (Echipa e1, Echipa e2)
    {
        return e1.Equals(e2); 
    }
    
    public static bool operator != (Echipa e1, Echipa e2)
    {
        return !e1.Equals(e2); 
    }
}
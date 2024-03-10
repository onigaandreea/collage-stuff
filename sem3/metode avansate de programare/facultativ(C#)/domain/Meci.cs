namespace facultativ.domain;

public class Meci : Entity<int>
{
    public Echipa Echipa1 { get; set; }
    
    public Echipa Echipa2 { get; set; }
    
    public DateOnly data { get; set; }

    public override string ToString()
    {
        return ID + " " + Echipa1 + " " + Echipa2 + " " + data;
    }

    public override bool Equals(object? obj)
    {
        if (obj is Meci)
        {
            return (obj as Meci).Echipa1 == Echipa1 && (obj as Meci).Echipa2 == Echipa2;
        }
        return base.Equals(obj);
    }

    public static bool operator ==(Meci m1, Meci m2)
    {
        return m1.Equals(m2);
    }
    
    public static bool operator !=(Meci m1, Meci m2)
    {
        return !m1.Equals(m2);
    }
}
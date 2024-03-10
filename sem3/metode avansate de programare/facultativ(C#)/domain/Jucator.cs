namespace facultativ.domain;

public class Jucator : Elev
{
    public Echipa Echipa { get; set; }

    public override string ToString()
    {
        return ID + " " + Nume + " " + Scoala + " " + Echipa;
    }

    public override bool Equals(object? obj)
    {
        if (obj is Jucator)
        {
            return (obj as Jucator).ID == ID;
        }
        return base.Equals(obj);
    }

    public static bool operator ==(Jucator j1, Jucator j2)
    {
        return j1.Equals(j2);
    }
    
    public static bool operator !=(Jucator j1, Jucator j2)
    {
        return !j1.Equals(j2);
    }
}
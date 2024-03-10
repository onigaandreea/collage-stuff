namespace facultativ.domain;

public enum Tip
{ 
    Rezerva, Participant
}
    
public class JucatorActiv : Entity<int>
{
    public int idJucator { get; set; }
    public int idMeci { get; set; }
    public int nrPuncteInscrise { get; set; }
    public Tip tipJucator { get; set; }
    
    public override string ToString() 
    { 
        return ID + " " + idJucator + " " + idMeci + " " + nrPuncteInscrise + " " + tipJucator;
    }
    public override bool Equals(object? obj) 
    { 
        if (obj is JucatorActiv) 
        { 
            return (obj as JucatorActiv).ID == ID;
        } 
        return base.Equals(obj);
    }
    
    public static bool operator ==(JucatorActiv j1, JucatorActiv j2) 
    { 
        return j1.Equals(j2);
    }
    
    public static bool operator !=(JucatorActiv j1, JucatorActiv j2) 
    { 
        return !j1.Equals(j2);
    }
}
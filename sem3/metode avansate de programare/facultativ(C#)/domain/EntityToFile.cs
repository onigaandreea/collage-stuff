namespace facultativ.domain;

public class EntityToFile
{
    public static Elev CreateElev(string line)
    {
        string[] fields = line.Split(','); // new char[] { ',' } 
        Elev elev = new Elev()
        {
            ID = Int32.Parse(fields[0]),
            Nume = fields[1],
            Scoala = fields[2]
        };
        return elev;
    }

    public static Echipa CreateEchipa(string line)
    {
        string[] fields = line.Split(',');
        Echipa echipa = new Echipa()
        {
            ID = Int32.Parse(fields[0]),
            Nume = fields[1]
        };
        return echipa;
    }

    public static Jucator CreateJucator(string line)
    {
        string[] fields = line.Split(',');
        Jucator jucator = new Jucator()
        {
            Echipa = CreateEchipa(fields[3] + ',' + fields[4]),
            ID = Int32.Parse(fields[0]),
            Nume = fields[1],
            Scoala = fields[2]
        };
        return jucator;
    }

    public static JucatorActiv CreateJucatorActiv(string line)
    {
        string[] fields = line.Split(',');
        JucatorActiv jucatorActiv = new JucatorActiv()
        {
            ID = Int32.Parse(fields[0]),
            idJucator = Int32.Parse(fields[1]),
            idMeci = Int32.Parse(fields[2]),
            nrPuncteInscrise = Int32.Parse(fields[3]),
            tipJucator = (Tip)Enum.Parse(typeof(Tip), fields[4])
        };
        return jucatorActiv;
    }

    public static Meci CreateMeci(string line)
    {
        string[] fields = line.Split(',');
        Meci meci = new Meci()
        {
            data = DateOnly.Parse(fields[5]),
            Echipa1 = CreateEchipa(fields[1] + ',' + fields[2]),
            Echipa2 = CreateEchipa(fields[3] + ',' + fields[4]),
            ID = Int32.Parse(fields[0])
        };
        return meci;
    }
}
using facultativ.domain;
using facultativ.repository;

namespace facultativ.service;

public class Service
{
    private Repository<int, Echipa> repoEchipa;

    private Repository<int, Elev> repoElev;

    private Repository<int, Jucator> repoJucator;

    private Repository<int, JucatorActiv> repoJucatorActiv;

    private Repository<int, Meci> repoMeci;

    public Service(Repository<int, Echipa> repoEchipa, Repository<int, Elev> repoElev,
        Repository<int, Jucator> repoJucator, Repository<int, JucatorActiv> repoJucatorActiv,
        Repository<int, Meci> repoMeci)
    {
        this.repoEchipa = repoEchipa;
        this.repoElev = repoElev;
        this.repoJucator = repoJucator;
        this.repoJucatorActiv = repoJucatorActiv;
        this.repoMeci = repoMeci;
    }

    public List<Jucator> jucatoriiUneiEchipe(Echipa echipa)
    {
        return repoJucator.FindAll().Where(jucator => jucator.Echipa == echipa).ToList();
    }

    public List<JucatorActiv> jucatoriActiviInMeci(Echipa echipa, Meci meci)
    {
        List<JucatorActiv> jucatoriDinMeci = repoJucatorActiv.FindAll().Where(jucator =>
            jucator.idMeci == meci.ID && jucator.tipJucator == Tip.Participant).ToList();
        List<JucatorActiv> jucatoriEchipa = new List<JucatorActiv>();
        foreach (JucatorActiv jucatori in jucatoriDinMeci)
        {
            if(this.getJucatorByID(jucatori.idJucator).Echipa == echipa)
                jucatoriEchipa.Add(jucatori);
        }
        return jucatoriEchipa;
    }

    public List<Meci> meciPerioadaCalendaristica(DateOnly begin, DateOnly end)
    {
        return repoMeci.FindAll().Where(meci => meci.data >= begin && meci.data <= end).ToList();
    }

    public string scorMeci(Meci meci)
    {
        int scor1 = 0, scor2 = 0;
        List<JucatorActiv> echipa1 = this.jucatoriActiviInMeci(meci.Echipa1, meci);
        List<JucatorActiv> echipa2 = this.jucatoriActiviInMeci(meci.Echipa2, meci);
        foreach (JucatorActiv jucator in echipa1)
        {
            scor1 = scor1 + jucator.nrPuncteInscrise;
        }
        foreach (JucatorActiv jucator in echipa2)
        {
            scor2 = scor2 + jucator.nrPuncteInscrise;
        }
        return scor1 + ":" + scor2;
    }

    public Echipa echipaDupaNume(string nume)
    {
        foreach (Echipa echipa in repoEchipa.FindAll())
        {
            if (echipa.Nume == nume)
            {
                return echipa;
            }
        }
        return null;
    }

    public Meci getMeci(int id)
    {
        foreach (Meci meci in repoMeci.FindAll())
        {
            if (meci.ID == id)
            {
                return meci;
            }
        }
        return null;
    }

    public Jucator getJucatorByID(int id)
    {
        foreach (Jucator jucator in repoJucator.FindAll())
        {
            if (jucator.ID == id)
            {
                return jucator;
            }
        }
        return null;
    }
}
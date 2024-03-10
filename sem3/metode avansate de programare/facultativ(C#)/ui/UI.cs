using facultativ.domain;
using facultativ.service;

namespace facultativ.ui;

public class UI
{
    private Service service;

    public UI(Service service)
    {
        this.service = service;
    }
    
    public void jucatoriEchipa()
    { 
        Console.WriteLine("Dati numele echipei: "); 
        string echipa = Console.ReadLine(); 
        Echipa echipaData = service.echipaDupaNume(echipa); 
        List<Jucator> echipe = service.jucatoriiUneiEchipe(echipaData); 
        Console.WriteLine("Jucatorii din echipa " + echipaData.Nume + " sunt:"); 
        foreach (Jucator jucator in echipe) 
        { 
            Console.WriteLine(jucator);
        }
    }

    public void jucatorActiv()
    { 
        Console.WriteLine("Dati numele echipei: "); 
        string echipa = Console.ReadLine(); 
        Echipa echipaData = service.echipaDupaNume(echipa); 
        Console.WriteLine("Dati id-ul meciului: "); 
        int idMeci = Convert.ToInt32(Console.ReadLine()); 
        Meci meci = service.getMeci(idMeci); 
        List<JucatorActiv> jucatoriActivi = service.jucatoriActiviInMeci(echipaData,meci); 
        Console.WriteLine("Jucatorii din echipa " + echipaData.Nume + " care au participat la meciul " + meci.ID + " sunt:"); 
        foreach (JucatorActiv jucator in jucatoriActivi) 
        { 
            Console.WriteLine(service.getJucatorByID(jucator.idJucator).Nume);
        }
    }
    
    public void meciuriDinPerioadaX() 
    { 
        Console.WriteLine("Dati data de inceput a perioadei: "); 
        DateOnly inceput = DateOnly.Parse(Console.ReadLine()); 
        Console.WriteLine("Dati data de sfarsit a perioadei: "); 
        DateOnly sfarsit = DateOnly.Parse(Console.ReadLine()); 
        List<Meci> meciuri = service.meciPerioadaCalendaristica(inceput, sfarsit); 
        Console.WriteLine("Meciurile din perioada " + inceput + " - " + sfarsit); 
        foreach (Meci meci in meciuri) 
        { 
            Console.WriteLine(meci);
        }
    }
    
    public void scorMeciDat() 
    { 
        Console.WriteLine("Dati id-ul unui meci: "); 
        int id = Convert.ToInt32(Console.ReadLine()); 
        Meci meci = service.getMeci(id); 
        string scor = service.scorMeci(meci); 
        Console.WriteLine("Scorul meciului " + id + " este: " + scor + "(" + meci.Echipa1 + " - " + meci.Echipa2 + ")");
    }

    public void consola()
    {
        while (true)
        {
            Console.WriteLine("Meniu: \n" +
                          "1. Toti jucatorii dintr-o echipa \n" +
                          "2. Toti jucatorii activi ai unei echipe, intr-un meci dat \n" +
                          "3. Toate meciurile dintr-o perioada calendaristica data \n" +
                          "4. Scorul unui meci dat \n" +
                          "5. Exit");
        
            Console.WriteLine("Dati o comanda: ");
            int id = Convert.ToInt32(Console.ReadLine());
            if(id == 1)
                this.jucatoriEchipa();
            else if(id == 2)
                this.jucatorActiv();
            else if(id == 3)
                this.meciuriDinPerioadaX();
            else if(id == 4)
                this.scorMeciDat();
            else if(id == 5)
                break;
        }
    }
}
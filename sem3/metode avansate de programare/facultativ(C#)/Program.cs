using System.Security.Cryptography.X509Certificates;
using facultativ.domain;
using facultativ.repository;
using facultativ.service;
using facultativ.ui;

public class Program
{
    static void Main(string[] args)
    {
        string fileNameEchipe = "..\\..\\..\\data\\echipe.txt";
        
        string fileNameElevi = "..\\..\\..\\data\\elevi.txt";
        
        string fileNameJucatori = "..\\..\\..\\data\\jucatori.txt";
        
        string fileNameJucatoriActivi = "..\\..\\..\\data\\jucatoriActivi.txt";
        
        string fileNameMeciuri = "..\\..\\..\\data\\meciuri.txt";

        InFileRepository<int, Echipa> repoEchipa = new InFileRepository<int, Echipa>(fileNameEchipe, EntityToFile.CreateEchipa);
        
        InFileRepository<int, Elev> repoElev = new InFileRepository<int, Elev>(fileNameElevi, EntityToFile.CreateElev);

        InFileRepository<int, Jucator> repoJucatori = new InFileRepository<int, Jucator>(fileNameJucatori, EntityToFile.CreateJucator);

        InFileRepository<int, JucatorActiv> repoJucatoriActivi = new InFileRepository<int, JucatorActiv>(fileNameJucatoriActivi, EntityToFile.CreateJucatorActiv);

        InFileRepository<int, Meci> repoMeciuri = new InFileRepository<int, Meci>(fileNameMeciuri, EntityToFile.CreateMeci);

        Service service = new Service(repoEchipa, repoElev, repoJucatori, repoJucatoriActivi, repoMeciuri);

        UI consola = new UI(service);
        
        consola.consola();
    }
}

using facultativ.domain;

namespace facultativ.repository;

public delegate E CreateEntity<E>(string line);

public class InFileRepository<ID,E> : InMemoryRepository<ID, E> where E : Entity<ID>
{
    protected string fileName;
    protected CreateEntity<E> createEntity;

    public InFileRepository(String fileName, CreateEntity<E> createEntity)
    {
        this.fileName = fileName;
        this.createEntity = createEntity;
        if (createEntity != null)
            loadFromFile();
    }

    public static List<T> ReadData<T>(string fileName, CreateEntity<T> createEntity)
    {
        List<T> list = new List<T>();
        using (StreamReader sr = new StreamReader(fileName))
        {
            string s;
            while ((s = sr.ReadLine()) != null)
            {
                T entity= createEntity(s);
                list.Add(entity);
            }
        }
        return list;
    }
    
    protected virtual void loadFromFile()
    {
        List<E> list = ReadData(fileName, createEntity);
        list.ForEach(x => entities[x.ID] = x);
    }
   
}
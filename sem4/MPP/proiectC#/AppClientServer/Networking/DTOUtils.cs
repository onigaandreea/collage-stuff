using model;

namespace Networking;

public class DTOUtils
{
    public static Task getFromDTO(TaskDTO usdto)
    {
        string desc =usdto.description;
        int ageMin = usdto.ageCatStart;
        int ageMax = usdto.ageCatEnd;
        return new Task(desc,ageMin,ageMax);

    }

}
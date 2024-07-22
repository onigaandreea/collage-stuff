using System;
using System.Collections.Generic;
using model;

namespace Networking;

[Serializable]
public class ListTasksResponse : Response
{
    private List<TaskDTO> tasks;

    public ListTasksResponse(List<TaskDTO> tasks)
    {
        this.tasks = tasks;
    }

    public virtual List<TaskDTO> getTasks { get { return this.tasks; } }
}
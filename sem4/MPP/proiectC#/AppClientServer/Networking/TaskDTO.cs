using System;

namespace Networking
{
    [Serializable]
    public class TaskDTO
    {
        public string description { get; set; }
        public int ageCatStart { get; set; }
        public int ageCatEnd { get; set; }
        public int nrChildren { get; set; }

        public TaskDTO(string description, int ageCatStart, int ageCatEnd, int nrKids)
        {
            this.description = description;
            this.ageCatStart = ageCatStart;
            this.ageCatEnd = ageCatEnd;
            this.nrChildren = nrKids;
        }

        public virtual string Description
        {
            get
            {
                return description;
            }
        }
        
        public virtual int AgeCatStart
        {
            get
            {
                return ageCatStart;
            }
        }
        public virtual int AgeCatEnd
        {
            get
            {
                return ageCatEnd;
            }
        }
        public virtual int NrChildren
        {
            get
            {
                return nrChildren;
            }
        }
    }
}
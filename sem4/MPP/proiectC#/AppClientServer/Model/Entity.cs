using System;
using Newtonsoft.Json;

namespace model
{
    [Serializable]
    public class Entity<TID>
    {
        [JsonProperty("id")]
        public TID ID { get; set; }
    }
}
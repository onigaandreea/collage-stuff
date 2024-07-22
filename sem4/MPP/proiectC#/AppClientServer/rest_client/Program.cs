using System;
using System.Threading;
using System.Threading.Tasks;
using System.Net.Http;

namespace rest_client
{
    internal class Program
    {
        private static HttpClient client = new HttpClient(new LoggingHandler(new HttpClientHandler()));
        private static string URL = "http://localhost:8080/org/tasks";

        public static void Main(string[] args)
        {
            RunAsync().Wait();
        }

        static async Task RunAsync()
        {
            client.BaseAddress = new Uri(URL);
            client.DefaultRequestHeaders.Accept.Clear();
            client.DefaultRequestHeaders.Accept.Add(new System.Net.Http.Headers.MediaTypeWithQualityHeaderValue("application/json"));

            model.Task task = new model.Task("cautare comori", 6, 8);
            
            model.Task result = await save(task);
            Console.WriteLine("Save task {0}", result);
            model.Task[] resultList = await findAll();
            foreach (model.Task m in resultList)
            {
                Console.WriteLine(m);
            }

            result = await find(4);
            Console.WriteLine("Find task {0}", result);
            task.description = "poezie";
            result = await update(task);
            Console.WriteLine("Update task {0}", result);

            await remove(36);
        }

        static async Task<model.Task[]?> findAll()
        {
            model.Task[]? result = null;

            HttpResponseMessage response = await client.GetAsync(URL);
            if (response.IsSuccessStatusCode)
            {
                result = await response.Content.ReadAsAsync<model.Task[]>();
            }
            return result;
        }

        static async Task<model.Task?> find(int id)
        {
            model.Task? result = null;

            HttpResponseMessage response = await client.GetAsync(URL + "/" + id.ToString());
            if (response.IsSuccessStatusCode)
            {
                result = await response.Content.ReadAsAsync<model.Task>();
            }
            return result;
        }

        static async Task<model.Task?> save(model.Task task)
        {
            model.Task? result = null;

            HttpResponseMessage response = await client.PostAsJsonAsync(URL, task);
            if (response.IsSuccessStatusCode)
            {
                result = await response.Content.ReadAsAsync<model.Task>();
                task.ID = result.ID;
            }
            Console.WriteLine("AAA" + task);
            return result;
        }
        
        static async Task<model.Task?> update(model.Task task)
        {
            model.Task? result = null;

            HttpResponseMessage response = await client.PutAsJsonAsync(URL + "/" + task.ID.ToString(), task);
            if (response.IsSuccessStatusCode)
            {
                result = await response.Content.ReadAsAsync<model.Task>();
            }
            return result;
        }

        static async Task remove(int id)
        {
            await client.DeleteAsync(URL + "/" + id.ToString());
        }
    }

    internal class LoggingHandler : DelegatingHandler
    {
        public LoggingHandler(HttpMessageHandler handler) : base(handler) { }

        protected override async Task<HttpResponseMessage> SendAsync(HttpRequestMessage request, CancellationToken cancellationToken)
        {
            Console.WriteLine("Request:");
            Console.WriteLine(request.ToString());
            if (request.Content != null)
            {
                Console.WriteLine(await request.Content.ReadAsStringAsync());
            }
            Console.WriteLine();

            HttpResponseMessage response = await base.SendAsync(request, cancellationToken);

            Console.WriteLine("Response:");
            Console.WriteLine(response.ToString());
            if (response.Content != null)
            {
                Console.WriteLine(await response.Content.ReadAsStringAsync());
            }
            Console.WriteLine();

            return response;
        }
    }
}
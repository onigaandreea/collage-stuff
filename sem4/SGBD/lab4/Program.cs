

using System;
using System.Data;
using System.Data.SqlClient;
using System.Threading;

namespace lab4
{
    internal class Program
    {
        private static readonly String ConnectionString = "Data Source=ANDREEA\\SQLEXPRESS; " +
                                                          "Initial Catalog=Biblioteca;Integrated Security=True;";

        static void Main()
        {
            ThreadPool.QueueUserWorkItem(Proc1);
            ThreadPool.QueueUserWorkItem(Proc2);

            Console.Read();
        }

        static void Proc1(Object? stateInfo)
        {
            using (SqlConnection Connection = new SqlConnection(ConnectionString))
            {
                using (SqlCommand command = new SqlCommand("DeadLockProc1", Connection))
                {
                    command.CommandType = CommandType.StoredProcedure;

                    Connection.Open();

                    int retries = 3;
                    while (true)
                    {
                        try
                        {
                            Console.WriteLine("Locking Cititor");
                            Console.WriteLine("Cititor locked");
                            int rowsAffected = command.ExecuteNonQuery();
                            Console.WriteLine("No deadlock");
                            break;
                        }
                        catch (SqlException exception) when (exception.Number == 1205)
                        {
                            Console.WriteLine("Deadlock");
                            if (--retries == 0)
                            {
                                throw;
                            }
                        }
                    }
                }
            }
        }

        static void Proc2(Object? stateInfo)
        {
            Thread.Sleep(1000);

            using (SqlConnection Connection = new SqlConnection(ConnectionString))
            {
                using (SqlCommand command = new SqlCommand("DeadLockProc2", Connection))
                {
                    command.CommandType = CommandType.StoredProcedure;

                    Connection.Open();

                    int retries = 3;
                    while (true)
                    {
                        try
                        {
                            Console.WriteLine("\t\t\t\tLocking Biblioteca");
                            Console.WriteLine("\t\t\t\tBiblioteca locked");
                            int rowsAffected = command.ExecuteNonQuery();
                            Console.WriteLine("\t\t\t\tNo deadlock");
                            break;
                        }
                        catch (SqlException exception) when (exception.Number == 1205)
                        {
                            Console.WriteLine("\t\t\t\tDeadlock");
                            if (--retries == 0)
                            {
                                throw;
                            }
                        }
                    }
                }
            }
        }
    }
}
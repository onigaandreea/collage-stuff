using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SQLite;
using System.Reflection;

namespace repository
{
    public static class DBUtils
    {
        private static IDbConnection instance = null;
        public static IDbConnection getConnection(IDictionary<string,string> props)
        {
            if (instance == null || instance.State == System.Data.ConnectionState.Closed)
            {
                instance = getNewConnection(props);
                instance.Open();
            }
            return instance;
        }

        private static IDbConnection getNewConnection(IDictionary<string,string> props)
        {
            return ConnectionFactory.getInstance().createConnection(props);
        }
    }
    
    public abstract class ConnectionFactory
    {
        protected ConnectionFactory()
        {
        }

        private static ConnectionFactory instance;

        public static ConnectionFactory getInstance()
        {
            if (instance == null)
            {

                Assembly assem = Assembly.GetExecutingAssembly();
                Type[] types = assem.GetTypes();
                foreach (var type in types)
                {
                    if (type.IsSubclassOf(typeof(ConnectionFactory)))
                        instance = (ConnectionFactory)Activator.CreateInstance(type);
                }
            }
            return instance;
        }
        public abstract  IDbConnection createConnection(IDictionary<string,string> props);
    }
    
    public class SqliteConnectionFactory : ConnectionFactory
    {
        public override IDbConnection createConnection(IDictionary<string,string> props)
        {
            //Mono Sqlite Connection

            //String connectionString = "URI=file:/Users/grigo/didactic/MPP/ExempleCurs/2017/database/tasks.db,Version=3";
            String connectionString = props["ConnectionString"];
            Console.WriteLine("SQLite ---Se deschide o conexiune la  ... {0}", connectionString);
            return new SQLiteConnection(connectionString);

            // Windows SQLite Connection, fisierul .db ar trebuie sa fie in directorul debug/bin
            //String connectionString = "Data Source=tasks.db;Version=3";
            //return new SQLiteConnection(connectionString);
        }
    }
}
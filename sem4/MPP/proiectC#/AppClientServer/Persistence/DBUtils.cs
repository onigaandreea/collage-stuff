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
        public static IDbConnection getConnection()
        {
            if (instance == null || instance.State == System.Data.ConnectionState.Closed)
            {
                instance = getNewConnection();
                instance.Open();
            }
            return instance;
        }

        private static IDbConnection getNewConnection()
        {
            return ConnectionFactory.getInstance().createConnection();
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
        public abstract  IDbConnection createConnection();
    }
    
    public class SqliteConnectionFactory : ConnectionFactory
    {
        public override IDbConnection createConnection()
        {
            //Mono Sqlite Connection
            String connectionString = "Data Source =C:/Users/oniga/Desktop/an2sem2/MPP/dbProiect/childrencompetition.db;Version=3";
            Console.WriteLine("SQLite ---Se deschide o conexiune la  ... {0}", connectionString);
            return new SQLiteConnection(connectionString);

        }
    }
}
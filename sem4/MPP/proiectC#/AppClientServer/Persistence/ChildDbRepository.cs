using System;
using System.Collections.Generic;
using System.Data;
using log4net;
using model;
namespace repository;

public class ChildDbRepository : IChildRepository
{
    private static readonly ILog log = LogManager.GetLogger("SortingTaskDbRepository");

    public ChildDbRepository()
		{
			log.Info("Creating SortingTaskDbRepository ");
			
		}

		public Child FindOne(int id)
		{
			log.InfoFormat("Entering findOne with value {0}", id);
			IDbConnection con = DBUtils.getConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from child where id_child=@id";
				IDbDataParameter paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);

				using (var dataR = comm.ExecuteReader())
				{
					if (dataR.Read())
					{
						int idC = dataR.GetInt32(0);
						String name = dataR.GetString(1);
						int age = dataR.GetInt32(2);
						Child child = new Child(name, age);
						child.ID = idC;
						log.InfoFormat("Exiting findOne with value {0}", child);
						return child;
					}
				}
			}
			log.InfoFormat("Exiting findOne with value {0}", null);
			return null;
		}

		public List<Child> FindAll()
		{
			IDbConnection con = DBUtils.getConnection();
			List<Child> children = new List<Child>();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from child";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						int idC = dataR.GetInt32(0);
						String name = dataR.GetString(1);
						int age = dataR.GetInt32(2);
						Child child = new Child(name, age);
						child.ID = idC;
						children.Add(child);
					}
				}
			}
			return children;
		}
		
		public Child Save(Child entity)
		{
			var con = DBUtils.getConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into child(child_name, child_age) values (@name, @age)";

				var paramName = comm.CreateParameter();
				paramName.ParameterName = "@name";
				paramName.Value = entity.name;
				comm.Parameters.Add(paramName);

				IDbDataParameter paramAge = comm.CreateParameter();
				paramAge.ParameterName = "@age";
				paramAge.Value = entity.age.ToString();
				comm.Parameters.Add(paramAge);

				var result = comm.ExecuteNonQuery();
				if (result == 0)
					//throw new RepositoryException("No task added !");
					throw new Exception("No task added !");
				else
				{
					return entity;
				}
			}
			
		}

		public Child Delete(int id)
		{
			return null;
		}

		public Child Update(Child entity)
		{
			return null;
		}
}
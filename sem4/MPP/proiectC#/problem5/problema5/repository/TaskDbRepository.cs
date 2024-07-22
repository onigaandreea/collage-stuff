using System;
using System.Collections.Generic;
using System.Data;
using log4net;
using model;
namespace problema5.repository;

public class TaskDbRepository : ITaskRepository
{
    private static readonly ILog log = LogManager.GetLogger("SortingTaskDbRepository");

		IDictionary<String, string> props;
		
		public TaskDbRepository(IDictionary<String, string> props)
		{
			log.Info("Creating SortingTaskDbRepository ");
			this.props = props;
		}

		public Task FindOne(int id)
		{
			log.InfoFormat("Entering findOne with value {0}", id);
			IDbConnection con = DBUtils.getConnection(props);

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from task where id_task=@id";
				IDbDataParameter paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);

				using (var dataR = comm.ExecuteReader())
				{
					if (dataR.Read())
					{
						int idT = dataR.GetInt32(0);
						String description = dataR.GetString(1);
						int ageS = dataR.GetInt32(2);
						int ageE = dataR.GetInt32(3);
						Task task = new Task(description, ageS, ageE);
						task.ID = idT;
						log.InfoFormat("Exiting findOne with value {0}", task);
						return task;
					}
				}
			}
			log.InfoFormat("Exiting findOne with value {0}", null);
			return null;
		}

		public IEnumerable<Task> FindAll()
		{
			IDbConnection con = DBUtils.getConnection(props);
			IList<Task> tasks = new List<Task>();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from task";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						int idT = dataR.GetInt32(0);
						String description = dataR.GetString(1);
						int ageS = dataR.GetInt32(2);
						int ageE = dataR.GetInt32(3);
						Task task = new Task(description, ageS, ageE);
						task.ID = idT;
						tasks.Add(task);
					}
				}
			}
			return tasks;
		}
		
		public Task Save(Task entity)
		{
			var con = DBUtils.getConnection(props);

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into task(description, age_category_start, age_category_end)  values (@description, @ageS, @ageE)";
				
				var paramDescription = comm.CreateParameter();
				paramDescription.ParameterName = "@description";
				paramDescription.Value = entity.description;
				comm.Parameters.Add(paramDescription);

				var paramAgeS = comm.CreateParameter();
				paramAgeS.ParameterName = "@ageS";
				paramAgeS.Value = entity.ageCatStart;
				comm.Parameters.Add(paramAgeS);

				IDbDataParameter paramAgeE = comm.CreateParameter();
				paramAgeE.ParameterName = "@ageE";
				paramAgeE.Value = entity.ageCatEnd;
				comm.Parameters.Add(paramAgeE);

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

		public Task Delete(int id)
		{
			return null;
		}

		public Task Update(Task entity)
		{
			return null;
		}

		public Task findByDescriptionAndAge(string name, int age)
		{
			log.InfoFormat("Entering findOne with value {0}", name);
			IDbConnection con = DBUtils.getConnection(props);

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from task where description = @desc and age_category_start < @age and age_category_end > @age1";
				IDbDataParameter paramDesc = comm.CreateParameter();
				paramDesc.ParameterName = "@desc";
				paramDesc.Value = name;
				comm.Parameters.Add(paramDesc);
				
				IDbDataParameter paramAge = comm.CreateParameter();
				paramAge.ParameterName = "@age";
				paramAge.Value = age;
				comm.Parameters.Add(paramAge);
				
				IDbDataParameter paramAge1 = comm.CreateParameter();
				paramAge1.ParameterName = "@age1";
				paramAge1.Value = age;
				comm.Parameters.Add(paramAge1);

				using (var dataR = comm.ExecuteReader())
				{
					if (dataR.Read())
					{
						int idT = dataR.GetInt32(0);
						String description = dataR.GetString(1);
						int ageS = dataR.GetInt32(2);
						int ageE = dataR.GetInt32(3);
						Task task = new Task(description, ageS, ageE);
						task.ID = idT;
						log.InfoFormat("Exiting findOne with value {0}", task);
						return task;
					}
				}
			}
			log.InfoFormat("Exiting findOne with value {0}", null);
			return null;
		}
}
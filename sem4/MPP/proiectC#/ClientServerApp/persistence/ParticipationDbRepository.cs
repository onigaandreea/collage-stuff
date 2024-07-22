using System;
using System.Collections.Generic;
using System.Data;
using log4net;
using model;
namespace repository;

public class ParticipationDbRepository : IParticipationRepository
{
	private static readonly ILog log = LogManager.GetLogger("SortingTaskDbRepository");
	private ChildDbRepository repoC;
	private TaskDbRepository repoT;

	IDictionary<String, string> props;

	public ParticipationDbRepository(IDictionary<String, string> props)
	{
		log.Info("Creating SortingTaskDbRepository ");
		this.props = props;
		repoC = new ChildDbRepository(props);
		repoT = new TaskDbRepository(props);
	}

	public IEnumerable<Participation> findByTask(Task task1)
	{
		log.InfoFormat("Entering findOne with value {0}", task1);
		IDbConnection con = DBUtils.getConnection(props);

		using (var comm = con.CreateCommand())
		{
			comm.CommandText = "select * from participation where id_task=@id";
			IDbDataParameter paramId = comm.CreateParameter();
			paramId.ParameterName = "@id";
			paramId.Value = task1.ID;
			comm.Parameters.Add(paramId);

			IList<Participation> participations = new List<Participation>();
			using (var dataR = comm.ExecuteReader())
			{
				while (dataR.Read())
				{
					int idP = dataR.GetInt32(0);
					int idC = dataR.GetInt32(1);
					int idT = dataR.GetInt32(2);
					Child child = repoC.FindOne(idC);
					Task task = repoT.FindOne(idT);
					Participation participation = new Participation(child, task);
					participation.ID = idP;
					participations.Add(participation);
				}
			}
			return participations;
		}
	}
	

		public Participation FindOne(int id)
		{
			log.InfoFormat("Entering findOne with value {0}", id);
			IDbConnection con = DBUtils.getConnection(props);

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from participation where id_participation=@id";
				IDbDataParameter paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);

				using (var dataR = comm.ExecuteReader())
				{
					if (dataR.Read())
					{
						int idP = dataR.GetInt32(0);
						int idC = dataR.GetInt32(1);
						int idT = dataR.GetInt32(2);
						Child child = repoC.FindOne(idC);
						Task task = repoT.FindOne(idT);
						Participation participation = new Participation(child, task);
						participation.ID = idP;
						log.InfoFormat("Exiting findOne with value {0}", participation);
						return participation;
					}
				}
			}
			log.InfoFormat("Exiting findOne with value {0}", null);
			return null;
		}

		public IEnumerable<Participation> FindAll()
		{
			IDbConnection con = DBUtils.getConnection(props);
			IList<Participation> participations = new List<Participation>();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from participation";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						int idP = dataR.GetInt32(0);
						int idC = dataR.GetInt32(1);
						int idT = dataR.GetInt32(2);
						Child child = repoC.FindOne(idC);
						Task task = repoT.FindOne(idT);
						Participation participation = new Participation(child, task);
						participation.ID = idP;
						participations.Add(participation);
					}
				}
			}
			return participations;
		}
		
		public Participation Save(Participation entity)
		{
			var con = DBUtils.getConnection(props);

			using (var comm = con.CreateCommand())
			{
				comm.CommandText =
					"insert into participation(id_child, id_task)  values (@idC, @idT)";

				var paramIdC = comm.CreateParameter();
				paramIdC.ParameterName = "@idC";
				paramIdC.Value = entity.child.ID;
				comm.Parameters.Add(paramIdC);

				var paramIdT = comm.CreateParameter();
				paramIdT.ParameterName = "@idT";
				paramIdT.Value = entity.task.ID;
				comm.Parameters.Add(paramIdT);

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

		public Participation Delete(int id)
		{
			return null;
		}

		public Participation Update(Participation entity)
		{
			return null;
		}
}
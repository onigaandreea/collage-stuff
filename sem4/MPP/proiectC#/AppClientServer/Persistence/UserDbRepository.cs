using System;
using System.Collections.Generic;
using System.Data;
using log4net;
using model;

namespace repository
{
    public class UserDbRepository: IUserRepository
	{
		private static readonly log4net.ILog log = LogManager.GetLogger("UserDbRepository");

		public UserDbRepository()
		{
			log.Info("Creating UserDbRepository ");
		}
		
		public User findByEmail(string email)
		{
			log.InfoFormat("Entering findByEmail with value {0}", email);
			IDbConnection con = DBUtils.getConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from user where username=@username";
				IDbDataParameter paramUsername = comm.CreateParameter();
				paramUsername.ParameterName = "@username";
				paramUsername.Value = email;
				comm.Parameters.Add(paramUsername);

				using (var dataR = comm.ExecuteReader())
				{
					if (dataR.Read())
					{
						int idU = dataR.GetInt32(0);
						String name = dataR.GetString(1);
						String username = dataR.GetString(2);
						String password = dataR.GetString(3);
						int office_no = dataR.GetInt32(4);
						User user = new User(name, username, password, office_no);
						user.ID = idU;
						log.InfoFormat("Exiting findByEmail with value {0}", user);
						return user;
					}
				}
			}
			log.InfoFormat("Exiting findByEmail with value {0}", null);
			return null;
		}

		public User FindOne(int id)
		{
			log.InfoFormat("Entering findOne with value {0}", id);
			IDbConnection con = DBUtils.getConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from user where user_id=@id";
				IDbDataParameter paramId = comm.CreateParameter();
				paramId.ParameterName = "@id";
				paramId.Value = id;
				comm.Parameters.Add(paramId);

				using (var dataR = comm.ExecuteReader())
				{
					if (dataR.Read())
					{
						int idU = dataR.GetInt32(0);
						String name = dataR.GetString(1);
						String username = dataR.GetString(2);
						String password = dataR.GetString(3);
						int office_no = dataR.GetInt32(4);
						User user = new User(name, username, password, office_no);
						user.ID = idU;
						log.InfoFormat("Exiting findOne with value {0}", user);
						return user;
					}
				}
			}
			log.InfoFormat("Exiting findOne with value {0}", null);
			return null;
		}

		public List<User> FindAll()
		{
			IDbConnection con = DBUtils.getConnection();
			List<User> users = new List<User>();
			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "select * from user";

				using (var dataR = comm.ExecuteReader())
				{
					while (dataR.Read())
					{
						int idU = dataR.GetInt32(0);
						String name = dataR.GetString(1);
						String username = dataR.GetString(2);
						String password = dataR.GetString(3);
						int office_no = dataR.GetInt32(4);
						User user = new User(name, username, password, office_no);
						user.ID = idU;
						users.Add(user);
					}
				}
			}

			return users;
		}
		
		public User Save(User entity)
		{
			var con = DBUtils.getConnection();

			using (var comm = con.CreateCommand())
			{
				comm.CommandText = "insert into user(name, username, password, office_no)  values (@name, @username, @password, @off_no)";

				var paramName = comm.CreateParameter();
				paramName.ParameterName = "@name";
				paramName.Value = entity.name;
				comm.Parameters.Add(paramName);

				var paramUsername = comm.CreateParameter();
				paramUsername.ParameterName = "@username";
				paramUsername.Value = entity.username;
				comm.Parameters.Add(paramUsername);

				IDbDataParameter paramPassword = comm.CreateParameter();
				paramPassword.ParameterName = "@password";
				paramPassword.Value = entity.password;
				comm.Parameters.Add(paramPassword);

				IDbDataParameter paramOfficeNo = comm.CreateParameter();
				paramOfficeNo.ParameterName = "@off_no";
				paramOfficeNo.Value = entity.officeNo.ToString();
				comm.Parameters.Add(paramOfficeNo);

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

		public User Delete(int id)
		{
			return null;
		}

		public User Update(User entity)
		{
			return null;
		}
	}
}


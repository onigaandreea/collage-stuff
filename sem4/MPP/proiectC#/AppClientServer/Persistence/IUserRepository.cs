using model;

namespace repository;

public interface IUserRepository : Repository<int, User>
{
    User findByEmail(string email);
}
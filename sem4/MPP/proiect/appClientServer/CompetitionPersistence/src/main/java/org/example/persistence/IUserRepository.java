package org.example.persistence;

import org.example.model.User;

public interface IUserRepository extends Repository<User,Integer>{
    User findByEmail(String email);
}

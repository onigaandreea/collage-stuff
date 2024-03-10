package repository;

import domain.User;
import domain.validators.ValidationException;
import domain.validators.Validator;

import java.util.List;

public class Userfile extends AbstractFileRepository<Long, User> {
    public Userfile(String fileName, Validator<User> validator) throws ValidationException {
        super(fileName, validator);
    }

    @Override
    public User extractEntity(List<String> attributes) {
        User user = new User(attributes.get(1),attributes.get(2),attributes.get(3));
        user.setId(Long.parseLong(attributes.get(0)));

        return user;
    }

    @Override
    protected String createEntityAsString(User entity) {
        return entity.getId()+";"+entity.getFirstName()+";"+entity.getLastName()+";"+entity.getEmail();
    }
}

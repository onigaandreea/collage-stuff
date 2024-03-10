package repository;

import domain.Friendship;
import domain.validators.ValidationException;
import domain.validators.Validator;

import java.time.LocalDateTime;
import java.util.List;

public class Friendshipfile extends AbstractFileRepository<Long, Friendship> {

    public Friendshipfile(String fileName, Validator<Friendship> validator) throws ValidationException {
        super(fileName, validator);
    }

    @Override
    public Friendship extractEntity(List<String> attributes) {
        Friendship friendship = new Friendship(Long.parseLong(attributes.get(1)),Long.parseLong(attributes.get(2)),
                LocalDateTime.parse(attributes.get(3)));
        friendship.setId(Long.parseLong(attributes.get(0)));
        return friendship;
    }

    @Override
    protected String createEntityAsString(Friendship entity) {
        return entity.getId() + ";" + entity.getIdUser1() + ";" + entity.getIdUser2() + ";" + entity.getFriendsFrom();
    }
}

package domain.validators;

import domain.Friendship;

public class FriendshipValidator implements Validator<Friendship>{

    @Override
    public void validate(Friendship entity) throws ValidationException {
        String errorMsg = "";
        if(entity.getId() == null)
            errorMsg += "A friendship can not have a null ID! \n";
        if(entity.getIdUser1() == 0)
            errorMsg += "There is no user that has his id 0! \n";
        if(entity.getIdUser2() == 0)
            errorMsg += "There is no user that has his id 0! \n";
        if(entity.getFriendsFrom() == null)
            errorMsg += "The date in witch two users became friends can not be null! \n";
        if(entity.getIdUser1() == entity.getIdUser2())
            errorMsg += "One user can not be friend with himself! \n";
        if(!errorMsg.equals(""))
            throw new ValidationException(errorMsg);
    }
}

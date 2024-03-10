package domain.validators;

import domain.User;

public class UserValidator implements Validator<User> {
    @Override
    public void validate(User entity) throws ValidationException {
        String errorMsg = "";
        if(entity.getFirstName() == null)
            errorMsg += "The first name can not be null! \n";
        if(entity.getLastName() == null)
            errorMsg += "The last name can not be null! \n";
        if(entity.getEmail() == null)
            errorMsg += "The email can not be null! \n";
        if(entity.getFirstName().startsWith(" ") || entity.getFirstName().contains("  ") ||
        entity.getFirstName().contains("1234567890") || entity.getFirstName().contains("!@#$%^&*+"))
            errorMsg += "The first name is invalid! \n";
        if(entity.getLastName().startsWith(" ") || entity.getLastName().contains("  ") ||
                entity.getLastName().contains("1234567890") || entity.getLastName().contains("!@#$%^&*+"))
            errorMsg += "The last name is invalid! \n";
        if(entity.getEmail().startsWith(" ") || entity.getEmail().contains("  ") ||
                !entity.getEmail().contains("@") || !entity.getEmail().contains("."))
            errorMsg += "The email is invalid! \n";

        if(!errorMsg.equals(""))
            throw new ValidationException(errorMsg);
    }
}

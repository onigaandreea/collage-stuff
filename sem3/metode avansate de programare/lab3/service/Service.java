package service;

import domain.User;
import domain.validators.UserValidator;
import domain.validators.ValidationException;
import repository.Repository;
import repository.memory.InMemoryRepository;

public class Service {
    /**
     * Repository containing the Users with the ID of type Long
     * */

    private final Repository<Long, User> repository;

    public Service() throws ValidationException {
        this.repository = new InMemoryRepository<>(new UserValidator());
    }

    /**
     * Adds a user to repository.
     * A new user is created based on the information provided as parameters
     * and the id is set with the next value in the repository.
     * The user is added only if it doesn't exist already and if it exists but is deleted, then it will be restored.
     * @param: FirstName  user's first name
     * @param: LastName   user's last name
     * @param: Email      user's email address
     * @throws   ValidationException if the user is not defined as required
     * @throws   IllegalArgumentException if the data read is not in the correct format
     */
    public void addUser(String FirstName,String LastName,String Email) throws IllegalArgumentException,ValidationException{
        User newuser = new User(FirstName,LastName,Email);
        newuser.setId((long)this.repository.size() + 1);

        //int exista = 0;
        for(User user: this.repository.findAll()){
            if (user.getEmail().equals(newuser.getEmail())){
                //exista = 1;
                //newuser.setId(user.getId());
                newuser = user;
                break;
            }
        }

        User savedUser = this.repository.save(newuser);
        if(savedUser == null){
            System.out.println(newuser + " has been added.");
        }
        else if(savedUser == newuser && savedUser.isDeleted()){
            savedUser.setDeleted(false);
            System.out.println(savedUser + " is restored.");
        }
        else System.out.println(newuser + " does already exist.");
    }

    /**
     * Searches a user in the repository.
     * @return   the user if it is found and not deleted, null otherwise
     * @param    ID   User's ID
     * @throws   IllegalArgumentException if the data read is not the correct format
     */
    private User searchUser(long ID) throws IllegalArgumentException
    {
        User searchedUser = this.repository.findOne(ID);
        if (searchedUser == null)
            System.out.println("Can not find the user with the ID " + ID);
        else if (searchedUser.isDeleted())
        {
            System.out.println("The user " + searchedUser + " has been deleted");
            return null;
        }
        return searchedUser;
    }

    /**
     * Marks a user as deleted if it exists. It is not removed from repository.
     * @param   ID   User's ID
     * @throws  IllegalArgumentException if the data read is not the correct format
     */
    public void removeUser(int ID) throws IllegalArgumentException
    {
        User userToRemove = this.searchUser(ID);
        if (userToRemove != null)
        {
            userToRemove.setDeleted(true);
            System.out.println(userToRemove + " has been deleted.");
        }
    }

    /**
     * Creates a friendship between 2 users.
     * @param   ID   User's ID
     * @param   ID2  User2's ID
     * @throws  IllegalArgumentException if the data read is not the correct format
     */
    public void addFriendship(long ID, long ID2) throws IllegalArgumentException, ValidationException {
        User user1 = this.searchUser(ID);
        User user2 = this.searchUser(ID2);

        if (user1 != null && user2 != null)
        {
            user1.getFriends().add(user2);
            this.repository.update(user1);
            user2.getFriends().add(user1);
            this.repository.update(user2);
        }
    }

    /**
     * Delete a friendship
     * @param   ID   User's ID
     * @param   ID2  User2's ID
     * @throws  IllegalArgumentException if the data read is not the correct format
     */
    public void removeFriendship(long ID, long ID2) throws IllegalArgumentException, ValidationException {
        User user1 = this.searchUser(ID);
        User user2 = this.searchUser(ID2);

        if (user1 != null && user2 != null)
        {
            user1.getFriends().remove(user2);
            this.repository.update(user1);
            user2.getFriends().remove(user1);
            this.repository.update(user2);
        }
    }

    /**
     * Search for a community starting with a specific user
     * @param   user   the User we start the search with
     */
    private void DFS(User user){
        user.setCommunity(true); //mark that this user is already in the community

        user.getFriends().forEach(friend ->
        {
            if (!friend.isInCommunity() && !friend.isDeleted())
            {
                DFS(friend); //continue the search with a friend that's not in the community and is not deleted.
            }
        });
    }

    /**
     * Counts the number of communities. Starting from every user, it adds to the same community all its friends.
     * The process continues for all the users.
     * @return     the number of communities
     */
    public int numberOfComunities()
    {
        int communities = 0;
        for (User user : this.repository.findAll())
        {
            if (!user.isInCommunity() && !user.isDeleted() && user.getFriends().size() != 0)
            {
                ++communities;
                DFS(user);
            }
        }

        this.repository.findAll().forEach(user ->
        {
            user.setCommunity(false);
        });

        return communities;
    }

    /**
     * Prints all the users without their friends.
     * @return     does not return
     */
    public void listUsers()
    {
        this.repository.findAll().forEach(user ->
        {
            if (!user.isDeleted())
            {
                System.out.println(user);
            }
        });
    }

    /**
     * Prints all the users including their friends.
     * @return     does not return
     */
    public void listUsersWithFriends()
    {
        this.repository.findAll().forEach(user ->
        {
            if (!user.isDeleted())
            {
                System.out.println(user);
                user.getFriends().forEach(friend ->
                {
                    if (!friend.isDeleted())
                    {
                        System.out.println("\t" + friend);
                    }
                });
            }
        });
    }
}

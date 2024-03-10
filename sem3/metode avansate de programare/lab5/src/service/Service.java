package service;

import config.Config;
import domain.Friendship;
import domain.User;
import domain.validators.FriendshipValidator;
import domain.validators.UserValidator;
import domain.validators.ValidationException;
import repository.*;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Service {
    /**
     * Repository containing the Users with the ID of type Long
     * */

    private final Repository<Long, User> repositoryUser;
    private final Repository<Long, Friendship> repositoryFriendship;

    public Service() throws ValidationException {
        //this.repositoryUser = new Userfile(Config.getProperties().getProperty("Users"),new UserValidator());
        this.repositoryUser = new UserDBRepository("jdbc:postgresql://localhost:5432/academic","postgres","Deea2002", new UserValidator());
        //this.repositoryFriendship = new Friendshipfile(Config.getProperties().getProperty("Friendships"), new FriendshipValidator());
        this.repositoryFriendship = new FriendshipDBRepo("jdbc:postgresql://localhost:5432/academic","postgres","Deea2002", new FriendshipValidator());
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
    public void addUser(String FirstName,String LastName,String Email) throws IllegalArgumentException, ValidationException, SQLException {
        User newuser = new User(FirstName,LastName,Email);
        long id = this.repositoryUser.size();
        while(this.repositoryUser.findOne(id) != null)
            id++;
        newuser.setId(id);
        for(User user: this.repositoryUser.findAll()){
            if (user.getEmail().equals(newuser.getEmail())){
                newuser = user;
                break;
            }
        }

        User savedUser = this.repositoryUser.save(newuser);
        if(savedUser == null){
            System.out.println(newuser + " has been added.");
        }
        else System.out.println(newuser + " does already exist.");
    }

    /**
     * Searches a user in the repository.
     * @return   the user if it is found and not deleted, null otherwise
     * @param    ID   User's ID
     * @return   the user, if it's found, null otherwise
     * @throws   IllegalArgumentException if the data read is not the correct format
     */
    private User searchUser(long ID) throws IllegalArgumentException, SQLException {
        User searchedUser = this.repositoryUser.findOne(ID);
        if (searchedUser == null)
            System.out.println("Can not find the user with the ID " + ID);
        /*else if (searchedUser.isDeleted())
        {
            System.out.println("The user " + searchedUser + " has been deleted");
            return null;
        }*/
        return searchedUser;
    }

    /**
     * Marks a user as deleted if it exists. It is not removed from repository.
     * @param   ID   User's ID
     * @throws  IllegalArgumentException if the data read is not the correct format
     */
    public void removeUser(long ID) throws IllegalArgumentException, ValidationException, SQLException {
        User userToRemove = this.searchUser(ID);
        if (userToRemove != null)
        {
            for(long i = 1; i <= this.repositoryUser.size(); i++)
            {
                Friendship friends = searchFriendship(ID,i);
                if(friends != null)
                    this.repositoryFriendship.remove(friends.getId());
            }
            this.repositoryUser.remove(userToRemove.getId());
            System.out.println(userToRemove + " has been deleted.");
        }
        else System.out.println("The user you want to remove does not exists.");
    }

    /**
     * Searches for a friendship between 2 users.
     * @param    ID1   User1's ID
     * @param    ID2   User2's ID
     * @return   the friendship if it exists, null otherwise
     * @throws   IllegalArgumentException if the data read is not the correct format
     */
    public Friendship searchFriendship(long ID1, long ID2) throws IllegalArgumentException, ValidationException, SQLException {
        for(Friendship searchedFriendship: this.repositoryFriendship.findAll())
            if(searchedFriendship.getIdUser1() == ID1 || searchedFriendship.getIdUser2() == ID1)
                if(searchedFriendship.getIdUser1() == ID2 || searchedFriendship.getIdUser2() == ID2)
                    return searchedFriendship;
        return null;
    }

    /**
     * Creates a friendship between 2 users.
     * @param   ID   User1's ID
     * @param   ID2  User2's ID
     * @throws  IllegalArgumentException if the data read is not the correct format
     */
    public void addFriendship(long ID, long ID2) throws IllegalArgumentException, ValidationException, SQLException {
        //verify if both of the users exists
        User user1 = this.searchUser(ID);
        User user2 = this.searchUser(ID2);
        if (user1 != null && user2 != null) {
            if (searchFriendship(ID, ID2) == null) {
                Friendship newFriendship = new Friendship(ID, ID2, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
                long id = this.repositoryFriendship.size();
                while (repositoryFriendship.findOne(id) != null)
                    id++;
                newFriendship.setId(id);
                this.repositoryFriendship.save(newFriendship);
                System.out.println(newFriendship + " has been added succesfuly!");
            }
            else System.out.println("They are already friends!");
        }
    }

    /**
     * Delete a friendship
     * @param   ID   User's ID
     * @param   ID2  User2's ID
     * @throws  IllegalArgumentException if the data read is not the correct format
     */
    public void removeFriendship(long ID, long ID2) throws IllegalArgumentException, ValidationException, SQLException {
        User user1 = this.searchUser(ID);
        User user2 = this.searchUser(ID2);

        if (user1 != null && user2 != null)
        {
            Friendship friendship = searchFriendship(ID,ID2);
            if(friendship != null)
            {
                this.repositoryFriendship.remove(friendship.getId());
                System.out.println(user1 + " and " + user2 + " are not friends anymore.");
            }
            else System.out.println(user1 + " and " + user2 + " are not even friends.");
        }
    }

    /**
     * Search for a community starting with a specific user
     * @param   user   the User we start the search with
     */
    private void DFS(User user) throws SQLException {
        user.setCommunity(true); //mark that this user is already in the community
        for(Friendship friendship: this.repositoryFriendship.findAll())
        {
            if (friendship.getIdUser1() == user.getId())
            {
                User friend = searchUser(friendship.getIdUser2());
                if(friend != null && !friend.isInCommunity())
                    DFS(friend); //continue the search with a friend that's not in the community and is not deleted.
            }
            else if (friendship.getIdUser2() == user.getId())
            {
                User friend = searchUser(friendship.getIdUser1());
                if(friend != null && !friend.isInCommunity())
                    DFS(friend); //continue the search with a friend that's not in the community and is not deleted.
            }
        }
    }

    /**
     * Counts the number of communities. Starting from every user, it adds to the same community all its friends.
     * The process continues for all the users.
     * @return     the number of communities
     */
    public int numberOfComunities() throws SQLException {
        int communities = 0;
        for (User user : this.repositoryUser.findAll())
        {
            if (!user.isInCommunity())
            {
                ++communities;
                DFS(user);
            }
        }

        this.repositoryUser.findAll().forEach(user ->
        {
            user.setCommunity(false);
        });

        return communities;
    }

    /**
     * Prints all the users without their friends.
     * @return     does not return
     */
    public void listUsers() throws SQLException {
        this.repositoryUser.findAll().forEach(System.out::println);
    }

    /**
     * Prints all the users including their friends.
     * @return     does not return
     */
    public void listUsersWithFriends() throws SQLException {
        this.repositoryUser.findAll().forEach(user ->
        {
            System.out.println(user);
            try {
                this.repositoryFriendship.findAll().forEach(friendship ->
                {
                    if(friendship.getIdUser1() == user.getId())
                    {
                        User friend = null;
                        try {
                            friend = searchUser(friendship.getIdUser2());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("\t" + friend);
                    }
                    else if(friendship.getIdUser2() == user.getId()){
                        User friend = null;
                        try {
                            friend = searchUser(friendship.getIdUser1());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        System.out.println("\t" + friend);
                    }
                });
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

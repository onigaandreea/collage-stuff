package com.example.lab7.service;

import com.example.lab7.domain.FriendRequest;
import com.example.lab7.domain.Friendship;
import com.example.lab7.domain.Message;
import com.example.lab7.domain.User;
import com.example.lab7.domain.validator.FriendshipValidator;
import com.example.lab7.domain.validator.UserValidator;
import com.example.lab7.repository.*;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Set;

public class Service {
    /**Repository containing the Users with the ID of type Long* */
    private final Repository<Long, User> repositoryUser;
    private final Repository<Long, Friendship> repositoryFriendship;
    private final Repository<Long, FriendRequest> repositoryRequest;
    private final Repository<Long, Message> repositoryMessage;

    public Service() {
        this.repositoryUser = new UserDBRepo("jdbc:postgresql://localhost:5432/academic","postgres","Deea2002", new UserValidator());
        this.repositoryFriendship = new FriendshipDBRepo("jdbc:postgresql://localhost:5432/academic","postgres","Deea2002", new FriendshipValidator());
        this.repositoryRequest = new FriendRequestDBRepo("jdbc:postgresql://localhost:5432/academic","postgres","Deea2002");
        this.repositoryMessage = new MessageDBRepo("jdbc:postgresql://localhost:5432/academic","postgres","Deea2002");
    }

    /**
     * Adds a user to repository.
     * A new user is created based on the information provided as parameters
     * The user is added only if it doesn't exist already.
     * @param   FirstName  user's first name
     * @param   LastName   user's last name
     * @param   Email      user's email address
     * @param   password   user's password
     * @return  the user if it's successfully added, null otherwise
     */
    public User addUser(String FirstName,String LastName,String Email,String password) {
        User newuser = new User(FirstName,LastName,Email);
        newuser.setPassword(password);
        newuser.setId((long) 1);

        User user = this.searchUserByEmail(Email);
        if(user == null)
        {
            User savedUser = this.repositoryUser.save(newuser);
            if(savedUser == null){
                return newuser;
            }
        }
        return null;
    }

    /**
     * Searches a user in the repository.
     * @return   the user if it is found, null otherwise
     * @param    ID   User's ID
     * @throws   IllegalArgumentException if the data read is not the correct format
     */
    private User searchUser(long ID){
        return this.repositoryUser.findOne(ID);
    }

    /**
     * Searches a user in the repository.
     * @return   the user if it is found, null otherwise
     * @param    email user's email
     * @throws   IllegalArgumentException if the data read is not the correct format
     */
    public User searchUserByEmail(String email) {
        for(User user: repositoryUser.findAll())
            if(user.getEmail().equals(email))
                return user;
        return null;
    }

    /**
     * Searches users with a specific name that are not friends with the given user
     * @param user the user for whom we search users with the specific string in their name
     * @param input the string that the name should contain
     * @return a list of users that satisfy the condition(an empty list if there are none)
     * @throws IllegalArgumentException if the data read is not the correct format
     */
    public Iterable<User> searchUserByName(User user, String input) throws IllegalArgumentException{
        Iterable<User> users = repositoryUser.findAll();
        Set<User> foundUsers = new HashSet<>();
        for (User u : users) {
            if(u.getFirstName().contains(input) || u.getLastName().contains(input))
                foundUsers.add(u);
        }
        Set<User> result = new HashSet<>();
        for (User foundUser : foundUsers) {
            if(this.repositoryFriendship.findAll() == null)
                result.add(foundUser);
            else
                for(Friendship friendship: this.repositoryFriendship.findAll()) {
                    if (!((friendship.getIdUser1() == foundUser.getId() && friendship.getIdUser2() == user.getId()) ||
                            (friendship.getIdUser2() == foundUser.getId() && friendship.getIdUser1() == user.getId()))) {
                        result.add(foundUser);
                    }
                }
        }
        return result;
    }

    /**
     * Removes a user from the repository
     * @param   ID   User's ID
     * @throws  IllegalArgumentException if the data read is not the correct format
     */
    public User removeUser(long ID){
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
            return userToRemove;
        }
        return null;
    }

    /**
     * Searches for all the user's friends
     * @param user the user for whom we search for friends
     * @return a list with the user's friends (an empty list if the user doesn't have friends)
     * @throws IllegalArgumentException if the data read is not the correct format
     */
    public Iterable<User> searchFriends(User user) throws IllegalArgumentException{
        Set<User> friends = new HashSet<>();
        Iterable<Friendship> friendships = repositoryFriendship.findAll();
        friendships.forEach(friendship -> {
            if (friendship.getIdUser1() == user.getId()) {
                friends.add(repositoryUser.findOne(friendship.getIdUser2()));
            } else if (friendship.getIdUser2() == user.getId()) {
                friends.add(repositoryUser.findOne(friendship.getIdUser1()));
            }
        });
        return friends;
    }

    /**
     * Makes a list with all the users
     * @return a list with all the users
     */
    public Iterable<User> getAll() {
        return this.repositoryUser.findAll();
    }

    /**
     * Adds a new request to the repository
     * @param id_user   the ID of the user who sends the request
     * @param id_requestedFriend  the ID of the user to whom the request is sent
     * @throws IllegalArgumentException if the data read is not the correct format
     */
    public void addRequest(Long id_user, Long id_requestedFriend) throws IllegalArgumentException {
        if(this.findRequest(id_user, id_requestedFriend) == null) {
            FriendRequest request = new FriendRequest(id_user, id_requestedFriend, LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES));
            request.setStatus("waiting");
            request.setId((long) request.hashCode());
            repositoryRequest.save(request);
        }
    }

    /**
     * Searches for a request
     * @param id_user  the ID of the user who sends the request
     * @param id_requestedFriend  the ID of the user to whom the request is sent
     * @return the request, if it exists already, null otherwise
     */
    public FriendRequest findRequest(Long id_user, Long id_requestedFriend) {
        for (FriendRequest request : repositoryRequest.findAll()) {
            if(request.getIdUser1() == id_user && request.getIdUser2() == id_requestedFriend)
                return request;
        }
        return null;
    }

    /**
     * Changes the status of a request
     * @param id_request the ID of the request
     * @param status the new status for the request
     */
    public void changeRequestStatus(Long id_request, String status) {
        for(FriendRequest request: repositoryRequest.findAll())
            if(request.getId().equals(id_request)){
                request.setStatus(status);
                repositoryRequest.update(request);
            }
    }

    /**
     * Search the requests from other users for a user
     * @param user the user for whom we search for requests
     * @return a list with the requests for the user
     */
    public Iterable<FriendRequest> searchRequestsForUser(User user) {
        Set<FriendRequest> requests = new HashSet<>();
        this.repositoryRequest.findAll().forEach(request -> {
            if(request.getIdUser2() == user.getId()) {
                requests.add(request);
            }
        });
        return requests;
    }

    public Iterable<FriendRequest> searchRequestsSentByUser(User user) {
        Set<FriendRequest> requests = new HashSet<>();
        this.repositoryRequest.findAll().forEach(request -> {
            if(request.getIdUser1() == user.getId()) {
                requests.add(request);
            }
        });
        return requests;
    }

    public FriendRequest removeRequest(Long id)
    {
        FriendRequest request = this.repositoryRequest.findOne(id);
        if(request != null)
           this.repositoryRequest.remove(id);
        return request;
    }

    /**
     * Searches for a friendship between 2 users.
     * @param    ID1   User1's ID
     * @param    ID2   User2's ID
     * @return   the friendship if it exists, null otherwise
     * @throws   IllegalArgumentException if the data read is not the correct format
     */
    public Friendship searchFriendship(long ID1, long ID2){
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
    public void addFriendship(long ID, long ID2) {
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
    public void removeFriendship(long ID, long ID2) {
        User user1 = this.searchUser(ID);
        User user2 = this.searchUser(ID2);

        if (user1 != null && user2 != null)
        {
            Friendship friendship = searchFriendship(ID,ID2);
            if(friendship != null)
            {
                this.repositoryFriendship.remove(friendship.getId());
                if(this.findRequest(ID,ID2) != null)
                    this.repositoryRequest.remove(this.findRequest(ID,ID2).getId());
                else if (this.findRequest(ID2,ID) != null)
                    this.repositoryRequest.remove(this.findRequest(ID2,ID).getId());
            }
        }
    }

    public void addMessage(Long id_from, Long id_to, String text) throws IllegalArgumentException {
        Message message = new Message(id_from,id_to, LocalTime.now().truncatedTo(ChronoUnit.MINUTES),text);
        repositoryMessage.save(message);
    }

    public Iterable<Message> messagesForTwoUsers(User user, long ID2){
        Set<Message> messages = new HashSet<>();
        this.repositoryMessage.findAll().forEach(message -> {
            if(message.getFrom() == user.getId() && message.getTo() == ID2 || message.getTo() == user.getId() && message.getFrom() == ID2) {
                messages.add(message);
            }
        });
        return messages;
    }
}

package ui;

import domain.validators.ValidationException;
import service.Service;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Console {
    final private Scanner in = new Scanner(System.in);
    private final Service service;

    public void addUserUI() throws InputMismatchException, IllegalArgumentException, ValidationException
    {
        System.out.print("First Name: ");
        String firstName = in.next();
        System.out.print("Last name: ");
        String lastName = in.next();
        System.out.print("Email: ");
        String email = in.next();
        service.addUser(firstName, lastName, email);
    }

    public void removeUserUI() throws InputMismatchException, IllegalArgumentException, ValidationException {
        System.out.print("ID User: ");
        int id = in.nextInt();
        service.removeUser(id);
    }

    public void addFriendToUserUI() throws ValidationException {
        System.out.print("ID User1: ");
        int id = in.nextInt();
        System.out.print("ID User2: ");
        int id2 = in.nextInt();
        service.addFriendship(id, id2);
    }

    public void removeFriendFromUserUI() throws ValidationException {
        System.out.print("ID User1: ");
        int id = in.nextInt();
        System.out.print("ID User2: ");
        int id2 = in.nextInt();
        service.removeFriendship(id, id2);
    }

    public Console(Service service)
    {
        this.service = service;
    }

    public void start()
    {
        boolean finished = false;
        while (!finished)
        {
            System.out.print("Menu: \n\t1 - add user\n\t2 - remove user\n\t3 - add friend\n\t" +
                    "4 - remove friend\n\t5 - number of comunities\n\t6 - list users\n\t" +
                    "7 - list users with friends\n\t8 - exit\n>>> ");
            try
            {
                int option = in.nextInt();
                switch (option)
                {
                    case 1:
                    {
                        addUserUI();
                        break;
                    }
                    case 2:
                    {
                        removeUserUI();
                        break;
                    }
                    case 3:
                    {
                        addFriendToUserUI();
                        break;
                    }
                    case 4:
                    {
                        removeFriendFromUserUI();
                        break;
                    }
                    case 5:
                    {
                        System.out.println("Number of communities: " + service.numberOfComunities());
                        break;
                    }
                    case 6:
                    {
                        service.listUsers();
                        break;
                    }
                    case 7:
                    {
                        service.listUsersWithFriends();
                        break;
                    }
                    case 8:
                    {
                        finished = true;
                    }
                    default:
                        break;
                }
            }
            catch (InputMismatchException exception)
            {
                System.err.println("Not an integer");
                if (in.next().isEmpty())
                    break;
            }
            catch (IllegalArgumentException | ValidationException exception) {
                System.err.println(exception);
            }
        }
        in.close();
    }
}

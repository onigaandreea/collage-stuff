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

    public void removeUserUI() throws InputMismatchException, IllegalArgumentException
    {
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

    public void addData() throws ValidationException {
        this.service.addUser("Andreea","Oniga", "onan@yahoo.com");
        this.service.addUser("Andreea","Negoitescu", "nean@yahoo.com");
        this.service.addUser("Sebastian","Marginean", "mase@yahoo.com");
        this.service.addUser("Denisa","Oltean", "olde@yahoo.com");
        this.service.addUser("Raluca","Barzaune", "bara@yahoo.com");
    }
    public void start()
    {
        boolean finished = false;
        try
        {
            addData();
        }
        catch (ValidationException e){
            e.printStackTrace();
        }
        while (!finished)
        {
            System.out.print("Menu: \n\t1 - add user\n\t2 - remove user\n\t3 - add friend\n\t4 - remove friend\n\t5 - number of comunities\n\t6 - list users with friends\n\t7 - exit\n>>> ");
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
                        service.listUsersWithFriends();
                        break;
                    }
                    case 7:
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

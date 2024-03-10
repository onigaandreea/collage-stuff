import domain.validators.ValidationException;
import service.Service;
import ui.Console;

public class Main {
    public static void main(String[] args) throws ValidationException {

        Service service = new Service();
        Console console = new Console(service);
        console.start();
    }
}
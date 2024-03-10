import domain.validators.ValidationException;
import service.Service;
import ui.Console;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ValidationException, SQLException {

        Service service = new Service();
        Console console = new Console(service);
        console.start();
    }
}
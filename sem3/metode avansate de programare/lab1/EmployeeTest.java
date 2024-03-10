public class EmployeeTest {

    public static void main(String args[]) {
        EmployeeExemple empOne = new EmployeeExemple("James Smith");
        EmployeeExemple empTow = new EmployeeExemple("Mary Anne");

        empOne.empAge(26);
        empOne.empDesignation("Senior Software Engineer");
        empOne.empSalary(1000);
        empOne.printEmployee();

        empTow.empAge(21);
        empTow.empDesignation("Software Engineer");
        empTow.empSalary(500);
        empTow.printEmployee();
    }
}

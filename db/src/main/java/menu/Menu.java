package menu;

import dto.Employee;
import dto.InsertEmployeeRequest;
import dto.InsertEmployeeResponse;
import service.DBService;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner input = new Scanner(System.in);
    DBService dbService = new DBService(null);


    public String display() throws Exception {

        while(true) {

            System.out.println("-- Main Menu--");
            System.out.println(
                    "Select an option: \n" +
                            "  1) Insert employee\n" +
                            "  2) Search employee\n" +
                            "  3) Sair\n "
            );
            int selection = input.nextInt();
            input.nextLine();

            switch (selection) {
                case 1:

                    InsertEmployeeResponse toSave = readNewEmployee();
                    InsertEmployeeRequest request = new InsertEmployeeRequest();
                    request.setFirstName(toSave.getFirstName());

                    InsertEmployeeResponse employee = dbService.insertEmployee(request);

                    System.out.println(employee);

                    break;
                case 2:

                    break;
                case 3:
                    System.exit(0);
                    break ;
                default:
                    System.out.println("Invalid action.");
                    break;
            }
        }
    }

    private InsertEmployeeResponse readNewEmployee() {

        System.out.println("-- New Employee Menu--");
        System.out.println("Insert date of birth as 'YYYY-MM-dd'");
        String dob = input.next();

        System.out.println("Insert first name");
        String firstName = input.next();

        System.out.println("Insert last name");
        String lastName = input.next();

        System.out.println("Insert gender as 'M' or 'F");
        String gender = input.next();

        InsertEmployeeRequest toSave = new InsertEmployeeRequest();
        toSave.setDob(dob);
        toSave.setFirstName(firstName);
        toSave.setLastName(lastName);
        toSave.setGender(gender);

        InsertEmployeeResponse response = new InsertEmployeeResponse();
        response.setFirstName(toSave.getFirstName());

        return response;
    }
}

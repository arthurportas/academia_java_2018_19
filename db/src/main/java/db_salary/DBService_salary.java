package db_salary;
import db.DB;
import salary.Employee_salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBService_salary {

    public List myList() throws ClassNotFoundException, SQLException {

        Statement statement = DB.INSTANCE().connection().createStatement();

        String salary="select * from salaries where salary>=1000;";

        ResultSet ListresultSet = statement.executeQuery(salary);
        List salaryList = new ArrayList();


        while (ListresultSet.next()){
             //List salaryList = new ArrayList();
             salaryList.add(ListresultSet.getInt("emp_no"));
             salaryList.add(ListresultSet.getInt("salary"));
            salaryList.add(ListresultSet.getString("from_date"));
            salaryList.add(ListresultSet.getString("to_date"));
             //return salaryList;

        }
        System.out.println(salaryList);
        return salaryList;
    }

   public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DBService_salary teste = new DBService_salary();
        teste.myList();
        //System.out.println(teste.myList());
    }
}

package DataHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InsertCustomer{

    public static void main(String[] args) {

    //database URL
        final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";

        Connection connection = null;
        PreparedStatement pstat = null;
        String firstname = "Michael";
        String lastname = "Stevenson";
        int i=0;

        try {

//establish connection to database
connection = DriverManager.getConnection(DATABASE_URL, "root","Knockbeg11");

//create Prepared Statement for inserting into table


pstat = connection.prepareStatement( "INSERT INTO Customer (FirstName, LastName) VALUES (?,?)");
pstat.setString(1,firstname);
pstat.setString(2,lastname);

i = pstat.executeUpdate();
System.out.println(i + " record successfully added to the database");

}


catch(SQLException sqlException ) {
sqlException . printStackTrace () ;
}
finally {
try{
pstat. close () ;
connection. close () ;
}
catch ( Exception exception ){
exception . printStackTrace () ;
}
}
} // end main
} // end class
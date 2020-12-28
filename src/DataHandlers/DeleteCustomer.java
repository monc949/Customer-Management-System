package DataHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteCustomer {

	public static void main(String[] args) {

		// database URL

		final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";
		
			Connection connection = null;
            PreparedStatement pstat = null;	
            int i = 0;
            int authorID = 1;		
			try{
				
				// establish connection to database
				connection = DriverManager.getConnection(DATABASE_URL, "root" , "Knockbeg11" );

				// create Statement for deleting from table
                pstat = connection.prepareStatement("Delete FROM Customer WHERE CustomerID = ?");	
                pstat.setInt(1, authorID);		
                
                //Delete data in database
				i = pstat.executeUpdate();
				System.out.println(i + " record successfully removed from the database. ");
				
			 }
			catch(SQLException sqlException ) {
				sqlException.printStackTrace();
			 }
			finally{
				try{
					pstat.close();
					connection.close();
				}
				catch ( Exception exception ){
					exception.printStackTrace();
				}
			}
		} // end main
} // end class
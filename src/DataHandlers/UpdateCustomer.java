package DataHandlers;

//Update an Author in the Authors table.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCustomer {

	public static void main(String[] args) {
		// database URL
		final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";
		
			String firstname="Lisa";
			String lastname="Brennan";
			Connection connection = null;
            PreparedStatement pstat = null;
            int i = 0;
			
			try{
				// establish connection to database
				connection = DriverManager.getConnection(
                DATABASE_URL, "root", "Knockbeg11" );
                
				// create Statement for updating table
                pstat = connection.prepareStatement("Update Authors SET lastName = ? Where firstName = ?");
                pstat.setString(1, lastname);
                pstat.setString(2, firstname);
																																		//TODO:
                //Update data in database
                i = pstat.executeUpdate();
                System.out.println(i + " record successfully updated in the database");
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
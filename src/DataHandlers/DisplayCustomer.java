package DataHandlers;

//Displaying the contents of the Authors table.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class DisplayCustomer {
	
		// database URL
		static final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";
	
	public static void main( String args[] ){
		
		Connection connection = null;
        PreparedStatement pstat = null;
		ResultSet resultSet = null;
try{
			
			// establish connection to database
			connection = DriverManager.getConnection(
			DATABASE_URL, "root", "Knockbeg11" );
			
			// create Statement for querying table
			pstat = connection.prepareStatement("SELECT * From Customer");
			
			// query database
			resultSet = pstat.executeQuery(
			"SELECT * From Customer" );
			
			// process query results
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			System.out.println( "Customer Table of CMS Database:\n" );
			
			 for ( int i = 1; i <= numberOfColumns; i++ )
			 System.out.print(metaData.getColumnName( i ) + "\t");
			 System.out.println();
			 
			 while(resultSet.next() ){
					for ( int i = 1; i <= numberOfColumns; i++ )
					System.out.print( resultSet.getObject( i ) + "\t\t");
					System.out.println();
					 }
				 }
				catch(SQLException sqlException ) {
					sqlException.printStackTrace();
				 }
				finally{
					try{
						resultSet.close();
						pstat.close();
						connection.close();
					}
					catch ( Exception exception ){
						exception.printStackTrace();
					}
				}
			} // end main
		} // end class
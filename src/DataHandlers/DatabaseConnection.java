package DataHandlers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

        public void connect() {
            // database URL
                final String DATABASE_URL = "jdbc:mysql://localhost/customer_management_system";
        
            //variables
                Connection connection = null;

            //establish connection to database
                try{
                    connection = DriverManager.getConnection(
                    DATABASE_URL, "root", "Knockbeg11" );
                }


                catch(SQLException sqlException ) {
                    sqlException.printStackTrace();
                }
                finally{
					try{
						connection.close();
					}
					catch ( Exception exception ){
						exception.printStackTrace();
					}
				}
            } 
        }



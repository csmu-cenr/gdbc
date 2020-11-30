import java.sql.*;
import java.util.ArrayList;

class FMPJDBCTest
{
	public static void main(String[ ] args)
	{
		
		/*
			
			create an .env file first
			cp example.env .env
			put in the credentials required
			then `source .env` 
			then `make`
		
		*/
		String host = System.getenv("FILEMAKER_HOST") ;
		String database = System.getenv("FILEMAKER_DATABASE") ;
		String username = System.getenv("FILEMAKER_USERNAME") ;		
		String password = System.getenv("FILEMAKER_PASSWORD") ;
		String query = System.getenv("FILEMAKER_SQL_TEST") ;
		String socketTimeout = System.getenv("FILEMAKER_SOCKET_TIMEOUT") ;

		// register the JDBC client driver
		try {
			Driver d = (Driver)Class.forName("com.filemaker.jdbc.Driver").newInstance();
		} catch(Exception e) {
			System.out.println(e);
		}
		// establish a connection to FileMaker
		Connection con;
		try {

			String url = "jdbc:filemaker://" + host + "/" + database + "?SocketTimeout=" + socketTimeout ;
			System.out.println( url ) ;

			con = DriverManager.getConnection(url, username, password);
		
			// get connection warnings
			SQLWarning warning = null;
			try {
				warning = con.getWarnings();
				if (warning == null) {
					System.out.println("No warnings");
					return;
			}
			
			while (warning != null) {
				System.out.println("Warning: "+warning);
				warning = warning.getNextWarning();
				}
			} catch (Exception e) {
				System.out.println(e);
			}

			/* run the test */
			try {
				Statement statement = null ;
				statement = con.createStatement() ;
				System.out.println(query) ;
				ResultSet rs = statement.executeQuery(query) ;

				// Get all the column names
				ResultSetMetaData rsm = rs.getMetaData();
				ArrayList<String> columnNames = new ArrayList<String>();
				String rowDelimiter = "\t" ;

				String headers = "" ;

				for(int i=1; i<=rsm.getColumnCount(); i++){
					headers += rsm.getColumnName(i) + rowDelimiter ;
					columnNames.add(rsm.getColumnName(i));
				}
				System.out.println(headers);
		      	while (rs.next()) {
					String output = "";

					for (String columnName : columnNames) {
						output += rs.getString(columnName) + rowDelimiter;
					}
					System.out.println(output);
				}

			} catch (Exception e) {
				System.out.println(e);
			}


		} catch(Exception e) {
			System.out.println(e);
		}
	}
}
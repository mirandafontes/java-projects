package inf008.persistencia.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class GenericDAOSQL {
	
	private static final String URI = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USER = "postgres";	
	private static final String PWD = "";
	
	private Connection conn = null;
	
	protected Connection getConnection() throws SQLException{
		if(this.conn != null)
			return this.conn;
		DriverManager.registerDriver(new org.postgresql.Driver());
		this.conn = DriverManager.getConnection(GenericDAOSQL.URI,
		 								        GenericDAOSQL.USER,
										        GenericDAOSQL.PWD);
		return this.conn;
	}
	
	
}

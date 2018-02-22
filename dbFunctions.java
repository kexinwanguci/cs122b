package Test;
import Test.movie;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
class dbFunctions {
	private Connection connection;
	static String last_path;
	static String last_user_name;
	static String last_pass;
	public void make_connection(String path, String user_name, String pass) throws Exception 
	{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		connection = DriverManager.getConnection(path, user_name, pass);
		last_path = path;
	    last_user_name = user_name;
	    last_pass = pass;
	}
//	
//	public ResultSet movie_batch_insert(ArrayList<movie> values) throws SQLException
//	{
//		connection.setAutoCommit(false);
//		String stmt = "INSERT INTO movies (title,year,director) VALUES (?,?,?)";
//		PreparedStatement ps = connection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
//		for(movie mov : values)
//		{
//			ps.setString(1, mov.getT());
//			ps.setInt(2, mov.getYear());
//			ps.setString(3, mov.getDirector());
//			ps.addBatch();
//			
//		}
//		ps.executeBatch();
//		connection.commit();
//		connection.setAutoCommit(true);
//		return ps.getGeneratedKeys();
//	}
	public ResultSet star_batch_insert(ArrayList<Star> values) throws SQLException
	{
		connection.setAutoCommit(false);
		String stmt = "INSERT INTO stars (first_name,last_name,dob) VALUES (?,?,?)";
		PreparedStatement ps = connection.prepareStatement(stmt, Statement.RETURN_GENERATED_KEYS);
		for(Star star : values)
		{
			ps.setString(1, star.getFirst_name());
			ps.setString(2, star.getLast_name());
			ps.setObject(3, star.getDob());
			ps.addBatch();
			
		}
		ps.executeBatch();
		connection.commit();
		connection.setAutoCommit(true);
		return ps.getGeneratedKeys();
	}
	
	public void starid_movieid_batch(LinkedHashMap<Integer, Integer>starid_movieid) throws SQLException 
	{
		String query = "INSERT INTO stars_in_movies (star_id, movies_id) VALUES (?,?)";
		PreparedStatement ps = connection.prepareStatement(query);
		
		for(Map.Entry<Integer, Integer> c : starid_movieid.entrySet())
		{
			ps.setInt(1, c.getKey());
			ps.setInt(2, c.getValue());
			ps.addBatch();
		}
		ps.executeBatch();

		
	}
	
	public void gim_batch_insert(String final_genres_in_movies_query) throws SQLException
	{
		connection.setAutoCommit(false);
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(final_genres_in_movies_query);
		connection.commit();
		connection.setAutoCommit(true);
		
	}
	
	public HashSet<String> getStarFromMovieId(Integer id) throws SQLException 
	{
		String query = "select CONCAT(first_name, ' ', last_name) as name FROM stars INNER JOIN stars_in_movies ON stars.id = stars_in_movies.star_id"
				+ 	" WHERE stars_in_movies.movies_id =	" + id;
		PreparedStatement ps = connection.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		HashSet<String> tempOutput = new HashSet<String>();
		while(rs.next())
		{
			tempOutput.add(rs.getString(1));
		}
		rs.close();
		ps.close();
		return tempOutput;
	}
	public void close() 
	{
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Warning:The database connection was not closed properly.");
		}
	}

}

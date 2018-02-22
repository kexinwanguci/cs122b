package Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

class UpdateGenres {
	static String sqlStr = "jdbc:mysql://localhost:3306/122b";  
    static String rootName = "root"; 
    static String rootPwd = "0000";  
    static int maxid = 0;
    private static String movieid = null;
    private static LinkedHashMap<Integer, ArrayList<Integer>>Movie_Genreid = new LinkedHashMap<Integer, ArrayList<Integer>>();
  
    public static void writeToMysql(movie m) {  
        System.out.println(m);  
       
        try {  
           
            Class.forName("com.mysql.jdbc.Driver");  
        } catch (ClassNotFoundException e) {  
            System.out.println("Not found！");  
            e.printStackTrace();  
        }  
          
        Statement st = null;  
         
        Connection con  =null;  
        try {  
           
            con = DriverManager.getConnection(sqlStr, rootName,rootPwd);  
             
            String mtitle= m.getT();  
            st =  con.createStatement(); 
            String sql3 = "SELECT id from movies WHERE title = \""+mtitle+"\"; ";
            ResultSet rs = st.executeQuery(sql3);
            while(rs.next()) {
            	if(rs.getString(1)!=null)
            	{
            		movieid = rs.getString(1);
            	}
            }
            
            String sql = "select max(id) from genres";
     
            
            ResultSet result = st.executeQuery(sql);  
            while(result.next()) {
            	if (result.getString(1) == null)
            		maxid = 0001;
            	else 
            		maxid = Integer.parseInt(result.getString(1))+1;
            }
            for (String x : m.getCat())
            {
            	System.out.println(x);
            	String sql2 = "insert ignore into genres VALUES( \""+maxid+"\",\""+x+"\");";
            	String sql4 = "INSERT INTO genres_in_movies VALUES(\""+movieid+"\",\""+maxid+"\");";
            	maxid++;
            	st.executeUpdate(sql2);
            	st.executeUpdate(sql4);
            }
            
            

               
           
        } catch (SQLException e) {  
            e.printStackTrace();  
        }finally{  
            try {  
                st.close();  
                con.close();  
            } catch (SQLException e) {  
                e.printStackTrace();  
            }  
        }  
    }  
//   



public static void main(String[] args) {  
	SAXParserFactory factory = SAXParserFactory.newInstance();  
      
    try {  
        SAXParser parser = factory.newSAXParser();  
          
        MovieParserHandler handler = new MovieParserHandler();  
        parser.parse("mains123.xml", handler);  
        System.out.println("TOTAL" + handler.getMovieList().size()  
                + "movies------");  
        for (movie m : handler.getMovieList()) { 
        		writeToMysql(m);
            System.out.println(m.getT());  
            System.out.println("CATEGORY");
            for(String x : m.getCat())
            {
            	System.out.print("	"+x);
            }
            System.out.println("----finish----");  
        }  
    } catch (ParserConfigurationException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    } catch (SAXException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    } catch (IOException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    }  
} 
}

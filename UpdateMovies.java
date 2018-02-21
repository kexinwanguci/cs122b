package Test;
import Test.movie;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.SAXParser;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Date;


  

import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  

import org.xml.sax.SAXException;  
import org.xml.sax.SAXParseException;  

 
import Test.MovieParserHandler;  

class UpdateMovies {
	static String sqlStr = "jdbc:mysql://localhost:3306/122b";  
    static String rootName = "root"; 
    static String rootPwd = "0000";  
    static String maxid = null;
  
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
            String myear = m.getYear();
            String mdirector =m.getDirector();
            String sql = "select max(id) from movies";
           // String sql = "insert into movies(id,title,year,director) values(\""+title+"\",\""+year+"\",\"+director+\")";  
            System.out.println(sql);  
            st =  con.createStatement(); 
            ResultSet result = st.executeQuery(sql);  
            while(result.next()) {
            maxid = (result.getString(1));
//            char max = (char)(maxid.charAt(8));
        
            maxid = maxid.substring(0,3)+(Integer.parseInt(maxid.substring(2))+1);
            }
           System.out.println((String)maxid);
            String insert = "insert into movies(id,title,year,director) "
            		+ "values(\""+maxid+"\",\""+mtitle+"\",\""+myear+"\",\""+mdirector+"\") ;";
//            		+ "WHERE NOT EXISTS (select * from movies WHERE title = \""+mtitle+"\" AND director = \""+mdirector+"\");";
//            st =  con.createStatement();
            st.executeUpdate(insert);
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
            System.out.println(m.getDirector());    
            System.out.println(m.getYear());  
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


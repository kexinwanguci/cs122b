package Test;


import java.io.IOException;  

import javax.xml.parsers.ParserConfigurationException;  
import javax.xml.parsers.SAXParser;  
import javax.xml.parsers.SAXParserFactory;  

import org.xml.sax.SAXException;  
import org.xml.sax.SAXParseException;  

import Test.movie;  
import Test.MovieParserHandler;  

public class MovieMain {  

  /** 
   * @param args 
   */  
  public static void main(String[] args) {  
      //获取一个SAXParserFactory的实例  
      SAXParserFactory factory = SAXParserFactory.newInstance();  
      //通过factory获取SAXParser实例  
      try {  
          SAXParser parser = factory.newSAXParser();  
          //创建SAXParserHandler对象  
          MovieParserHandler handler = new MovieParserHandler();  
          parser.parse("mains123.xml", handler);  
          System.out.println("TOTAL" + handler.getMovieList().size()  
                  + "movies------");  
          for (movie m : handler.getMovieList()) {  
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
package Test;

import java.util.ArrayList;  

import org.xml.sax.Attributes;  
import org.xml.sax.SAXException;  
import org.xml.sax.helpers.DefaultHandler;  
  
import Test.movie;  
  
  
public class MovieParserHandler extends DefaultHandler {  
    String value = null;  
    movie m = null;  
    private ArrayList<movie> movieList = new ArrayList<movie>();  
    public ArrayList<movie> getMovieList() {  
        return movieList;  
    }  
    private String elementName="@#";
    private String directorname = null;
  
    int movieIndex = 0;  
  
    @Override  
    public void startDocument() throws SAXException {  
        // TODO Auto-generated method stub  
        super.startDocument();  
        System.out.println("SAX~Start...");  
    }  
  
    @Override  
    public void endDocument() throws SAXException {  
        // TODO Auto-generated method stub  
        super.endDocument();  
        System.out.println("SAX~Over!!!");  
    }  
      
 
    @Override  
    public void startElement(String uri, String localName, String qName,  
            Attributes attributes) throws SAXException {
    		String directorname = null;  
        super.startElement(uri, localName, qName, attributes); 
        if(qName.equals("dirname"))
        	{
        	directorname = qName;
        	elementName = qName;
        	};
        if (qName.equals("film")) {  
        		System.out.println("Here!!!!!!");
            movieIndex++;    
        
            m = new movie();   
            System.out.println("======================开始遍历第"+movieIndex+"本书的内容================="); 
 
        }  
        else if (qName.equals("t") ) {  
        	elementName=qName;;  
        } 
        else if (qName.equals("year") ) {  
        	elementName=qName;;  
        }
        else if (qName.equals("dirn") ) {  
        
        		elementName = qName;
        }
        else if (qName.equals("cat") ) {  
        	elementName=qName;;  
        }
    }  
    
    @Override  
    public void endElement(String uri, String localName, String qName)  
            throws SAXException {    
        super.endElement(uri, localName, qName);   
        if (qName.equals("film")) {  
            movieList.add(m);  
            m = null;  
            System.out.println("\n======================================");  
        }  
        else if (qName.equals("t")) {  
        	
            m.setT(value);  
        }  
        else if (qName.equals("dirn")) { 
            m.setDirector(directorname);  
        }  
        else if (qName.equals("dirname")) { 
            directorname = value;  
        }
        else if (qName.equals("year")) { 
            m.setYear(value);  
        }  
        else if (qName.equals("cat")) {
            m.setCat(value);  
        }  
        else
        {
        	elementName = "@#";
        }
   
    }  
      
      
    @Override  
    public void characters(char[] ch, int start, int length)
            throws SAXException {
    		super.characters(ch, start, length);  
        value = new String(ch, start, length);
        if(!elementName.equals("@#")&&!value.trim().equals("")&&!value.trim().equals("R")) {
        		
        	 	
        		System.out.println(elementName+":"+value);}
    }  
      
}

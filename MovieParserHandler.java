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
    private ArrayList<String> cats = new ArrayList<String>();
    private String elementName="@#";
    private String directorname = null;
    private String defaultyear = null;
  
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
            movieIndex++;    
        
            m = new movie();   
            System.out.println("======================================="); 
 
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
        else if (qName.equals("dirstart")) {
        		defaultyear = value.substring(1);
        		
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
        	boolean b = false;
        	int y =0;
        	try {
        		 y=Integer.parseInt(value); 
        		b = true;}
        	catch(Exception ex){
        		System.out.println(ex.getMessage());
        		
        	}
        	if (b == true&& y >1800&&y<2019){
        		m.setYear(value);
        	}
        	else
        	{
        		System.out.println("defaultyear");
        		m.setYear(defaultyear);
        		System.out.println("Lack of year information. Using default year!");
        	}
      
        	
        }  
        else if (qName.equals("cat")) {
            m.setCat(value);  
            System.out.println("Category"+value);
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

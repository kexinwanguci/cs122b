package Test;

import java.util.ArrayList;

public class movie {   
    private String title;  
    private String director;  
    private String year;  
    private ArrayList<String> cats = new ArrayList<String>();    
    public String getT() {  
        return title;  
    }  
    public void setT(String title) {  
        this.title = title;  
    }  
    public String getDirector() {  
        return director;  
    }  
    public void setDirector(String director) {  
        this.director = director;  
    }  
  
    
    public String getYear() {  
        return year;  
    }  
    public void setYear(String year) {  
        this.year = year;  
    }  
    public ArrayList<String> getCat() {  
        return cats;  
    }  
    public void setCat(String cat) {  
        this.cats.add(cat);  
    }  
 
      
      
} 
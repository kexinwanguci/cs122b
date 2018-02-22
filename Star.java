package Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.HashMap;

class Star {
	private Integer id;
	private String dob;
	private String first_name;
	private String last_name;
	private HashMap<Integer, String> movies;
	public Star(Integer id,  String first_name, String last_name, String dob, HashMap<Integer, String> movies)
	{
		super();
		this.id = id;
		this.dob = dob;
		this.first_name = first_name;
		this.last_name = last_name;
		this.movies = movies;
	}
	
	public Star()
	{
		this.dob = null;
		this.first_name = "";
		this.last_name = "";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDob() {
		return dob;
	}
	
	public static boolean isValidFormat(String format, String value) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(value);
            if (!value.equals(sdf.format(date))) {
                date = null;
            }
        } catch (ParseException ex) {
            ex.printStackTrace();
            return false;
        }
        return date != null;
    }
	
	public void setDob(String dob) {
		this.dob = (dob== null ? "0000/00/00" : dob);
		if(!isValidFormat("yyyy/MM/dd", this.dob) && !isValidFormat("yyyy-MM-dd", this.dob))
		{
			this.dob = "0000/00/00";	
			
		}
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name == null?"":first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name == null ? "" : last_name;
	}
}

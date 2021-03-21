package Database;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class to represent a PlayStation game.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class Game {
	private String name; 
	private Calendar released; 
	private Game next; 
	private int totalTrophies;

    public Game() {}

    public Game(String name, Calendar released, int totalTrophies) {
    	this.name = name;
    	this.released = released;
    	this.totalTrophies = totalTrophies;
    	this.next = null;
    }

    public String toString() {
    	//GregorianCalendar c = (GregorianCalendar) this.released;
    	
    	
    	DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
    	formatter.setTimeZone(released.getTimeZone());
    	//formatter.format(new Date()).replaceAll("\\.", "");
		return "\""+ this.name +"\""+ ", released on: " + formatter.format(released.getTime()) ;
    }
    
    

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		/*
		if (next == null) {
			if (other.next != null)
				return false;
		} else if (!next.equals(other.next))
			return false;
			*/
		if (released == null) {
			if (other.released != null)
				return false;
		} else if (!released.equals(other.released))
			return false;
		if (totalTrophies != other.totalTrophies)
			return false;
		return true;
	}

	public Calendar getReleased() {
		// TODO Auto-generated method stub
		return this.released;
	}

	public int getTotalTrophies() {
		// TODO Auto-generated method stub
		return this.totalTrophies;
	}

	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	public Game getNext() {
		return next;
	}

	public void setNext(Game next) {
		this.next = next;
	}
	
}

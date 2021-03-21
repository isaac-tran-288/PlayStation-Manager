package Database;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

/**
 * Class to represent a PlayStation user.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class User {
	private String username; 
	private int level; 
	private double key; 
	private ArrayList<Trophy> trophies; 
	private GameList games; 
	private Calendar dob; 
	private User left; 
	private User right;

	public User(String username, Calendar dob, int level) {
		this.username = username;
		this.dob = dob;
		this.level = level;
		this.trophies = null;
		this.games = null;
		this.key = this.getKey();
		this.left = null;
		this.right = null;
    }

    /**
     * DO NOT MODIFY THIS METHOD
     * This method uses the username and level to create a unique key.
     * As we don't want the username's hash to increase the level, it's first converted
     * to a floating point, then added to the level.
     * @return the unique key
     */
    public double calculateKey() {
        int hash = Math.abs(username.hashCode());
        // Calculate number of zeros we need
        int length = (int)(Math.log10(hash) + 1);
        // Make a divisor 10^x
        double divisor = Math.pow(10, length);
        // Return level.hash
        return level + hash / divisor;
    }

    public String toString() {
    	String s = "User: "+ username +"\n\n" + "Trophies: \n";
        for (int i = 0; i< trophies.size(); i++) {
        	s+= trophies.get(i).toString()+"\n";
        }
        SimpleDateFormat f = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);          
    	s += "\n"+"Games: \n" + games.toString()
    	      + "\n\nBirth Date: "+ f.format(dob.getTime()); 
    	
		return s;
	}

	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	public Calendar getDob() {
		// TODO Auto-generated method stub
		return this.dob;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevel() {
		// TODO Auto-generated method stub
		return this.level;
	}

	public double getKey() {
		// TODO Auto-generated method stub
		this.key = this.calculateKey();
		return this.key;
	}

	public void setGames(GameList games) {
		// TODO Auto-generated method stub
		this.games = games;
	}

	public void setTrophies(ArrayList<Trophy> trophies) {
		// TODO Auto-generated method stub
		this.trophies = trophies;
	}

	public User getLeft() {
		// TODO Auto-generated method stub
		return this.left;
	}

	public User getRight() {
		// TODO Auto-generated method stub
		return this.right;
	}

	public void setLeft(User left) {
		// TODO Auto-generated method stub
		this.left = left;
	}

	public void setRight(User right) {
		// TODO Auto-generated method stub
		this.right = right;
	}

	public ArrayList<Trophy> getTrophies() {
		// TODO Auto-generated method stub
		return this.trophies;
	}

	public GameList getGames() {
		// TODO Auto-generated method stub
		return this.games;
	}
}

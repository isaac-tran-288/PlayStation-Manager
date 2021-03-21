package Database;

/**
 * Class to represent a single linked-list of Database.Game objects.
 * Created for Data Structures, SP2 2017
 * @author James Baumeister
 * @version 1.0
 */
public class GameList {

    public Game head;

	public GameList(Game head) {
		this.head = head;
    }

    public String toString() {
    	String s = "";
    	Game g = head;
		if (head == null) {
			return "Empty game list";
		}
		else
			while(g != null) {
				s +=  g.toString();
				if (g.getNext() != null) {
					s += "\n";
				}
				g = g.getNext();
			}
		return s;
    }


	public void addGame(Game g) {
		// TODO Auto-generated method stub
		if (g == null) {
			throw new IllegalArgumentException();
		}
		if(head == null) {
			head = g;
		}
		else {
			Game ref = head;
			boolean check = true;
			while (ref.getNext() != null){
				if (ref.equals(g)) {
					check = false;
					break;
				}
				ref = ref.getNext();
			}
			if (check == true) {
			ref.setNext(g);
			g.setNext(null);
			}
		}
	}

	public Game getGame(String name) {
		// TODO Auto-generated method stub
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (head == null) {
			return null;
		}
		else {
			Game ref = head;
			Game found = null;
			while (ref != null) {
				if (ref.getName() == name) {
					found = ref;
				}
				ref = ref.getNext();
			}
			return found;
		}
		
	}

	public void removeGame(String name) {
		// TODO Auto-generated method stub
		if (name == null ) {
			throw new IllegalArgumentException();
		}
		
		
		if (this.getGame(name) != null) {
			Game g = this.getGame(name);
			Game ref = head;
			if (ref.equals(g)) {
				if (ref.getNext() == null) {
				head = null;
				}
				else {
					head = ref.getNext();
				}
			}
			else {
			while (ref.getNext() != null){
				if (ref.getNext().equals(g)) {
					break;
				}
				ref = ref.getNext();
			}
			if (g.getNext() == null) {
				ref.setNext(null);
			}
			else {
				ref.setNext(g.getNext());
			}
			}
		}
	}

	public void removeGame(Game g) {
		// TODO Auto-generated method stub
		if (g == null) {
			throw new IllegalArgumentException();
		}
		if (g != null) {
			Game ref = head;
			if (ref.equals(g)) {
				if (ref.getNext() == null) {
				head = null;
				}
				else {
					head = ref.getNext();
				}
			}
			else {
			while (ref.getNext() != null){
				if (ref.getNext().equals(g)) {
					break;
				}
				ref = ref.getNext();
			}
			if (g.getNext() == null) {
				ref.setNext(null);
			}
			else {
				ref.setNext(g.getNext());
			}
			}
		}
	}
}

package Database;


import java.util.Stack;

import Database.Trophy.Rank;

/**
 * Uses a binary search tree to store a database of
 * PlayStation users. Nodes are ordered by user unique key (see the
 * User class for more detail).
 * Created for Data Structures, SP2 2017
 * @author Quang Huy Tran 
 * emailID: traqy014
 * @version 1.0
 */
public class BinaryTree {

	public User root;
	
	public BinaryTree() {
		this.root = null;
	}
	
	

	/**
	 * Making new friends is great. This method should add your new
	 * bestie to your database (tree). Remember that they should be
	 * added according to their key.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean beFriend(User friend) throws IllegalArgumentException {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
		
		User current = this.root;
		User p = null; // pointer of current
		while (current != null) {  
	        p = current;  
	        if (friend.getKey() < current.getKey())  
	            current = current.getLeft();  
	        else
	            current = current.getRight();  
	    }
		if (p == null)  {
	        root = friend;
	        return true;
		}
	    // If the new User key is less then the leaf User key  
	    // Assign the new User to be its left child  
	    else if (friend.getKey() < p.getKey())  {
	        p.setLeft(friend); 
	        return true;
	    }
	    // else if it is greater, assign the new User to be its right child  
	    else if (friend.getKey() > p.getKey()) {
	        p.setRight(friend);
	        return true;
	    }
	    else { // if the User is duplicated or all other error cases
	    	return false;
	    }
		
	}

	/**
	 * Sometimes friendships don't work out. In those cases it's best
	 * to remove that "friend" altogether. This method should remove
	 * all trace of that "friend" in the database (tree).
	 * @param friend the "friend" to remove
	 * @return true if successfully removed, false for all error cases
	 * @throws IllegalArgumentException if "friend" is null
	 */
	public boolean deFriend(User friend) throws IllegalArgumentException {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
		 // pointer to store parent node of current node
        User parent = null;
 
        // start with root node
        User curr = root;
 
        // search friend in BST and set its parent pointer
        while (curr != null && !curr.equals(friend))
        {
            // update parent node as current node
            parent = curr;
 
            // if given User key is less than the current node, go to left subtree
            // else go to right subtree
            if (friend.getKey() < curr.getKey()) {
                curr = curr.getLeft();
            }
            else {
                curr = curr.getRight();
            }
        }
 
        // return if key is not found in the tree
        if (curr == null) {
            return false;
        }
 
        // Case 1: User node to be deleted has no children  (a leaf node)
        if (curr.getLeft() == null && curr.getRight() == null)
        {
            // if node to be deleted is not a root node, then set its
            // parent left/right child to null
            if (curr != root) {
                if (parent.getLeft() == curr) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
            // if tree has only root node, delete it and set root to null
            else {
                root = null;
            }
            return true;
        }
 
        // Case 2: node to be deleted has two children
        else if (curr.getLeft() != null && curr.getRight() != null)
        {
            User succ = maximumKey(curr.getLeft());
            User parSucc = parMaxKey(curr.getLeft());
            if (curr != root)
            {
                if (curr == parent.getLeft()) {
                    parent.setLeft(succ);
                } else {
                    parent.setRight(succ);
                }
            }
            else {
            	root = succ;
            }
            if (succ.getLeft() == null) {
                parSucc.setRight(null);
            }
            else {
                	parSucc.setRight(succ.getLeft());
            }
            succ.setLeft(curr.getLeft());
            succ.setRight(curr.getRight());
            
            return true;
        }
 
        // Case 3: node to be deleted has only one child
        else
        {
            // find child node
            User child = (curr.getLeft() != null)? curr.getLeft(): curr.getRight();
 
            // if node to be deleted is not a root node, then set its parent
            // to its child
            if (curr != root)
            {
                if (curr == parent.getLeft()) {
                    parent.setLeft(child);
                } else {
                    parent.setRight(child);
                }
            }
 
            // if node to be deleted is root node, then set the root to child
            else {
                root = child;
            }
            return true;
        }
 
	}
	//find the node which has highest key in a sub-tree
	private User maximumKey(User curr)
    {
        while(curr.getRight() != null) {
            curr = curr.getRight();
        }
        return curr;
    }
	//find the parent node of the node which has highest key in a subtree
	private User parMaxKey(User current) {
		while(current.getRight().getRight() != null) {
            current = current.getRight();
        }
        return current;
	}
	
	/**
	 * In your quest to be the very best you need to know how many
	 * of your friends are ranked higher than you. This method should
	 * return the number of higher ranked users that the provided reference
	 * user, or zero if there are none (woot!).
	 * @param reference The starting point in the search
	 * @return Number of higher ranked users or -1 if user not found
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countBetterPlayers(User reference) throws IllegalArgumentException {
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		int count = 0;
		boolean check = false;
		Stack<User> s = new Stack<User>(); 
		User current = root; 
		while (!s.isEmpty() || current != null) { 
			if (current != null) { 
				s.push(current); 
				current = current.getLeft(); 
				} 
			else { 
				User node = s.pop(); 
				if (node.equals(reference)) {
			    	check = true;
			    }
			    if (node.getLevel() > reference.getLevel()) {
			    	count++;
			    }
			    current = node.getRight(); 
			    } 
			}
		if (check == false) {
			return -1;
		}
		else
	    return count;
		
	}

	/**
	 * Bragging rights are well earned, but it's good to be sure that you're actually
	 * better than those over whom you're lording your achievements. This method
	 * should find all those friends who have a lower level than you, or zero if
	 * there are none (you suck).
	 * @param reference The starting point in the search
	 * @return Number of lower ranked users
	 * @throws IllegalArgumentException if reference is null
	 */
	public int countWorsePlayers(User reference) throws IllegalArgumentException {
		if (reference == null) {
			throw new IllegalArgumentException();
		}
		int count = 0;// count variable
		boolean check = false; // to check if the reference User is in the tree
		//inorder traverse through the tree iteratively
		Stack<User> s = new Stack<User>(); 
		User current = root; 
		while (!s.isEmpty() || current != null) { 
			if (current != null) { 
				s.push(current); 
				current = current.getLeft(); 
				} 
			else { 
				User node = s.pop(); 
				if (node.equals(reference)) {
			    	check = true;
			    }
			    if (node.getLevel() < reference.getLevel()) {
			    	count++;
			    }
			    current = node.getRight(); 
			    } 
			}
		if (check == false) {
			return -1;
		}
		else
	    return count;
		
	}

	/**
	 * The best player may not necessarily be measured by who has the highest level.
	 * Platinum trophies are the holy grail, so it would be good to know who has the
	 * most. This method should return the user with the highest number of platinum trophies.
	 * @return the user with the most platinum trophies, or null if there are none
	 */
	public User mostPlatinums() {
		int maxPlant = 0; // highest number of Plantinum trophy
		int maxGold = 0; // to compare Gold trophy in case 2 or more Users have same maxPlant 
		User max = null; // the target User
		//inorder traverse through the tree iteratively
		Stack<User> s = new Stack<User>(); 
		User current = root; 
		while (!s.isEmpty() || current != null) { 
			if (current != null) { 
				s.push(current); 
				current = current.getLeft(); 
				} 
			else { 
				int plantinumCount = 0;
				int goldCount = 0;
				User node = s.pop(); 
				
				for (int i = 0; i < node.getTrophies().size(); i++) {
			    	if (node.getTrophies().get(i).getRank() == Rank.PLATINUM) {
			    		plantinumCount++;
			    	}
			    	if (node.getTrophies().get(i).getRank() == Rank.GOLD) {
			    		goldCount++;
			    	}
			    }
			    if (plantinumCount > maxPlant ) {
			    	maxPlant = plantinumCount;
			    	maxGold = goldCount;
			    	max = node;
			    }
			    else if (plantinumCount == maxPlant) {
			    	if (goldCount > maxGold) {
			    		maxGold = goldCount;
				    	max = node;
			    	}
			    }
			    current = node.getRight(); 
			    } 
			}
		if (maxPlant == 0) {
			return null;
		}
		else
	    return max;
	}

	/**
	 * You or one of your friends bought a new game! This method should add it to their
	 * GameList.
	 * @param username The user who has bought the game
	 * @param game The game to be added
	 */
	public void addGame(String username, Game game) throws IllegalArgumentException {
		if (username == null || game == null) {
			throw new IllegalArgumentException();
		}
		boolean check = false; // to check if the searching user is in the tree
		//inorder traverse through the tree iteratively to search for the User
		Stack<User> s = new Stack<User>(); 
		User current = root; 
		while (!s.isEmpty() || current != null) { 
			if (current != null) { 
				s.push(current); 
				current = current.getLeft(); 
				} 
			else { 
				User node = s.pop(); 
				if (node.getUsername() == username) { // compare username
			    	check = true;
			    	node.getGames().addGame(game);
			    }
			    current = node.getRight(); 
			    } 
			}
		if (check == false) {
			throw new IllegalArgumentException();
		}
		
	}

	/**
	 * You or one of your friends achieved a new trophy! This method should add it to
	 * their trophies.
	 * @param username The user who has earned a new trophy
	 * @param trophy The trophy to be added   
	 */
	public void addTrophy(String username, Trophy trophy) throws IllegalArgumentException {
		if (username == null || trophy == null) {
			throw new IllegalArgumentException();
		}
		boolean check = false; // to check if the searching user is in the tree
		//inorder traverse through the tree iteratively to search for the User
		Stack<User> s = new Stack<User>(); 
		User current = root; 
		while (!s.isEmpty() || current != null) { 
			if (current != null) { 
				s.push(current); 
				current = current.getLeft(); 
				} 
			else { 
				User node = s.pop(); 
				if (node.getUsername() == username) {
			    	check = true;
			    	node.getTrophies().add(trophy);
			    }
			    current = node.getRight(); 
			    } 
			}
		if (check == false) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * You or one of your friends has gained one level! This is great news, except that
	 * it may have ruined your tree structure! A node move may be in order.
	 * @param username The user whose level has increased
	 */
	public void levelUp(String username) throws IllegalArgumentException {
		if (username == null) {
			throw new IllegalArgumentException();
		}
		boolean check = false; // to check if the searching user is in the tree
		User copy = null; // to store the value of the target User
		//inorder traverse through the tree iteratively to search for the User
		Stack<User> s = new Stack<User>(); 
		User current = root; 
		while (!s.isEmpty() || current != null) { 
			if (current != null) { 
				s.push(current); 
				current = current.getLeft(); 
				} 
			else { 
				User node = s.pop(); 
				if (node.getUsername() == username) { //found the User
			    	check = true;
			    	copy = node;
			    	copy.setLevel(copy.getLevel()+1); // increase the level
			    	if (node.getRight() != null) {
			    		User min = minimumKey(node.getRight());// find the smallest key in the right subtree
			    		if (copy.getKey() > min.getKey()) { // if the new Key violates the rules
			    			
			    			this.deFriend(node);// delete the User with old Key
			    			//remove the previous tree connection 
			    			copy.setLeft(null);
			    			copy.setRight(null);
			    			//add the User with new Key back to the tree
			    			this.beFriend(copy);
			    		}
			    	}
			    	break;
			    }
			    current = node.getRight(); 
			    } 
		}
		
		if (check == false) {
			throw new IllegalArgumentException();
		}
		
	}
	//find User who has smallest Key in the subtree
	private User minimumKey(User curr)
    {
        while(curr.getLeft() != null) {
            curr = curr.getLeft();
        }
        return curr;
    }
	
	/** recursively calculate the height of a tree or subtree
	 * @param node The root User to start the calculation
	 */
	public int height(User node)  
    { 
        if (node == null) 
            return 0; 
        else 
        { 
            /* compute the depth of each subtree */
            int lHeight = height(node.getLeft()); 
            int rHeight = height(node.getRight()); 
   
            /* use the larger one */
            if (lHeight > rHeight) 
                return (lHeight + 1); 
             else 
                return (rHeight + 1); 
        } 
    } 
	/**
	 * Calculate the balance of a User node
	 * @param s the target User node
	 * @return the subtraction of right subtree's and left subtree's height
	 */
	public int balance(User s) {
		if (s == null) {
			return 0;
		}
		return height(s.getRight()) - height(s.getLeft());
	}
	//rotate left 
	private User rotateLeft(User s) {
		User temp = s.getRight();
		s.setRight(temp.getLeft());
		temp.setLeft(s);
		return temp;
	}
	//rotate left 
	private User rotateRight(User s) {
			User temp = s.getLeft();
			s.setLeft(temp.getRight());
			temp.setRight(s);
			return temp;
	}
	 public User FindParent(User root, User node)
	    {
	        if (root == null || node == null)
	        {
	            return null;
	        }
	        else if ( (root.getRight() != null && root.getRight().equals(node)) || (root.getLeft() != null && root.getLeft().equals(node)))
	        {
	            return root;
	        }
	        else
	        {
	            User found = FindParent(root.getRight(), node);
	            if (found == null)
	            {
	                found = FindParent(root.getLeft(), node);
	            }
	            return found;
	        }
	    }

	/**
	 * As your friends list grows, adding with regular binary tree rules will
	 * result in an unbalanced and inefficient tree. One approach to fix this
	 * is to implement an add method that uses AVL balancing. This method should
	 * work in the same way as beFriend, but maintain a balanced tree according to
	 * AVL rules.
	 * @param friend The friend to be added
	 * @return true if  successfully added, false for all error cases
	 * @throws IllegalArgumentException if friend is null
	 */
	public boolean addAVL(User friend) throws IllegalArgumentException {
		if (friend == null) {
			throw new IllegalArgumentException();
		}
		boolean add = false;
		User current = this.root;
		User p = null; // pointer of current
		while (current != null) {  
	        p = current;  
	        if (friend.getKey() < current.getKey())  
	            current = current.getLeft();  
	        else
	            current = current.getRight();  
	    }
		if (p == null)  {
	        root = friend;
	        add = true;
		}
	    // If the new User key is less then the leaf User key  
	    // Assign the new User to be its left child  
	    else if (friend.getKey() < p.getKey())  {
	        p.setLeft(friend); 
	        add = true;
	    }
	    // else if it is greater, assign the new User to be its right child  
	    else if (friend.getKey() > p.getKey()) {
	        p.setRight(friend);
	        add = true;
	    }
	    
		while (p != null) {
			
		//User c = s.pop();
		p = this.FindParent(root, p);
		int bal = balance(p);
		
		// Left Case 
        if (bal < -1 ) {
        	if (friend.getKey() < p.getLeft().getKey()) { //left-left
        		if (p.equals(root)) {
        			root = rotateRight(root);
        		}
            	else
            		this.FindParent(root, p).setLeft(rotateRight(p));
                    //p = rotateRight(p); 
        	}
        	else if (friend.getKey() > p.getLeft().getKey()) { //left-right
        		if (p.equals(root)) {
            		root.setLeft(this.rotateLeft(root.getLeft()));
        			root = rotateRight(root);
        		}
            	else
            	p.setLeft(this.rotateLeft(p.getLeft()));
        		this.FindParent(root, p).setLeft(rotateRight(p));
                //p = this.rotateRight(p);
        	}
        }
        // Right Case 
        else if (bal > 1) {
        	if (friend.getKey() > p.getRight().getKey()) { //right-right
        		if (p.equals(root)) {
        			root = rotateLeft(root);
        		}
            	else
            		this.FindParent(root, p).setRight(rotateLeft(p));
            		//p = rotateLeft(p);
        	}
        	else if (friend.getKey() < p.getRight().getKey()) { //right-left
        		if (p.equals(root)) {
            		root.setRight(this.rotateRight(root.getRight()));
        			root = rotateLeft(root);
        		}
            	else
                p.setRight(this.rotateRight(p.getRight()));
        		this.FindParent(root, p).setRight(rotateLeft(p));
                //p = this.rotateLeft(p);
        	}
        	
        }
		}
        
		return add;
	}

	/**
	 * A nice, neat print-out of your friends would look great in the secret scrap-book
	 * that you keep hidden underneath your pillow. This method should print out the
	 * details of each user, traversing the tree in order.
	 * @return A string version of the tree
	 */
	public String toString() {
		String string = "";
		//inorder traverse through the tree iteratively to print all Users
		Stack<User> s = new Stack<User>(); 
		User current = root; 
		while (!s.isEmpty() || current != null) { 
			if (current != null) { 
				s.push(current); 
				current = current.getLeft(); 
				} 
			else { 
				User node = s.pop(); 
				if (s.isEmpty() && node.getRight() == null) {// the last item to print
				string += node.toString() ;
				}
				else {
					string += node.toString() +"\n";
				}
			    current = node.getRight(); 
			    } 
			}


		return string;
	}
}

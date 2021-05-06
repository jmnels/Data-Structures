package project3;
/**
 * This is the main HashTable class that ties it all together.
 * The goal is to add nodes into a hash table using separate
 * chaining for collisions. This is done by using double-ended
 * singly liked lists.  
 * @author Joshua Nelson
 * @version 08/02/2020
 */
public class HashTable {
	/**
	 * The node class is the information for all of the countries to 
	 * be stored in the hash table.
	 * @author Joshua Nelson
	 * @version 08/02/2020
	 */
	private class Node {
		String name;
		double gdpPerCapita;
		Node nextNode;
		/**
		 * Node constructor
		 * @param name
		 * @param gdpPerCapita
		 */
		public Node(String name, double gdpPerCapita) {
		this.name = name;
		this.gdpPerCapita = gdpPerCapita;
		}
		/**
		 * custom print method for Nodes
		 */
		public void printNode() {
		System.out.printf("%-25s%,-20.2f\n", name, gdpPerCapita);
		}
		}
	/**
	 * This class creates the individual links for the 
	 * linked list
	 * @author Joshua Nelson
	 * @version 07/17/2020
	 */
	private class Link
	{
		private Node data;
		public Link next;
		
		public Link(Node d)
		{
			data = d;
		}
		/**
		 * this method returns the countries name
		 * @return data.name
		 */
		public String getkey()
		{
			return data.name;
		}
	}
	/**
	 * This class creates a list of links, which are
	 * essentially lists of nodes.
	 * @author Joshua Nelson
	 * @version 08/02/2020
	 */
	private class LinkList
	{
		private Link first;
		private Link last;
		/**
		 * constructor of liked list
		 */
		public void LinkLIst()
		{
			first = null;
			last = null;
		}
		/**
		 * This method inserts a new link
		 * into the end of a linked list
		 * @param l is a node that is used
		 * to make a link.
		 */
		public void insert(Node l)
		{
			Link link = new Link(l);
			Link front = first;
			
			if(front == null)
			{
				first = link;
				last = link;
			}
			else
			{
				last.next = link;
				last = link;
			}
			
		}
		/**
		 * This method deletes a link out of 
		 * the linked list
		 * @param key is a country name
		 */
		public void delete(String key)
		{
			Link previous = null;
			Link current = first;
			
			while(current != null && key.compareTo(current.getkey()) != 0)
			{
				previous = current;
				current = current.next;
			}
			
			if(previous == null) 
			{
				first = first.next;
			}
			else
			{
				previous.next = current.next;
			}
			
			System.out.println(key + " has been deleted from the hash table.");
		}
		/**
		 * This method traverses a list to find
		 * the link.
		 * @param key is a country name
		 * @return null if not found, or the node if found.
		 */
		public Link find(String key)
		{
			Link current = first;
			
			while(current != null && current.getkey().compareTo(key) != 0)
			{
				current = current.next;
			}
			if(current == null)
				return null;
			else
			{
				return current;
			}
		}
		/**
		 * This method displays the linked list of nodes.
		 */
		public void displayList()
		{
			Link current = first;
			
			if(current == null)
			{
				System.out.print(hj + ".  ");
				System.out.println("Empty");
			}
			while(current != null)
			{
				if(current == first)
				{
					System.out.print(hj + ".  ");
					current.data.printNode();
					current = current.next;
				}
				else
				{
					System.out.printf("%6s", "");
					current.data.printNode();
					current = current.next;
				}
			}
		}
	}
	LinkList[] hashArray;
	/**
	 * This is a constructor for a hash table
	 */
	public HashTable()
	{
		hashArray = new LinkList[311];
		for(int j = 0; j<311; j++)
		{
			hashArray[j] = new LinkList();
		}
	}
	/**
	 * This is the hash function that turns a string into an
	 * array of chars, finds their ascii value and then
	 * adds them. Next it divides the total by a prime number
	 * roughly double the number of elements to add to the array.
	 * @param n is a country name to be hashed.
	 * @return the hash value.
	 */
	public int hash(String n)
	{
		int h = 0;
		for(char c : n.toCharArray())
		{
			int b = c;
			h += b;
		}
		return h % 311;
	}
	/**
	 * Inserts a country and gdp per capita to the hash table.
	 * @param country
	 * @param gdpPerCapita
	 */
	public void insert(String country, double gdpPerCapita)
	{
		Node n = new Node(country, gdpPerCapita);
		int hashV = hash(n.name);
		hashArray[hashV].insert(n);
	}
	/**
	 * This method find a specified country and its gdp per capita.
	 * @param country
	 * @return gdp per capita.
	 */
	public double find(String country)
	{
		int hashV = hash(country);
		Link theLink = hashArray[hashV].find(country);
		if(theLink == null)
			return -1;
		else
			return theLink.data.gdpPerCapita;
	}
	/**
	 * This method deletes a specified country form the hash table.
	 * @param country
	 */
	public void delete(String country)
	{
		int hashVal = hash(country);
		hashArray[hashVal].delete(country);
	}
	static int hj;
	/**
	 * This method prints a well formatted hash
	 * table
	 */
	public void display()
	{
		for(hj = 0; hj<311; hj++)
		{
			hashArray[hj].displayList();
		}
	}
	/**
	 * This method displays the number of open cells in 
	 * the hash table as well as the number of cells that contain
	 * more that one item in the linked list.
	 */
	public void printFreeAndCollisions()
	{
		int spaces = 0, collisions = 0;
		
		for(int i = 0; i<311;i++)
		{
			if(hashArray[i].first == null)
				spaces++;
			else if(hashArray[i].first.next != null)
				collisions++;
		}
		
		System.out.println("There are " + spaces + " spaces available and " + collisions + " collisions in the hash table");
	}
}

package project2;

import java.util.*;
/**
 * The BinarySearchTree class creates a binary search tree with
 * nodes consisting of a name and a gdp per capita.
 * The nodes are then inserted by name. The class contains methods
 * to find, delete, traverse inorder, preorder, and postorder, and
 * methods to print the top ten highest and lowest gdp.
 * @author Joshua Nelson
 * @version 07/19/2020 
 */
public class BinarySearchTree {
	/**
	 * Node class for constructing the tree and
	 * a method to print the nodes.
	 * @author Joshua Nelson
	 *
	 */
	private class Node {
		String name;
		double gdpPerCapita;
		Node leftChild;
		Node rightChild;
		/**
		 * Node constructor
		 * @param name
		 * @param gdpPerCapita
		 */
		public Node(String name, double gdpPerCapita) {
			this.name = name;
			this.gdpPerCapita = gdpPerCapita;
		/**
		 * this method prints a neatly formatted node.
		 */
		}
		public void printNode() {
			System.out.printf("%-25s%,-20.2f\n", name, gdpPerCapita);
		}
    }
	
	public Node root;
	Node[] a = new Node[10];
	public static int count = 0;
	/**
	 * The insert method constructs the tree and 
	 * can be used to add additional nodes
	 * @param name
	 * @param gdpPerCapita
	 */
	public void insert(String name, double gdpPerCapita)
	{
		Node nNode = new Node(name, gdpPerCapita);
		if(root==null)
			root = nNode;
		else
		{
			Node current = root;
			Node parent;
			while(true)
			{
				parent = current;
				if(name.compareTo(current.name) < 0)
				{
					current = current.leftChild;
					if(current == null)
					{
						parent.leftChild = nNode;
						return;
					}
				}
				else
				{
					current = current.rightChild;
					if(current == null)
					{
						parent.rightChild = nNode;
						return;
					}
				}
			}
		}
	}
	/**
	 * The find method takes a string parameter and
	 * searches the tree to find the name and return 
	 * the gdp.
	 * @param name
	 * @return gdpPerCapita
	 */
	public double find(String name)
	{
		Node current = root;
		count = 0;
		while(current.name.compareTo(name) != 0)
		{
			count++;
			if(name.compareTo(current.name) < 0)
			{
				current = current.leftChild;
			}
			else
				current = current.rightChild;
			if(current == null)
				return -1;
		}
		return current.gdpPerCapita;
	}
	/**
	 * The delete method takes in a string and finds
	 * and deletes the node by name.
	 * @param name
	 */
	public void delete(String name)
	{
		Node current = root;
		Node parent = root;
		boolean isLeftChild = true;
		
		while(current.name.compareTo(name) != 0)
		{
			parent = current;
			if(name.compareTo(current.name) < 0)
			{
				isLeftChild = true;
				current = current.leftChild;
			}
			else
			{
				isLeftChild = false;
				current = current.rightChild;
			}
			if(current == null)
				return ;
		}
		
		if(current.leftChild == null && current.rightChild == null)
		{
			if(current == root)
				root = null;
			else if(isLeftChild)
				parent.leftChild = null;
			else
				parent.rightChild = null;
		}
		else if(current.rightChild == null)
		{
			if(current == root)
				root = current.rightChild;
			else if(isLeftChild)
				parent.rightChild = current.leftChild;
			else
				parent.rightChild = current.rightChild;
		}
		else
		{
			Node successor = current;
			Node successorParent = current;
			Node delNode = current;
			Node delcurrent = delNode.rightChild;
			while(delcurrent != null)
			{
				successorParent = successor;
				successor = delcurrent;
				delcurrent = delcurrent.leftChild;
			}
			
			if(successor.name.compareTo(delNode.rightChild.name) != 0)
			{
				successorParent.leftChild = successor.rightChild;
				successor.rightChild = delNode.rightChild;
			}
			
			if(current == root)
				root = successor;
			else if(isLeftChild)
				parent.leftChild = successor;
			else
				parent.rightChild = successor;
			
			successor.leftChild = current.leftChild;
		}
		System.out.println(name + " has been deleted from tree");
	}
	/**
	 * This method prints all the nodes of the tree in order
	 * using a stack.
	 */
	public void printInorder()
	{
		if(root != null)
		{
			Stack<Node> s = new Stack<>();
			Node current = root;
			
			while(current != null || s.size() > 0)
			{
				if(current != null)
				{
					s.push(current);
					current = current.leftChild;
				}
				else
				{
					current = s.pop();
					current.printNode();
					current = current.rightChild;
				}
			}
		}
	}
	/**
	 * This method prints all of the nodes of
	 * the tree pre-order using a stack.
	 */
	public void printPreorder()
	{
		if(root != null)
		{
			Stack<Node> s = new Stack<>();
			Node current = root;
			
			while(current!= null || s.size() >0 )
			{
				if(current != null)
				{
					current.printNode();
					s.push(current);
					current = current.leftChild;
				}
				else
				{
					current = s.pop();
					current = current.rightChild;
				}
			}
		}
	}
	/**
	 * This method prints all nodes of the
	 * tree in post order using two stacks.
	 */
	public void printPostorder()
	{
		if(root != null)
		{
			Stack<Node> s = new Stack<>();
			Stack<Node> p = new Stack<>();
			Node current = root;
			s.push(current);
			
			while(s.size() > 0)
			{
				current = s.pop();
				p.push(current);
				
				if(current.leftChild != null)
				{
					s.push(current.leftChild);
				}
				if(current.rightChild != null)
				{
					s.push(current.rightChild);
				}
			}
			
			while(p.size() > 0)
			{
				current = p.pop();
				current.printNode();
			}
		}
	}
	/**
	 * this method uses iteration to traverse the
	 * tree and compare the gdp of all nodes. It
	 * stores the lowest in an array and prints the
	 * bottom ten lowest gdp in the world.
	 * 
	 */
	public void printBottomTen()
	{
		Node current = root;
		Node temp = root;
		
		if(current == null)
		{
			return;
		}
		for(int h = 0; h<10; h++)
		{
			a[h] = current;
		}
		while(current != null)
		{
			if(current.leftChild == null)
			{
				Node find = current;
				for(int i = 0; i<10; i++)
				{
					if(find.gdpPerCapita < a[i].gdpPerCapita)
					{
						Node y = a[i];
						a[i] = find;
						find = y;
					}
				}
				current = current.rightChild;
			}
			else
			{
				Node pre = current.leftChild;
				while(pre.rightChild != null && pre.rightChild != current)
				{
					pre = pre.rightChild;
				}
				if(pre.rightChild == null)
				{
					pre.rightChild = current;
					current = current.leftChild;
				}
				else
				{
					pre.rightChild = null;
					Node find = current;
					for(int i = 0; i<10; i++)
					{
						if(find.gdpPerCapita < a[i].gdpPerCapita)
						{
							Node y = a[i];
							a[i] = find;
							find = y;
						}
					}
					current = current.rightChild;
				}
			}
		}
		for(Node j : a)
		{
			j.printNode();
		}
	}
	/**
	 * This method uses iteration to traverse the tree
	 * and uses an array to hold the top ten gdps in 
	 * the world and print them.
	 */
	public void printTopTen()
	{

		Node current = root;
		Node fill = new Node("h", 0.00);
		
		if(current == null)
		{
			return;
		}
		for(int h = 0; h<10; h++)
		{
			a[h] = fill;
		}
		while(current != null)
		{
			if(current.leftChild == null)
			{
				Node find = current;
				for(int i = 0; i<10; i++)
				{
					if(find.gdpPerCapita == a[i].gdpPerCapita)
						break;
					if(find.gdpPerCapita > a[i].gdpPerCapita)
					{
						Node temp = a[i];
						a[i] = find;
						find = temp;
					}
				}
				current = current.rightChild;
			}
			else
			{
				Node pre = current.leftChild;
				while(pre.rightChild != null && pre.rightChild != current)
				{
					pre = pre.rightChild;
				}
				if(pre.rightChild == null)
				{
					pre.rightChild = current;
					current = current.leftChild;
				}
				else
				{
					pre.rightChild = null;
					Node find = current;
						
					for(int i = 0; i<10; i++)
					{
						if(find.gdpPerCapita == a[i].gdpPerCapita)
							break;
						if(find.gdpPerCapita > a[i].gdpPerCapita)
						{
							Node temp = a[i];
							a[i] = find;
							find = temp;
						}
					}
					current = current.rightChild;
				}
			}
		}
		for(Node j : a)
		{
			j.printNode();
		}
	
	}
}

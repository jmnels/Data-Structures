package project2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 * COP 3530: Project 2 - Binary Search Trees
 * <p>
 * This class reads in the input file and parses it accordingly.
 * It then builds the tree.<p> Then the class goes on to
 * call methods required for the assignment.
 *
 * @author Joshua Nelson
 * @version 07/19/2020
 */
public class Project2 {

	static Scanner scanner = new Scanner(System.in);
	static String fname;
	static String[] tempArr;
	
	public static void main(String[] args) throws FileNotFoundException, IOException {

		System.out.println("COP 3530 Project 2 - Joshua Nelson\n");
		System.out.println("Binary Search Trees\n");
		System.out.print("Enter the file name: ");
		fname = scanner.nextLine();
		
		FileInputStream fstream = new FileInputStream(fname);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		int countries = 0;

	    BinarySearchTree bTree = new BinarySearchTree();

		while ((strLine = br.readLine()) != null)   {
		  tempArr = strLine.split(",");
		  
		  if(countries == 0)
			  countries++;
		  else
		  {
			  String name = tempArr[0];
			  double pop = Double.parseDouble(tempArr[3]);
			  double g = Double.parseDouble(tempArr[4]);
			  double gdp = g/pop;
			  bTree.insert(name, gdp);
		  }
		}
		System.out.println("\nInorder Traversal:\n");
		System.out.printf("%-25s%-20s\n", "Name", "GDP per Capita");
		for(int i = 0; i<45; System.out.print("-"), i++);
		System.out.println("\n");
		bTree.printInorder();
		System.out.println("\n");
		bTree.delete("Australia");
		bTree.delete("Czech Republic");
		bTree.delete("Norway");
		System.out.println("\nPreorder Traversal:\n");
		System.out.printf("%-25s%-20s\n", "Name", "GDP per Capita");
		for(int i = 0; i<45; System.out.print("-"), i++);
		System.out.println("\n");
		bTree.printPreorder();
		System.out.println("\n");
		String[] c = {"Sri Lanka", "North Cyprus", "Czech Republic", "Norway"};
		
		for(String s : c)
		{
			if(bTree.find(s) != -1)
			{
				System.out.printf("\n" + s + " is found with GDP per Capita %.2f \n" + bTree.count + " nodes visited\n", bTree.find(s));
			}
			else
			{
				System.out.println("\n" + s + " is not found \n" + bTree.count + " nodes visited");
			}
		}
		System.out.println("\n");
		String[] d = {"Malawi", "Somalia", "Canada", "Tunisia", "New Zealand"};
		for(String t : d)
		{
			bTree.delete(t);
		}
		System.out.println("\nPostorder Traversal:\n");
		System.out.printf("%-25s%-20s\n", "Name", "GDP per Capita");
		for(int i = 0; i<45; System.out.print("-"), i++);
		System.out.println("\n");
		bTree.printPostorder();
		System.out.println("\n");
		System.out.println("\nBottom ten countries regarding GDP perCapita:\n");
		System.out.printf("%-25s%-20s\n", "Name", "GDP per Capita");
		for(int i = 0; i<45; System.out.print("-"), i++);
		System.out.println("\n");
		bTree.printBottomTen();
		System.out.println("\n");
		System.out.println("\nTop ten countries regarding GDP perCapita\n");
		System.out.printf("%-25s%-20s\n", "Name", "GDP per Capita");
		for(int i = 0; i<45; System.out.print("-"), i++);
		System.out.println("\n");
		bTree.printTopTen();
	}

}

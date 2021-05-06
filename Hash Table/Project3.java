package project3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
/**
 * COP 3530: Project 3 - Hash Tables
 * <p>
 * This class contains the main method that reads in the file that is 
 * input by the user containing countries and assigns the details
 * to the appropriate variables to be inserted into the hash table.<p>
 * The class then outputs a well formatted hash table of linked lists
 * using separate chaining. Followed by a demo of the various methods
 * in the HashTable class and their resulting hash tables.<p>
 * @author Joshua Nelson
 * @version 08/02/2020
 */
public class Project3 {
	
	static Scanner scanner = new Scanner(System.in);
	static String fname;
	static String[] tempArr;

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		System.out.println("COP 3530 Project 3 - Joshua Nelson");
		System.out.println("Hash Tables\n");
		System.out.print("Enter the file name: ");
		fname = scanner.nextLine();
		
		
		FileInputStream fstream = new FileInputStream(fname);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		int countries = 0;
		
		HashTable hTable = new HashTable();
		
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
				  hTable.insert(name, gdp);
				  countries++;
			  }
			}
		
		System.out.println("\nThere were " + (countries - 1) + " countries read into the hash table.\n");
		System.out.println("Hash table content:\n");
		hTable.display();
		System.out.println("\n");
		String[] s = {"Cyprus", "Kazakhstan", "Hungary", "Japan"};
		for(String c : s)
		{
			hTable.delete(c);
		}
		System.out.println("\n");
		String[] t = {"Costa Rica", "North Cyprus", "Hungary"};
		for(String c : t)
		{
			if(hTable.find(c)==-1)
				System.out.println(c + " is not found");
			else
				System.out.printf(c + " is found with GDP per Capita of %.2f\n", hTable.find(c));
		}
		System.out.println("\n");
		String[] u = {"Zambia", "Canada", "Egypt", "Yemen", "India", "Singapore"};
		for(String c : u)
		{
			hTable.delete(c);
		}
		System.out.println("\n");
		for(String c : s)
		{
			if(hTable.find(c)==-1)
				System.out.println(c + " is not found");
			else
				System.out.printf(c + " is found with GDP per Capita of %.2f\n", hTable.find(c));
		}
		System.out.println("\n");
		System.out.println("Hash table content:\n");
		hTable.display();
		System.out.println("\n");
		hTable.printFreeAndCollisions();
	}

}

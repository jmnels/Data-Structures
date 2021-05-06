package project1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * COP 3530: Project1 - Arrays Searches and Sorts
 * <p>
 * This class begins by asking for the user to input a .csv file name.
 * The class then scans the file and parses the input and constructs a Country object.
 * The Country objects are stored in a Country object array.<p>
 * The user is then given a list of options to either print a Countries report,
 * sort the Objects according to Name, Happiness, GPD per capita, or display
 * a single Country object according to name. The user may also exit the program
 * when desired.<p>
 * @author Joshua Nelson
 * @version 07/05/2020
 */

public class Project1 {
	
	static Scanner scanner = new Scanner(System.in);
	static String fname;
	static String[] tempArr;
	static int countries = 0, i = 0;
	static Country[] C = new Country[155];

	/**
	 * This is the main method. Here The title is displayed and the user is prompted
	 * to input the file name. The file is read by a buffered reader, parsed into a 
	 * temporary array, made into the proper variables and the Country object is constructed
	 * and stored into an array. Then invoking the pick method.
	 * @param args standard java main args.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
				
		System.out.println("COP 3530 Project 1");
		System.out.println("Instructor: Xudong Liu\n");
		System.out.println("Array Searches and Sorts");
		System.out.print("Enter the file name: ");
		fname = scanner.nextLine();
		
		FileInputStream fstream = new FileInputStream(fname);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		while ((strLine = br.readLine()) != null)   {
		  tempArr = strLine.split(",");
		  
		  if(countries == 0) {
			  countries++;
			  }
		  else {
			  String x = tempArr[0];
			  String y = tempArr[1];
			  String z = tempArr[2];
			  int pop = Integer.parseInt(tempArr[3]);
			  double G = Double.parseDouble(tempArr[4]);
			  int Happy = Integer.parseInt(tempArr[5]);
			  
			  Country c =  new Country(x, y, z, pop, G, Happy);
			  /**c.setName(x);
			  c.setCode(y);
			  c.setCapital(z);
			  c.setPop(pop);
			  c.setGDP(G);
			  c.setHap(Happy);**/
			  //System.out.println(c.getName());
			  C[countries-1] = c;
			  countries++;
		  }
		}
		System.out.println("\nThere were " + (countries-1) + " countries read.");
		br.close();
		
		pick();
	}
	/**
	 * This method prompts the user with six options. Print a countries report, or sort by Name, 
	 * Happiness, GDP per capita, or find a single country by name. The user can also chose an option
	 * to quit the program. A switch statement is used to handle the choices along with recursive calls
	 * to the method to continuously display the options and execute.
	 */
	public static void pick() {
		
	int choice = 0;

	System.out.println("\n1. Print a countries report");
	System.out.println("2. Sort by Name");
	System.out.println("3. Sort by Happiness");
	System.out.println("4. Sort by GDP per Capita");
	System.out.println("5. Find and print a given country");
	System.out.println("6. Quit");
	System.out.print("Enter your choice: ");
	if(scanner.hasNextInt())
	{
		choice = scanner.nextInt();	
		scanner.nextLine();
	}
	else
	{
		System.out.println("\nInvalid choice enter 1-6");
		scanner.nextLine();
		pick();
	}
	switch(choice) {
	case 1:
	{
		System.out.println(String.format("\n%-35s%-10s%-20s%-15s%-18s%-5s", "Name", "Code", "Capital", "Population", "GDP", "Happiness"));
		for(int z = 0; z <105; z++)
		{
			System.out.print("-");
		}
		System.out.print("\n");
		for(Country c : C)
		{
			System.out.println(c);
		}
	}
	break;
	case 2:
	{
		i = 1;
		int in, out;
		
		for(out=1; out<C.length; out++)
		{
			Country temp = C[out];
			in = out;
			while(in>0 && C[in-1].getName().compareTo(temp.getName()) > 0)
			{
				C[in] = C[in-1];
				--in;
			}
			C[in] = temp;
		}
		System.out.println("\nCountries sorted by Name.");
	}
	break;
	case 3:
	{
		i = 0;
		int out, in, min;
		
		for(out=0; out<C.length-1; out++)
		{
			min = out;
			for(in=out+1; in<C.length; in++)
			{
				if(C[in].getHappy() < C[min].getHappy())
				{
					min = in;
				}
			}
			Country temp = C[min];
			C[min] = C[out];
			C[out] = temp;
		}
		System.out.println("\nCountries sorted by Happiness.");
	}
	break;
	case 4:
	{
		i = 0;
		int out, in;
		
		for(out=C.length-1; out>1; out--)
		{
			for(in=0; in<out; in++)
			{
			if((C[in].getGDP()/C[in].getPop()) < (C[in+1].getGDP()/C[in+1].getPop()))
					{
						Country temp = C[in];
						C[in] = C[in+1];
						C[in+1] = temp;
					}
			}
		}
		System.out.println("\nCountries sorted by GDP per capita.");
	}
	break;
	case 5:
	{
		System.out.print("\nEnter country name: ");
		String search = scanner.nextLine();
		if(i == 1)
		{
			System.out.println("\nBinary search");
			int lowerBound = 0;
			int upperBound = C.length;
			int curIn;
			while(true)
			{
				
				curIn = (lowerBound + upperBound)/2;

				if(C[curIn].getName().compareTo(search)==0)
				{
					Country.iPrint(C[curIn]);
					break;
				}
				else if(lowerBound>upperBound)
				{
					System.out.println("\nError country " + search + " not found");
					break;
				}
				else
				{
					if(C[curIn].getName().compareTo(search) < 0)
					{
						lowerBound = curIn +1;
					}
					else
					{
						upperBound = curIn - 1;
					}
				}
			}
		}
		else
		{
			System.out.println("\nSequential search");
			for(int j = 0; j<C.length; j++)
			{
				if(C[j].getName().compareTo(search) == 0)
				{
					Country.iPrint(C[j]);
					pick();
				}
			}
			System.out.println("\nError country " + search + " not found");
		}
	}
	break;
	case 6:
	{
		System.out.println("\nHave a good day!");
		System.exit(0);
	}
	break;
	default:
	{
		System.out.println("Invalid choice enter 1-6: ");
	}
	break;
	}
	pick();
	}
		
	
}
package project1;

/**
 * The Country class was created to be a template for the Country object.
 * THe class includes the encapsulated variables as well as the appropriate
 * getters and setters. The class also contains a constructor, a Compare method, and a method
 * to print the objects.
 * @author Joshua Nelson
 * @version 07/05/2020
 */

public class Country {

	private String Name;
	private String code;
	private String Capital;
	private int Population;
	private double GDP;
	private int HappinessRank;
	
	
	Country(String n, String c, String cap, int p, double g, int h)
	{
		this.Name = n;
		this.Capital = cap;
		this.code = c;
		this.Population = p;
		this.GDP = g;
		this.HappinessRank = h;
	}
	/**
	 * This is the setter method for the Name variable.
	 * @param name this is the name of the country object.
	 */
	public void setName(String name)
	{
		Name = name;
	}
	/**
	 * This is the setter method for the code variable.
	 * @param Cd this is the code of the Country object.
	 */
	public void setCode(String Cd)
	{
		code = Cd;
	}
	/**
	 * This is the setter method for the Capital variable.
	 * @param capital this is the Capital of the Country object.
	 */
	public void setCapital(String capital)
	{
		Capital = capital;
	}
	/**
	 * This is the setter method for the Population variable
	 * @param p this is the Population of the Country object.
	 */
	public void setPop(int p)
	{
		Population = p;
	}
	/**
	 * This is the setter method for the GDP variable. 
	 * @param g this is the GDP of the country object.
	 */
	public void setGDP(long g)
	{
		GDP = g;
	}
	/**
	 * This is the setter method for the Happiness variable.
	 * @param hap this is the HappinessRank of the Country object
	 */
	public void setHap(int hap)
	{
		HappinessRank = hap;
	}
	/**
	 * This is the getter method for the Name variable.
	 * @return returns the Name of the Country object.
	 */
	public String getName()
	{
		return Name;
	}
	/**
	 * This is the getter for the code variable
	 * @return returns the code of the Country object.
	 */
	public String getCode()
	{
		return code;
	}
	/**
	 * This is the getter for the Capital variable.
	 * @return returns the Capital of the country object.
	 */
	public String getCapital()
	{
		return Capital;
	}
	/**
	 * this is the getter for the Population variable.
	 * @return returns the Population of the Country object.
	 */
	public int getPop()
	{
		return Population;
	}
	/**
	 * this is the getter for the GDP variable.
	 * @return returns the GDP of the Country object.
	 */
	public double getGDP()
	{
		return GDP;
	}
	/**
	 * this is the getter for the HappinessRank.
	 * @return returns the HappinessRank of the Country object.
	 */
	public int getHappy()
	{
		return HappinessRank;
	}
	/**
	 * This method compares Country objects.
	 * @param x parameter is a Country object.
	 */
	public void Compare(Country x)
	{
		if(x.getName().compareTo(this.getName()) == 0)
		{
			return;
		}
	}
	/**
	 * This method reformats the way the Country object is printed.
	 * @return it returns a neatly formatted Country object.
	 */
	@Override
	public String toString()
	{
		return String.format("%-35s%-10s%-20s%-15d%-18.2e%-5d", Name, code, Capital, Population, GDP, HappinessRank);
		
	}
	/**
	 * this method prints the Country object.
	 * @param c the parameter is a Country object.
	 */
	public static void iPrint(Country c)
	{
		System.out.println(String.format("\n%-20s%s", "Name:", c.getName()));
		System.out.println(String.format("%-20s%s", "Code:", c.getCode()));
		System.out.println(String.format("%-20s%s", "Capital:", c.getCapital()));
		System.out.println(String.format("%-20s%s", "Population:", c.getPop()));
		System.out.println(String.format("%-20s%.2e", "GDP:", c.getGDP()));
		System.out.println(String.format("%-20s%s", "Happiness Rank:", c.getHappy()));
	
	}
		
}


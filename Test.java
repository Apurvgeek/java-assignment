import java.util.*;
import java.io.*;
import java.time.*;

abstract class Person
{
	private String name;
	abstract void display();

	Person(String name)
	{
		this.name = name;
	}
	Person()
	{

	}

	void set_name(String name)
	{
		this.name = name;
	}

	String get_name()
	{
		return name;
	}
}

class Employee extends Person implements Serializable
{
	private int id;
	private LocalDate date_of_join;

	Employee(int id,LocalDate date_of_join,String name)
	{
		super(name);
		this.id = id;
		this.date_of_join = date_of_join;
	}

	void display()
	{
		System.out.println( "Person's ID is: "+get_id()+"\nPerson's name is: "+ get_name() +"\nPerson's date_of_join is: "+ get_date_of_join() );
	}

	void set_id(int id)
	{
		this.id = id;
	}

	void set_date_of_join(LocalDate date_of_join)
	{
		this.date_of_join = date_of_join;
	}

	int get_id()
	{
		return id;
	}

	LocalDate get_date_of_join()
	{
		return date_of_join;
	}

	public String toString()
	{
		String msg = "Employee ID is: " + get_id() + "\n" + "Employee Name is: " + get_name() + "\n" + "Employee date of join is: " + get_date_of_join()+"\n";
		return msg;
	}

}

class Test
{
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		ArrayList<Employee> employee = new ArrayList<Employee>(1);
		for(int i=0;i<6;i++)
		{
			System.out.println("Enter the date_of_join like(2018-11-01)");
			
			LocalDate lc = date(sc.nextLine());
			System.out.println("Enter the name of Employee");
			String name = sc.nextLine();
			Employee e = new Employee(id(),lc,name);
			employee.add(e);
		}

		serializeObjects(employee);
		deserializeObjects(employee);
	}

	static int id()
	{
		System.out.println("Enter the ID of employee");
		int id = sc.nextInt();
		sc.nextLine();
		try
		{
			if(id<0)
			{
				throw new Except();
			}
		}
		catch(Except e)
		{
			System.out.println("Id can't be negative");
		}
		if (id<0) {
			return id();
		}
		else
		{
			return id;
		}
	}

	static LocalDate date(String input)
	{
		try{
			LocalDate lc =LocalDate.parse(input);
			return lc;
		}
		catch(Throwable e)
		{
			System.out.println("Error enter date according to the format");
		    System.out.println("Enter the date_of_join like(2018-11-01)");
		   	input = sc.nextLine();
		   	LocalDate lc = date(input);
		    return lc;
		}
	} 

	static void serializeObjects(ArrayList<Employee> employee)
	{
		FileOutputStream fot = null;
		ObjectOutputStream out = null;
		try
		{	
			fot = new FileOutputStream("E:/records.txt");
			out = new ObjectOutputStream(fot);
			for(Employee i : employee)
			{
				if(LocalDate.of(2015,01,01).compareTo((LocalDate)i.get_date_of_join())>=0)
				{
					out.writeObject(i);
				}
			}
			out.close();
			fot.close();
			System.out.println("The Object was successfully written in file.");
		}
		catch(IOException ex)
		{
			System.out.println("Error");
		}
	}

	static void deserializeObjects(ArrayList<Employee> employee)
	{
		FileInputStream fin = null;
		ObjectInputStream oin = null;
		try
		{
			fin = new FileInputStream("E:/records.txt");
			oin = new ObjectInputStream(fin);
			oin.readObject();
			for(Employee i : employee)
			{
				if(LocalDate.of(2015,01,01).compareTo((LocalDate)i.get_date_of_join())>=0)
				{
					System.out.println(i);
				}
			}
			
		}
		catch (Throwable e) {
			System.out.println("Error" + e);
			
		}
			}
}

class Except extends Exception
{
	Except()
	{
		
	}
}
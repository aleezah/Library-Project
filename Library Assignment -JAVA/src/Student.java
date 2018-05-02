import java.util.ArrayList;


public class Student {

	private String firstName;
	private String lastName;
	private int stuNum;
	private double accountBalance;
	private int numOfBooks;
	private boolean canCheckOut;
	private ArrayList<Book> studentBookList= new ArrayList <Book>(0);

	public Student(String firstN, String lastN, int studentNum, double balance, int numOfCheckOuts, boolean canBorrow )
	{
		// TODO Auto-generated constructor stub

		firstName = firstN;
		lastName = lastN;
		stuNum = studentNum;
		accountBalance = balance;
		numOfBooks = numOfCheckOuts;
		canCheckOut = canBorrow;
		


	}

	public ArrayList<Book> getbookList ()
	{
		return studentBookList;
	}
	public void addbookList (Book checkOutBook)
	{
		studentBookList.add(checkOutBook);
	}
	public void removeFromBookList ()
	{
		
	}
	public String getFirstN ()
	{
		return firstName;
	}
	public String getLastN ()
	{
		return lastName;
	}
	public int getStudentNum ()
	{
		return stuNum;
	}
	public double getBalance ()
	{
		return accountBalance;
	}
	public int numOfCheckOuts ()
	{
		return numOfBooks;
	}
	public boolean canBorrowBook()
	{
		if (accountBalance > 5.00 || numOfBooks==3)
		{
			canCheckOut= false;
		}
		else
		{
			canCheckOut= true;
		}
		return canCheckOut;
	}

	public void changeBalance (double newAccoutBalance)
	{
		accountBalance= newAccoutBalance;
	}

	public void changeNumberOfCheckOuts (int newNumBooks)
	{
		numOfBooks= newNumBooks;
	}

}



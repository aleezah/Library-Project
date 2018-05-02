import java.util.ArrayList;

public class Book {

	private String title;
	private String author;
	private int isbn;
	private String category;
	private double cost;
	private int rating;
	private int totalCopies;
	private int availableCopies;



	public Book(String bookTitle, String bookAuthor, int bookISBN, String bookCategory,  double bookCost, int bookRating,int totalBookCopies, int availableBookCopies)
	{
		title= bookTitle; 
		author= bookAuthor;
		isbn= bookISBN;
		category= bookCategory;
		cost= bookCost;
		rating= bookRating;
		totalCopies= totalBookCopies-1;
		availableCopies= availableBookCopies;
	}

	public String getTitle ()
	{
		return title;
	}

	public String getAuthor ()
	{
		return author;
	}
	public int getISBN ()
	{
		return isbn;
	}
	public String getCategory ()
	{
		return category;
	}
	public int getRating ()
	{
		return rating;
	}
	public double getCost()
	{
		return cost;
	}
	public int getTotalCopies ()    
	{
		return totalCopies;
	}

	public int getAvailableCopies ()
	{
		return availableCopies;
	}
	public boolean isAvailable()
	{
		if (availableCopies== 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	public void changeTotalCopies(int newBookCopies)        
	{
		totalCopies= newBookCopies;
	}
	
	
}


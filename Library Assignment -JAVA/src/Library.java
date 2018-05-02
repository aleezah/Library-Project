import java.util.ArrayList;
public class Library {

	private ArrayList<Student> studentList= new ArrayList <Student>(0); //list of all existing students
	private ArrayList<Book> bookList= new ArrayList <Book>(0); // list of all existing books


	public Library(ArrayList<Student> stuList,ArrayList<Book> booList ) {
		// TODO Auto-generated constructor stub
		studentList = stuList;
		bookList =booList;

	}
	public void getBook(Book b1,Book b2,Book b3,Book b4) // this method contains list of all books the library currently owns
	{
		bookList.add(b1);
		bookList.add(b2);
		bookList.add(b3);
		bookList.add(b4);
	}

	public void getStudent(Student s1, Student s2)// this method contains list of all students currently using the library
	{

		studentList.add(s1);
		studentList.add(s2);

	}
	public void addStudent(String firstN, String lastN, int studentNum, double balance, int numOfCheckOuts, boolean canBorrow)      // this method allows students to be added onto the current existing student list
	{
		Student newStu = new Student (firstN, lastN, studentNum, balance, numOfCheckOuts, canBorrow);
		studentList.add(newStu);
	}

	public void addBook(String bookTitle, String bookAuthor, int bookISBN, String bookCategory, int bookRating, double bookCost, int totalBookCopies, int availableBookCopies)      // this method allows books to be added onto the current existing bookList
	{
		Book newBook= new Book (bookTitle, bookAuthor, bookISBN, bookCategory, bookCost, bookRating, totalBookCopies, availableBookCopies);
		bookList.add(newBook);
	}

	public ArrayList<Book> returnBook()
	{
		return 	bookList;
	}	
	public ArrayList<Student> returnStudent()
	{
		return 	studentList;
	}	
	public void deleteStudent(int stuNum)      // this method allows books to be added onto the current existing bookList
	{
		for (int i = 0; i < studentList.size(); i++) {
			if (stuNum==(studentList.get(i).getStudentNum ())) {
				studentList.remove(i);
			}
		}

	}

	public void deleteAllBook(String title)      // this method allows books to be added onto the current existing bookList
	{
		for (int i = 0; i < bookList.size(); i++) {
			if (title.equals(bookList.get(i).getTitle())) {
				bookList.remove(i);
			}
		}

	}
	public void addFines (int stuNum, double fine)    
	{
		for (int i = 0; i < studentList.size(); i++) {
			if (stuNum==(studentList.get(i).getStudentNum ()))
			{
				double studentAddFine=studentList.get(i).getBalance()+fine;
				studentList.get(i).changeBalance(studentAddFine);

			}
		}
	}

	public void removeBookCopies (String title)    
	{
		for (int i = 0; i < bookList.size(); i++) 
		{
			if (title.equals(bookList.get(i).getTitle()))
			{
				if(bookList.get(i).getTotalCopies()==0)
				{
					bookList.remove(i);
				}
				else
				{
					int bookCopy=bookList.get(i).getTotalCopies()-1;
					bookList.get(i).changeTotalCopies(bookCopy);
				}
			}
		}
	}
	public void checkoutBook(String title, int stuNum)
	{
		for (int i = 0; i < bookList.size(); i++) 
		{
			for (int k = 0;k < studentList.size(); k++) 
			{
				if (title.equals(bookList.get(i).getTitle())&& stuNum==(studentList.get(k).getStudentNum ())&& studentList.get(k).getbookList().size()<3 && bookList.get(i).getTotalCopies() >0)
				{
					studentList.get(k).addbookList(bookList.get(i));
					int bookCopy=bookList.get(i).getTotalCopies()-1;
					bookList.get(i).changeTotalCopies(bookCopy);
				}
			}
		}
	}
}




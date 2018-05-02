// The "MovingBallApplet5" class.
import java.applet.*;
import java.awt.*;
// import an extra class for the ActionListener
import java.awt.event.*;
import java.awt.Choice;
import java.util.ArrayList;

public class mainClass extends Applet implements Runnable, ActionListener
{
	// Place instance variables here
	// Initialization of variables
	int xpos;       //x-position of the mouse click
	int ypos;       //y-position of the mouse click
	// declare two instance variables at the head of the program
	Image dbImage;
	Graphics dbg;

	ArrayList<Student> studentList= new ArrayList <Student>(); //list of all existing students
	ArrayList<Book> bookList= new ArrayList <Book>(); // list of all existing books

	final String PICTURE_PATH1 = "libraryHome.png";
	final String PICTURE_PATH2 = "libraryADMIN_0.png";
	final String PICTURE_PATH3 = "librarySTUDENT_0.png";
	final String PICTURE_PATH4 = "home_Button.jpg";//************REPLACE*******************
	final String PICTURE_PATH5 = "ADMIN_deleteBookcopy.png";
	final String PICTURE_PATH6 = "ADMIN_addBookcopy.png";
	final String PICTURE_PATH7 = "ADMIN_addBook.png";
	final String PICTURE_PATH8 = "ADMIN_deleteStudent.png";
	final String PICTURE_PATH9 = "librarySTUDENT_1.png";//***CHANGE***
	final String PICTURE_PATH10 = "ADMIN_deleteBooktitle.png";
	final String PICTURE_PATH11 = "ADMIN_deleteBook.png";
	final String PICTURE_PATH12 = "ADMIN_addBookTitle.png";
	final String PICTURE_PATH13 = "ADMIN_addStudent.png";


	Image picture; //home screen
	Image picture2;//administration/Staff page 
	Image picture3;//student login page
	Image picture4;//search catalog page
	Image picture5;//home screen *****REPLACE*****
	Image picture6;//ADMIN_Delete a book copy page
	Image picture7;//ADMIN_Add ONE book copy page
	Image picture8;	//ADMIN_Delete student page
	Image picture9;//STUDENT_Student profile page ***CHANGE***
	Image picture10;//ADMIN_Delete ALL book copies page
	Image picture11;//ADMIN_Delete book page (option to chose from delete one or delete all) page
	Image picture12;//ADMIN_add a book page
	Image picture13;//ADMIN_add student page 

	int picWidth, picHeight;
	int picWidth2, picHeight2;
	int picWidth3, picHeight3;
	int picWidth4, picHeight4;
	int picWidth5, picHeight5;
	int picWidth6, picHeight6;
	int picWidth7, picHeight7;
	int picWidth8, picHeight8;
	int picWidth9, picHeight9;
	int picWidth10, picHeight10;
	int picWidth11, picHeight11;
	int picWidth12, picHeight12;
	int picWidth13, picHeight13;


	//***POSSIBLE REMOVAL***
	String infoMessage;
	String infoTitle;
	String screen;

	//Library Object
	Library library1;

	//Pages
	boolean mainScreen = true; //initial Screen
	//All pages for students page
	boolean studentCheckInCheckOut = false; //after student button is clicked, this page is shown
	boolean studentProfilePage = false;//after student logs in this page is shown ***REMOVED***
	boolean returnPage = false;//when return book button is clicked this page is shown
	boolean newReturnPage = false;//after book has been returned this page is shown***REMOVED***
	boolean checkoutPage = false;//when checkout button is clicked this page is shown
	boolean newCheckOutPage = false;//updated student profile after new book is checked out***REMOVED***
	boolean manageStudentBalance = false;// After manage student balance button is clicked**HAS NOT BEEN ADDED**
	boolean manageStudentBalanceAddPage = false;
	boolean manageStudentBalanceDeductPage = false;
	//All pages for ADMIN
	boolean staffPage = false;//after staff button is clicked, this page is shown
	boolean addStudent = false;//after add student button is clicked
	boolean addbook = false;//after add book button is clicked. Shows option for adding new or existent book.
	boolean deleteStudent = false;//after delete student button is clicked
	boolean deleteBook = false;//after delete book button is clicked
	boolean addNewBookPage=false; //page for adding new books
	boolean addExistingBookPage=false; //page for adding book that exists
	boolean deleteOneBookPage=false; //page for deleting one book copy
	boolean deleteAllBookPage=false; //page for deleting all book copies
	//All pages for book searches
	boolean lookUpPage = false;//after lookUp button is clicked, this page is shown
	boolean searchByCatagory = false;// after Search by category button is clicked
	boolean searchByAuthor = false;//after search by author button is clicked
	boolean compareBooks = false;// after compare book button is clicked
	//Pages that **MAY BE REMOVED**
	boolean errorPage = false;// If a function is not possible (e.g. checkout book while already having over 3 books) then this page is shown
	boolean finePage = false;// If student has to pay a fine for a book this page is shown.***** MAY POSSIBLY NOT BE USED*****


	//All Buttons

	Button backToMainButton;// button to return to main page from search and staff page

	Button staffButton;// button for staff page
	Button addBookButton;//used to go to add book page
	Button addStudentButton;//used to go to add student page
	Button deleteBookButton;//used to go to delete book page
	Button deleteStudentButton;//used to go to delete student page
	Button studentBalanceButton;//used to go to student Balance page
	Button addNewBook;//used to add a new book page
	Button addExistingBook;//used to add an existing book
	Button deleteOneBook;//used to delete a single book copy
	Button deleteAllBook;//used to delete all book copies
	Button okayAllBookDelete;//refreshes page for deleting all book copies
	Button okayOneBookDelete;//refreshes page to deleting a single book copy
	Button okayDeleteStudent;//refreshes page to deleting a student

	Button lookUpButton;// button for search page
	Button lookUpCatagoryButton;//used to look up book by category
	Button lookUpAuthorButton;//used to look up book by author
	Button compareBooksButton;//used to compare books

	Button studentButton; //button to go to student log in page
	Button backToStudentProfileButton;// button to return to student profile
	Button logInButton;//button for login for student page
	Button logOutButton;// used to "log out" from student page and returns to main page
	Button checkoutButton;// used to checkout Book
	Button checkoutOkayButton;//book check out confirmation
	Button returnButton;// used to return book
	Button okayReturnButton;//book return confirmation
	Button manageStudentBalanceButton; //button to manage student balance 
	Button manageStudentBalanceAddOkay;// button to refresh page and update new student balance
	Button manageStudentBalanceDeductOkay;// button to refresh page and update new student balance
	Button manageStudentBalanceAdd;// button to refresh page and update new student balance
	Button manageStudentBalanceDeduct;// button to refresh page and update new student balance
	Button okayCheckout;// button to refresh page and update new student balance
	Button okayReturn;

	//Dropdown stuff
	Choice listOfBooks= new Choice(); //list of all books in Library
	Choice listOfStudents= new Choice();//list of all students using Library

	// All Textfield for Staff Page
	TextField studentFName; //Type in student first name for adding student page
	TextField studentLName;//Type in student last name for adding student page
	TextField studentNum;//Type in student number for adding student page
	TextField bookTitle;//Type in book title for adding book page
	TextField bookAuthor;//Type in book author for adding book page
	TextField bookISBN;//Type in book ISBN for adding book page
	TextField bookCategory;//Type in category title for adding book page
	TextField bookCost;//Type in book cost for adding book page
	TextField bookRating;//Type in rating title for adding book page
	TextField addFine;//Type in fine to add
	TextField deductFine;//Type in fine to deduct

	int appletsize_x = 800;
	int appletsize_y = 500;


	public void init ()
	{
		// Place the body of the initialization method here
		resize (appletsize_x, appletsize_y);

		setBackground (Color.BLACK);


		setLayout (null);

		picture = getToolkit ().getImage (PICTURE_PATH1);
		prepareImage (picture, this);

		picture2 = getToolkit ().getImage (PICTURE_PATH2);
		prepareImage (picture2, this);

		picture3 = getToolkit ().getImage (PICTURE_PATH3);
		prepareImage (picture3, this);

		picture4 = getToolkit ().getImage (PICTURE_PATH4);
		prepareImage (picture4, this);

		picture5 = getToolkit ().getImage (PICTURE_PATH5);
		prepareImage (picture5, this);

		picture6 = getToolkit ().getImage (PICTURE_PATH6);
		prepareImage (picture6, this);

		picture7 = getToolkit ().getImage (PICTURE_PATH7);
		prepareImage (picture7, this);

		picture8 = getToolkit ().getImage (PICTURE_PATH8);
		prepareImage (picture8, this);

		picture9 = getToolkit ().getImage (PICTURE_PATH9);
		prepareImage (picture9, this);

		picture10 = getToolkit ().getImage (PICTURE_PATH10);
		prepareImage (picture10, this);

		picture11 = getToolkit ().getImage (PICTURE_PATH11);
		prepareImage (picture11, this);

		picture12 = getToolkit ().getImage (PICTURE_PATH12);
		prepareImage (picture12, this);

		picture13 = getToolkit ().getImage (PICTURE_PATH13);
		prepareImage (picture13, this);

		//Buttons for Student page
		studentButton = new Button ("STUDENT LOGIN");
		studentButton.setBounds (470, 275, 300, 50);
		manageStudentBalanceButton = new Button ("MANAGE STUDENT BALANCE");
		manageStudentBalanceButton.setBounds (470, 200, 300, 50);
		checkoutButton = new Button ("CHECK  OUT BOOK");
		checkoutButton.setBounds (470, 300, 300, 50);
		returnButton = new Button ("RETURN BOOK");
		returnButton.setBounds (470, 400, 300, 50);
		manageStudentBalanceDeductOkay = new Button ("OK");
		manageStudentBalanceDeductOkay.setBounds (730, 433, 60, 60);
		manageStudentBalanceDeduct = new Button ("DEDUCT FINE");
		manageStudentBalanceDeduct.setBounds (470, 200, 300, 50);
		manageStudentBalanceAdd = new Button ("ADD FINE");
		manageStudentBalanceAdd.setBounds (470, 300, 300, 50);
		manageStudentBalanceAddOkay = new Button ("OK");
		manageStudentBalanceAddOkay.setBounds (730, 433, 60, 60);
		studentButton.addActionListener (this);
		manageStudentBalanceButton.addActionListener (this);
		checkoutButton.addActionListener (this);
		manageStudentBalanceAddOkay.addActionListener (this);
		manageStudentBalanceDeduct.addActionListener (this);
		manageStudentBalanceDeductOkay.addActionListener (this);
		manageStudentBalanceAdd.addActionListener (this);
		//textfields
		addFine= new TextField("",100); 
		addFine.setBounds (472, 303,270, 20);
		deductFine= new TextField("Type in a fine to deduct (number value)",100); 
		deductFine.setBounds (472, 283,270, 20);


		//Buttons for Staff page
		staffButton = new Button ("ADMINISTRATION");
		staffButton.setBounds (470, 150, 300, 50);
		addStudentButton = new Button ("Add a Student");
		addStudentButton.setBounds (100, 265, 260, 50);
		deleteStudentButton = new Button ("Delete a Student");
		deleteStudentButton.setBounds (100, 355, 260, 50);
		backToMainButton = new Button ("Main");
		backToMainButton.setBounds (10, 433, 60, 60);
		addBookButton = new Button ("Add a Book");
		addBookButton.setBounds (450, 265, 260, 50);
		deleteBookButton = new Button ("Delete a book");
		deleteBookButton.setBounds (450, 355, 260, 50);
		addNewBook = new Button ("ADD A NEW BOOK");
		addNewBook.setBounds (120, 355,570, 60);
		addExistingBook = new Button ("ADD AN EXISTING BOOK");
		addExistingBook.setBounds (120, 135, 570, 60);
		deleteOneBook = new Button ("DELETE A SINGLE BOOK");
		deleteOneBook.setBounds (120, 355,570, 60);
		deleteAllBook = new Button ("DELETE ALL EXISTING COPIES");
		deleteAllBook.setBounds (120, 135, 570, 60);
		okayAllBookDelete = new Button ("Ok");
		okayAllBookDelete.setBounds (730, 433, 60, 60);
		okayOneBookDelete = new Button ("Ok");
		okayOneBookDelete.setBounds (730, 433, 60, 60);
		okayDeleteStudent = new Button ("Ok");
		okayDeleteStudent.setBounds (730, 433, 60, 60);
		okayCheckout = new Button ("Ok");
		okayCheckout .setBounds (730, 433, 60, 60);
		okayReturn = new Button ("Ok");
		okayReturn .setBounds (730, 433, 60, 60);



		staffButton.addActionListener (this);
		deleteBookButton.addActionListener (this);
		backToMainButton.addActionListener (this);
		addBookButton.addActionListener (this);
		deleteStudentButton.addActionListener (this);
		addStudentButton.addActionListener (this);
		addNewBook.addActionListener(this);
		addExistingBook.addActionListener(this);
		deleteOneBook.addActionListener(this);
		deleteAllBook.addActionListener(this);
		okayAllBookDelete.addActionListener(this);
		okayOneBookDelete.addActionListener(this);
		okayDeleteStudent.addActionListener(this);
		okayCheckout.addActionListener(this);

		//buttons for search page
		lookUpButton = new Button ("SEARCH");
		lookUpButton.setBounds (470, 400, 300, 50);
		lookUpButton.addActionListener (this);

		//Choice 
		listOfBooks.setBounds(473,270,270,100);
		listOfStudents.setBounds(473,270,270,100);

		//add student TextField
		studentFName= new TextField("",20); 
		studentLName= new TextField("",100); 
		studentNum= new TextField("",100); 
		studentFName.setBounds (472, 233,270, 38);
		studentLName.setBounds (472, 290,270, 38);;
		studentNum.setBounds (472, 351,270, 38);;

		//Add Book TextField
		bookTitle= new TextField("",100); 
		bookAuthor= new TextField("",100); 
		bookISBN= new TextField("",100); 
		bookCategory= new TextField("",100); 
		bookCost= new TextField("",100); 
		bookRating= new TextField("",100); 
		bookISBN.setBounds (420, 277,300, 20);
		bookAuthor.setBounds (420, 243,300, 20);
		bookTitle.setBounds (420, 210,300, 20);
		bookCategory.setBounds (420, 313,300, 20);
		bookCost.setBounds (420, 345,300, 20);
		bookRating.setBounds (420, 380,300, 20);

		//Buttons for main page
		add (studentButton);
		add (staffButton);
		add (lookUpButton);

		//Objects
		//pre-made books
		Book book1= new Book ("The Sorcerer's Stone", "J.K.Rowling",1001,"Fantasy",10.99,3,4,4 );
		Book book2= new Book ("The Chamber of Secrets", "J.K.Rowling",1002,"Fantasy",10.99,3,4,4 );
		Book book3= new Book ("Sherlock Holmes", "Arthur Conan Doyle",1100,"Mystery",12.99,3,4,4 );
		Book book4= new Book ("Fault in our Stars", "John Green",1200,"Romance",15.99,3,4,4 );
		//pre-made students
		Student student1= new Student ("Rumsha", "Rafi",768412,0,0, true);
		Student student2= new Student ("Aleeza", "Hashmi",629072,0,0, true);
		//Library object
		library1=new Library (studentList, bookList);
		library1.getBook(book1, book2, book3, book4);
		library1.getStudent(student1,student2);

		MediaTracker tracker = new MediaTracker (this);
		// Add the picture to the list of images to be tracked
		tracker.addImage (picture, 0);
		tracker.addImage (picture2, 1);
		tracker.addImage (picture3, 2);
		tracker.addImage (picture4, 3);
		tracker.addImage (picture5, 4);
		tracker.addImage (picture6, 5);
		tracker.addImage (picture7, 6);
		tracker.addImage (picture8, 7);
		tracker.addImage (picture9, 8);
		tracker.addImage (picture10, 9);
		tracker.addImage (picture11, 10);
		tracker.addImage (picture12, 11);
		tracker.addImage (picture13, 12);
		// Wait until all the images are loaded.  This can throw an
		// InterruptedException although it's not likely, so we ignore
		// it if it occurs.
		try
		{
			tracker.waitForAll ();
		}
		catch (InterruptedException e)
		{
		}
		// If there were any errors loading the image, then abort the
		// program with a message.
		if (tracker.isErrorAny ())
		{
			showStatus ("Couldn't load " + PICTURE_PATH1);
			return;
		}

		// Initialize the picture size

		picWidth = picture.getWidth (null);
		picHeight = picture.getHeight (null);


		picWidth2 = picture2.getWidth (null);
		picHeight2 = picture2.getHeight (null);


		picWidth3 = picture3.getWidth (null);
		picHeight3 = picture3.getHeight (null);


		picWidth4 = picture4.getWidth (null);
		picHeight4 = picture4.getHeight (null);

		picWidth5 = picture5.getWidth (null);
		picHeight5 = picture5.getHeight (null);


		picWidth6= picture6.getWidth (null);
		picHeight6 = picture6.getHeight (null);


		picWidth7 = picture7.getWidth (null);
		picHeight7 = picture7.getHeight (null);


		picWidth8 = picture8.getWidth (null);
		picHeight8 = picture8.getHeight (null);

		picWidth9 = picture9.getWidth (null);
		picHeight9 = picture9.getHeight (null);

		picWidth10 = picture10.getWidth (null);
		picHeight10 = picture10.getHeight (null);

		picWidth11 = picture11.getWidth (null);
		picHeight11 = picture11.getHeight (null);

		picWidth12 = picture12.getWidth (null);
		picHeight12 = picture12.getHeight (null);

		picWidth12 = picture12.getWidth (null);
		picHeight12 = picture12.getHeight (null);
	} // init method


	public void run ()
	{

		// lower ThreadPriority
		Thread.currentThread ().setPriority (Thread.MIN_PRIORITY);

		// run a long while (true) this means in our case "always"
		while (true)
		{


			Thread.currentThread ().setPriority (Thread.MAX_PRIORITY);
		}

	}


	/** Update - Method, implements double buffering */
	public void update (Graphics g)
	{

		// initialize buffer
		if (dbImage == null)
		{
			dbImage = createImage (this.getSize ().width, this.getSize ().height);
			dbg = dbImage.getGraphics ();
		}

		// clear screen in background
		dbg.setColor (getBackground ());
		dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

		// draw elements in background
		dbg.setColor (getForeground ());
		paint (dbg);

		// draw image on the screen
		g.drawImage (dbImage, 0, 0, this);

	}


	public void actionPerformed (ActionEvent evt)
	{
		// Here we will ask what component called this method
		if (evt.getSource () == backToMainButton)
		{

			mainScreen = true;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;

			listOfBooks.removeAll();

			remove (backToMainButton);
			remove (addStudentButton);
			remove (deleteStudentButton);
			remove (deleteBookButton);
			remove (addBookButton);
			remove(addNewBook);
			remove(addExistingBook);
			remove(deleteOneBook);
			remove(deleteAllBook);
			remove(listOfBooks);
			remove(bookTitle);
			remove(bookISBN);
			remove(bookAuthor);
			remove(bookISBN);
			remove(bookCategory);
			remove(bookCost);
			remove(bookRating);
			remove (studentFName);
			remove(studentLName);
			remove(studentNum);
			remove(listOfStudents);
			remove(deleteAllBook);
			remove(deleteOneBook);
			remove(okayDeleteStudent);
			remove(okayOneBookDelete);
			remove(okayAllBookDelete);


			add (studentButton);
			add (staffButton);
			add (lookUpButton);



			repaint ();
		}
		
		
		else if (evt.getSource () == studentButton)
		{

			mainScreen = false;
			studentCheckInCheckOut = true; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			addExistingBookPage=false;
			remove (staffButton);
			remove (studentButton);
			remove (lookUpButton);

			add(checkoutButton);
			add(returnButton);
			add(manageStudentBalanceButton);
			add (backToMainButton);


			repaint ();
		}
		else if (evt.getSource () == returnButton)
		{
			listOfStudents.removeAll();
			listOfBooks.removeAll();
			add(listOfStudents);
			add(listOfBooks);
			

			for (int i=0; i<library1.returnStudent().size();i++)
			{
				listOfStudents.add(String.valueOf(library1.returnStudent().get(i).getStudentNum()));
			}
			

			for (int i=0; i<library1.returnBook().size();i++)
			{
				listOfBooks.add(library1.returnBook().get(i).getTitle());
			}

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = true;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			addExistingBookPage=false;
			
			remove(checkoutButton);
			remove(returnButton);
			remove(manageStudentBalanceButton);
			add(okayCheckout);
			
				
			
			
			listOfStudents.setBounds(473,220,270,100);			
			
			repaint ();
		}
		else if (evt.getSource () == okayReturn)
		{
			listOfStudents.removeAll();
			listOfBooks.removeAll();
			add(listOfStudents);
			add(listOfBooks);
			

			for (int i=0; i<library1.returnStudent().size();i++)
			{
				listOfStudents.add(String.valueOf(library1.returnStudent().get(i).getStudentNum()));
			}
			

			for (int i=0; i<library1.returnBook().size();i++)
			{
				listOfBooks.add(library1.returnBook().get(i).getTitle());
			}

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = true;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			addExistingBookPage=false;
			
			remove(checkoutButton);
			remove(returnButton);
			remove(manageStudentBalanceButton);
			add(okayCheckout);
			
			int userNum = Integer.valueOf(listOfStudents.getSelectedItem());
			String title = listOfBooks.getSelectedItem();
			library1.checkoutBook(title,userNum);
			
			
			
			listOfStudents.setBounds(473,220,270,100);			
			
			repaint ();
		}

		else if (evt.getSource () == checkoutButton)
		{
			listOfStudents.removeAll();
			listOfBooks.removeAll();
			add(listOfStudents);
			add(listOfBooks);
			

			for (int i=0; i<library1.returnStudent().size();i++)
			{
				listOfStudents.add(String.valueOf(library1.returnStudent().get(i).getStudentNum()));
			}
			

			for (int i=0; i<library1.returnBook().size();i++)
			{
				listOfBooks.add(library1.returnBook().get(i).getTitle());
			}

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = true;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			addExistingBookPage=false;
			
			remove(checkoutButton);
			remove(returnButton);
			remove(manageStudentBalanceButton);
			add(okayCheckout);
			
				
			
			
			listOfStudents.setBounds(473,220,270,100);			
			
			repaint ();
		}
		else if (evt.getSource () == okayCheckout)
		{
			listOfStudents.removeAll();
			listOfBooks.removeAll();
			add(listOfStudents);
			add(listOfBooks);
			

			for (int i=0; i<library1.returnStudent().size();i++)
			{
				listOfStudents.add(String.valueOf(library1.returnStudent().get(i).getStudentNum()));
			}
			

			for (int i=0; i<library1.returnBook().size();i++)
			{
				listOfBooks.add(library1.returnBook().get(i).getTitle());
			}

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = true;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			addExistingBookPage=false;
			
			remove(checkoutButton);
			remove(returnButton);
			remove(manageStudentBalanceButton);
			add(okayCheckout);
			
			int userNum = Integer.valueOf(listOfStudents.getSelectedItem());
			String title = listOfBooks.getSelectedItem();
			library1.checkoutBook(title,userNum);
			
			
			
			listOfStudents.setBounds(473,220,270,100);			
			
			repaint ();
		}


		else if (evt.getSource () == manageStudentBalanceButton)
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = true;
			errorPage = false;
			finePage = false;
			staffPage = false;
			addExistingBookPage=false;
			manageStudentBalanceAddPage = false;
			manageStudentBalanceDeductPage = false;

			remove(checkoutButton);
			remove(returnButton);
			remove(manageStudentBalanceButton);

			add(manageStudentBalanceAdd);
			add(manageStudentBalanceDeduct);
			add (backToMainButton);


			repaint ();
		}
			else if (evt.getSource () == manageStudentBalanceAdd) //****************HELP FIX THIS PLEASE************
		{
			
			for (int i=0; i<library1.returnStudent().size();i++)
			{
				listOfStudents.add(String.valueOf(library1.returnStudent().get(i).getStudentNum()));
			}
			
			int studentNum = Integer.valueOf(listOfStudents.getSelectedItem());
			double fines = Double.parseDouble(addFine.getText());
			listOfStudents.removeAll();
			library1.addFines(studentNum,fines);

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = true;
			errorPage = false;
			finePage = false;
			staffPage = false;
			addExistingBookPage=false;
			manageStudentBalanceAddPage = false;
			manageStudentBalanceDeductPage = false;

			remove(manageStudentBalanceAdd);
			remove(manageStudentBalanceDeduct);
		
			add(listOfStudents);
			add(addFine);
			add(manageStudentBalanceAddOkay);

		

			repaint ();
		}
		/*		else if (evt.getSource () == manageStudentBalanceButton)
			{

				mainScreen = false;
				studentCheckInCheckOut = true; 
				lookUpPage = false;
				studentProfilePage = false;
				returnPage = false;
				newReturnPage = false;
				checkoutPage = false;
				newCheckOutPage = false;
				searchByCatagory = false;
				searchByAuthor = false;
				compareBooks = false;
				addStudent = false;
				addbook = false;
				deleteStudent = false;
				deleteBook = false;
				manageStudentBalance = false;
				errorPage = false;
				finePage = false;
				staffPage = false;
				addExistingBookPage=false;


				add(manageStudentBalanceAddOkay);
				add (backToMainButton);


				repaint ();
			}
		 */

		else if (evt.getSource () == lookUpButton)
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = true;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			remove (staffButton);
			remove (studentButton);
			remove (lookUpButton);

			add (backToMainButton);


			repaint ();
		}

		else if (evt.getSource () == staffButton)
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = true;

			remove (staffButton);
			remove (studentButton);
			remove (lookUpButton);
			add (addStudentButton);
			add (deleteStudentButton);
			add (backToMainButton);
			add (addBookButton);			
			add (deleteBookButton);


			repaint ();
		}
		if (evt.getSource () == addStudentButton)
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = true;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			remove (staffButton);
			remove (studentButton);
			remove (lookUpButton);
			remove (addStudentButton);
			remove (deleteStudentButton);
			remove (addBookButton);			
			remove (deleteBookButton);

			add (studentFName);
			add(studentLName);
			add(studentNum);


			repaint ();
		}

		if (evt.getSource () == deleteStudentButton)
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = true;
			deleteBook = false;


			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			remove (staffButton);
			remove (studentButton);
			remove (lookUpButton);
			remove (addStudentButton);
			remove (deleteStudentButton);
			remove (addBookButton);			
			remove (deleteBookButton);

			listOfStudents.removeAll();

			for (int i=0; i<library1.returnStudent().size();i++)
			{
				listOfStudents.add(String.valueOf(library1.returnStudent().get(i).getStudentNum()));
			}
			add(listOfStudents);
			add(okayDeleteStudent);

			repaint ();
		}
		else if (evt.getSource () == (okayDeleteStudent))
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = true;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			deleteOneBookPage=false;
			deleteAllBookPage=false;

			remove(listOfStudents);


			int userNum = Integer.valueOf(listOfStudents.getSelectedItem());

			library1.deleteStudent(userNum);
			listOfStudents.removeAll();
			for (int i=0; i<library1.returnStudent().size();i++)
			{
				listOfStudents.add((String.valueOf(library1.returnStudent().get(i).getStudentNum())));
			}
			System.out.println(library1.returnStudent().size());	
			add(listOfStudents);

			repaint ();

		}
		else if (evt.getSource () == (addBookButton))
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = true;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;

			add(addNewBook);
			add(addExistingBook);


			remove (addStudentButton);
			remove (deleteStudentButton);
			remove (deleteBookButton);
			remove (addBookButton);




			repaint ();

		}
		else if (evt.getSource () == (addNewBook))
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			addNewBookPage=true;

			remove(addNewBook);
			remove(addExistingBook);

			add(bookTitle);
			add(bookISBN);
			add(bookAuthor);
			add(bookISBN);
			add(bookCategory);
			add(bookCost);
			add(bookRating);
			repaint ();

		}
		else if (evt.getSource () == (addExistingBook))
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			addNewBookPage=false;
			addExistingBookPage=true;

			remove(addNewBook);
			remove(addExistingBook);

			listOfBooks.removeAll();

			for (int i=0; i<library1.returnBook().size();i++)
			{
				listOfBooks.add(library1.returnBook().get(i).getTitle());
			}

			add(listOfBooks);
			repaint ();

		}
		else if (evt.getSource () == (deleteBookButton))
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = true;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;

			listOfBooks.removeAll();
			add(deleteOneBook);
			add(deleteAllBook);


			remove (addStudentButton);
			remove (deleteStudentButton);
			remove (deleteBookButton);
			remove (addBookButton);




			repaint ();

		}

		else if (evt.getSource () == (deleteAllBook))
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			deleteOneBookPage=false;
			deleteAllBookPage=true;

			System.out.println(library1.returnBook().size());

			listOfBooks.removeAll();

			for (int i=0; i<library1.returnBook().size();i++)
			{
				listOfBooks.add(library1.returnBook().get(i).getTitle());
			}

			add(listOfBooks);
			add(okayAllBookDelete);

			remove(deleteOneBook);
			remove(deleteAllBook);




			repaint ();

		}
		else if (evt.getSource () == (okayAllBookDelete))
		{

			remove(listOfBooks);
			remove(okayAllBookDelete);	

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			deleteOneBookPage=false;
			deleteAllBookPage=true;


			String title = listOfBooks.getSelectedItem();

			library1.deleteAllBook(title);
			listOfBooks.removeAll();
			for (int i=0; i<library1.returnBook().size();i++)
			{
				listOfBooks.add(library1.returnBook().get(i).getTitle());
			}
			System.out.println(library1.returnBook().size());	
			add(listOfBooks);
			add(okayAllBookDelete);



			repaint ();

		}
		else if (evt.getSource () == (deleteOneBook))
		{

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			deleteOneBookPage=true;
			deleteAllBookPage=false;

			listOfBooks.removeAll();
			for (int i=0; i<library1.returnBook().size();i++)
			{
				listOfBooks.add(library1.returnBook().get(i).getTitle());
			}

			System.out.println(library1.returnBook().size());
			add(listOfBooks);
			add(okayOneBookDelete);

			remove(deleteOneBook);
			remove(deleteAllBook);






			repaint ();

		}
		else if (evt.getSource () == (okayOneBookDelete))
		{

			remove(listOfBooks);
			remove(okayAllBookDelete);	

			mainScreen = false;
			studentCheckInCheckOut = false; 
			lookUpPage = false;
			studentProfilePage = false;
			returnPage = false;
			newReturnPage = false;
			checkoutPage = false;
			newCheckOutPage = false;
			searchByCatagory = false;
			searchByAuthor = false;
			compareBooks = false;
			addStudent = false;
			addbook = false;
			deleteStudent = false;
			deleteBook = false;
			manageStudentBalance = false;
			errorPage = false;
			finePage = false;
			staffPage = false;
			deleteOneBookPage=true;
			deleteAllBookPage=false;

			String title = listOfBooks.getSelectedItem();

			library1.removeBookCopies(title);
			listOfBooks.removeAll();
			for (int i=0; i<library1.returnBook().size();i++)
			{
				listOfBooks.add(library1.returnBook().get(i).getTitle());
			}



			System.out.println(library1.returnBook().size());	
			add(listOfBooks);
			add(okayOneBookDelete);



			repaint ();

		}
	}


	public void paint (Graphics g)
	{
		// Place the body of the drawing method here

		// set color

		if (mainScreen == true)
		{
			g.drawImage (picture, 0, 0, null);
		}
		else if (deleteStudent == true)
		{
			g.drawImage (picture8, 0, 0, null);
		}

		else if (addStudent == true)
		{
			g.drawImage (picture13, 0, 0, null);
		}
		else if (staffPage == true)
		{
			g.drawImage (picture2, 0, 0, null);
		}

		else if (deleteBook == true)
		{
			g.drawImage (picture11, 0, 0, null);
		}
		else if (addbook==true)
		{
			g.drawImage (picture7, 0, 0, null);
		}
		else if (addExistingBookPage == true)
		{
			g.drawImage (picture6, 0, 0, null);
		}
		else if (deleteOneBookPage == true)
		{
			g.drawImage (picture5, 0, 0, null);//fix later
		}
		else if (deleteAllBookPage == true)
		{
			g.drawImage (picture10, 0, 0, null);
		}
		else if (studentCheckInCheckOut == true)
		{
			g.drawImage (picture3, 0, 0, null);
		}
		else if (addNewBookPage == true)
		{
			g.drawImage (picture12, 0, 0, null);
		}




		g.setColor (Color.cyan);
		g.drawString ("(" + xpos + "," + ypos + ")", xpos, ypos);
	} // paint method



} // MovingBallApplet5 class




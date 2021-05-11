package classes;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
	
	Scanner scp = new Scanner(System.in);
	Scanner scb = new Scanner(System.in);
	Scanner scl = new Scanner(System.in);
	Scanner scu = new Scanner(System.in);
	BooksManagement bm = new BooksManagement();
	UserManagement um = new UserManagement();
	LoansManagement lm = new LoansManagement();
	InstanceManagement im = new InstanceManagement();
	
	public void showMenuP() throws SQLException {
		boolean salir = false;
		int optionP = 0;
		int optionB = 0;
		int optionL = 0;
		int optionU = 0;
		int optionI = 0;
	
		System.out.println("**** MENU ****");
		System.out.println("Select 1 to enter to the books menu. ");
		System.out.println("Select 2 to enter to the loans menu. ");
		System.out.println("Select 3 to enter to the users menu. ");
		System.out.println("Select 4 to enter to the instances menu. ");
		System.out.println("Select 5 to exit. ");
		
		try {
			while(!salir) {
				optionP = scp.nextInt();
				switch(optionP) {
				case 1:
					System.out.println("**** MENU BOOKS ****");
					System.out.println("Select 1 to enter to show all books. ");
					System.out.println("Select 2 to display the details of a book. ");
					System.out.println("Select 3 to create a book. ");
					System.out.println("Select 4 to delete a book. ");
					System.out.println("Select 5 to search for a book by title. ");
					System.out.println("Select 6 to search for a book by author. ");
					System.out.println("Select 7 to modify a book. ");
					optionB = scb.nextInt();
						switch(optionB) {
						case 1:
							bm.showAllBooks();
							break;
						case 2:
							bm.showABook();
							break;
						case 3:
							System.out.println("");
			            	String filling = scp.nextLine();
							System.out.println("Introduce the title: ");
			            	String title = scp.nextLine();
			            	System.out.println("Introduce the description: ");
			            	String description = scp.nextLine();
			            	System.out.println("Introduce the number of sheets: ");
			            	int num_sheets = scp.nextInt();
			            	System.out.println("Introduce the isbn: ");
			            	String isbn = scp.next();
			            	isbn = bm.ValidationIsbn(isbn);
			            	System.out.println("Introduce the editorial: ");
			            	String editorial = scp.next();
			            	System.out.println("Introduce the edition:");
			            	int edition = scp.nextInt();
			            	System.out.println("Introduce the date of publication");
	                        String date_publication = scp.next();      
			            	bm.showAllAuthors();
			            	System.out.println("Introduce the id of author: ");
			            	int id_author = scp.nextInt();
			            	bm.createBook(title, description, num_sheets, isbn, editorial, edition, date_publication, id_author);
			                break;
						case 4:
							bm.deleteBook();
							break;
						case 5:
							bm.lookForBookT();
							break;
						case 6:
							bm.lookForBookA();
							break;
						case 7:
							System.out.println("Introduce the id of the book: ");
                            int id = scp.nextInt();
                            System.out.println("Introduce the title: ");
                            String Title = scp.next();
                            System.out.println("Introduce the description: ");
                            String Description = scp.next();
                            System.out.println("Introduce the number of sheets: ");
                            int Number_of_sheets = scp.nextInt();
                            System.out.println("Introduce the isbn: ");
                            String ISBN = scp.next();
                            ISBN = bm.ValidationIsbn(ISBN);
                            System.out.println("Introduce the editorial of the book: ");
                            int Editorial = scp.nextInt();
                            System.out.println("Introduce the edition of the book: ");
                            int Edition = scp.nextInt();
                            System.out.println("Introduce the date of publication: ");
                            String Date_of_publication = scp.next();
                            bm.modifyBook(id, Title, Description, Number_of_sheets, ISBN, Editorial, Edition, Date_of_publication);	
						}
					break;
				case 2:
					System.out.println("**** MENU LOANS ****");
					System.out.println("Select 1 to enter to show all loans. ");
					System.out.println("Select 2 to display the details of a loan. ");
					System.out.println("Select 3 to create a loan. ");
					System.out.println("Select 4 to return a loan. ");
					optionL = scb.nextInt();
						switch(optionL) {
						case 1:
							lm.showLoans();
							break;
						case 2:
							lm.showALoan();
							break;
						case 3:
							um.showUsers();
                            System.out.println("Introduce the user_id of a loan: ");
                            int userId = scp.nextInt();
                            System.out.println("Introduce the Departure Date of a loan: ");
                            String departure_Date = scp.next();
                            System.out.println("Introduce the Maximun Date To Return of a loan: ");
                            String maximun_Date_To_Return = scp.next();
                            lm.createALoan(userId, departure_Date, maximun_Date_To_Return);
							break;
						case 4:
							lm.returnLoan();
							break;
						}	
					break;
				case 3:
					System.out.println("**** MENU USERS ****");
					System.out.println("Select 1 to enter to show all users. ");
					System.out.println("Select 2 to display the details of a user. ");
					System.out.println("Select 3 to create a user. ");
					System.out.println("Select 4 to delete a user. ");
					optionU = scb.nextInt();
						switch(optionU) {
						case 1:
							um.showUsers();
							break;
						case 2:
							um.showAUser();
							break;
						case 3:
							System.out.println("Introduce the name: ");
			            	String name = scp.next();
			            	System.out.println("Introduce the surname: ");
			            	String surname = scp.next();
			            	System.out.println("Introduce the Email: ");
			            	String email = scp.next();
			            	email = um.validationEmail(email);
			            	System.out.println("Introduce the phone: ");
			            	String phone = scp.next();
			            	phone = um.validationPhone(phone);
			            	um.createUser(name,surname,email, Integer.parseInt(phone));
							break;
						case 4:
							um.deleteUser();
							break;
						}	
					break;
				case 4:
					System.out.println("**** MENU INSTANCES ****");
					System.out.println("Select 1 to enter to show all instances. ");
					System.out.println("Select 2 to display the details of a instance. ");
					System.out.println("Select 3 to create a instance. ");
					System.out.println("Select 4 to modify a instance. ");
					optionI = scb.nextInt();
					switch(optionI) {
					case 1:
						im.showInstances();
						break;
					case 2:
						im.showAInstance();
						break;
					case 3:
						im.createAInstance();
						break;
					case 4:
						System.out.println("Introduce the id of the instance: ");
                        int id_books = scp.nextInt();
                        System.out.println("Introduce the date of the admission of the instance");
                        String Date_of_admission = scp.next();
                        System.out.println("Introduce the quantity of the instance: ");
                        int quantity = scp.nextInt();
                        im.modifyInstance(id_books,Date_of_admission, quantity);
                        break;
					}
					break;
				case 0:
					System.out.println();
					System.out.println("**** MENU ****");
					System.out.println("Select 1 to enter to the books menu. ");
					System.out.println("Select 2 to enter to the loans menu. ");
					System.out.println("Select 3 to enter to the users menu. ");
					System.out.println("Select 4 to enter to the instances menu. ");
					System.out.println("Select 5 to exit. ");
					break;
				case 5:
					salir = true;
					break;
				default:
					System.out.println("The options have to be between 0 and 5. ");
				}	
			}
		}catch (InputMismatchException e) {
			System.out.println("You need to introduce a number. ");
			scp.nextInt();
		}
		System.out.println("End of menu. ");
	}
}

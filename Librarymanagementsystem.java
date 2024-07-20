import java.sql.*;
import java.util.Scanner;

public class Librarymanagementsystem {
    public static void main(String[] args)  {
        try {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/library", "root", "mysqlpassword"
            );
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM books");
            statement = connection.createStatement();

            String insertBookSQL = "INSERT IGNORE INTO books (id, Title, Author) VALUES (?, ?, ?)";


            PreparedStatement preparedstatement = connection.prepareStatement(insertBookSQL);
            preparedstatement.setString(1, "23");
            preparedstatement.setString(2, "Things We Left Behind");
            preparedstatement.setString(3, "Lucy");
            preparedstatement.executeUpdate();
            preparedstatement.setString(1, "16");  // Set the first placeholder to 24
            preparedstatement.setString(2, "Ugly Love");  // Set the second placeholder to the book title
            preparedstatement.setString(3, "Colleen Hoover");  // Set the third placeholder to the author name
            preparedstatement.executeUpdate();
            preparedstatement.setString(1, "1");  // Set the first placeholder to 24
            preparedstatement.setString(2, "Wildfire");  // Set the second placeholder to the book title
            preparedstatement.setString(3, "Hannah Grace ");  // Set the third placeholder to the author name
            preparedstatement.executeUpdate();


            Library library = new Library();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Library Management System");
                System.out.println("Choose an option: ");
                System.out.println("1. Add Book");
                System.out.println("2. Remove Book");
                System.out.println("3. Search Book");
                System.out.println("4. List Books");
                System.out.println("5. Add Member");
                System.out.println("6. Remove Member");
                System.out.println("7. Search Member");
                System.out.println("8. List Members");
                System.out.println("9. Exit");
                String input = scanner.nextLine();
                int option = Integer.parseInt(input);
                switch (option) {
                    case 1:
                        System.out.println("Enter book ID: ");
                        String bookId = scanner.nextLine();
                        System.out.println("Enter book title: ");
                        String title = scanner.nextLine();
                        System.out.println("Enter book author: ");
                        String author = scanner.nextLine();
                        Book book = new Book(bookId, title, author);
                        library.Add(book);
                        preparedstatement.setString(1, bookId);
                        preparedstatement.setString(2, title);
                        preparedstatement.setString(3, author);
                        int rowsInserted = preparedstatement.executeUpdate();
                        if (rowsInserted > 0) {
                            System.out.println("Book inserted successfully!");
                        }
                        break;
                    case 2:
                        String deleteBookSQL = "DELETE FROM books WHERE id = ?";
                        PreparedStatement preparedStatement = connection.prepareStatement(deleteBookSQL);
                        System.out.print("Enter book ID to remove: ");
                        bookId = scanner.nextLine();
                        preparedStatement.setString(1, bookId);
                        library.Remove(bookId);
                        int rowsDeleted = preparedStatement.executeUpdate();
                        if (rowsDeleted > 0) {
                            System.out.println("Book removed successfully!");
                        } else {
                            System.out.println("No book found with the provided ID.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter book ID to search: ");
                        bookId = scanner.nextLine();
                        String searchBookSQL = "SEARCH FROM books WHERE id = ?";
                        PreparedStatement preparedstatement1 = connection.prepareStatement(searchBookSQL);
                        preparedstatement1.setString(1, bookId);
                        Book searchedBook = library.Search(bookId);
                        if (searchedBook!=null) {
                            System.out.println(searchedBook);
                        } else {
                            System.out.println("Book not found.");
                        }
                        break;
                    case 4:
                        System.out.println("List of Books:");
                        for (Book books : library.listBooks()) {
                            System.out.println(books);
                        }
                        break;
                    case 5:
                        String addmember = "INSERT IGNORE INTO members (id, username) VALUES (?, ?)";
                        PreparedStatement preparedstatement2 = connection.prepareStatement(addmember);
                        System.out.print("Enter member ID: ");
                        String memberId = scanner.nextLine();
                        System.out.print("Enter member name: ");
                        String name = scanner.nextLine();
                        Member member = new Member(memberId, name);
                        library.addMember(member);
                        member.toString();
                        preparedstatement2.setString(1,memberId);
                        preparedstatement2.setString(2, name);
                        int rowsInserted2 = preparedstatement2.executeUpdate();
                        if (rowsInserted2 > 0) {
                            System.out.println("Member inserted successfully!");
                        }
                        break;
                    case 6:
                        String deletemember = "DELETE FROM members WHERE id = ?";
                        PreparedStatement preparedStatement3 = connection.prepareStatement(deletemember);
                        System.out.print("Enter member ID to remove: ");
                        memberId = scanner.nextLine();
                        preparedStatement3.setString(1, memberId);
                        library.removeMember(memberId);
                        int rowsDeleted2 = preparedStatement3.executeUpdate();
                        if (rowsDeleted2 > 0) {
                            System.out.println("Book removed successfully!");
                        } else {
                            System.out.println("No book found with the provided ID.");
                        }
                        break;
                    case 7:
                        System.out.print("Enter member ID to search: ");
                        memberId = scanner.nextLine();
                        Member searchedMember = library.searchMember(memberId);
                        if (searchedMember != null) {
                            System.out.println(searchedMember);
                        } else {
                            System.out.println("Member not found.");
                        }
                        break;
                    case 8:
                        System.out.println("List of Members:");
                        for (Member members : library.listMembers()) {
                            System.out.println(members);
                        }
                        break;
                    case 9:
                        System.out.println("Exit");
                        scanner.close();
                        System.exit(9);
                        break;
                    default:
                        System.out.println("Please try again, enter a number from 1 to 9 : ");
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
}
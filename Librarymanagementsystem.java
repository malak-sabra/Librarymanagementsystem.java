import java.util.Scanner;
public class Librarymanagementsystem {
    public static void main(String[] args) {
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
                    System.out.println("Book Added.");
                    break;
                case 2:
                    System.out.print("Enter book ID to remove: ");
                    bookId = scanner.nextLine();
                    library.Remove(bookId);
                    System.out.println("Book Removed.");
                    break;
                case 3:
                    System.out.print("Enter book ID to search: ");
                    bookId = scanner.nextLine();
                    Book searchedBook = library.Search(bookId);
                    if (searchedBook != null) {
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
                    System.out.print("Enter member ID: ");
                    String memberId = scanner.nextLine();
                    System.out.print("Enter member name: ");
                    String name = scanner.nextLine();
                    Member member = new Member(memberId, name);
                    library.addMember(member);
                    member.toString();
                    System.out.println("Member Added");
                    break;
                case 6:
                    System.out.print("Enter member ID to remove: ");
                    memberId = scanner.nextLine();
                    library.removeMember(memberId);
                    System.out.println("Member Removed.");
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
}
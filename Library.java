import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Library {
    private List<Book> books;
    private List<Member> members;
    public Library()
    {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    public void Add(Book book)
    {
        books.add(book);
    }

    public void Remove(String bookId)
    {
        books.removeIf(book -> book.getId().equals(bookId));
    }

    public Book Search(String bookId) {
        for(Book book: books)
        {
            if(book.getId().equals(bookId))
            {
                return book;
            }
        }
        return null;
    }

    public List<Book> listBooks() {
        String query = "SELECT id, Title, Author FROM books";
        try
        {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/library", "root", "mysqlpassword"
            );
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                String id = resultSet.getString("id");
                String title = resultSet.getString("Title");
                String author = resultSet.getString("Author");

                // Create a Book object and add it to the list
                Book book = new Book(id, title, author);
                books.add(book);
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return books;
    }

    public void addMember(Member member) {
        members.add(member);
    }

    public void removeMember(String memberId) {
        members.removeIf(member -> member.getId().equals(memberId));
    }

    public Member searchMember(String memberId) {
        for (Member member : members) {
            if (member.getId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    public List<Member> listMembers() {
        String query = "SELECT id, username FROM members";
        try
        {
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/library", "root", "mysqlpassword"
            );
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                String id = resultSet.getString("id");
                String username = resultSet.getString("Username");
                Member member =new Member(id,username);
                members.add(member);
            }

        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        return members;
    }
}

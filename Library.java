import java.util.List;
import java.util.ArrayList;
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
        for (Book book : books) {
            if (book.getId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public List<Book> listBooks() {
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
        return members;
    }
}

public class Member {
    private String id;
    private String username;

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
    public String getId() {
        return id;
    }

    public String getName() {
        return username;
    }
    public String toString() {
        return "Member [id=" + id + ", name=" + username + "]";
    }
}



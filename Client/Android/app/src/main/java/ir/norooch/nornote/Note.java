package ir.norooch.nornote;

public class Note {
    private int id;
    private String title;
    private String content;
    private String user;

    public Note(String title, String content, String user) {
        this.title = title;
        this.content = content;
        this.user = user;
    }

    public Note() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

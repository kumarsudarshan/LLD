package library.models;

public class Author {
    private Integer authorId;
    private String name;
    private Integer bookId;
    private String bookTitle;

    public Author(Integer authorId, String name, Integer bookId, String bookTitle) {
        this.authorId = authorId;
        this.name = name;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
}

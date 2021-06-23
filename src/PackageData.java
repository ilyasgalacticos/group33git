import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {

    private String operationType;
    private Books book;
    private ArrayList<Books> books;

    public PackageData(String operationType, Books book, ArrayList<Books> books) {
        this.operationType = operationType;
        this.book = book;
        this.books = books;
    }

    public PackageData() {
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public ArrayList<Books> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Books> books) {
        this.books = books;
    }
}

package snowman.business;

import java.io.Serializable;

/**
 * Immutable class
 */
final public class BookCopy implements Serializable {

    private static final long serialVersionUID = -63976228084869815L;
    private String bookISBN;
    private String title;
    public CheckoutRecordEntry checkoutRecordEntry;
    private int copyNum;
    private boolean isAvailable;

    public BookCopy(String bookISBN, String title, int copyNum, boolean isAvailable) {
        this.bookISBN = bookISBN;
        this.title = title;
        this.copyNum = copyNum;
        this.isAvailable = isAvailable;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public int getCopyNum() {
        return copyNum;
    }

    public void changeAvailability() {
        isAvailable = !isAvailable;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null) return false;
        if (!(ob instanceof BookCopy)) return false;
        BookCopy copy = (BookCopy) ob;
        return copy.getIsbn().equals(this.getIsbn()) && copy.copyNum == copyNum;
    }

    public String getIsbn() {
        return this.bookISBN;
    }

    @Override
    public String toString() {
        return String.format("copy# %d , available: %b", getCopyNum(), isAvailable());
    }

    public void checkout() {
        this.isAvailable = false;
    }

    public String getTitle() {
        return this.title;
    }

    public void setISBN(String isbn) {
        this.bookISBN = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBookISBN() {
        return bookISBN;
    }

    public void setBookISBN(String bookISBN) {
        this.bookISBN = bookISBN;
    }

    public void setCopyNum(int copyNum) {
        this.copyNum = copyNum;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean getAvailable() {
        return isAvailable;
    }
}

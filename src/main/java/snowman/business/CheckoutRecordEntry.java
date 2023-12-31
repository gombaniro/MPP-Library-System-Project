package snowman.business;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * Immutable class
 */
final public class CheckoutRecordEntry implements Serializable {

    private static final long serialVersionUID = -63976228084869815L;
    private LocalDate checkoutDate;
    private LocalDate dueDate;
    public BookCopy bookCopy;
    public CheckoutRecord checkoutRecord;

    public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookCopy) {
        this.checkoutDate = checkoutDate;
        this.dueDate = dueDate;
        this.bookCopy = bookCopy;
        bookCopy.checkoutRecordEntry = this;
    }

    public LocalDate getCheckoutDate() {
        return checkoutDate;
    }

    public void setCheckoutDate(LocalDate checkoutDate) {
        this.checkoutDate = checkoutDate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public BookCopy getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopy bookCopy) {
        this.bookCopy = bookCopy;
    }
}

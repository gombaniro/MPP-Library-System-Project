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
	private BookCopy bookCopy;

	public CheckoutRecordEntry(LocalDate checkoutDate, LocalDate dueDate, BookCopy bookCopy) {
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.bookCopy = bookCopy;
	}
}

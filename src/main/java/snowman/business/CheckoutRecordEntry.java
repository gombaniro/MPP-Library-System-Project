package snowman.business;

import java.io.Serializable;
import java.util.Date;

/**
 * Immutable class
 */
final public class CheckoutRecordEntry implements Serializable {

	private static final long serialVersionUID = -63976228084869815L;
	private Date checkoutDate;
	private Date dueDate;
	private BookCopy bookCopy;

	public CheckoutRecordEntry(Date checkoutDate, Date dueDate, BookCopy bookCopy) {
		this.checkoutDate = checkoutDate;
		this.dueDate = dueDate;
		this.bookCopy = bookCopy;
	}
}

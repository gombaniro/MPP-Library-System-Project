package snowman.business;

import java.io.Serializable;
import java.util.*;

/**
 *
 */
final public class Book implements Serializable {
	
	private static final long serialVersionUID = 6110690276685962829L;
	private List<BookCopy> copies;
	private List<Author> authors;
	private String isbn;
	private String title;
	private int maxCheckoutLength;
	public Book(String isbn, String title, int maxCheckoutLength, List<Author> authors, ArrayList<BookCopy> copyList) {
		this.isbn = isbn;
		this.title = title;
		this.maxCheckoutLength = maxCheckoutLength;
		this.authors = Collections.unmodifiableList(authors);
		this.copies = copyList;
	}
	
	public void updateCopies(BookCopy copy) {
		for(int i = 0; i < copies.size(); ++i) {
			BookCopy c = copies.get(i);
			if(c.equals(copy)) {
				copies.set(i,copy);
			}
		}
	}

	public List<Integer> getCopyNums() {
		List<Integer> retVal = new ArrayList<>();
		for(BookCopy c : copies) {
			retVal.add(c.getCopyNum());
		}
		return retVal;
		
	}
	
	public void addCopy() {
		BookCopy copy = new BookCopy(this.isbn,this.title,copies.size() +1, true);
		copies.add(copy);
	}
	
	
	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(ob.getClass() != getClass()) return false;
		Book b = (Book)ob;
		return b.isbn.equals(isbn);
	}
	
	
//	public boolean isAvailable() {
//		if(copies == null) {
//			return false;
//		}
//		return Arrays.stream(copies.toArray())
//				     .map(l -> l.isAvailable())
//				     .reduce(false, (x,y) -> x || y);
//	}
//	@Override
//	public String toString() {
//		return "isbn: " + isbn + ", maxLength: " + maxCheckoutLength + ", available: " + isAvailable();
//	}
	
	public int getNumCopies() {
		return copies.size();
	}
	
	public String getTitle() {
		return title;
	}
	public BookCopy[] getCopies() {
		BookCopy[] bookCopies = new BookCopy[copies.size()];
		Iterator<BookCopy> it = copies.iterator();
		for(int i = 0; i < copies.size(); i++){
			bookCopies[i] = copies.get(i);
		}
		return bookCopies;
	}
	
	public List<Author> getAuthors() {
		return authors;
	}
	
	public String getIsbn() {
		return isbn;
	}
	
//	public BookCopy getNextAvailableCopy() {
//		Optional<BookCopy> optional
//			= Arrays.stream(copies)
//			        .filter(x -> x.isAvailable()).findFirst();
//		return optional.isPresent() ? optional.get() : null;
//	}
	
	public BookCopy getCopy(int copyNum) {
		for(BookCopy c : copies) {
			if(copyNum == c.getCopyNum()) {
				return c;
			}
		}
		return null;
	}
	public int getMaxCheckoutLength() {
		return maxCheckoutLength;
	}

	
	
	
	
}

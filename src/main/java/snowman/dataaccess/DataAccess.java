package snowman.dataaccess;

import java.util.HashMap;

import snowman.business.Book;
import snowman.business.LibraryMember;

public interface DataAccess { 
	public HashMap<String,Book> readBooksMap();
	public HashMap<String,User> readUserMap();
	public HashMap<String, LibraryMember> readMemberMap();
	public void saveNewMember(LibraryMember member);

	public void saveNewCopy(HashMap<String, Book> books);

	public void saveCheckoutRecord(HashMap<String, Book> books,HashMap<String, LibraryMember> members);
}

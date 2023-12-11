package snowman.dataaccess;

import java.util.HashMap;

import snowman.business.Book;
import snowman.business.LibraryMember;

public interface DataAccess {
    HashMap<String, Book> readBooksMap();

    HashMap<String, User> readUserMap();

    HashMap<String, LibraryMember> readMemberMap();

    void saveNewMember(LibraryMember member);

    void saveNewCopy(HashMap<String, Book> books);

    void saveCheckoutRecord(HashMap<String, Book> books, HashMap<String, LibraryMember> members);

    void saveNewBook(Book book);
}

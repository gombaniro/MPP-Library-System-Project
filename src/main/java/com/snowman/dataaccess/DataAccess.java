package com.snowman.dataaccess;

import java.util.HashMap;

import com.snowman.business.Book;
import com.snowman.business.LibraryMember;

public interface DataAccess { 
	 HashMap<String,Book> readBooksMap();
	 HashMap<String,User> readUserMap();
	 HashMap<String, LibraryMember> readMemberMap();
	 void saveNewMember(LibraryMember member);
}

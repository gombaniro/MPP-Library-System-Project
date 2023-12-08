package snowman.business;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import snowman.dataaccess.Auth;
import snowman.dataaccess.DataAccess;
import snowman.dataaccess.DataAccessFacade;
import snowman.dataaccess.User;

public class SystemController implements ControllerInterface {
	public static Auth currentAuth = null;
	
	public void login(String id, String password) throws LoginException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, User> map = da.readUserMap();
		if(!map.containsKey(id)) {
			throw new LoginException("ID " + id + " not found");
		}
		String passwordFound = map.get(id).getPassword();
		if(!passwordFound.equals(password)) {
			throw new LoginException("Password incorrect");
		}
		currentAuth = map.get(id).getAuthorization();
		
	}

	@Override
	public void checkout(String bookISBN, String memberID) throws CheckoutException {
		DataAccess da = new DataAccessFacade();
		HashMap<String, Book> booksMap = da.readBooksMap();
		if(!booksMap.containsKey(bookISBN)) {
			throw new CheckoutException("BookISBN " + bookISBN + " not found!");
		}
		HashMap<String, LibraryMember> membersMap = da.readMemberMap();
		if(!membersMap.containsKey(memberID)) {
			throw new CheckoutException("MemberID " + memberID + " not found!");
		}

	}

	@Override
	public List<String> allMemberIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readMemberMap().keySet());
		return retval;
	}
	
	@Override
	public List<String> allBookIds() {
		DataAccess da = new DataAccessFacade();
		List<String> retval = new ArrayList<>();
		retval.addAll(da.readBooksMap().keySet());
		return retval;
	}
	
	
}

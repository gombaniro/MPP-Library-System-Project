package snowman.business;

import java.lang.reflect.Member;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import snowman.dataaccess.Auth;
import snowman.dataaccess.DataAccess;
import snowman.dataaccess.DataAccessFacade;
import snowman.dataaccess.User;
import snowman.librarysystem.LibWindow;

import javax.swing.*;

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
		HashMap<String, LibraryMember> membersMap = da.readMemberMap();
		BookCopy copy =  this.checkoutCheck(bookISBN,memberID,booksMap,membersMap);
		if(copy == null){
			return;
		}
		//create a record
		CheckoutRecordEntry entry = new CheckoutRecordEntry(LocalDate.now(),LocalDate.now().plusDays(7L),copy);
		//add record
		Book book = booksMap.get(bookISBN);
		LibraryMember member = membersMap.get(memberID);
		member.getCheckoutRecord().addRecordEntry(entry);
		copy.checkout();
		//print current copy
		System.out.println(copy.toString());
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
	
	public BookCopy checkoutCheck(String bookISBN, String memberID,HashMap<String, Book> booksMap,
								  HashMap<String, LibraryMember> membersMap) {
		//check if book exists
		if(!booksMap.containsKey(bookISBN)) {
			JOptionPane.showMessageDialog(null, "BookISBN " + bookISBN + " not found!");
			return null;
		}
		//check if member exists
		if(!membersMap.containsKey(memberID)) {
			JOptionPane.showMessageDialog(null, "MemberID " + memberID + " not found!");
			return null;
		}
		//check if book copy available
		Book book = booksMap.get(bookISBN);
		BookCopy copy = null;
		for(BookCopy c : book.getCopies()){
			if(c.isAvailable()){
				copy = c;
				break;
			}
		}
		if(copy == null){
			JOptionPane.showMessageDialog(null, "Current book has no available copy !");
			return null;
		}
		return copy;
	}
}

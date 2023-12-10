package snowman.business;

import java.time.LocalDate;
import java.util.*;

import snowman.dataaccess.Auth;
import snowman.dataaccess.DataAccess;
import snowman.dataaccess.DataAccessFacade;
import snowman.dataaccess.User;

import javax.swing.*;

public class SystemController implements ControllerInterface {
    public static Auth currentAuth = null;

    public void login(String id, String password) throws LoginException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, User> map = da.readUserMap();
        if (!map.containsKey(id)) {
            throw new LoginException("ID " + id + " not found");
        }
        String passwordFound = map.get(id).getPassword();
        if (!passwordFound.equals(password)) {
            throw new LoginException("Password incorrect");
        }
        currentAuth = map.get(id).getAuthorization();

    }

    @Override
    public void checkout(String bookISBN, String memberID) throws CheckoutException {
        DataAccess da = new DataAccessFacade();
        HashMap<String, Book> booksMap = da.readBooksMap();
        HashMap<String, LibraryMember> membersMap = da.readMemberMap();

        BookCopy copy = this.checkoutCheck(bookISBN, memberID, booksMap, membersMap);
        if (copy == null) {
            return;
        }
        int maxLength = booksMap.get(bookISBN).getMaxCheckoutLength();
        //create a record
        CheckoutRecordEntry entry = new CheckoutRecordEntry(LocalDate.now(), LocalDate.now().plusDays(maxLength), copy);
        //add record
        Book book = booksMap.get(bookISBN);
        LibraryMember member = membersMap.get(memberID);
        if (member == null) {
            return;
        }

        CheckoutRecord record = member.getCheckoutRecord();
        entry.checkoutRecord = record;

        record.addRecordEntry(entry);
        copy.checkout();
        book.updateCopies(copy);
        booksMap.put(bookISBN, book);
        membersMap.put(memberID, member);
        da.saveCheckoutRecord(booksMap, membersMap);
        JOptionPane.showMessageDialog(null, "ISBN: " + bookISBN + " copyNum: + " + copy.getCopyNum() + "  is successfully checked out");
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

    public BookCopy checkoutCheck(String bookISBN, String memberID, HashMap<String, Book> booksMap,
                                  HashMap<String, LibraryMember> membersMap) {
        //check if book exists
        if (!booksMap.containsKey(bookISBN)) {
            JOptionPane.showMessageDialog(null, "BookISBN " + bookISBN + " not found!");
            return null;
        }
        //check if member exists
        if (!membersMap.containsKey(memberID)) {
            JOptionPane.showMessageDialog(null, "MemberID " + memberID + " not found!");
            return null;
        }
        //check if book copy available
        Book book = booksMap.get(bookISBN);
        Iterator<BookCopy> it = Arrays.stream(book.getCopies()).iterator();
        BookCopy c = null;
        while (it.hasNext()) {
            c = it.next();
            if (c.isAvailable()) {
                break;
            } else {
                c = null;
            }
        }
        if (c == null) {
            JOptionPane.showMessageDialog(null, "Current book has no available copy !");
            return null;
        }
        c.setISBN(book.getIsbn());
        c.setTitle(book.getTitle());
        return c;
    }
}

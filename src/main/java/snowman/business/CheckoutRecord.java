package snowman.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Immutable class
 */
final public class CheckoutRecord implements Serializable {

    public LibraryMember libraryMember;
    private List<CheckoutRecordEntry> recordEntryList = new ArrayList<>();

    public CheckoutRecord(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    public CheckoutRecord(ArrayList<CheckoutRecordEntry> checkoutRecordEntries) {
        this.recordEntryList = checkoutRecordEntries;
    }

    public List<CheckoutRecordEntry> getRecordEntryList() {
        return recordEntryList;
    }

    public void addRecordEntry(CheckoutRecordEntry entry) {
        recordEntryList.add(entry);
        entry.checkoutRecord = this;
    }
}

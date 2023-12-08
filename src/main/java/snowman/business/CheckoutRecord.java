package snowman.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Immutable class
 */
final public class CheckoutRecord implements Serializable {

	private List<CheckoutRecordEntry> recordEntryList =  new ArrayList<>();

	public List<CheckoutRecordEntry> getRecordEntryList(){
		return recordEntryList;
	}

	public void addRecordEntry(CheckoutRecordEntry recordEntry){
		recordEntryList.add(recordEntry);
	}
}

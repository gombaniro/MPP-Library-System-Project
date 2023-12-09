package snowman.business;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Immutable class
 */
final public class CheckoutRecord implements Serializable {

	private static List<CheckoutRecordEntry> recordEntryList =  new ArrayList<>();

	public CheckoutRecord() {
		recordEntryList =  new ArrayList<>();
	}

	public List<CheckoutRecordEntry> getRecordEntryList(){
		return recordEntryList;
	}

	public void addRecordEntry(CheckoutRecordEntry recordEntry){
		recordEntryList.add(recordEntry);
	}



}

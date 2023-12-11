package snowman.librarysystem.dialogs;

import snowman.business.Book;
import snowman.business.BookCopy;
import snowman.business.CheckoutRecord;
import snowman.business.CheckoutRecordEntry;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckoutRecordModel extends AbstractTableModel{

    protected List<CheckoutRecordEntry> recordEntries;

    protected String[] columnNames = new String[]{
            "checkoutDate", "dueDate", "bookISBN", "title", "copyNum", "isAvailable"
    };


    protected Class[] columnClasses = new Class[]{
            LocalDate.class, LocalDate.class, String.class, String.class, int.class, boolean.class
    };


    CheckoutRecordModel(List<CheckoutRecordEntry> entries) {
        this.recordEntries = entries;
    }

    // Information about each column
    public String getColumnName(int column) {
        return columnNames[column];
    }

    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }

    @Override
    public int getRowCount() {
        if (recordEntries == null) {
            return 0;
        }
        return recordEntries.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (recordEntries == null) {
            return null;
        }
        CheckoutRecordEntry entry = recordEntries.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return entry.getCheckoutDate();
            case 1:
                return entry.getDueDate();
            case 2:
                return entry.getBookCopy().getBookISBN();
            case 3:
                return entry.getBookCopy().getTitle();
            case 4:
                return entry.getBookCopy().getCopyNum();
            case 5:
                return entry.getBookCopy().isAvailable();
            default:
                return null;
        }
    }

    public void setCheckoutRecordEntries(ArrayList<CheckoutRecordEntry> searchResult) {
        this.recordEntries = searchResult;
    }


}

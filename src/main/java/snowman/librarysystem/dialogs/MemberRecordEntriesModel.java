package snowman.librarysystem.dialogs;

import snowman.business.Book;
import snowman.business.BookCopy;
import snowman.business.CheckoutRecordEntry;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;
import java.util.List;

public class MemberRecordEntriesModel extends AbstractTableModel {

    protected Book book;
    protected List<CheckoutRecordEntry> entries;

    protected String[] columnNames = new String[] {
        "ISBN", "Title", "Copy#"
    };

    protected Class[] columnClasses = new Class[]{
        String.class, String.class, Integer.class
    };


    MemberRecordEntriesModel(List<CheckoutRecordEntry> entries) {
        this.entries = entries;
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
        if (entries == null) {
            return 0;
        }
        return entries.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (entries == null) {
            return null;
        }
        CheckoutRecordEntry entry = entries.get(rowIndex);
        switch (columnIndex) {
            case 0: return entry.bookCopy.getIsbn();
            case 1: return entry.bookCopy.getTitle();
            case 2: return entry.bookCopy.getCopyNum();
            default: return null;
        }
    }
}

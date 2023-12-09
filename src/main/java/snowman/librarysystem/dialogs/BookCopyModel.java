package snowman.librarysystem.dialogs;

import snowman.business.Book;
import snowman.business.BookCopy;

import javax.swing.table.AbstractTableModel;
import java.util.Arrays;

public class BookCopyModel extends AbstractTableModel {

    protected Book book;
    protected BookCopy[] copies;

    protected String[] columnNames = new String[] {
            "ISBN", "Title", "Available", "Copy#"
    };

    protected Class[] columnClasses = new Class[]{
            String.class, String.class, Boolean.class, Integer.class
    };


    BookCopyModel(Book book) {
        System.out.println("BookCopyModel:::" + book);
        this.book = book;
        if (book != null) {
            System.out.println("copies:::" + Arrays.toString(book.getCopies()));
            copies = book.getCopies();
        }

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
        if (copies == null) {
            return 0;
        }
        return copies.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (book == null || copies == null) {
            return null;
        }
        BookCopy copy = copies[rowIndex];
        switch (columnIndex) {
            case 0: return copy.getIsbn();
            case 1: return copy.getTitle();
            case 2: return copy.isAvailable();
            case 3: return copy.getCopyNum();
            default: return null;
        }
    }
}

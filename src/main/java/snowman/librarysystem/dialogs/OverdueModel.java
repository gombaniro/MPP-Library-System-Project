package snowman.librarysystem.dialogs;

import snowman.business.Book;
import snowman.business.BookCopy;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;

public class OverdueModel extends AbstractTableModel {

    private final Book book;

    protected String[] columnNames = new String[]{
            "ISBN", "Title", "Copy #", "Member Id", "Due Date",
    };

    protected Class[] columnClasses = new Class[]{
            String.class, String.class, int.class, String.class, LocalDate.class,
    };

    OverdueModel(Book book) {
        this.book = book;
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
        if (book == null) {
            return 0;
        }

        return book.getCopies().length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BookCopy bookCopy = book.getCopies()[rowIndex];
        System.out.println(bookCopy);
        // "bookISBN", "title", "copyNum", "memberId", "dueDate",
        switch (columnIndex) {
            case 0:
                return bookCopy.getBookISBN();
            case 1:
                return bookCopy.getTitle();
            case 2:
                return bookCopy.getCopyNum();
            case 3:
                if (bookCopy.checkoutRecordEntry == null) {
                    return "";
                }
                return bookCopy.checkoutRecordEntry.checkoutRecord.libraryMember.getMemberId();
            case 4:
                if (bookCopy.checkoutRecordEntry == null) {
                    return "";
                }
                return bookCopy.checkoutRecordEntry.getDueDate();
            default:
                return null;
        }
    }
}

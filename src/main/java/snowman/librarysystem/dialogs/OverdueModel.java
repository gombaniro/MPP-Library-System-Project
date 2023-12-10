package snowman.librarysystem.dialogs;

import snowman.business.Book;
import snowman.business.BookCopy;

import javax.swing.table.AbstractTableModel;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OverdueModel extends AbstractTableModel {

    private List<BookCopy> copies = new ArrayList<>();

    protected String[] columnNames = new String[]{
        "ISBN", "Title", "Copy #", "Member Id", "Due Date", "Overdue Status"
    };

    protected Class[] columnClasses = new Class[]{
        String.class, String.class, int.class, String.class, LocalDate.class, String.class
    };

    OverdueModel(Book book) {
        if (book != null) {
            copies = Arrays.asList(book.getCopies());
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
        return copies.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        BookCopy bookCopy = copies.get(rowIndex);
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
            case 5:
                if (bookCopy.checkoutRecordEntry == null) return "Not overdue";

                if (bookCopy.checkoutRecordEntry.checkoutRecord.libraryMember != null
                        && LocalDate.now().isAfter(bookCopy.checkoutRecordEntry.getDueDate())
                ) {
                    return "Overdue";
                }

                return "Not overdue";
            default:
                return null;
        }
    }
}

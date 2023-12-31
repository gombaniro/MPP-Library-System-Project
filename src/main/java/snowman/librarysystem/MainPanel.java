package snowman.librarysystem;

import snowman.business.ControllerInterface;
import snowman.business.SystemController;
import snowman.dataaccess.Auth;
import snowman.librarysystem.dialogs.*;
import snowman.librarysystem.eventHandlers.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;


public class MainPanel extends JPanel implements LibWindow {
    ControllerInterface ci = new SystemController();
    public final static MainPanel INSTANCE = new MainPanel();
    JMenuBar menuBar;
    JButton allBookIds, allMemberIds;
    JButton addCopyBook;
    JButton addNewBook;
    JButton addMember, checkoutButton, checkoutRecordButton;
    private boolean isInitialized = false;

    public MainPanel() {
    }

    public void init() {
        setLayout(new BorderLayout());

        createMenus();
        insertSplashImage();

        setSize(660, 500);
        isInitialized = true;
    }

    private void insertSplashImage() {
        String pathToImage = String.valueOf(Paths.get(System.getProperty("user.dir"),
                "src", "main", "java", "snowman", "librarysystem", "library.jpg"));
        ImageIcon image = new ImageIcon(pathToImage);
        add(new JLabel(image), BorderLayout.SOUTH);
    }

    private void createMenus() {
        menuBar = new JMenuBar();
        addMenuItems();
    }

    private void addMenuItems() {
        allBookIds = new JButton("All Book Ids");
        allBookIds.addActionListener(new AllBookIdsListener());
        allMemberIds = new JButton("All Member Ids");
        allMemberIds.addActionListener(new AllMemberIdsListener());

        menuBar.add(allBookIds);
        menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
        menuBar.add(allMemberIds);
        menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons

//        JButton showUserRecords = new JButton("Show User Records");
//        showUserRecords.addActionListener(new UserRecordListener(
//                new UserRecordDialog(null, "Show User Records", true)
//        ));
//        menuBar.add(showUserRecords);

 //       menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons

        if (SystemController.currentAuth == Auth.ADMIN
                || SystemController.currentAuth == Auth.BOTH) {

            // Add a book menu
            addCopyBook = new JButton("Book Copy");
            addCopyBook.addActionListener(new AddCopyBookListener(new AddBookCopyDialog(
                    null, "Add a book copy", true)));

            addNewBook = new JButton("New Book");
            addNewBook.addActionListener(new AddNewBookListener(new AddNewBookDialog(
                    null, "Add a new book", true)));


            // Add a member menu
            addMember = new JButton("Add Member");
            addMember.addActionListener(new AddMemberListener(new AddMemberDialog(
                    null, "Add a member", true)));

            menuBar.add(addCopyBook);
            menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
            menuBar.add(addNewBook);
            menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
            menuBar.add(addMember);
        }

        if (SystemController.currentAuth == Auth.LIBRARIAN
                || SystemController.currentAuth == Auth.BOTH) {

            checkoutButton = new JButton("Checkout");
            checkoutButton.addActionListener(new CheckoutButtonListener());
            menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
            menuBar.add(checkoutButton);

        }
        checkoutRecordButton = new JButton("Checkout Record");
        checkoutRecordButton.addActionListener(new CheckoutRecordListener(new CheckoutRecordDialog(
                null, "Show a librarian member's checkout record", true)));
        menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
        menuBar.add(checkoutRecordButton);

        JButton overdueButton = new JButton("Overdue");
        overdueButton.addActionListener(new OverdueListener(new OverdueDialog(null, "Overdue", true)));

        menuBar.add(Box.createRigidArea(new Dimension(5, 0)));
        menuBar.add(overdueButton);

        add(menuBar, BorderLayout.PAGE_START);
    }

    class AllBookIdsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LibrarySystem.hideAllWindows();
            AllBookIdsWindow.INSTANCE.init();

            List<String> ids = ci.allBookIds();
            Collections.sort(ids);
            StringBuilder sb = new StringBuilder();
            for (String s : ids) {
                sb.append(s + "\n");
            }
            System.out.println(sb.toString());
            AllBookIdsWindow.INSTANCE.setData(sb.toString());
            AllBookIdsWindow.INSTANCE.pack();
            //AllBookIdsWindow.INSTANCE.setSize(660,500);
            Util.centerFrameOnDesktop(AllBookIdsWindow.INSTANCE);
            AllBookIdsWindow.INSTANCE.setVisible(true);

        }

    }

    class AllMemberIdsListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LibrarySystem.hideAllWindows();
            AllMemberIdsWindow.INSTANCE.init();
            AllMemberIdsWindow.INSTANCE.pack();
            AllMemberIdsWindow.INSTANCE.setVisible(true);


            LibrarySystem.hideAllWindows();
            AllBookIdsWindow.INSTANCE.init();

            List<String> ids = ci.allMemberIds();
            Collections.sort(ids);
            StringBuilder sb = new StringBuilder();
            for (String s : ids) {
                sb.append(s + "\n");
            }
            System.out.println(sb.toString());
            AllMemberIdsWindow.INSTANCE.setData(sb.toString());
            AllMemberIdsWindow.INSTANCE.pack();
            //AllMemberIdsWindow.INSTANCE.setSize(660,500);
            Util.centerFrameOnDesktop(AllMemberIdsWindow.INSTANCE);
            AllMemberIdsWindow.INSTANCE.setVisible(true);


        }

    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }


    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;

    }

    class CheckoutButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            LibrarySystem.hideAllWindows();
            CheckoutWindow.INSTANCE.init();
            CheckoutWindow.INSTANCE.pack();
            CheckoutWindow.INSTANCE.setLocationRelativeTo(null);
            CheckoutWindow.INSTANCE.setTitle("Check out a book");
            CheckoutWindow.INSTANCE.setVisible(true);
        }

    }

    class UserRecordListener implements ActionListener {
        Dialog d;

        public UserRecordListener(Dialog d) {
            this.d = d;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            d.setVisible(true);
        }

    }

}
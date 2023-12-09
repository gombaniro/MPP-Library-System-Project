package snowman.librarysystem;

import snowman.business.ControllerInterface;
import snowman.business.SystemController;
import snowman.dataaccess.Auth;
import snowman.librarysystem.dialogs.AddBookCopyDialog;
import snowman.librarysystem.dialogs.AddMemberDialog;
import snowman.librarysystem.dialogs.CheckoutRecordDialog;
import snowman.librarysystem.dialogs.UserRecordDialog;import snowman.librarysystem.eventHandlers.AddCopyBookListener;
import snowman.librarysystem.eventHandlers.AddMemberListener;
import snowman.librarysystem.eventHandlers.CheckoutRecordListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;


public class MainPanel extends JPanel implements LibWindow {
	ControllerInterface ci = new SystemController();
	public final static MainPanel INSTANCE =new MainPanel();
	JPanel mainPanel;
	JMenuBar menuBar;
    JMenu options;
	JButton login, allBookIds, allMemberIds;
	JMenu books;
	JButton addBook, list;
	JMenu members;
	JButton addMember, memberRecord, checkoutButton,checkoutRecordButton;
    private boolean isInitialized = false;

    public MainPanel() {}
    
    public void init() {
		setLayout(new BorderLayout());

		createMenus();
    	insertSplashImage();
		
		//pack();

		setSize(660,500);
		isInitialized = true;
    }

    private void insertSplashImage() {
		String pathToImage = String.valueOf(Paths.get( System.getProperty("user.dir"),
				"src", "main", "java", "snowman", "librarysystem", "library.jpg"));
        ImageIcon image = new ImageIcon(pathToImage);
		add(new JLabel(image), BorderLayout.SOUTH);
    }
    private void createMenus() {
    	menuBar = new JMenuBar();
		addMenuItems();
//		setJMenuBar(menuBar);
    }
    
    private void addMenuItems() {
//        allBookIds = new JButton("All Book Ids");
//        allBookIds.addActionListener(new AllBookIdsListener());
//        allMemberIds = new JButton("All Member Ids");
//        allMemberIds.addActionListener(new AllMemberIdsListener());
//
//		menuBar.add(allBookIds);
//		menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
//		menuBar.add(allMemberIds);

		JButton showUserRecords = new JButton("Show User Records");
		showUserRecords.addActionListener(new UserRecordListener(
				new UserRecordDialog(null, "Show User Records", true)
		));
		menuBar.add(showUserRecords);

		menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons

		if (SystemController.currentAuth == Auth.ADMIN
			|| SystemController.currentAuth == Auth.BOTH) {

			// Add a book menu
			addBook = new JButton("Add Book");
			addBook.addActionListener(new AddCopyBookListener(new AddBookCopyDialog(
					null, "Add a book copy", true)));

			// Add a member menu
			addMember = new JButton("Add Member");
			addMember.addActionListener(new AddMemberListener(new AddMemberDialog(
					null, "Add a member", true)));

			menuBar.add(addBook);
			menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
			menuBar.add(addMember);
		}

		if (SystemController.currentAuth == Auth.LIBRARIAN
				|| SystemController.currentAuth == Auth.BOTH) {

			checkoutButton = new JButton("Checkout");
			checkoutButton.addActionListener(new CheckoutButtonListener());
			menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
			menuBar.add(checkoutButton);
			checkoutRecordButton = new JButton("Checkout Record");
			checkoutRecordButton.addActionListener(new CheckoutRecordListener(new CheckoutRecordDialog(
					null, "Show a librarian member's checkout record", true)));
//			checkoutRecordButton.addActionListener(new CheckoutRecordButtonListener());
			menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
			menuBar.add(checkoutRecordButton);
		}

		add(menuBar, BorderLayout.PAGE_START);
    }

    class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
//			if (SystemController.currentAuth != null) {
//				JOptionPane.showMessageDialog(LibrarySystem.INSTANCE, "User already logged in");
//				return;
//			}

			// main panel
//			LibrarySystem.hideAllWindows();
//			changePanel(this);
//			LoginPanel.INSTANCE.init();
//			Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
//			LoginPanel.INSTANCE.setVisible(true);
		}
    	
    }
    class AllBookIdsListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			LibrarySystem.hideAllWindows();
			AllBookIdsWindow.INSTANCE.init();
			
			List<String> ids = ci.allBookIds();
			Collections.sort(ids);
			StringBuilder sb = new StringBuilder();
			for(String s: ids) {
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
			for(String s: ids) {
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
		isInitialized =val;
		
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

	//

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

package snowman.librarysystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collections;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;

import snowman.business.ControllerInterface;
import snowman.business.SystemController;
import snowman.librarysystem.dialogs.AddBookCopyDialog;
import snowman.librarysystem.dialogs.AddMemberDialog;
import snowman.librarysystem.eventHandlers.AddCopyBookListener;
import snowman.librarysystem.eventHandlers.AddMemberListener;


public class LibrarySystem extends JFrame implements LibWindow {
	ControllerInterface ci = new SystemController();
	public final static LibrarySystem INSTANCE =new LibrarySystem();
	JPanel mainPanel;
	JMenuBar menuBar;
    JMenu options;
	JButton login, allBookIds, allMemberIds;
	JMenu books;
	JButton addBook, list;
	JMenu members;
	JButton addMember, memberRecord;
    String pathToImage;
    private boolean isInitialized = false;
    
    private static LibWindow[] allWindows = { 
    	LibrarySystem.INSTANCE,
		LoginWindow.INSTANCE,
		AllMemberIdsWindow.INSTANCE,	
		AllBookIdsWindow.INSTANCE
	};
    	
	public static void hideAllWindows() {		
		for(LibWindow frame: allWindows) {
			frame.setVisible(false);			
		}
	}
     
    private LibrarySystem() {}
    
    public void init() {
    	formatContentPane();
    	setPathToImage();
    	insertSplashImage();
		
		createMenus();
		//pack();
		setSize(660,500);
		isInitialized = true;
    }
    
    private void formatContentPane() {
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,1));
		getContentPane().add(mainPanel);	
	}
    
    private void setPathToImage() {
    	String currDirectory = System.getProperty("user.dir");
    	pathToImage = currDirectory+"\\src\\main\\java\\snowman\\librarysystem\\library.jpg";
    }
    
    private void insertSplashImage() {
        ImageIcon image = new ImageIcon(pathToImage);
		mainPanel.add(new JLabel(image));	
    }
    private void createMenus() {
    	menuBar = new JMenuBar();
		addMenuItems();
		setJMenuBar(menuBar);		
    }
    
    private void addMenuItems() {
        login = new JButton("Login");
        login.addActionListener(new LoginListener());
        allBookIds = new JButton("All Book Ids");
        allBookIds.addActionListener(new AllBookIdsListener());
        allMemberIds = new JButton("All Member Ids");
        allMemberIds.addActionListener(new AllMemberIdsListener());

        // Add a book menu
		addBook = new JButton("Add Book");
        addBook.addActionListener(new AddCopyBookListener(new AddBookCopyDialog(
                this, "Add a book copy", true)));

        // Add a member menu
        addMember = new JButton("Add Member");
        addMember.addActionListener(new AddMemberListener(new AddMemberDialog(
                this,"Add a member", true)));

        menuBar.add(login);
        menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
        menuBar.add(allBookIds);
        menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
        menuBar.add(allMemberIds);
        menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
        menuBar.add(addBook);
        menuBar.add(Box.createRigidArea(new Dimension(5, 0))); // Add space between buttons
        menuBar.add(addMember);
    }

    class LoginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (SystemController.currentAuth != null) {
				JOptionPane.showMessageDialog(LibrarySystem.INSTANCE, "User already logged in");
				return;
			}

			LibrarySystem.hideAllWindows();
			LoginWindow.INSTANCE.init();
			Util.centerFrameOnDesktop(LoginWindow.INSTANCE);
			LoginWindow.INSTANCE.setVisible(true);
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
    
}

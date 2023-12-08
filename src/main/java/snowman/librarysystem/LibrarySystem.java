package snowman.librarysystem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import javax.swing.*;

import snowman.business.ControllerInterface;
import snowman.business.SystemController;


public class LibrarySystem extends JFrame implements LibWindow {
	ControllerInterface ci = new SystemController();
	public final static LibrarySystem INSTANCE =new LibrarySystem();
	JPanel mainPanel;
	JMenuBar menuBar;
    JMenu options;
	JButton login, allBookIds, allMemberIds;
	JMenu books;
	JButton addBook, list, logout, checkout;
	JMenu members;
	JButton addMember, memberRecord;
    String pathToImage;
    private boolean isInitialized = false;
    
    private static LibWindow[] allWindows = { 
    	LibrarySystem.INSTANCE,
		LoginPanel.INSTANCE,
		AllMemberIdsWindow.INSTANCE,	
		AllBookIdsWindow.INSTANCE,
			CheckoutWindow.INSTANCE
	};
    	
	public static void hideAllWindows() {		
		for(LibWindow frame: allWindows) {
			frame.setVisible(false);			
		}
	}
     
    private LibrarySystem() {}
    
    public void init() {
		LoginPanel loginWindow = LoginPanel.INSTANCE;
		loginWindow.init();
		getContentPane().add(loginWindow);

		setSize(660,500);
		isInitialized = true;
    }

	public void changePanel(JPanel newPanel) {
//		LibrarySystem.hideAllWindows();
		getContentPane().removeAll();
		getContentPane().add(newPanel);
		getContentPane().validate();
		getContentPane().repaint();
	}

	class CheckoutListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (SystemController.currentAuth == null) {
				JOptionPane.showMessageDialog(LibrarySystem.INSTANCE, "Not log in");
				return;
			}
			LibrarySystem.hideAllWindows();
			CheckoutWindow.INSTANCE.init();
			Util.centerFrameOnDesktop(CheckoutWindow.INSTANCE);
			CheckoutWindow.INSTANCE.setVisible(true);
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

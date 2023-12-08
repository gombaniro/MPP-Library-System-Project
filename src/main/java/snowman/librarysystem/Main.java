package snowman.librarysystem;

import snowman.business.SystemController;

import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;



public class Main {

	public static void main(String[] args) {
	      EventQueue.invokeLater(() ->
	         {
 				updateLoginStatus();
	            LibrarySystem.INSTANCE.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            
	            LibrarySystem.INSTANCE.init();
	            centerFrameOnDesktop(LibrarySystem.INSTANCE);
	            LibrarySystem.INSTANCE.setVisible(true);
	         });
	   }

		public static void updateLoginStatus() {
			String loginStatus = SystemController.currentAuth == null
					? " (Not Logged in) "
					: SystemController.currentAuth+"";
			LibrarySystem.INSTANCE.setTitle("Sample Library Application. Logged in as: " + loginStatus);
		}
	   
	   public static void centerFrameOnDesktop(Component f) {
			Toolkit toolkit = Toolkit.getDefaultToolkit();
			int height = toolkit.getScreenSize().height;
			int width = toolkit.getScreenSize().width;
			int frameHeight = f.getSize().height;
			int frameWidth = f.getSize().width;
			f.setLocation(((width - frameWidth) / 2), (height - frameHeight) / 3);
		}
}

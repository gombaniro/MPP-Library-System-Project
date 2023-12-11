package snowman.librarysystem.eventHandlers;

import snowman.business.SystemController;
import snowman.dataaccess.Auth;
import snowman.librarysystem.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AddNewBookListener implements ActionListener {

    Dialog dialog;

    public AddNewBookListener(Dialog d) {
        dialog = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (SystemController.currentAuth == null) {
            JOptionPane.showMessageDialog(LibrarySystem.INSTANCE, "You have to login as admin");
            return;
        }

        if (!(SystemController.currentAuth.equals(Auth.ADMIN) || SystemController.currentAuth.equals(Auth.BOTH))) {
            JOptionPane.showMessageDialog(LibrarySystem.INSTANCE, "You need admin privileges");
            return;
        }
        dialog.setVisible(true);
    }
}

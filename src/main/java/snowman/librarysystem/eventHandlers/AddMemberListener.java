package snowman.librarysystem.eventHandlers;


import snowman.business.SystemController;
import snowman.dataaccess.Auth;
import snowman.librarysystem.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMemberListener implements ActionListener {
    Dialog dialog;

    public AddMemberListener(Dialog d) {
        dialog = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (SystemController.currentAuth == null) {
            JOptionPane.showMessageDialog(LibrarySystem.INSTANCE, "You have to login");
            return;
        }

        if (!SystemController.currentAuth.equals(Auth.ADMIN)) {
            JOptionPane.showMessageDialog(LibrarySystem.INSTANCE, "You have to be admin");
            return;
        }
        dialog.setVisible(true);
    }
}

package snowman.librarysystem.eventHandlers;


import snowman.business.SystemController;
import snowman.dataaccess.Auth;
import snowman.librarysystem.LibrarySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckoutRecordListener implements ActionListener {

    Dialog dialog;

    public CheckoutRecordListener(Dialog d) {
        dialog = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.setVisible(true);
    }
}

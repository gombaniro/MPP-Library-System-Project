package com.snowman.librarysystem.eventHandlers;

import com.snowman.librarysystem.dialogs.AddMemberDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMemberListener implements ActionListener {
    AddMemberDialog dialog;

    public AddMemberListener(AddMemberDialog d) {
        dialog = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.setVisible(true);
    }
}

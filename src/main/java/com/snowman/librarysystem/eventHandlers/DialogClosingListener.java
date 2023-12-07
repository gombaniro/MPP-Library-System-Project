package com.snowman.librarysystem.eventHandlers;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DialogClosingListener extends  WindowAdapter {

    Dialog dialog;

    public DialogClosingListener(Dialog d) {
        dialog = d;
    }
    @Override
    public void windowClosing(WindowEvent e) {
        dialog.setVisible(false);
    }
}

package com.snowman.librarysystem.dialogs;

import com.snowman.librarysystem.eventHandlers.DialogClosingListener;

import java.awt.*;

public class AddBookDialog extends Dialog {
    public AddBookDialog(Frame owner, String title, boolean modal) {

        super(owner, title, modal);
    }
}

package snowman.librarysystem.eventHandlers;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OverdueListener implements ActionListener {

    Dialog dialog;

    public OverdueListener(Dialog d) {
        dialog = d;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        dialog.setVisible(true);
    }
}

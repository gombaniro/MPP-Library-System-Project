package snowman.librarysystem;

import javax.swing.*;

public class LibrarySystem extends JFrame implements LibWindow {

    public final static LibrarySystem INSTANCE = new LibrarySystem();
    private boolean isInitialized = false;

    private static final LibWindow[] allWindows = {
        LibrarySystem.INSTANCE,
        LoginPanel.INSTANCE,
        AllMemberIdsWindow.INSTANCE,
        AllBookIdsWindow.INSTANCE,
        CheckoutWindow.INSTANCE
    };

    public static void hideAllWindows() {
        for (LibWindow frame : allWindows) {
            frame.setVisible(false);
        }
    }

    private LibrarySystem() {
    }

    public void init() {
        LoginPanel loginWindow = LoginPanel.INSTANCE;
        loginWindow.init();
        getContentPane().add(loginWindow);

        setSize(1000, 500);
        isInitialized = true;
    }

    public void changePanel(JPanel newPanel) {
        getContentPane().removeAll();
        getContentPane().add(newPanel);
        getContentPane().validate();
        getContentPane().repaint();
    }

    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;

    }
}

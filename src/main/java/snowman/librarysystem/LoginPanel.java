package snowman.librarysystem;

import snowman.business.ControllerInterface;
import snowman.business.SystemController;

import java.awt.*;

import javax.swing.*;


public class LoginPanel extends JPanel implements LibWindow {
    public static final LoginPanel INSTANCE = new LoginPanel();
	
	private boolean isInitialized = false;
	
	private JPanel upperHalf;
	private JPanel middleHalf;

	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
	private JPanel leftTextPanel;
	private JPanel rightTextPanel;
	
	private JTextField username;
	private JTextField password;
	private JLabel label;
	private JButton loginButton;
	private JButton logoutButton;
	
	private ControllerInterface ci = new SystemController();
	
	
	public boolean isInitialized() {
		return isInitialized;
	}
	public void isInitialized(boolean val) {
		isInitialized = val;
	}
	private JTextField messageBar = new JTextField();
	public void clear() {
		messageBar.setText("");
	}
	
	/* This class is a singleton */
    private LoginPanel() {}
    
    public void init() {
		setLayout(new GridBagLayout());  // set Layout as 'GridBagLayout'

		defineUpperHalf();
		defineMiddleHalf();

		GridBagConstraints constraints = new GridBagConstraints();
		constraints.gridwidth = GridBagConstraints.REMAINDER; // specify that the component is the last in its row.
		constraints.fill = GridBagConstraints.HORIZONTAL; // specify that component should be displayed as wide as its display area
		constraints.weighty = 1.0; // the way to specify where (vertically) to place the components
		constraints.anchor = GridBagConstraints.CENTER; // place components into the center

		JPanel panelGroup = new JPanel();
		panelGroup.setLayout(new BoxLayout(panelGroup, BoxLayout.PAGE_AXIS));
		panelGroup.add(topPanel);
		panelGroup.add(middlePanel);
		panelGroup.add(lowerPanel);
		add(panelGroup);

    		isInitialized(true);
//    		pack();
    		//setSize(660, 500);
    }

    private void defineUpperHalf() {
		upperHalf = new JPanel();
		upperHalf.setLayout(new BorderLayout());
		defineTopPanel();
		defineMiddlePanel();
		defineLowerPanel();
		upperHalf.add(topPanel, BorderLayout.NORTH);
		upperHalf.add(middlePanel, BorderLayout.CENTER);
		upperHalf.add(lowerPanel, BorderLayout.SOUTH);

	}

	private void defineMiddleHalf() {
		middleHalf = new JPanel();
		middleHalf.setLayout(new BorderLayout());
		JSeparator s = new JSeparator();
		s.setOrientation(SwingConstants.HORIZONTAL);
		//middleHalf.add(Box.createRigidArea(new Dimension(0,50)));
		middleHalf.add(s, BorderLayout.SOUTH);

	}

	private void defineTopPanel() {
		topPanel = new JPanel();
		JPanel intPanel = new JPanel(new BorderLayout());
		intPanel.add(Box.createRigidArea(new Dimension(0,20)), BorderLayout.NORTH);
		JLabel loginLabel = new JLabel("Login");
		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
		intPanel.add(loginLabel, BorderLayout.CENTER);
		topPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		topPanel.add(intPanel);

	}
    	
    	
    	
	private void defineMiddlePanel() {
		middlePanel=new JPanel();
		middlePanel.setLayout(new FlowLayout(FlowLayout.LEFT));
		defineLeftTextPanel();
		defineRightTextPanel();
		middlePanel.add(leftTextPanel);
		middlePanel.add(rightTextPanel);
	}
	private void defineLowerPanel() {
		lowerPanel = new JPanel();
		loginButton = new JButton("Login");
		addLoginButtonListener(loginButton);
		lowerPanel.add(loginButton);
	}

	private void defineLeftTextPanel() {

		JPanel topText = new JPanel();
		JPanel bottomText = new JPanel();
		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));

		username = new JTextField(10);
		label = new JLabel("Username");
		label.setFont(Util.makeSmallFont(label.getFont()));
		topText.add(username);
		bottomText.add(label);

		leftTextPanel = new JPanel();
		leftTextPanel.setLayout(new BorderLayout());
		leftTextPanel.add(topText,BorderLayout.NORTH);
		leftTextPanel.add(bottomText,BorderLayout.CENTER);
	}
	private void defineRightTextPanel() {

		JPanel topText = new JPanel();
		JPanel bottomText = new JPanel();
		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));

		password = new JPasswordField(10);
		label = new JLabel("Password");
		label.setFont(Util.makeSmallFont(label.getFont()));
		topText.add(password);
		bottomText.add(label);

		rightTextPanel = new JPanel();
		rightTextPanel.setLayout(new BorderLayout());
		rightTextPanel.add(topText,BorderLayout.NORTH);
		rightTextPanel.add(bottomText,BorderLayout.CENTER);
	}

	private void addLoginButtonListener(JButton butn) {
		butn.addActionListener(evt -> {

			String idValue = username.getText();
			String passwordValue = password.getText();
			try {
				ci.login(idValue, passwordValue);

				JOptionPane.showMessageDialog(this, "Login successful");

				Main.updateLoginStatus();
				MainPanel.INSTANCE.init();
				LibrarySystem.INSTANCE.changePanel(MainPanel.INSTANCE);
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(this, "Login failed: " + ex.getMessage());
			}
		});
	}
}

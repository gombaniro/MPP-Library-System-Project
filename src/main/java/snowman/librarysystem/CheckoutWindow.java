package snowman.librarysystem;

import snowman.business.Book;
import snowman.business.CheckoutException;
import snowman.business.ControllerInterface;
import snowman.business.SystemController;
import snowman.dataaccess.Auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class CheckoutWindow extends JFrame implements LibWindow {
	public static final CheckoutWindow INSTANCE = new CheckoutWindow();
    ControllerInterface ci = new SystemController();
	private boolean isInitialized = false;
	public JPanel getMainPanel() {
		return mainPanel;
	}
	private JPanel mainPanel;
	private JPanel topPanel;
	private JPanel middlePanel;
	private JPanel lowerPanel;
//	private TextArea textArea;

	private JPanel upperHalf;
	private JPanel middleHalf;
	private JPanel lowerHalf;

	private JPanel leftTextPanel;
	private JPanel rightTextPanel;
	private JLabel bookLabel = new JLabel("bookISBN: ");
	private JLabel memberLabel = new JLabel("memberID: ");


	private JTextField book;
	private JTextField member;

  	private JButton checkoutButton;

	  private JButton clearButton;
    private JTextField messageBar = new JTextField();
	public void clear() {
		messageBar.setText("");
	}

	private CheckoutWindow() {}
	
	public void init() {
		mainPanel = new JPanel();
		defineUpperHalf();
		defineMiddleHalf();
		defineLowerHalf();
		BorderLayout bl = new BorderLayout();
		bl.setVgap(30);
		mainPanel.setLayout(bl);
		mainPanel.add(upperHalf, BorderLayout.NORTH);
		mainPanel.add(middleHalf, BorderLayout.CENTER);
		mainPanel.add(lowerHalf, BorderLayout.SOUTH);
		getContentPane().add(mainPanel);
		setSize(new Dimension(660,500));
		isInitialized(true);
		pack();
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
		s.setOrientation(SwingConstants.VERTICAL);
		//middleHalf.add(Box.createRigidArea(new Dimension(0,50)));
		middleHalf.add(s, BorderLayout.SOUTH);

	}
	private void defineLowerHalf() {

		lowerHalf = new JPanel();
		lowerHalf.setLayout(new FlowLayout(FlowLayout.LEFT));

		JButton backButton = new JButton("<= Back to Main");
		addBackButtonListener(backButton);
		lowerHalf.add(backButton);

	}
	private void defineTopPanel() {
		topPanel = new JPanel();
		JPanel intPanel = new JPanel(new BorderLayout());
		intPanel.add(Box.createRigidArea(new Dimension(0,20)), BorderLayout.NORTH);
//		JLabel loginLabel = new JLabel("Checkout");
//		Util.adjustLabelFont(loginLabel, Color.BLUE.darker(), true);
//		intPanel.add(loginLabel, BorderLayout.CENTER);
		topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
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
		checkoutButton = new JButton("Checkout");
		clearButton = new JButton("clear");
		addCheckoutButtonListener(checkoutButton);
		addClearButtonListener(clearButton);
		lowerPanel.add(checkoutButton);
		lowerPanel.add(clearButton);
	}

	private void addClearButtonListener(JButton clearButton) {
//		this.
	}

	private void defineLeftTextPanel() {

		JPanel topText = new JPanel();
		JPanel bottomText = new JPanel();
		topText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));
		bottomText.setLayout(new FlowLayout(FlowLayout.LEFT,5,0));

		book = new JTextField(10);
		bookLabel = new JLabel("BookISBN");
		bookLabel.setFont(Util.makeSmallFont(bookLabel.getFont()));
		topText.add(book);
		bottomText.add(bookLabel);

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

		member = new JPasswordField(10);
		memberLabel = new JLabel("MemberID");
		memberLabel.setFont(Util.makeSmallFont(memberLabel.getFont()));
		topText.add(member);
		bottomText.add(memberLabel);

		rightTextPanel = new JPanel();
		rightTextPanel.setLayout(new BorderLayout());
		rightTextPanel.add(topText,BorderLayout.NORTH);
		rightTextPanel.add(bottomText,BorderLayout.CENTER);
	}

	private void addBackButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			LibrarySystem.hideAllWindows();
			LibrarySystem.INSTANCE.setVisible(true);
		});
	}

	private void addCheckoutButtonListener(JButton butn) {
		butn.addActionListener(evt -> {
			String book = bookLabel.getText();
            String member = memberLabel.getText();
			try {
				ci.checkout(book,member);
			} catch (CheckoutException e) {
				throw new RuntimeException(e);
			}
		});
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



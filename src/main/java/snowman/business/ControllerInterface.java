package snowman.business;

import java.util.List;

public interface ControllerInterface {
	public void login(String id, String password) throws LoginException;
	public void checkout(String bookISBN, String memberID) throws CheckoutException;
	public List<String> allMemberIds();
	public List<String> allBookIds();
	
}

package snowman.business;

import java.util.List;

public interface ControllerInterface {
    void login(String id, String password) throws LoginException;
    void checkout(String bookISBN, String memberID) throws CheckoutException;
    List<String> allMemberIds();
    List<String> allBookIds();
}

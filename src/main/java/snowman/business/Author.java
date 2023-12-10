package snowman.business;

import java.io.Serializable;
import java.util.List;

final public class Author extends Person implements Serializable {
    private final String bio;

    private String credentials;

    private List<Book> bookList;

    public String getBio() {
        return bio;
    }

    public Author(String f, String l, String t, Address a, String bio) {
        super(f, l, t, a);
        this.bio = bio;
    }

    private static final long serialVersionUID = 7508481940058530471L;
}

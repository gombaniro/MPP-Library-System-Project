package snowman.business;

import java.io.Serializable;
import java.util.ArrayList;

final public class LibraryMember extends Person implements Serializable {

    private String memberId;
    private final CheckoutRecord checkoutRecord;

    public LibraryMember(String memberId, String fname, String lname, String tel, Address add) {
        super(fname, lname, tel, add);
        this.memberId = memberId;
        checkoutRecord = new CheckoutRecord(this);
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @Override
    public String toString() {
        return "Member Info: " + "ID: " + memberId + ", name: " + getFirstName() + " " + getLastName() +
                ", " + getTelephone() + " " + getAddress();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof LibraryMember)) {
            return false;
        }
        LibraryMember that = (LibraryMember) obj;
        return this.getFirstName().equals(that.getFirstName()) &&
                getLastName().equals(that.getLastName()) &&
                getTelephone().equals(that.getTelephone());
    }

    private static final long serialVersionUID = -2226197306790714013L;

    public CheckoutRecord getCheckoutRecord() {
        return checkoutRecord;
    }
}

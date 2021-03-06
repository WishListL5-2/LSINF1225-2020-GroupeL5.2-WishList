package be.LaPireTeam.wishlist.Objects;

public class Wish {
    public final int ID;
    private String name;
    private Integer priority;
    private String comment;
    private boolean bookingStatus;
    private String product;

    public Wish(int id, String name, Integer priority, String comment, String product) {
        if (id == -1) {
            this.ID = -1;
        } else {
            this.ID = id;
        }
        this.name = name;
        this.priority = priority;
        this.comment = comment;
        this.product = product;
        this.bookingStatus = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPriority() {
        return priority;
    }

    public String getComment() {
        return comment;
    }

    public boolean getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public String getProduct() {
        return product;
    }

}

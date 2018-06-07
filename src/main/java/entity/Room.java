package entity;

public class Room {

    private int roomNumber;
    private boolean booked;
    private Guest guest;
    private int roomId;

    public Room(int roomNumber, boolean booked) {
        this.roomNumber = roomNumber;
        this.booked = booked;
    }

    public Room(int roomNumber, boolean booked, int roomId) {
        this.roomNumber = roomNumber;
        this.booked = booked;
        this.roomId = roomId;
    }

    public Room(int roomNumber, Guest guest, int roomId) {
        this.roomNumber = roomNumber;
        this.guest = guest;
        this.roomId = roomId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}

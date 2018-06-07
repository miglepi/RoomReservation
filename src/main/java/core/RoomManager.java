package core;

import entity.Guest;
import entity.Room;

import java.util.ArrayList;

public interface RoomManager {

    ArrayList<Room> getRoomList();

    Room getAvailableRoom();

    int checkIn(Guest guest);

    ArrayList<Guest> getGuestListByRoom (int roomId);

    boolean checkOut(Room room);

    ArrayList<Room> getBookedRooms();
}

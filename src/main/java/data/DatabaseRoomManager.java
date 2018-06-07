package data;

import core.RoomManager;
import entity.Guest;
import entity.Room;

import java.util.ArrayList;

public class DatabaseRoomManager implements RoomManager {

    private final Repository repository;

    public DatabaseRoomManager(Repository repository) {
        this.repository = repository;
    }

    @Override
    public ArrayList<Room> getRoomList() {
        return repository.getRoomList();
    }

    @Override
    public int checkIn(Guest guest) {
        Room room = repository.getAvailableRoom();
        if (room == null) {
            return -1;
        }
        repository.insertGuest(guest);
        room.setGuest(guest);
        room.setBooked(true);
        repository.updateRoom(room);
        repository.insertToHistory(room.getRoomId(), guest.getId());
        return room.getRoomNumber();
    }

    @Override
    public ArrayList<Guest> getGuestListByRoom(int roomId) {
        return repository.getGuestsByRoom(roomId);
    }

    @Override
    public boolean checkOut(Room room) {
        room.setGuest(null);
        room.setBooked(false);
        repository.updateRoom(room);
        return true;
    }

    @Override
    public ArrayList<Room> getBookedRooms() {
        return repository.getBookedRooms();
    }
}
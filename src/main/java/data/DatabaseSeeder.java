package data;

import core.RoomFactory;
import entity.Room;

import java.util.List;

public class DatabaseSeeder {

    private final Repository repository;
    private final RoomFactory roomFactory;

    public DatabaseSeeder(Repository repository, RoomFactory roomFactory) {
        this.repository = repository;
        this.roomFactory = roomFactory;
    }

    public void seed() {
        if (!repository.areRoomsCreated()) {
            List<Room> rooms = roomFactory.createRoomList();
            rooms.forEach(room -> {
                repository.insertRoom(room);
            });
        }
    }
}

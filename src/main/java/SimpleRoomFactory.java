import core.RoomFactory;
import entity.Room;

import java.util.ArrayList;

public class SimpleRoomFactory implements RoomFactory {
    private int roomCount;

    public SimpleRoomFactory(int roomCount) {
        this.roomCount = roomCount;
    }

    @Override
    public ArrayList<Room> createRoomList() {
        ArrayList<Room> roomList = new ArrayList<>();
        for (int i = 0; i < roomCount; i++) {
            roomList.add(new Room(i+1, false));
        }
        return roomList;
    }
}
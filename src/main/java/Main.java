import core.RoomFactory;
import core.RoomManager;
import data.DatabaseRoomManager;
import data.DatabaseSeeder;
import data.Repository;
import entity.Room;
import ui.MainFrameController;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //   DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        //   databaseInitializer.initialize();

        Repository repository = new Repository();
        RoomFactory roomFactory = new SimpleRoomFactory(5);
        //RoomManager roomManager = new InMemoryRoomManager(roomFactory, new HistoryManager());
        RoomManager roomManager = new DatabaseRoomManager(repository);



        //  repository.insert(1, "Migle", "Pilinkute");
        //  repository.selectGuest("Migle", "Pilinkute");
        // repository.selectRoom(1);
        DatabaseSeeder databaseSeeder = new DatabaseSeeder(repository, roomFactory);
        databaseSeeder.seed();

        MainFrameController controller = new MainFrameController(roomManager);
        controller.showMainFrameWindow();

        ArrayList<Room> roomList = new ArrayList<>();



    }
}

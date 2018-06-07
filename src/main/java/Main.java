import core.RoomFactory;
import core.RoomManager;
import data.*;
import ui.MainFrameController;

public class Main {
    public static void main(String[] args) {

        DataSourceFactory dataSourceFactory = new DataSourceFactory();
        Repository repository = new Repository(dataSourceFactory.createDataSource());

        RoomFactory roomFactory = new SimpleRoomFactory(5);
        RoomManager roomManager = new DatabaseRoomManager(repository);

        DatabaseSeeder databaseSeeder = new DatabaseSeeder(repository, roomFactory);
        databaseSeeder.seed();

        MainFrameController controller = new MainFrameController(roomManager);
        controller.showMainFrameWindow();

    }
}

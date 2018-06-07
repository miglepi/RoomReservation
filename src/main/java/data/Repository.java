package data;

import entity.Guest;
import entity.Room;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;

public class Repository {

    private DataSource dataSource;

    private static final String GUESTS_TABLE = "guests";
    private static final String ROOMS_TABLE = "rooms";
    private static final String HISTORY_TABLE = "history";

    public Repository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertRoom(Room room) {
        String sql = "INSERT INTO " + ROOMS_TABLE + " (roomNumber, isBooked) VALUES (?,?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, room.getRoomNumber());
            statement.setBoolean(2, room.isBooked());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertGuest(Guest guest) {
        String sql = "INSERT INTO " + GUESTS_TABLE + " (name, surname) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql,
                     Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, guest.getName());
            statement.setString(2, guest.getSurname());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    guest.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating guest failed, no ID obtained.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertToHistory(int roomId, int guestId) {
        String sql = "INSERT INTO " + HISTORY_TABLE + " (roomId, guestId) VALUES (?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, roomId);
            statement.setInt(2, guestId);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean areRoomsCreated() {
        String sql = "SELECT * FROM " + ROOMS_TABLE;

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Room getAvailableRoom() {
        String sql = "SELECT * FROM " + ROOMS_TABLE + " WHERE IsBooked=0 LIMIT 1";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            if (resultSet.next()) {
                Room room = new Room(resultSet.getInt("RoomNumber"), resultSet.getBoolean("IsBooked"),
                        resultSet.getInt("RoomId"));
                return room;
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void updateRoom(Room room) {
        String sql = "UPDATE " + ROOMS_TABLE + " SET IsBooked=?, GuestId=? WHERE RoomId=?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setBoolean(1, room.isBooked());
            if (room.getGuest() == null) {
                statement.setNull(2, java.sql.Types.INTEGER);
            } else {
                statement.setInt(2, room.getGuest().getId());
            }
            statement.setInt(3, room.getRoomId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public ArrayList<Room> getBookedRooms() {

        String sql = "SELECT * FROM " + ROOMS_TABLE + " INNER JOIN " + GUESTS_TABLE + " ON guests.Id = rooms.GuestId";

        ArrayList<Room> rooms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                if (resultSet.getBoolean("IsBooked")) {
                    Room room = new Room(resultSet.getInt("RoomNumber"),
                            new Guest(resultSet.getString("Name"), resultSet.getString("Surname")),
                            resultSet.getInt("RoomId"));
                    rooms.add(room);
                }
            }
            return rooms;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Room> getRoomList() {

        String sql = "SELECT * FROM " + ROOMS_TABLE;

        ArrayList<Room> rooms = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Room room = new Room(resultSet.getInt("RoomNumber"), resultSet.getBoolean("IsBooked"), resultSet.getInt("RoomId"));
                rooms.add(room);

            }
            return rooms;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Guest> getGuestsByRoom(int roomId) {
        String sql = "SELECT * FROM " + HISTORY_TABLE + " INNER JOIN "
                + GUESTS_TABLE + " ON guests.Id = history.GuestId WHERE RoomId=?";

        ArrayList<Guest> guests = new ArrayList<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, roomId);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Guest guest = new Guest(resultSet.getString("Name"), resultSet.getString("Surname"));
                    guests.add(guest);
                }
                return guests;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}

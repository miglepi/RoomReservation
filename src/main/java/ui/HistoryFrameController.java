package ui;

import core.RoomManager;
import entity.Guest;
import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.List;

public class HistoryFrameController {

    private HistoryFrame historyFrame;
    private JButton backBtn;
    private JComboBox roomBox;
    private JTextField statusTf;
    private JTextArea historyArea;
    private RoomManager roomManager;


    public HistoryFrameController(RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
        initListeners();
        showRoomList();
        roomBox.setRenderer(new MyComboBoxRenderer("Select a room"));
        roomBox.setSelectedIndex(-1);
        historyArea.setText("");
        statusTf.setText("");
    }

    public void showHistoryFrameWindow() {
        historyFrame.setVisible(true);
    }


    private void initComponents() {
        historyFrame = new HistoryFrame();
        backBtn = historyFrame.getBackBtn();
        roomBox = historyFrame.getRoomBox();
        statusTf = historyFrame.getStatusTf();
        historyArea = historyFrame.getHistoryArea();
    }

    private void initListeners() {
        backBtn.addActionListener(new BackBtnLister());
        roomBox.addActionListener(new RoomBoxLister());
    }

    private class BackBtnLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrameController mainFrameController = new MainFrameController(roomManager);
            mainFrameController.showMainFrameWindow();
            historyFrame.setVisible(false);
        }
    }

    private class RoomBoxLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            List<Room> rooms = roomManager.getRoomList();
            for (int i = 0; i < rooms.size(); i++) {
                if (roomBox.getSelectedIndex() + 1 == rooms.get(i).getRoomNumber()) {
                    historyArea.setText("");
                    showGuestsList(rooms.get(i).getRoomId());
                    if (rooms.get(i).isBooked()) {
                        statusTf.setText("Booked");
                    } else {
                        statusTf.setText("Available");
                    }
                }
            }
        }
    }

    private void showRoomList() {
        List<Room> roomList = roomManager.getRoomList();
        String[] rooms = new String[roomList.size()];
        for(int i = 0; i < rooms.length; i++) {
            rooms[i] = String.valueOf(roomList.get(i).getRoomNumber());
            roomBox.addItem(rooms[i]);
        }
    }

    private void showGuestsList(int roomId) {
        List<Guest> guests = roomManager.getGuestListByRoom(roomId);
        guests.sort(Comparator.comparing(Guest::getId));
        if (guests.size() > 0) {
            for (Guest guest : guests) {
                historyArea.append(guest.getName() + " " + guest.getSurname() + "\n");
            }
        } else {
            historyArea.setText("Room history is empty.");
        }
    }
}

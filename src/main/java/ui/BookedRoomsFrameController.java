package ui;

import core.RoomManager;
import entity.Room;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class BookedRoomsFrameController {

    private BookedRoomsFrame bookedRoomsFrame;
    private JButton backBtn;
    private JList list;
    private RoomManager roomManager;

    public BookedRoomsFrameController(RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
        initListeners();
        showBookedRoomList();
    }

    public void showCurrentStatusFrameWindow() {
        bookedRoomsFrame.setVisible(true);
    }

    private void initComponents() {
        bookedRoomsFrame = new BookedRoomsFrame();
        backBtn = bookedRoomsFrame.getBackBtn();
        list = bookedRoomsFrame.getList();
    }

    private void initListeners() {
        backBtn.addActionListener(new BackBtnLister());
    }


    private class BackBtnLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrameController mainFrameController = new MainFrameController(roomManager);
            mainFrameController.showMainFrameWindow();
            bookedRoomsFrame.setVisible(false);
        }
    }

    private void showBookedRoomList() {
        List<Room> rooms = roomManager.getBookedRooms();
        String[] array = new String[rooms.size()];
        DefaultListModel listModel = new DefaultListModel();
        if (rooms.size() == 0) {
            listModel.addElement("All rooms are available to book!");
        } else {
            for (int i = 0; i < array.length; i++) {
                array[i] = "Room: " + rooms.get(i).getRoomNumber()
                        + "  Guest: " + rooms.get(i).getGuest().getName()
                        + " " + rooms.get(i).getGuest().getSurname();
                listModel.addElement(array[i]);
            }
        } list.setModel(listModel);
    }
}

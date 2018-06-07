package ui;

import core.RoomManager;
import entity.Room;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class CheckOutFrameController {

    private CheckOutFrame checkOutFrame;
    private JButton backBtn;
    private JList list;
    private JButton checkOutBtn;
    private RoomManager roomManager;
    private List<Room> rooms;

    public CheckOutFrameController(RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
        initListeners();
        showRoomList();
    }

    public void showCheckOutFrameWindow() {
        checkOutFrame.setVisible(true);
    }

    private void initComponents() {
        checkOutFrame = new CheckOutFrame();
        backBtn = checkOutFrame.getBackBtn();
        list = checkOutFrame.getList();
        checkOutBtn = checkOutFrame.getCheckOutBtn();
    }

    private void initListeners() {
        backBtn.addActionListener(new BackBtnLister());
        checkOutBtn.addActionListener(new CheckOutBtnLister());
        list.addListSelectionListener(new ListSelectionLister());
    }

    private class BackBtnLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            MainFrameController mainFrameController = new MainFrameController(roomManager);
            mainFrameController.showMainFrameWindow();
            checkOutFrame.setVisible(false);
        }
    }

    private class CheckOutBtnLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Room room = rooms.get(list.getSelectedIndex());
            roomManager.checkOut(room);
            if (!room.isBooked()) {
                JOptionPane.showMessageDialog(null,
                        "Guest is checked out. Room " + room.getRoomNumber() + " is empty.");
                showRoomList();
            } else {
                JOptionPane.showMessageDialog(null,
                        "Unable to check out a guest.");
            }
        }
    }

    private class ListSelectionLister implements javax.swing.event.ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            checkOutBtn.setEnabled(true);
        }
    }

    private void showRoomList() {
        rooms = roomManager.getBookedRooms();
        String[] array = new String[rooms.size()];
        DefaultListModel listModel = new DefaultListModel();
        if (rooms.size() == 0) {
            listModel.addElement("Rooms are empty. Please check in a guest!");
            list.setEnabled(false);
            checkOutBtn.setEnabled(false);
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

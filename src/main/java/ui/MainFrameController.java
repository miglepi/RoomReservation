package ui;

import core.RoomManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFrameController {

    private MainFrame mainFrame;
    private JButton checkInBtn;
    private JButton checkOutBtn;
    private JButton currentStatusBtn;
    private JButton historyBtn;
    private RoomManager roomManager;

    public MainFrameController(RoomManager roomManager) {
        this.roomManager = roomManager;
        initComponents();
        initListeners();
    }

    public void showMainFrameWindow() {
        mainFrame.setVisible(true);
    }

    private void initComponents() {
        mainFrame = new MainFrame();
        checkInBtn = mainFrame.getCheckInButton();
        checkOutBtn = mainFrame.getCheckOutBtn();
        currentStatusBtn = mainFrame.getCurrentStatusBtn();
        historyBtn = mainFrame.getHistoryBtn();
    }

    private void initListeners() {
        checkInBtn.addActionListener(new CheckInBtnLister());
        checkOutBtn.addActionListener(new CheckOutBtnLister());
        currentStatusBtn.addActionListener(new CurrenStatusBtnLister());
        historyBtn.addActionListener(new HistoryBtnLister());
    }

    private class CheckInBtnLister implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CheckInFrameController checkInFrameController = new CheckInFrameController(roomManager);
            checkInFrameController.showCheckInFrameWindow();
            mainFrame.setVisible(false);
        }
    }

    private class CheckOutBtnLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            CheckOutFrameController checkOutFrameController = new CheckOutFrameController(roomManager);
            checkOutFrameController.showCheckOutFrameWindow();
            mainFrame.setVisible(false);
        }
    }

    private class CurrenStatusBtnLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            BookedRoomsFrameController bookedRoomsFrameController = new BookedRoomsFrameController(roomManager);
            bookedRoomsFrameController.showCurrentStatusFrameWindow();
            mainFrame.setVisible(false);
        }
    }

    private class HistoryBtnLister implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            HistoryFrameController historyFrameController = new HistoryFrameController(roomManager);
            historyFrameController.showHistoryFrameWindow();
            mainFrame.setVisible(false);
        }
    }
}

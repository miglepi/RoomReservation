package core;

import entity.Guest;

public interface History {
    void addToHistory(int roomId, Guest guest);
}

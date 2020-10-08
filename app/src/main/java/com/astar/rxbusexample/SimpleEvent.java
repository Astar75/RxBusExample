package com.astar.rxbusexample;

/**
 * Простой объект для передачи.
 * Может быть любым
 */
public class SimpleEvent {
    private int count;

    public SimpleEvent(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}

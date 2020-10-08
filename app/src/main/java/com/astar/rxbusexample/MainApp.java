package com.astar.rxbusexample;

import android.app.Application;

public class MainApp extends Application {

    private RxBus bus;

    @Override
    public void onCreate() {
        super.onCreate();
        bus = new RxBus();
    }

    public RxBus getRxBus() {
        return bus;
    }
}

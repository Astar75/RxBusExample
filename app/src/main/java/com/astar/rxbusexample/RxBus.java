package com.astar.rxbusexample;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

public final class RxBus {

    public RxBus() {}

    private PublishSubject<Object> bus = PublishSubject.create();

    public void send(Object o) {
        bus.onNext(o);
    }

    public Observable<Object> listen() {
        return bus;
    }
}

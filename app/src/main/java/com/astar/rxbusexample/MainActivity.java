package com.astar.rxbusexample;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private Disposable disposable = null;
    private SomethingService service = null;
    private RxBus rxBus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rxBus = ((MainApp) getApplication()).getRxBus();
        service = new SomethingService(rxBus);
        service.start();

        listenEvents();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposable.dispose();
        service.stopService();
    }

    // тут принимаем данные
    private void listenEvents() {
        disposable = rxBus.listen()
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(final Object o) {
                        if (o instanceof SimpleEvent) {
                            String count = "Пришедшие данные: " + ((SimpleEvent) o).getCount();
                            showMessage(count);
                        }
                    }
                });
    }

    private void showMessage(final String message) {
        // запускаем Toast в главном UI потоке
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
package org.islamright.tebian.util;

import android.os.Handler;
import android.os.Looper;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

public final class BusProvider {
    private static final MainThreadBus BUS = new MainThreadBus();

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {
        // No instances.

    }
}


 class MainThreadBus extends Bus {
     private final Handler mHandler = new Handler(Looper.getMainLooper());

     @Override
     public void post(final Object event) {
         if (Looper.myLooper() == Looper.getMainLooper()) {
             super.post(event);
         } else {
             mHandler.post(new Runnable() {
                 @Override
                 public void run() {
                     MainThreadBus.super.post(event);
                 }
             });
         }
     }

 }

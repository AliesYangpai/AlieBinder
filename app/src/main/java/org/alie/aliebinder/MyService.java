package org.alie.aliebinder;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * 服务提供者
 */
public class MyService extends Service {


    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MyAidlImpl();
    }

}

package org.alie.aliebinder;

import android.os.RemoteException;

/**
 * Created by Alie on 2019/9/28.
 * 类描述
 * 版本
 */
public class MyAidlImpl extends IMyAidlInterface.Stub {

    private String name;

    @Override
    public String getName() throws RemoteException {
        return name;
    }

    @Override
    public String setName(String name) throws RemoteException {
        this.name = name;
        return null;
    }
}

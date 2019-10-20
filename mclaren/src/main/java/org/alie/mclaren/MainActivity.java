package org.alie.mclaren;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import org.alie.aliebinder.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void load(View view) {
        startActivity();
        Intent intent = new Intent();
        intent.setAction("org.alie.aliebinder.MyService");
        intent.setPackage("org.alie.aliebinder"); // 这里设置包名只是希望让指定的 app可以直接处理 一定程度上也是一种优化
        bindService(intent, new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                // 拿到proxy （不要忘了 proxy是发送）
                // IMyAidlInterface.Stub.asInterface，在C端连接成功，我们就需要去发送数据，就需要用到proxy
                // 而asInterface,就是返回的proxy，这个proxy，是具备发送能力的，也是实现了 IMyAidlInterface接口
                IMyAidlInterface iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
                try {
                    iMyAidlInterface.setName("LaFerrari");

                    Toast.makeText(MainActivity.this,
                            "====" + iMyAidlInterface.getName(), Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }
}

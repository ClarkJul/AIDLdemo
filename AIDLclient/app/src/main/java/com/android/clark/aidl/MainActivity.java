package com.android.clark.aidl;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends Activity {

    private static String TAG ="MainActivity";
    private ServiceConnection serviceConnection=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("------>","连接成功");
            IBookManager bookManager=IBookManager.Stub.asInterface(service);
            try {
                List<Book> list=bookManager.getBookList();
                Log.d("------>",list.size()+"");
                Log.d("------>",list.toString());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent();
        intent.setAction("com.android.clark.aidlserver.BookManagerService");
        intent.setPackage("com.android.clark.aidlserver");
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(serviceConnection);
    }
}

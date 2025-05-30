package com.aaa.market;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NWImageView extends androidx.appcompat.widget.AppCompatImageView {
    public static final int GET_DATA_SUCCESS = 1;
    public static final int NETWORK_ERROR = 2;
    public static final int SERVER_ERROR = 3;

    Context context;

    //子线程不能操作UI，通过Handler设置图片
    private final Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == GET_DATA_SUCCESS) {
                Bitmap bitmap = (Bitmap) msg.obj;
                setImageBitmap(bitmap);
            }
        }
    };

    public NWImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NWImageView(Context context) {
        super(context);
    }

    public NWImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //设置网络图片
    public void setImageURL(final String path) {
        //开启一个线程用于联网
        new Thread(() -> {
            try {
                //把传过来的路径转成URL
                URL url = new URL(path);
                //获取连接
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //使用GET方法访问网络
                connection.setRequestMethod("GET");
                //超时时间为3秒
                connection.setConnectTimeout(3000);
                //获取返回码
                int code = connection.getResponseCode();
                if (code == 200) {
                    InputStream inputStream = connection.getInputStream();
                    //使用工厂把网络的输入流生产Bitmap
                    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    //利用Message把图片发给Handler
                    Message msg = Message.obtain();
                    msg.obj = bitmap;
                    msg.what = GET_DATA_SUCCESS;
                    handler.sendMessage(msg);
                    inputStream.close();
                }else {
                    //服务启发生错误
                    handler.sendEmptyMessage(SERVER_ERROR);
                }
            } catch (IOException e) {
                e.printStackTrace();
                //网络连接错误
                handler.sendEmptyMessage(NETWORK_ERROR);
            }
        }).start();
    }

}
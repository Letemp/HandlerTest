package com.example.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//声明
	private TextView myText;
	private Handler myHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myText=(TextView)findViewById(R.id.myText);//注册
		myText.setText("生成的随机数为："+Math.random());
		myHandler=new Handler(){
			public void handlerMessage(Message msg){
				super.handleMessage(msg);
				//如果该消息是本程序所发送的，前后标志一样
				if(msg.what==0x12){
					myText.setText("生成的随机数为：\n"+Math.random());
				}
			}
		};
		//单独启动一个线程动态生成随机数
		new Thread(new Runnable(){
			public void run(){
				try{
					while(true){
						Thread.sleep(300);//程序休眠0.3秒
						Message msg=new Message();
						msg.what=0x12;//消息的标记
						myHandler.sendMessage(msg);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}).start();
	}
}

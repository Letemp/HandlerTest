package com.example.handlertest;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	//����
	private TextView myText;
	private Handler myHandler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		myText=(TextView)findViewById(R.id.myText);//ע��
		myText.setText("���ɵ������Ϊ��"+Math.random());
		myHandler=new Handler(){
			public void handlerMessage(Message msg){
				super.handleMessage(msg);
				//�������Ϣ�Ǳ����������͵ģ�ǰ���־һ��
				if(msg.what==0x12){
					myText.setText("���ɵ������Ϊ��\n"+Math.random());
				}
			}
		};
		//��������һ���̶߳�̬���������
		new Thread(new Runnable(){
			public void run(){
				try{
					while(true){
						Thread.sleep(300);//��������0.3��
						Message msg=new Message();
						msg.what=0x12;//��Ϣ�ı��
						myHandler.sendMessage(msg);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			};
		}).start();
	}
}

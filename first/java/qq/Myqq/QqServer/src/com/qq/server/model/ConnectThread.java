/**
 * ��������ĳ���ͻ���ͨ���߳�
 */

package com.qq.server.model;

import java.net.*;
import java.util.HashMap;
import java.util.Iterator;

import com.qq.common.Message;
import com.qq.common.MessageType;
import com.qq.server.tools.ManageClientThread;

import java.io.*;

public class ConnectThread extends Thread{
	
	Socket s;//�õ�һ��Socket
	
	public ConnectThread(Socket s)
	{
		//�ѷ�������ͻ��˵����Ӹ���s,���߳���һ��Socket
		this.s = s;
	}
	
	
	//�û�������Ϣ֪ͨ�������ˣ������û�
	public void notifyThread(String myId){
		HashMap hm = ManageClientThread.hm;
		Iterator it = hm.keySet().iterator(); 
		while(it.hasNext())
		{
			Message m = new Message();
			m.setCon(myId);//��con����������
			m.setMesType(MessageType.Message_return_online);//���ظ������������ߵ���Ϣ
			
			String onlineId = it.next().toString();
			try{
			ObjectOutputStream oos = new ObjectOutputStream
					(ManageClientThread.getClientThread(onlineId).s.getOutputStream());
			
			m.setGetter(onlineId);//onlineId�ĺ��ѻ��յ����û����ߵ���Ϣ
			oos.writeObject(m);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public void run()
	{
		while(true)
		{
			//��������߳̾Ϳ��Խ��տͻ��˵���Ϣ
			
			try{
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
			
				//��Ϣ�жϣ���ͨ��Ϣ�͸��º����б�
				if(m.getMesType().equals(MessageType.Message_com))
				{
					//����һ�·������õ��˿ͻ���1
				System.out.println(m.getSender() + "��" + m.getGetter()+" ˵�� " + m.getCon());
				
				//���ת��
				
				//ȡ�ý����˵�ͨѶ�߳�
				ConnectThread ct = ManageClientThread.getClientThread(m.getGetter());//�ҵ������˵�Socket
				ObjectOutputStream oos = new ObjectOutputStream(ct.s.getOutputStream());//�����˵�Socket�ͷ�����������
				oos.writeObject(m);//��������������ȥ
				}
				else if(m.getMesType().equals(MessageType.Message_get_online))
				{
					//���ڷ������ĺ��Ѹ����������û����ػ�ȥ
					//����hashmap�õ����ߵ���
					
					
					String res = ManageClientThread.getAllOnline();
					Message return_m = new Message();
					return_m.setMesType(MessageType.Message_return_online);
					return_m.setCon(res);
					return_m.setGetter(m.getSender());
					
					//������ڻ��͸�����˵ģ�����ת��
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());//�����˵�Socket�ͷ�����������
					oos.writeObject(return_m);//��������������ȥ
					
				}
				
				
			}catch(Exception e){
				
			}
		}
		
	}
}
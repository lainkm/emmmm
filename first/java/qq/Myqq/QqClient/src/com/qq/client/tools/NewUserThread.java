/*
 * �ͻ��˺ͷ������˱���ͨ�� ���߳�
 * 
 * ����һ̨�����ϵ�½�Ķ��qq������̣߳����й�������дһ��������
 * Ҳʵ��������߳���
 * */

package com.qq.client.tools;

import java.io.*;
import java.net.*;

import com.qq.client.view.QqChat;
import com.qq.client.view.QqList;
import com.qq.common.*;

public class NewUserThread extends Thread{

	private Socket s;
	public NewUserThread(Socket s) {
		this.s = s;
		
	}
	
	public void run(){
		while(true){
			//��ͣ�Ķ�ȡ�ӷ������˷�������Ϣ
			try{
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message)ois.readObject();
				
				if(m.getMesType().equals(MessageType.Message_com)){
					//�������ͨ��Ϣ�����Ѵӷ�������õ���Ϣ����ͣ�أ���ʾ���������
					System.out.println("��ȡ���ӷ�������������Ϣ  " + m.getSender() + "�� " +
				m.getGetter() + "�������ݣ�" + m.getCon());
				
				QqChat qc = ManageQqChat.getQqChat(m.getGetter() + " " + m.getSender());
				qc.ShowMessage(m);
				}
				else if(m.getMesType().equals(MessageType.Message_return_online)){
					//��������󷵻غ����б��İ�
					
					//�޸ĺ����б�
					QqList ql = ManageQqList.getQqList(m.getGetter());
					System.out.println("�ͻ����յ�" + m.getGetter());
					
					if(ql!=null)
					{
						ql.updateQqList(m);
					}
				}
				
				
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}
}
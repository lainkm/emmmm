package com.qq.server.tools;

import java.util.*;

import com.qq.server.model.ConnectThread;

public class ManageClientThread {
	
	public static HashMap hm= new HashMap<String, ConnectThread>();//ֻ����һ��hm�����þ�̬
	
	
	//��hm������һ���ͻ���ͨѶ�߳�
	public static void addClientThread(String userId, ConnectThread ct){
		hm.put(userId, ct);//���û�id���̷߳Ž�ȥ
	}
	
	//����һ���̣߳���ΪҪͨ������߳�ת��
	public static ConnectThread getClientThread(String userId)
	{
		return (ConnectThread)hm.get(userId);
	}
	
	//���ص�ǰ���ߵ��˵����
	public static String getAllOnline()
	{
		//hashMap�õ�keyֵ���õ�����
		String res = "";
		Iterator it=hm.keySet().iterator();
		while(it.hasNext())
		{
			res += it.next().toString() + " ";
		}
		return res;
	}
}
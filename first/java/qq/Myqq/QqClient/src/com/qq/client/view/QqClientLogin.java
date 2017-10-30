/*
 * qq�ͻ��˵�¼����
 * */
package com.qq.client.view;
import javax.swing.*;
import java.io.*;
import com.qq.client.model.QqClientUser;
import com.qq.common.User;
import com.qq.client.tools.*;
import com.qq.common.*;
import java.awt.*;
import java.awt.event.*;

public class QqClientLogin extends JFrame implements ActionListener, MouseListener{

	//��������
	JLabel jbl1;
	
	//�����в�����
	JPanel jp2;
	JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
	JTextField jp2_jtf;
	JPasswordField jp2_jpf;
	JCheckBox jp2_jcb1, jp2_jcb2;
	
	//�����·���ʽ��ť
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;
	
	int xOld = 0;
	int yOld = 0;
	//����
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QqClientLogin qqClientLogin = new QqClientLogin();
	}

	public QqClientLogin()
	{
		
		/*�����޸Ĵ��ڱ߿򣬵��ǳ��˵�����
        this.addMouseListener(new MouseAdapter() {  
            @Override  
            public void mousePressed(MouseEvent e) {  
                xOld = e.getX();  
                yOld = e.getY();  
            }  
        });  
        this.addMouseMotionListener(new MouseMotionAdapter() {  
            @Override  
            public void mouseDragged(MouseEvent e) {  
                int xOnScreen = e.getXOnScreen();  
                int yOnScreen = e.getYOnScreen();  
                int xx = xOnScreen - xOld;  
                int yy = yOnScreen - yOld;  
                QqClientLogin.this.setLocation(xx, yy);  
            }  
        });  
        */
		
		//����
		jbl1 = new JLabel(new ImageIcon("image/morning.jpg"));
		
		//�в�
		jp2 = new JPanel(new GridLayout(3, 3));
		jp2_jbl1 = new JLabel("qq�˻�", JLabel.CENTER);
		jp2_jbl2 = new JLabel("qq����", JLabel.CENTER);
		jp2_jbl3 = new JLabel("�һ�����", JLabel.CENTER);
		jp2_jbl4 = new JLabel("ע���˺�", JLabel.CENTER);
		jp2_jbl3.setForeground(Color.blue);
		jp2_jbl4.setForeground(Color.blue);
		
		jp2_jtf = new JTextField();
		jp2_jpf = new JPasswordField();
		jp2_jcb1 = new JCheckBox("������½");
		jp2_jcb2 = new JCheckBox("��ס����");
		
		//�ؼ�����
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf);
		jp2.add(jp2_jbl4);
		jp2.add(jp2_jbl2);
		jp2.add(jp2_jpf);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
	
		this.add(jp2);
		this.add(jp2,"Center");
	
		//�ϱ�
		jp1 = new JPanel();//Ĭ����ʽ����
		jp1_jb1 = new JButton(new ImageIcon("image/login.png"));
		
		//��Ӧ��½
		jp1_jb1.addActionListener(this);
		jp2_jbl4.addMouseListener(this);
		jp2_jbl3.addMouseListener(this);
		//��ť����jp1
		jp1.add(jp1_jb1);
		
		//��jbl1���ڱ���
		this.add(jbl1,"North");
		//��jp1�����ϱ�
		this.add(jp1,"South");
		
		this.setSize(400, 350);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		//�û������½
		if(e.getSource()==jp1_jb1)
		{
			//����qcu����
			QqClientUser qqClientUser = new QqClientUser();
			
			//�õ���½��user��Ϣ
			User u = new User();
			u.setId(jp2_jtf.getText().trim());
			u.setPw(new String(jp2_jpf.getPassword()));
			
			if(qqClientUser.checkUser(u))
			{
				
				try{
					
				QqList qqList = new QqList(u.getId());
				ManageQqList.addQqList(u.getId(), qqList);
				
				
				//����һ��Ҫ�󷵻����ߺ��ѵ�����������ʱ��CheckUser�Ѿ������߳���
				ObjectOutputStream oos = new ObjectOutputStream
						(ManageNewUserConnectThread.getNewUserThread(u.getId()).getS().getOutputStream());
				
				//����һ��message��
				Message m = new Message();
				m.setMesType(MessageType.Message_get_online);//����õ����ߺ��ѵİ�
				m.setSender(u.getId());//��ʾ������ǵ�ǰ����ŵ�qq���ѣ�������˭���͵�����
				oos.writeObject(m);
				
				}
				catch(Exception e0){
					e0.printStackTrace();
				}
				
				
				
				//�򿪺����б����رյ�¼����
				this.dispose();
			}
			else
			{
				JOptionPane.showMessageDialog(this, "�û������������");
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jp2_jbl3&& e.getClickCount() == 1)
		{
			//�һ�����
			
		}
		else if(e.getSource() == jp2_jbl4&& e.getClickCount() == 1)
		{
			//ע���˺�
			QqRegister qr = new QqRegister();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jp2_jbl4)
		jp2_jbl4.setForeground(Color.pink);
		else if(e.getSource() == jp2_jbl3)
			jp2_jbl3.setForeground(Color.pink);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == jp2_jbl4)
		jp2_jbl4.setForeground(Color.blue);
		else if(e.getSource() == jp2_jbl3)
			jp2_jbl3.setForeground(Color.blue);
	}

}
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class InsertFriendFrame extends JFrame {
	private JLabel name = new JLabel("�̸�");
	private JLabel group = new JLabel("�׷�");
	private JLabel phoneNumber = new JLabel("��ȭ��ȣ");
	private JLabel email = new JLabel("Email");
	private JLabel picture = new JLabel("����");
	
	private JTextField nameField = new JTextField();
	private JTextField groupField = new JTextField();
	private JTextField phoneNumberField = new JTextField();
	private JTextField emailField = new JTextField();
	private JTextField pictureField = new JTextField();
	
	private JButton doneButton = new JButton("Done");
	
	//friendListFrame�� ���ڷ� �޾ƿͼ� �߰��� ģ�� ���� â���� ģ�� ��� â�� ������ �� �ְ� ����
	public InsertFriendFrame(FriendListFrame friendListFrame) {
		super("�߰��� ģ�� ����");
		setSize(600,150);
		setLayout(new BorderLayout());		
		setVisible(true);
		
		JPanel chart = new JPanel(new GridLayout(2,5));
		
		chart.add(name);
		chart.add(group);
		chart.add(phoneNumber);
		chart.add(email);
		chart.add(picture);
		
		chart.add(nameField);//�̸� �� ��ġ	
		chart.add(groupField);//�׷�
		chart.add(phoneNumberField);//��ȭ��ȣ
		chart.add(emailField);//email
		chart.add(pictureField);//����
		
		JPanel body = new JPanel(new BorderLayout());
		
		body.add(chart,BorderLayout.CENTER );
		body.add(doneButton, BorderLayout.EAST);
		
		add(body);
			
		class DoneButtonHandler implements ActionListener {
			
			JTextField nameField, groupField, phoneNumberField, emailField;
			
			Friend friend = new Friend();
		
			//Ŭ������ textField���� �ּҸ� �޴´�.
			DoneButtonHandler(JTextField nameField, JTextField groupField, JTextField phoneNumberField, JTextField emailField){
				
				this.nameField = nameField;
				this.groupField = groupField;
				this.phoneNumberField = phoneNumberField;
				this.emailField = emailField;
				
			}
			
			public void actionPerformed(ActionEvent e) {
	
				//done ��ư�� ������ �����ڿ��� �޾ƿ� ģ�� ������ ���õ�
				//�߰��� ���� �� �ƹ��͵� ���� �κ��� ������ ���� ���
				try {
					friend.setName(nameField.getText());
					friend.setGroup(groupField.getText());
					friend.setPhoneNumber(phoneNumberField.getText());
					friend.setEmail(emailField.getText());
					
					//Ŭ�� ��� ���
					System.out.println("Done button clicked");
					
					//��â�� friendList�� ���ο� ģ�� �߰�
					friendListFrame.friendList.arr.add(friend);
					
					//���ο� ģ���� ���� line panel����->line ������ arraylist�� ����
					Line line = new Line(friend);
					friendListFrame.lineArr.add(line);
					
					//���ο� ģ���� �߰��ʿ� ���� ���̾ƿ��� �ٲ��ֱ� -> line�߰�
					friendListFrame.wholeChart.setLayout(new GridLayout(friendListFrame.friendList.numFriends()+1,1));
					friendListFrame.wholeChart.add(line);
					
					//���ΰ�ħ
					friendListFrame.revalidate();
			
					//�߰�â �ݱ�
					dispose();
				} catch (NoInformationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}				
			}
		}		
		DoneButtonHandler listener = new DoneButtonHandler(nameField, groupField, phoneNumberField, emailField);
		doneButton.addActionListener(listener);	
	}	}

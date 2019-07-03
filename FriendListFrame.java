import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class FriendListFrame extends JFrame implements ActionListener {	
	JButton addButton = new JButton("Add");
	JButton deleteButton = new JButton("Delete");
	JButton modifyButton = new JButton("Modify");
	JButton saveFileButton = new JButton("Save File");

	JLabel empty = new JLabel("        ");
	JLabel name = new JLabel("�̸�");
	JLabel group = new JLabel("�׷�");
	JLabel phoneNumber = new JLabel("��ȭ��ȣ");
	JLabel email = new JLabel("Email");
	JLabel picture = new JLabel("����	");
	
	JPanel wholeChart;
	JPanel buttonsPanel;
	JPanel list;
		
	FriendList friendList;
	ArrayList<Line> lineArr;

	public FriendListFrame(FriendList friendList) {
		//ģ�� ��� â �����
		super("ģ�� ���");	
		setLayout(new BorderLayout());	
		setSize(600,400);		
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ǥ �κ� panel����� 
		wholeChart = new JPanel(new GridLayout(friendList.numFriends()+1,1));
		
		//��ư panel�����
		buttonsPanel = new JPanel(new GridLayout(4,1));		
		buttonsPanel.add(addButton);
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(modifyButton);
		buttonsPanel.add(saveFileButton);
		
		//ǥ�� ���� ���� menu panel �����
		list = new JPanel(new GridLayout(1,6));		
		list.add(empty);
		list.add(name);
		list.add(group);
		list.add(phoneNumber);
		list.add(email);
		list.add(picture);
		
		//ǥ�� menu panel ���ϱ�
		wholeChart.add(list);
		
		//���ڷ� ���� friendList�� �ּҸ� �� Ŭ������ �ֱ�
		this.friendList = friendList;
		
		//ǥ�� ���� �κ��� ��Ÿ���� arraylist�� ����� line�� �߰��ϱ�
		lineArr = new ArrayList<Line>();
		for(int i =0; i<friendList.numFriends();i++) {
			Friend friend = new Friend();
			friend = friendList.arr.get(i);
			
			Line line = new Line(friend);
			lineArr.add(line);
			
			wholeChart.add(line);
		}
		
		//�����ӿ� �г� �߰��ϱ�
		add(wholeChart, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.EAST);
		
		//��ư�� �̺�Ʈ �޾��ֱ�
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		modifyButton.addActionListener(this);
		saveFileButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			if(e.getSource() == addButton) {
				//�߰� â ����
				//�̶� �� Ŭ������ ���ڷ� �Ѱ��༭ ���ο� â���� ���� â�� ������ �� �ֵ��� ��
				InsertFriendFrame newInsertFriendFrame = new InsertFriendFrame(this);	
				
				//add ��ư Ŭ�� ��� �ֿܼ� ���
				System.out.println("Add button clicked");
			}			
			else if(e.getSource() == deleteButton) {
				//delete ��ư Ŭ�� ��� �ֿܼ� ���
				System.out.println("Delete button clicked");

				//üũ �ڽ��� üũ�� ģ�� ���� ����
				for(int i=0; i<friendList.numFriends(); i++)
					if( lineArr.get(i).checkBox.isSelected()) {
						friendList.arr.remove(i);
						lineArr.remove(i);
						break;
					}
					
				//ģ�� ��� â ���ΰ�ħ
				update();
				revalidate();
			}
			else if (e.getSource() == modifyButton) {	
				//modify ��ư Ŭ�� ��� �ֿܼ� ���
				System.out.println("Modify button clicked");
				
				//üũ �ڽ��� üũ�� ģ�� ������ ���Ͽ� modify��
				//�ٲ� ģ�� ���� �� �ƹ��͵� ���� �κ��� ������ ���� ���
				try {
					for(int i =0; i<friendList.numFriends(); i++) {
						if(lineArr.get(i).checkBox.isSelected()) {
						
						//i��° ģ�� ���� �ٲٱ�
						Friend friend = new Friend();
										
						friend.setName(lineArr.get(i).nameLabel.getText());
						friend.setGroup(lineArr.get(i).groupField.getText());
						friend.setPhoneNumber(lineArr.get(i).phoneNumberField.getText());
						friend.setEmail(lineArr.get(i).emailField.getText());
						
						//friendList�� i��° ģ�� ���� ����
						friendList.arr.set(i,friend);
				
						//���� ���·� �ǵ�����
						lineArr.get(i).checkBox.doClick();
						lineArr.get(i).groupField.setEditable(false);		
						lineArr.get(i).emailField.setEditable(false);
						lineArr.get(i).phoneNumberField.setEditable(false);
						
						break;
						}
					}
					
					//ģ�� ��� â ���ΰ�ħ
					update();
					revalidate();
				}catch (NoInformationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
			else if(e.getSource() == saveFileButton) {	
				//save file ��ư Ŭ�� ��� �ֿܼ� ���
				System.out.println("Save file button clicked");
				
				//IOException�� �߻��� �� �����Ƿ� try-catch������ ����
				try {
					//���α׷��� ����� friendList�� ���Ͽ� ����
					FriendListFile friendListFile = new FriendListFile();
					friendListFile.readListToFile(friendList);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	
	//ģ�� ��� â ���ΰ�ħ
	private void update() {	
			//wholeChart �г��� �ٲ�Ƿ� �̿� ���Ͽ� ���� ������Ʈ -> ���ΰ�ħ
			wholeChart.removeAll();
			wholeChart.setLayout(new GridLayout(friendList.numFriends()+1,1));
			wholeChart.add(list);
			for(int i =0; i< friendList.numFriends(); i++)
				wholeChart.add(lineArr.get(i));
		}
}
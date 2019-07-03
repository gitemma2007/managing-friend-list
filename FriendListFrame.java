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
	JLabel name = new JLabel("이름");
	JLabel group = new JLabel("그룹");
	JLabel phoneNumber = new JLabel("전화번호");
	JLabel email = new JLabel("Email");
	JLabel picture = new JLabel("사진	");
	
	JPanel wholeChart;
	JPanel buttonsPanel;
	JPanel list;
		
	FriendList friendList;
	ArrayList<Line> lineArr;

	public FriendListFrame(FriendList friendList) {
		//친구 목록 창 만들기
		super("친구 목록");	
		setLayout(new BorderLayout());	
		setSize(600,400);		
		setVisible(true);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//표 부분 panel만들기 
		wholeChart = new JPanel(new GridLayout(friendList.numFriends()+1,1));
		
		//버튼 panel만들기
		buttonsPanel = new JPanel(new GridLayout(4,1));		
		buttonsPanel.add(addButton);
		buttonsPanel.add(deleteButton);
		buttonsPanel.add(modifyButton);
		buttonsPanel.add(saveFileButton);
		
		//표의 가장 위의 menu panel 만들기
		list = new JPanel(new GridLayout(1,6));		
		list.add(empty);
		list.add(name);
		list.add(group);
		list.add(phoneNumber);
		list.add(email);
		list.add(picture);
		
		//표에 menu panel 더하기
		wholeChart.add(list);
		
		//인자로 받은 friendList의 주소를 이 클래스에 주기
		this.friendList = friendList;
		
		//표의 정보 부분을 나타내는 arraylist를 만들고 line을 추가하기
		lineArr = new ArrayList<Line>();
		for(int i =0; i<friendList.numFriends();i++) {
			Friend friend = new Friend();
			friend = friendList.arr.get(i);
			
			Line line = new Line(friend);
			lineArr.add(line);
			
			wholeChart.add(line);
		}
		
		//프레임에 패널 추가하기
		add(wholeChart, BorderLayout.CENTER);
		add(buttonsPanel, BorderLayout.EAST);
		
		//버튼에 이벤트 달아주기
		addButton.addActionListener(this);
		deleteButton.addActionListener(this);
		modifyButton.addActionListener(this);
		saveFileButton.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
			if(e.getSource() == addButton) {
				//추가 창 띄우기
				//이때 이 클래스를 인자로 넘겨줘서 새로운 창에서 현재 창을 관리할 수 있도록 함
				InsertFriendFrame newInsertFriendFrame = new InsertFriendFrame(this);	
				
				//add 버튼 클릭 사실 콘솔에 출력
				System.out.println("Add button clicked");
			}			
			else if(e.getSource() == deleteButton) {
				//delete 버튼 클릭 사실 콘솔에 출력
				System.out.println("Delete button clicked");

				//체크 박스가 체크된 친구 정보 삭제
				for(int i=0; i<friendList.numFriends(); i++)
					if( lineArr.get(i).checkBox.isSelected()) {
						friendList.arr.remove(i);
						lineArr.remove(i);
						break;
					}
					
				//친구 목록 창 새로고침
				update();
				revalidate();
			}
			else if (e.getSource() == modifyButton) {	
				//modify 버튼 클릭 사실 콘솔에 출력
				System.out.println("Modify button clicked");
				
				//체크 박스가 체크된 친구 정보에 대하여 modify함
				//바뀐 친구 정보 중 아무것도 없는 부분이 있으면 오류 띄움
				try {
					for(int i =0; i<friendList.numFriends(); i++) {
						if(lineArr.get(i).checkBox.isSelected()) {
						
						//i번째 친구 내용 바꾸기
						Friend friend = new Friend();
										
						friend.setName(lineArr.get(i).nameLabel.getText());
						friend.setGroup(lineArr.get(i).groupField.getText());
						friend.setPhoneNumber(lineArr.get(i).phoneNumberField.getText());
						friend.setEmail(lineArr.get(i).emailField.getText());
						
						//friendList의 i번째 친구 내용 갱신
						friendList.arr.set(i,friend);
				
						//원래 상태로 되돌리기
						lineArr.get(i).checkBox.doClick();
						lineArr.get(i).groupField.setEditable(false);		
						lineArr.get(i).emailField.setEditable(false);
						lineArr.get(i).phoneNumberField.setEditable(false);
						
						break;
						}
					}
					
					//친구 목록 창 새로고침
					update();
					revalidate();
				}catch (NoInformationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}			
			}
			else if(e.getSource() == saveFileButton) {	
				//save file 버튼 클릭 사실 콘솔에 출력
				System.out.println("Save file button clicked");
				
				//IOException이 발생할 수 있으므로 try-catch문으로 구성
				try {
					//프로그램에 저장된 friendList을 파일에 저장
					FriendListFile friendListFile = new FriendListFile();
					friendListFile.readListToFile(friendList);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	
	//친구 목록 창 새로고침
	private void update() {	
			//wholeChart 패널이 바뀌므로 이에 대하여 정보 업데이트 -> 새로고침
			wholeChart.removeAll();
			wholeChart.setLayout(new GridLayout(friendList.numFriends()+1,1));
			wholeChart.add(list);
			for(int i =0; i< friendList.numFriends(); i++)
				wholeChart.add(lineArr.get(i));
		}
}
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
	private JLabel name = new JLabel("이름");
	private JLabel group = new JLabel("그룹");
	private JLabel phoneNumber = new JLabel("전화번호");
	private JLabel email = new JLabel("Email");
	private JLabel picture = new JLabel("사진");
	
	private JTextField nameField = new JTextField();
	private JTextField groupField = new JTextField();
	private JTextField phoneNumberField = new JTextField();
	private JTextField emailField = new JTextField();
	private JTextField pictureField = new JTextField();
	
	private JButton doneButton = new JButton("Done");
	
	//friendListFrame를 인자로 받아와서 추가될 친구 정보 창에서 친구 목록 창을 제어할 수 있게 설정
	public InsertFriendFrame(FriendListFrame friendListFrame) {
		super("추가될 친구 정보");
		setSize(600,150);
		setLayout(new BorderLayout());		
		setVisible(true);
		
		JPanel chart = new JPanel(new GridLayout(2,5));
		
		chart.add(name);
		chart.add(group);
		chart.add(phoneNumber);
		chart.add(email);
		chart.add(picture);
		
		chart.add(nameField);//이름 들어갈 위치	
		chart.add(groupField);//그룹
		chart.add(phoneNumberField);//전화번호
		chart.add(emailField);//email
		chart.add(pictureField);//사진
		
		JPanel body = new JPanel(new BorderLayout());
		
		body.add(chart,BorderLayout.CENTER );
		body.add(doneButton, BorderLayout.EAST);
		
		add(body);
			
		class DoneButtonHandler implements ActionListener {
			
			JTextField nameField, groupField, phoneNumberField, emailField;
			
			Friend friend = new Friend();
		
			//클래스의 textField들의 주소를 받는다.
			DoneButtonHandler(JTextField nameField, JTextField groupField, JTextField phoneNumberField, JTextField emailField){
				
				this.nameField = nameField;
				this.groupField = groupField;
				this.phoneNumberField = phoneNumberField;
				this.emailField = emailField;
				
			}
			
			public void actionPerformed(ActionEvent e) {
	
				//done 버튼을 누르면 생성자에서 받아온 친구 정보가 세팅됨
				//추가할 정보 중 아무것도 없는 부분이 있으면 오류 띄움
				try {
					friend.setName(nameField.getText());
					friend.setGroup(groupField.getText());
					friend.setPhoneNumber(phoneNumberField.getText());
					friend.setEmail(emailField.getText());
					
					//클릭 사실 출력
					System.out.println("Done button clicked");
					
					//본창의 friendList에 새로운 친구 추가
					friendListFrame.friendList.arr.add(friend);
					
					//새로운 친구에 대한 line panel생성->line 모으는 arraylist에 저장
					Line line = new Line(friend);
					friendListFrame.lineArr.add(line);
					
					//새로운 친구가 추가됨에 따라 레이아웃을 바꿔주기 -> line추가
					friendListFrame.wholeChart.setLayout(new GridLayout(friendListFrame.friendList.numFriends()+1,1));
					friendListFrame.wholeChart.add(line);
					
					//새로고침
					friendListFrame.revalidate();
			
					//추가창 닫기
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

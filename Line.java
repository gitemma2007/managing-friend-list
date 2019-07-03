import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Line extends JPanel{
	JCheckBox checkBox;
	JLabel nameLabel;
	JTextField groupField,phoneNumberField,emailField;
	
	//인자로 온 friend에 대하여 친구 정보 한 줄 panel생성
	Line(Friend friend){
		setLayout(new GridLayout(1,6));
		
		checkBox = new JCheckBox();
		nameLabel = new JLabel(friend.getName());
		groupField = new JTextField(friend.getGroup());
		phoneNumberField = new JTextField(friend.getPhoneNumber());
		emailField = new JTextField(friend.getEmail());

		//초기 상태는 수정할 수 없게 설정
		groupField.setEditable(false);
		phoneNumberField.setEditable(false);
		emailField.setEditable(false);
		
		add(checkBox);
		add(nameLabel); //이름 들어갈 위치
		add(groupField);//그룹
		add(phoneNumberField);//전화번호
		add(emailField);//email
		add(new JLabel("      ")); //사진 부분은 비어있음	
		
		//checkbox가 클릭되면 친구 정보를 수정할 수 있게 함
		class CheckBoxHandler implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				groupField.setEditable(true);
				phoneNumberField.setEditable(true);
				emailField.setEditable(true);
			}
		}
		
		CheckBoxHandler listener = new CheckBoxHandler();
		checkBox.addActionListener(listener);
	}	
}


	

	

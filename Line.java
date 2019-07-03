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
	
	//���ڷ� �� friend�� ���Ͽ� ģ�� ���� �� �� panel����
	Line(Friend friend){
		setLayout(new GridLayout(1,6));
		
		checkBox = new JCheckBox();
		nameLabel = new JLabel(friend.getName());
		groupField = new JTextField(friend.getGroup());
		phoneNumberField = new JTextField(friend.getPhoneNumber());
		emailField = new JTextField(friend.getEmail());

		//�ʱ� ���´� ������ �� ���� ����
		groupField.setEditable(false);
		phoneNumberField.setEditable(false);
		emailField.setEditable(false);
		
		add(checkBox);
		add(nameLabel); //�̸� �� ��ġ
		add(groupField);//�׷�
		add(phoneNumberField);//��ȭ��ȣ
		add(emailField);//email
		add(new JLabel("      ")); //���� �κ��� �������	
		
		//checkbox�� Ŭ���Ǹ� ģ�� ������ ������ �� �ְ� ��
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


	

	

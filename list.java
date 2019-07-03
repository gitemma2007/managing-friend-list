import java.util.ArrayList;

import javax.swing.JTextField;

class Friend
{
	private String name; 		//�̸�
	private String group; 		//�׷�
	private String phoneNumber; //��ȭ��ȣ
	private String email; 		//�̸��� 
	
	//�̸�, �׷�, ��ȭ��ȣ, �̸����� �����ϰų� �˷��ִ� �޼ҵ��, �ƹ� ������ ������ ������ ���� ���
	public String getName() {return name;}
	public void setName(String name) throws NoInformationException {
		if(!name.isEmpty())
			this.name = name;
		else 
			throw new NoInformationException("name ������ �����ϴ�.");
		}
	
	public String getGroup() {return group;}
	public void setGroup(String group) throws NoInformationException {
		if(!group.isEmpty()) 
			this.group = group;
		else
			throw new NoInformationException("group ������ �����ϴ�.");
	}
	
	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) throws NoInformationException {
		if(!phoneNumber.isEmpty())
			this.phoneNumber = phoneNumber;
		else
			throw new NoInformationException("phone number ������ �����ϴ�.");
		}
	
	public String getEmail() {return email;}
	public void setEmail(String email) throws NoInformationException {
		if(!email.isEmpty())
			this.email = email;
		else
			throw new NoInformationException("email ������ �����ϴ�.");
		}
	
	//�ϳ��� ģ�� ���� ���
	public void print(){
		System.out.println(name +" : "+ group+" : " + phoneNumber+" : " + email + " : "); 
	}
}

class FriendList
{
	//ģ�� ����� arraylist�� ����
	ArrayList<Friend> arr = new ArrayList <Friend>();
	
	//i��° ģ�� ���� �˷��ִ� �޼ҵ�
	public Friend getFriend(int i){
		return arr.get(i);
	}
	
	//ģ�� ��Ͽ� �� �� �ִ��� �˷��ִ� �޼ҵ�
	public int numFriends(){
		return arr.size();
	}
}
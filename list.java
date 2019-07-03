import java.util.ArrayList;

import javax.swing.JTextField;

class Friend
{
	private String name; 		//이름
	private String group; 		//그룹
	private String phoneNumber; //전화번호
	private String email; 		//이메일 
	
	//이름, 그룹, 전화번호, 이메일을 설정하거나 알려주는 메소드들, 아무 정보도 들어오지 않으면 오류 띄움
	public String getName() {return name;}
	public void setName(String name) throws NoInformationException {
		if(!name.isEmpty())
			this.name = name;
		else 
			throw new NoInformationException("name 정보가 없습니다.");
		}
	
	public String getGroup() {return group;}
	public void setGroup(String group) throws NoInformationException {
		if(!group.isEmpty()) 
			this.group = group;
		else
			throw new NoInformationException("group 정보가 없습니다.");
	}
	
	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String phoneNumber) throws NoInformationException {
		if(!phoneNumber.isEmpty())
			this.phoneNumber = phoneNumber;
		else
			throw new NoInformationException("phone number 정보가 없습니다.");
		}
	
	public String getEmail() {return email;}
	public void setEmail(String email) throws NoInformationException {
		if(!email.isEmpty())
			this.email = email;
		else
			throw new NoInformationException("email 정보가 없습니다.");
		}
	
	//하나의 친구 정보 출력
	public void print(){
		System.out.println(name +" : "+ group+" : " + phoneNumber+" : " + email + " : "); 
	}
}

class FriendList
{
	//친구 목록을 arraylist로 관리
	ArrayList<Friend> arr = new ArrayList <Friend>();
	
	//i번째 친구 정보 알려주는 메소드
	public Friend getFriend(int i){
		return arr.get(i);
	}
	
	//친구 목록에 몇 명 있는지 알려주는 메소드
	public int numFriends(){
		return arr.size();
	}
}
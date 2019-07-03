import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class FriendListFile
{	
	File file;
	public FriendList readFileToList(String fileName)
	{		
		FriendList newFriendList = new FriendList();
		try 
		{
			file = new File(fileName);
						
			int i = 0;
			
			Scanner input = new Scanner(file);
			
			while(input.hasNext())
			{								
				String line = input.nextLine();
				
				boolean start = line.startsWith("//");
				
			    if( start == false && line != "\n") 
				{
			        Friend newFriend= new Friend();
			    	
			    	line = line.replaceAll("\\p{Z}","");
					
					String[] tokens = line.split(":");
				
					newFriend.setName(tokens[0]);
					
					//group �̸� ���� Ȯ��
					if(tokens[1].equals("1") == true || tokens[1].equals("2") == true || tokens[1].equals("3") == true)
						newFriend.setGroup(tokens[1]);
					else
					{
						System.out.println("Illegal value");
						continue;
					}
					
					newFriend.setPhoneNumber(tokens[2]);
					
					//email �Է¿� ���� �ִ��� Ȯ��
					if(tokens[3].contains("@") == true)
						newFriend.setEmail(tokens[3]);
					else
					{
						System.out.println("Illegal email address");
						continue;
					}	
					
					//case 3-1 üũ  
					if(tokens[0] == null ||tokens[1] == null ||tokens[2] == null) //||tokens[3] == null )
						{
							System.out.println("Irregular Input Line");
							continue;
						}
									
					newFriendList.arr.add(i, newFriend);
					i++;
				}		
			}		
			input.close();			
		}
		catch(FileNotFoundException e) 
		{
			System.out.println("Unknown File");
		}
		catch(Exception e)
		{
			e.printStackTrace();
			System.out.println("error");
		}			
		
		//name ��ġ�� �� �˻�
		int nameCheck = 0;
		for(int i = 0; i< newFriendList.numFriends() - 1; i++)
		{
			Friend friend1 = new Friend();
			friend1 = newFriendList.arr.get(i);
			String s1 = friend1.getName();
			
			for(int j = i+1; j < newFriendList.numFriends(); j++)
			{			
				Friend friend2 = new Friend();
				friend2 = newFriendList.arr.get(j);
				String s2 = friend2.getName();
				
				if(s2.equals(s1) == true)	
					nameCheck++;					
			}
		}
		if(nameCheck != 0)
			System.out.println("Name Conflict");
		
		return newFriendList;
	}	
	
	public void readListToFile(FriendList friendList) throws IOException {
		//���� ��ġ �ҷ�����
		File file = new File("C:\\Users\\������\\Desktop\\friendlist-norm.data");
		//output stream����
		FileOutputStream fos = new FileOutputStream(file);
		//���α׷��� ����� ģ������� ��Ŀ� �°� ���Ͽ� ����
		for(int i=0; i<friendList.numFriends(); i++) {
			
			//friend��ü �����Ͽ� i��° ģ�� �ޱ�
			Friend friend = new Friend();
			friend = friendList.arr.get(i);
			
			//friendŬ������ �޼ҵ带 �̿��Ͽ� ���� �Ѱ��ֱ�
			String name = friend.getName();
			String group = friend.getGroup();
			String phoneNumber = friend.getPhoneNumber();
			String email = friend.getEmail();
		
			//��Ŀ� �°� ģ�� ���� �� �� ����
			String str = name + " 	: 	" + group + " 	:	 " + phoneNumber + " 	:	 " + email +"\n";
			
			//�� �� ���Ͽ� ����
			fos.write(str.getBytes());
		}
	}
}
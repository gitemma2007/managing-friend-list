import java.awt.GridLayout;

public class Test{
	public static void main(String[] args) {
		FriendListFile newFile = new FriendListFile();
		FriendList newFriendList = newFile.readFileToList("C:\\Users\\∞≠πŒ¡÷\\Desktop\\friendlist-norm.data");
		
		FriendListFrame friendListFrame = new FriendListFrame(newFriendList);
	}
}
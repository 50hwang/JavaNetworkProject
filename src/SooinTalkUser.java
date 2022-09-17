import java.net.Socket;


public class SooinTalkUser {

	/* fields */
	private String userId;
	private String userPw;
	private String userName;
	private int payAmount;
	
	private Socket userSocket;
	private int userSocketIndex;
	
	private boolean isLogin;

	/* constructors */

	
	/* methods */
	public boolean isLogin() {
		return isLogin;
	}
	public void setLogin(boolean isLogin) {
		this.isLogin = isLogin;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(int payAmount) {
		this.payAmount = payAmount;
	}
	public Socket getUserSocket() {
		return userSocket;
	}
	public void setUserSocket(Socket userSocket) {
		this.userSocket = userSocket;
	}
	public int getUserSocketIndex() {
		return userSocketIndex;
	}
	public void setUserSocketIndex(int userSocketIndex) {
		this.userSocketIndex = userSocketIndex;
	}	
	
	
	
}

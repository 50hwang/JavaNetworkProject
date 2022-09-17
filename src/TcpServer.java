import java.io.IOException;
import java.util.ArrayList;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;

public class TcpServer {
	
	private static ArrayList<SooinTalkUser> dummyUserList;
	private static ArrayList<SooinTalkUser> connectUserList;
	
	public static void createDummyUserList() {
		dummyUserList = new ArrayList<SooinTalkUser>();
		
		SooinTalkUser pkh = new SooinTalkUser();
		pkh.setUserId("pkh1115");
		pkh.setUserPw("1234");
		pkh.setUserName("Park Kyu Ho");
		pkh.setPayAmount(1000000);
		dummyUserList.add(pkh);

		SooinTalkUser hsi = new SooinTalkUser();
		pkh.setUserId("shi0902");
		pkh.setUserPw("1234");
		pkh.setUserName("Hwang Soo In");
		pkh.setPayAmount(500000);
		dummyUserList.add(hsi);
	}
	
	public static void main(String[] args) {
		ServerSocket serverSocket = null;
		Socket socket = null;
		
		//ArrayList<Socket> socketList = new ArrayList<Socket>();
		createDummyUserList();
		
		connectUserList = new ArrayList<SooinTalkUser>();
		
		try {
			serverSocket = new ServerSocket();
			serverSocket.bind(new InetSocketAddress("localhost", 5001));	//192.168.0.11

			while(true) {
				System.out.println("Wait connection...]");
				socket = serverSocket.accept();
				//inputStream = socket.getInputStream();
				System.out.println("Connect");
				
				SooinTalkUser acceptUser = new SooinTalkUser();
				acceptUser.setUserSocket(socket);
				acceptUser.setUserSocketIndex(connectUserList.size());
				connectUserList.add(acceptUser);
				
				//socketList.add(socket);
				
				Thread threadSocket = new Thread(new TcpServerSub(connectUserList, connectUserList.size() - 1));
				threadSocket.start();
				
				//for(int i = 0; i < socketList.size(); i++) {
				//	OutputStream os = socketList.get(i).getOutputStream();
				//	String strSend = new String("Hello Clients");
				//	byte[] byteSend = new byte[100];
				//	byteSend = strSend.getBytes("UTF-8");
					
				//	os.write(byteSend);
				//	os.flush();
				//}
			
			}
			
			/*byte[] byteRecv = new byte[100];
			
			while(true) { 
				int recvNum = inputStream.available();
			    if(recvNum > 0) {
			    	recvNum = inputStream.read(byteRecv);
			    	String strRecv = new String(byteRecv, 0, recvNum);
			    	System.out.println(strRecv);
			    } else {
			    
			    }
			}*/
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	public ArrayList<SooinTalkUser> getUserList() {
		return dummyUserList;
	}

	public void setUserList(ArrayList<SooinTalkUser> userList) {
		this.dummyUserList = userList;
	}
}

import java.net.Socket;
import java.util.ArrayList;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

public class TcpServerSub implements Runnable {

	private ArrayList<SooinTalkUser> connectUserList;
	private ArrayList<SooinTalkUser> checkUserList;
	private int userIndex;
	private InputStream is;
	byte[] byteRecv = new byte[5000];

	public TcpServerSub(ArrayList<SooinTalkUser> inputUserList, ArrayList<SooinTalkUser> dummyUserList, int inputUserIndex) {
		connectUserList = inputUserList;
		checkUserList = dummyUserList;
		userIndex = inputUserIndex;

		try {
			is = connectUserList.get(inputUserIndex).getUserSocket().getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public boolean processLogin(ClientData recvData) {
		
		System.out.println(recvData.getID());
		System.out.println(recvData.getPw());
		
		for(int i = 0; i < checkUserList.size(); i++) {
			if((checkUserList.get(i).getUserId().equals(recvData.getID())) && (checkUserList.get(i).getUserPw().equals(recvData.getPw()))) {
				System.out.println("로그인에 성공했습니다.");
				
				connectUserList.get(userIndex).setLogin(true);
				connectUserList.get(userIndex).setUserId(recvData.getID());
				connectUserList.get(userIndex).setUserPw(recvData.getPw());
				connectUserList.get(userIndex).setPayAmount(checkUserList.get(i).getPayAmount());
				
				System.out.println("ID : " + connectUserList.get(userIndex).getUserId() + ", Amount : " + connectUserList.get(userIndex).getPayAmount());
				return true;
			}
		}
		return false;
	}

	public void run() {
		try {
			while (true) {
				
				// TODO : should make log in process
					// �대���ㅼ�� 吏��ы��
				
				if (is.available() > 0) {
					int byteCount = is.read(byteRecv);
					
					ByteArrayInputStream bis = new ByteArrayInputStream(byteRecv);
					
					ObjectInputStream ois = new ObjectInputStream(bis);
					
					Object recvObject = ois.readObject();
					
					ClientData recvData = (ClientData)recvObject;
					if(recvData.getHeaderType() == 0xFF01) {
						processLogin(recvData);
					}
					//String strRecv = new String(byteRecv, 0, byteCount);
					//System.out.println(strRecv);
					
					/* for (int i = 0; i < connectUserList.size(); i++) {
						if(userIndex != i) {
							OutputStream os = connectUserList.get(i).getUserSocket().getOutputStream();
							byte[] byteSend = new byte[100];
							byteSend = strRecv.getBytes("UTF-8");
							
							os.write(byteSend);
							os.flush();							
						}
					}
					*/
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

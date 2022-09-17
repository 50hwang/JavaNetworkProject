import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.util.*;
import java.net.Socket;

class TcpClientSub implements Runnable {
	
	private Socket subSocket;
	private String subName;
	private String subMessage;
	OutputStream os;
	
	Scanner scin = new Scanner(System.in);
	
	public TcpClientSub(Socket socket, String name) {
		subSocket = socket;
		subName = name;
	}
	
	public void run() {
		try {
			os = subSocket.getOutputStream();
		} catch (Exception e) {
			e.printStackTrace();			
		}
		
		try {
			
			SendData sendData1 = new SendData();

			System.out.print("Input ID : >");	
			String sendUserID = scin.next();

			System.out.print("Input PW : >");
			String sendUserPW = scin.next();
			
			sendData1.setID(sendUserID);
			sendData1.setPw(sendUserPW);
					
			byte serializedLogin[];
			ByteArrayOutputStream baosLogin = new ByteArrayOutputStream();
			ObjectOutputStream oosLogin = new ObjectOutputStream(baosLogin); 
			oosLogin.writeObject(sendData1);
			
			serializedLogin = baosLogin.toByteArray();
			
			os.write(serializedLogin);
			os.flush();	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		while (true) {
			try {
				//Thread.sleep(1000);
				subMessage = scin.nextLine();
				subMessage = subName + ">" + subMessage;
				
				byte[] byteSend = new byte[100];
				byteSend = subMessage.getBytes("UTF-8");
				
				os.write(byteSend);
				os.flush();		
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}

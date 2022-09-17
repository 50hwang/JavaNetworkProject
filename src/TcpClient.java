import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.io.InputStream;
import java.io.OutputStream;

public class TcpClient {
	public static void main(String[] args) {
		Socket socket = new Socket();
		try {	
			socket.connect(new InetSocketAddress("localhost", 5001));			
			
			InputStream is = socket.getInputStream();
			
			byte[] byteRecv = new byte[100];
			
			Thread subClient = new Thread(new TcpClientSub(socket, args[0]));
			subClient.start();
			
			while(true)
			{
				if(is.available() > 0) {
					int byteCount = is.read(byteRecv);
					String strRecv = new String(byteRecv, 0, byteCount);
					System.out.println(strRecv);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}

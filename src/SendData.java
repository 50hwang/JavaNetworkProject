import java.io.Serializable;

public class SendData implements Serializable{
	/* fields */
	private static final long serialVersionUID = 1L;
	
	private int headerType;
	private int length;
	
	private String conversation;
	private long payAmount;
	
	private String ID;
	private String pw;
	
	/* constructors */
	
	/* methods */
	public int getHeaderType() {
		return headerType;
	}
	public void setHeaderType(int headerType) {
		this.headerType = headerType;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getConversation() {
		return conversation;
	}
	public void setConversation(String conversation) {
		this.conversation = conversation;
	}
	public long getPayAmount() {
		return payAmount;
	}
	public void setPayAmount(long payAmount) {
		this.payAmount = payAmount;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	
	
}

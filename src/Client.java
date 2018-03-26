import java.net.*;
public class Client {

	public static void main(String[] args) {
		 try{
			 Socket s=new Socket("127.0.0.1",9888);
			 System.out.println("connected to Client");
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		 
	}

}

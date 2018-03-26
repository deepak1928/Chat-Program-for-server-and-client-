import java.net.*;

public class Test1 {

	public static void main(String[] args) {
		try {
         ServerSocket server =new ServerSocket(9888);
         Socket ss= server.accept();
         
         System.out.println("Server Connected");
		}catch(Exception e) {e.printStackTrace();}
	}

}

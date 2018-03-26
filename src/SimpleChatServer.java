import java.io.*;
import java.net.*;
import java.util.*;
import java.util.ArrayList;



public class SimpleChatServer {
ArrayList clientOutputStream;
public class clientHandler implements Runnable{
	//public void run()
	
		BufferedReader reader;//=new BufferedReader()
		Socket sock;
		public clientHandler(Socket clientsocket){
			try {
				sock=clientsocket;
				InputStreamReader isReader=new InputStreamReader(sock.getInputStream());
				reader=new BufferedReader(isReader);
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
		public void run(){
			String message;
			try {
				while((message=reader.readLine())!=null){
					System.out.println("read"+message);
					telleveryonemessage(message);
					}
				}catch(Exception ex) {
					ex.printStackTrace();
				}
		}
		
	
}
	public static void main(String[] args) throws Exception {
		 new SimpleChatServer().go();
	}
	
	public void go() throws Exception {
		clientOutputStream=new ArrayList();
		try {
			ServerSocket ServerSock=new ServerSocket(5000);
			System.out.println("Server is setting up connection");
		
			while(true) {
				Socket clientSocket=ServerSock.accept();		
				PrintWriter writer=new PrintWriter(clientSocket.getOutputStream());
				clientOutputStream.add(writer);
				
				Thread thread =new Thread(new clientHandler(clientSocket));
				thread.start();
			
				System.out.println("Got A CONNECTION!");
				
			}
			
			
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public void telleveryonemessage(String message) throws Exception
	{
		Iterator it=clientOutputStream.iterator();
		while(it.hasNext()) {
			try {
				PrintWriter writer=(PrintWriter)it.next();
				writer.println(message);
				writer.flush();
				
			}catch(Exception ex) {
				ex.printStackTrace();
			}
		}
	}

}

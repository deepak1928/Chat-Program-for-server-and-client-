import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
//import java.util.*;
import java.net.*;

public class SimpleChatClient {
 JFrame frame;
 JTextArea incoming ;
 JTextArea outgoing ;
 JPanel mainpanel;
 BufferedReader reader;
 PrintWriter write;
 Socket sock;
	public static void main(String[] args) throws Exception {
		SimpleChatClient client=new SimpleChatClient();
		client.go();
	}
		public void  go() throws Exception
		{
		frame=new JFrame("Simple Chating System ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainpanel=new JPanel();
		JButton button=new JButton("SEND");
		button.addActionListener(new SendButtonListener());
		incoming =new JTextArea(15,50);
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setName("INCOMING");
		//incoming.setEditable(false);
		JScrollPane iScroller=new JScrollPane(incoming);
		iScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		iScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		//BufferedReader reader1=new BufferedReader(new InputStreamReader(System.in));
		
		outgoing =new JTextArea(15,25);
		outgoing.setLineWrap(true);
		outgoing.setWrapStyleWord(true);
		outgoing.setName("OUTGOING");
	//	outgoing.setEditable(false);
		JScrollPane oScroller=new JScrollPane(outgoing);
		oScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		oScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		mainpanel.add(iScroller);
		mainpanel.add(oScroller);
		mainpanel.add(button);
		setupNetworking();
		
		Thread readerthread=new Thread( new IncomingReader());
		readerthread.start();
		
		frame.getContentPane().add(BorderLayout.CENTER,mainpanel);
		frame.setSize(50,300);
		frame.setVisible(true);
		}

		public void setupNetworking() throws Exception {
			try {
				 
				  sock = new Socket("127.0.0.1", 5000);
                  // reading from keyboard (keyRead object)
				  reader = new BufferedReader(new InputStreamReader(System.in));
                  // sending to client (pwrite object)
				  OutputStream ostream = sock.getOutputStream(); 
				  write = new PrintWriter(ostream, true);
				}catch(IOException ex) {
					ex.printStackTrace();
					
				}
		}
	public class SendButtonListener implements ActionListener {
		 public void actionPerformed(ActionEvent ev) {
			try{
				write.println(outgoing.getText());
				write.flush();
			
		}catch(Exception ex) {
			ex.printStackTrace();
			}
		outgoing.setText("");
		outgoing.requestFocus();
		}
		
	}
	
	public class IncomingReader implements Runnable
	{
		public void run() {
		String message ;
		try {
			while((message=reader.readLine())!=null)
			{
				System.out.println(message+"");
				incoming.append(message+"\n");
				incoming.append("hello");
			}
			}catch(Exception ex) {
				incoming.append("error occured");
				ex.printStackTrace();
			
		}
	}
	

	}


}
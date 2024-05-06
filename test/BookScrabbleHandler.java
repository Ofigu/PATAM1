package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookScrabbleHandler implements ClientHandler {
	BufferedReader in;
	PrintWriter out;
	@Override
	
	public void handleClient(InputStream inFromclient, OutputStream outToClient) {
		
		
		in=new BufferedReader(new InputStreamReader(inFromclient));
		out=new PrintWriter(outToClient,true);
		try {
			String line=in.readLine();
			String[] parts = line.split(",");
			String action=parts[0]; 
			String[] newArray = new String[parts.length - 1];
			System.arraycopy(parts, 1, newArray, 0, newArray.length); // copy the array without the first element
			DictionaryManager dm=DictionaryManager.get();
			boolean res=false;
			if(action.equals("Q"))
			{ 
				res=dm.query(newArray);
			}
			else {
				if(action.equals("C"))
				{ 
					res=dm.challenge(newArray);
				}
			}
			out.println(res);

			inFromclient.close();
			outToClient.close();
						

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		out.close();
		
	}
	
}

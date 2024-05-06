package test;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.ServerSocket;

public class MyServer {
    int port;
    boolean stop;
    ClientHandler ch;

    public MyServer(int port, ClientHandler ch) {
        this.port = port;
        this.ch = ch;
        stop = false;
    }

    public void start(){
        stop = false;
        new Thread(()->startServer()).start();
    }

    public void startServer() {
        ServerSocket server = null;
        Socket client = null;
        try {
            server = new ServerSocket(port);
            server.setSoTimeout(1000);
            while(!stop){
                try {
                    client = server.accept();
                    ch.handleClient(client.getInputStream(), client.getOutputStream());
                    ch.close();
                    client.close();
                }
                catch(SocketTimeoutException e){}
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            ch.close();
            try {
                server.close();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                client.close();
            }
            catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void close(){
        stop = true;
    }
}

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by Eleonora on 16.04.2017.
 */
public class UDPServer {

    private DatagramSocket socket;

    public static void main(String[] args) {

            UDPServer udpServer = new UDPServer();
    }

    private UDPServer() {
        new StartThread();
    }

    public class StartThread implements Runnable {
        StartThread() {
            Thread thread = new Thread(this);
            thread.start();
        }

        public void run() {
            byte[] buffer = new byte[4];
            int port = 6666;
            int client_port;
            int capacity;
            try {
                socket = new DatagramSocket(port);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            while (true) try {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);
                InetAddress client = packet.getAddress();
                client_port = packet.getPort();
                capacity = ByteBuffer.wrap(buffer).getInt();
                System.out.println(capacity + " from " + client + " : " + client_port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
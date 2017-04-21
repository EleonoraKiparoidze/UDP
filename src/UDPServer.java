import java.io.IOException;
import java.lang.reflect.Array;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Eleonora on 16.04.2017.
 */
public class UDPServer {

    private DatagramSocket socket;
    private Map < SocketAddress, List<Integer>> information = new ConcurrentHashMap<>();

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
            int clientPort;
            int capacity;
            SocketAddress socketAddress;
            List<Integer> listOfCapacity;
            try {
                socket = new DatagramSocket(port);
            } catch (SocketException e) {
                e.printStackTrace();
            }
            while (true) {
                try {
                    DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                    socket.receive(packet);
                    InetAddress clientAddress = packet.getAddress();
                    clientPort = packet.getPort();
                    capacity = ByteBuffer.wrap(buffer).getInt();
                    socketAddress = new SocketAddress(clientPort,clientAddress);
                    information.put(socketAddress,Arrays.asList(capacity));

                    for(SocketAddress key: information.keySet()){
                        System.out.println("Key" + key.getAddress().toString() +":" + key.getPort()+ " Value: ");
                        for (Integer value : information.get(key)){
                            System.out.println("  " + value);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
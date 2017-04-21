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
    private Map < String, List<Integer>> information = new ConcurrentHashMap<>();

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
            String clientKey;
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
                    clientKey = String.valueOf(clientAddress)+":"+String.valueOf(clientPort);
                    capacity = ByteBuffer.wrap(buffer).getInt();

                    information.put(clientKey,Arrays.asList(capacity));
                    //System.out.println(capacity + " from " + clientAddress + " : " + clientPort);

                    for(String key: information.keySet()){
                        System.out.println("Key" + key + " Value: ");
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
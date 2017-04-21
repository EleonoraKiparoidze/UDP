

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Eleonora on 16.04.2017.
 */
public class UDPServer {

    private DatagramSocket socket;
    private Map<SocketAddress, List<ClientCapacityInfo>> socketAddressAndCapacityListMap = new ConcurrentHashMap<>();

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
            byte[] buffer = new byte[Integer.BYTES];
            int port = 6666;
            int clientPort;
            int capacity;
            SocketAddress socketAddress;
            ClientCapacityInfo clientCapacityInfo;
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

                    socketAddress = new SocketAddress(clientPort, clientAddress);

                    Timestamp timeReceive = Timestamp.valueOf(LocalDateTime.now());

                    clientCapacityInfo = new ClientCapacityInfo(timeReceive,capacity);

                    if(socketAddressAndCapacityListMap.containsKey(socketAddress)){
                        socketAddressAndCapacityListMap.get(socketAddress).add(clientCapacityInfo);
                    }else{
                        socketAddressAndCapacityListMap.put(socketAddress,new ArrayList<>(Arrays.asList(clientCapacityInfo)));
                    }
                    for (SocketAddress key : socketAddressAndCapacityListMap.keySet()) {
                        System.out.println(key.toString() + " Value: ");
                        for (ClientCapacityInfo value : socketAddressAndCapacityListMap.get(key)) {
                            System.out.println("  " + value.toString());
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
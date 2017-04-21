import java.net.InetAddress;

/**
 * Created by Eleonora on 21.04.2017.
 */
public class SocketAddress {
    private int port;
    private InetAddress address;

    public SocketAddress(int port, InetAddress address) {
        this.port = port;
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public void setAddress(InetAddress address) {
        this.address = address;
    }
}

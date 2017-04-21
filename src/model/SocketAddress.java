package model;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocketAddress that = (SocketAddress) o;

        if (port != that.port) return false;
        return address != null ? address.equals(that.address) : that.address == null;
    }

    @Override
    public int hashCode() {
        int result = port;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "model.SocketAddress{" +
                "port=" + port +
                ", address=" + address +
                '}';
    }
}

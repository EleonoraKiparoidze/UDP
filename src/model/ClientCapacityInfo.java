package model;

import java.sql.Timestamp;

/**
 * Created by Eleonora on 21.04.2017.
 */
public class ClientCapacityInfo {
    private Timestamp timeReceive;
    private int capacity;

    public ClientCapacityInfo(Timestamp timeReceive, int capacity) {
        this.timeReceive = timeReceive;
        this.capacity = capacity;
    }

    public Timestamp getTimeReceive() {
        return timeReceive;
    }

    public void setTimeReceive(Timestamp timeReceive) {
        this.timeReceive = timeReceive;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientCapacityInfo that = (ClientCapacityInfo) o;

        if (capacity != that.capacity) return false;
        return timeReceive != null ? timeReceive.equals(that.timeReceive) : that.timeReceive == null;
    }

    @Override
    public int hashCode() {
        int result = timeReceive != null ? timeReceive.hashCode() : 0;
        result = 31 * result + capacity;
        return result;
    }

    @Override
    public String toString() {
        return "model.ClientCapacityInfo{" +
                "timeReceive=" + timeReceive +
                ", capacity=" + capacity +
                '}';
    }
}

package MyModels;

public class Ticket {

    int id;
    int flightId;
    int customrId;

    public Ticket() {}

    public Ticket(int id, int flightId, int customrId) {
        this.id = id;
        this.flightId = flightId;
        this.customrId = customrId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", flightId=" + flightId +
                ", customrId=" + customrId +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public int getCustomrId() {
        return customrId;
    }

    public void setCustomrId(int customrId) {
        this.customrId = customrId;
    }
}

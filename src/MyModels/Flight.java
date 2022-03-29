package MyModels;


import java.sql.Timestamp;
import java.util.Date;

public class Flight {
    int id;
    int airlineCompany_Id;
    int originCountry;
    int destinationCountry;
    Timestamp departureTime;
    Timestamp landingTime;
    int remainingTickets;

    public Flight() {}

    public Flight(int id, int airlineCompany_Id, int originCountry, int destinationCountry,
                  Timestamp departureTime, Timestamp landingTime, int remainingTickets) {
        this.id = id;
        this.airlineCompany_Id = airlineCompany_Id;
        this.originCountry = originCountry;
        this.destinationCountry = destinationCountry;
        this.departureTime = departureTime;
        this.landingTime = landingTime;
        this.remainingTickets = remainingTickets;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", airlineCompany_Id=" + airlineCompany_Id +
                ", originCountry=" + originCountry +
                ", destinationCountry=" + destinationCountry +
                ", departureTime=" + departureTime +
                ", landingTime=" + landingTime +
                ", remainingTickets=" + remainingTickets +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAirlineCompany_Id() {
        return airlineCompany_Id;
    }

    public void setAirlineCompany_Id(int airlineCompany_Id) {
        this.airlineCompany_Id = airlineCompany_Id;
    }

    public int getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(int originCountry) {
        this.originCountry = originCountry;
    }

    public int getDestinationCountry() {
        return destinationCountry;
    }

    public void setDestinationCountry(int destinationCountry) {
        this.destinationCountry = destinationCountry;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Timestamp departureTime) {
        this.departureTime = departureTime;
    }

    public Date getLandingTime() {
        return landingTime;
    }

    public void setLandingTime(Timestamp landingTime) {
        this.landingTime = landingTime;
    }

    public int getRemainingTickets() {
        return remainingTickets;
    }

    public void setRemainingTickets(int remainingTickets) {
        this.remainingTickets = remainingTickets;
    }


}

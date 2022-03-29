package MyDao;

import MyError.mError;
import MyModels.AirlineCompany;
import MyModels.Customer;
import MyModels.Flight;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightsDao implements interfaceDAO<Flight> {

    DaoConnection dconnect = new DaoConnection();
    List<Flight> flightList = new ArrayList<>();

    @Override
    public Flight get(int id) {
        Flight flight = null;
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from flights where id = " + id);
            res.next();
            flight = new Flight(res.getInt(1),res.getInt(2),res.getInt(3),
                    res.getInt(4),res.getTimestamp(5),
                    res.getTimestamp(6),res.getInt(7));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (flight);
    }

    @Override
    public List getAll() {
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from flights");
            while (res.next()) {
                flightList.add(new Flight(res.getInt(1),res.getInt(2),
                        res.getInt(3), res.getInt(4),
                        res.getTimestamp(5), res.getTimestamp(6),
                        res.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (flightList);
    }

    @Override
    public void add(Flight flight) {
        try {
            dconnect.stm.executeQuery("insert into flights(id,airline_company_id,origin_Country_id," +
                    "destination_country_id, departure_time, landing_time, remaining_tickets) values(" +
                    "(\"" + flight.getId() +"\"," + flight.getAirlineCompany_Id() + "\"," +
                    flight.getOriginCountry() + "\"," + flight.getDestinationCountry() + "\"," +
                    flight.getDepartureTime() + "\"," + flight.getLandingTime() +
                    "\"," + flight.getRemainingTickets() + "\")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Flight flight) {
        try {
            dconnect.stm.executeQuery("delete from flights where id = " + flight.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Flight flight) {
        try {
            ResultSet res = dconnect.stm.executeQuery("update flights set airline_company_id = \"" + flight.getAirlineCompany_Id() +
                    "\",origin_country_id = " + flight.getOriginCountry() +
                    "\",destination_country_id = " + flight.getDestinationCountry() +
                    "\",departure_time = " + flight.getDepartureTime() +
                    "\",landing_time = " + flight.getLandingTime() +
                    "\",remaining_tickets = " + flight.getRemainingTickets() +
                    "where id = " + flight.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Flight> getFlightsByOriginCountryId(int id) {
        List<Flight> list = new ArrayList<>();
        try {
            ResultSet res = dconnect.stm.executeQuery("select * flights where id = " + id);
            while (res.next()) {
                list.add(new Flight(res.getInt(1),res.getInt(2),
                        res.getInt(3), res.getInt(4),
                        res.getTimestamp(5), res.getTimestamp(6),
                        res.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Flight> getFlightsByDestinationCountryId(int id) {
        List<Flight> list = new ArrayList<>();
        try {
            ResultSet res = dconnect.stm.executeQuery("select * flights where destination_country_id = " + id);
            while (res.next()) {
                list.add(new Flight(res.getInt(1),res.getInt(2),
                        res.getInt(3), res.getInt(4),
                        res.getTimestamp(5), res.getTimestamp(6),
                        res.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Flight> getFlightsByCustomer (Customer customer) {
        List<Flight> list = new ArrayList<>();
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from flights where id = " +
                    "(select flight_id from flights where " +customer.getId() + " = customer_id)");
            while (res.next()) {
                list.add(new Flight(res.getInt(1),res.getInt(2),
                        res.getInt(3), res.getInt(4),
                        res.getTimestamp(5), res.getTimestamp(6),
                        res.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Flight> getFlightsByDepartureDate (Timestamp date){
        List<Flight> list = new ArrayList<>();
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from flights where departure_time =" + date);
            while (res.next()) {
                list.add(new Flight(res.getInt(1),res.getInt(2),
                        res.getInt(3), res.getInt(4),
                        res.getTimestamp(5), res.getTimestamp(6),
                        res.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Flight> getFlightsByLandingDate (Timestamp date){
        List<Flight> list = new ArrayList<>();
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from flights where departure_time =" + date);
            while (res.next()) {
                list.add(new Flight(res.getInt(1),res.getInt(2),
                        res.getInt(3), res.getInt(4),
                        res.getTimestamp(5), res.getTimestamp(6),
                        res.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Flight> getFlightsByAirlineId(int id) {
        List<Flight> list = new ArrayList<>();
        try {
            ResultSet res = dconnect.stm.executeQuery("select * flights where airline_company_id = " + id);
            while (res.next()) {
                list.add(new Flight(res.getInt(1),res.getInt(2),
                        res.getInt(3), res.getInt(4),
                        res.getTimestamp(5), res.getTimestamp(6),
                        res.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public mError checkFlight(Flight flight) {
        mError ret = new mError();
        if (flight.getLandingTime().compareTo(flight.getDepartureTime()) > 0) {
            ret.setMes("ERROR - landing date is before departure date");
            ret.setErrornum(1);

        }
        if (flight.getOriginCountry() == flight.getDestinationCountry()) {
            ret.setErrornum(2);
            ret.setMes("ERROR - destination and landing country is the same");
        }
        if (flight.getRemainingTickets() < 1) {
            ret.setErrornum(3);
            ret.setMes("ERROR - no remaining tickets");
        }
        return ret;
    }
}

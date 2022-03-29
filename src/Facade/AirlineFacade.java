package Facade;

import MyDao.AirlineCompanyDao;
import MyDao.FlightsDao;
import MyModels.AirlineCompany;
import MyModels.Flight;
import Token.LoginToken;
import MyError.mError;

import java.util.ArrayList;
import java.util.List;

public class AirlineFacade extends FacadeBase{

    LoginToken token;

    public AirlineFacade(LoginToken token) {
        this.token = token;
    }

    public List<Flight> get_my_flights() {
        List<Flight> list = new ArrayList<>();
        AirlineCompanyDao airDao = new AirlineCompanyDao();
        FlightsDao flightDao = new FlightsDao();
        list = flightDao.getFlightsByAirlineId(airDao.getIdByUserid(token.getId()));
        return list;
    }

    public void update_airline(AirlineCompany airline) {
        if (token.getId() != airline.getUserId()) {
            System.out.println("airline id is incorrect");
            return;
        }
        AirlineCompanyDao dao = new AirlineCompanyDao();
        dao.update(airline);
    }

    public void add_flight(Flight flight) {
        FlightsDao dao = new FlightsDao();
        mError e =  dao.checkFlight(flight);
        if (e.getErrornum() != 0) {
            System.out.println(dao.checkFlight(flight).getMes());
            return;
        }
        AirlineCompanyDao airDao = new AirlineCompanyDao();
        int id = airDao.getIdByUserid(token.getId());
        if (flight.getId() != id) {
            System.out.println("id doesn't matches");
            return;
        }
        dao.add(flight);
    }

    public void update_flight (Flight flight) {
        FlightsDao dao = new FlightsDao();
        mError e =  dao.checkFlight(flight);
        if (e.getErrornum() != 0) {
            System.out.println(dao.checkFlight(flight).getMes());
            return;
        }
        AirlineCompanyDao airDao = new AirlineCompanyDao();
        int id = airDao.getIdByUserid(token.getId());
        if (flight.getId() != id) {
            System.out.println("id doesn't matches");
            return;
        }
        dao.update(flight);
    }

    public  void remove_flight (Flight flight) {
        AirlineCompanyDao airDao = new AirlineCompanyDao();
        int id = airDao.getIdByUserid(token.getId());
        if (flight.getId() != id) {
            System.out.println("id doesn't matches");
            return;
        }
        FlightsDao dao = new FlightsDao();
        dao.remove(flight);
    }


}

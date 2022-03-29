package Facade;

import MyDao.*;
import MyError.mError;
import MyModels.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class FacadeBase {

    List<Flight> get_all_flights() {
        return new AirlineCompanyDao().getAll();
    }

    Flight get_flight_by_id(int id) {
        return new FlightsDao().get(id);
    }

    List<Flight> get_flights_by_parameters(int origin_country, int destination_country, Timestamp date) {
        DaoConnection dconnect = new DaoConnection();
        List<Flight> list = new ArrayList<>();
        try {
            ResultSet res = dconnect.getStm().executeQuery("select * result from get_flights_by_parameters( " +
                    origin_country + "," + destination_country + "," + date + ")");
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

    List<AirlineCompany>get_all_airlines() {
        return new AirlineCompanyDao().getAll();
    }

    AirlineCompany get_airline_by_id(int id) {
        return  new AirlineCompanyDao().get(id);
    }

    AirlineCompany get_airline_by_parameters(String name, int country_id) {
        DaoConnection dconnect = new DaoConnection();
        AirlineCompany airline = null;
        try {
            ResultSet res = dconnect.getStm().executeQuery("select * from airlines where country_id " +
                    country_id + " and "  + " name " + name + ")");
            while (res.next()) {
                airline = new AirlineCompany(res.getInt(1),res.getString(2),
                        res.getInt(3), res.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return airline;
    }

    List<Country> get_all_countries() {
        return new CountriesDao().getAll();
    }

    Country get_country_by_id(int id) {
        return new CountriesDao().get(id);
    }

    public void create_new_user(User user){
        UsersDao dao = new UsersDao();
        mError m = new mError();
        m = dao.checkUser(user);
        if (m.getErrornum() != 0){
            System.out.println(m.getMes());
            return;
        }
        dao.add(user);
    }
}

package MyDao;

import MyModels.AirlineCompany;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AirlineCompanyDao implements interfaceDAO<AirlineCompany> {

    DaoConnection dconnect = new DaoConnection();
    List<AirlineCompany> airlineList = new ArrayList<>();

    @Override
    public AirlineCompany get(int id) {
        AirlineCompany airline = null;
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from airline_companies where id = " + id);
            res.next();
            airline = new AirlineCompany(res.getInt(1),res.getString(2),res.getInt(3),
                    res.getInt(4));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (airline);
    }

    @Override
    public List getAll() {
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from airline_companies");
            while (res.next()) {
                airlineList.add(new AirlineCompany(res.getInt(1),res.getString(2),res.getInt(3),
                        res.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (airlineList);
    }

    @Override
    public void add(AirlineCompany airline) {
        try {
            dconnect.stm.executeQuery("insert into airline_companies(id,name,Country_id,user_id) values(" +
                    "(\"" + airline.getId() +"\"," + airline.getName() + "\"," +
                    airline.getCountryId() +
                    "\"," + airline.getUserId() + "\")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(AirlineCompany airline) {
        try {
            dconnect.stm.executeQuery("delete from airline_companies where id = " + airline.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(AirlineCompany airline) {
        try {
            dconnect.stm.executeQuery("update airline_companies set name = \"" + airline.getName() +
                    "\",country_id = " + airline.getCountryId() +
                    "\",user_id = " + airline.getUserId() + "where id = " + airline.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AirlineCompany> getAirlinesByCountry(int id) {
        List<AirlineCompany> list = new ArrayList<>();
        try {
            ResultSet res = dconnect.stm.executeQuery("select * airline_companies where id = " + id);
            while (res.next()) {
                list.add(new AirlineCompany(res.getInt(1),res.getString(2),res.getInt(3),
                        res.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int getIdByUserid(int userid) {
        int ret = -1;
        try {
            ResultSet res = dconnect.stm.executeQuery("select id from airline_companies where user_id = " + userid);
            while (res.next()) {
                ret = res.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }


}

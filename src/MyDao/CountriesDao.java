package MyDao;

import MyModels.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CountriesDao implements interfaceDAO<Country>{

    DaoConnection dconnect = new DaoConnection();
    List<Country> countriesList = new ArrayList<>();

    public List<Country> getAll(){
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from countries");
            while(res.next()) {
                countriesList.add(new Country(res.getInt(1), res.getString(2)));
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return countriesList;
    }

    public Country get(int id) {
        Country country = null;
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from countries where id = " + id);
            while (res.next()){
                country = new Country(res.getInt(1),res.getString(2));
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return country;
    }

    public void add(Country country) {
        try{
            dconnect.stm.executeQuery("insert into Countries(id,name) values( "+ country.getId() + ","
                    + country.getName() + ")");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void remove(Country country){
        try {
            dconnect.stm.executeQuery("delete from countries where id = " + country.getId());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(Country country){
        try {
            dconnect.stm.executeQuery("update countries set name =" + country.getName()
                    + "where id = " + country.getId());
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

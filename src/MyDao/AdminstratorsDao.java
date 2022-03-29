package MyDao;

import MyModels.Adminstrator;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminstratorsDao implements interfaceDAO<Adminstrator>{

    DaoConnection dconnect = new DaoConnection();
    List<Adminstrator> adminstratorList = new ArrayList<>();

    @Override
    public Adminstrator get(int id) {
        Adminstrator admin = null;
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from adminstrators where id = " + id);
            while(res.next()){
                admin = new Adminstrator(res.getInt("id"),res.getString(2),
                        res.getString(3),res.getInt(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (admin);
    }

    @Override
    public List getAll() {
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from Adminstrators");
            while(res.next()) {
                adminstratorList.add(new Adminstrator(res.getInt(1), res.getString(2),
                        res.getString(3), res.getInt(4)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (adminstratorList);
    }

    @Override
    public void add(Adminstrator admin) {
        try {
            dconnect.stm.executeQuery("insert into adminstrators(id,fist_name,last_name,user_id) values(" +
                    "(\"" + admin.getId() +"\"," + admin.getFirstName() + "\"," + admin.getLastName() +
                    "\"," + admin.getUserId() + "\")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Adminstrator admin) {
        try {
            dconnect.stm.executeQuery("delete from adminstrators where id = " + admin.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Adminstrator admin) {
        try {
            dconnect.stm.executeQuery("update adminstrators set first_name = \"" + admin.getFirstName() +
            "\",last_name = " + admin.getLastName() +
                    "\",user_id = " + admin.getUserId() + "where id = " + admin.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

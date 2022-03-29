package MyDao;

import MyError.mError;
import MyModels.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDao implements interfaceDAO<User>{

    DaoConnection dconnect = new DaoConnection();
    List<User> userList = new ArrayList<>();

    @Override
    public User get(int id) {
        User user = null;
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from Users where id = " + id);
            res.next();
            user = new User(res.getInt(1), res.getString(2),
                    res.getString(3), res.getString(4), res.getInt(5));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (user);
    }

    @Override
    public List getAll() {
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from Users");
            while (res.next()) {
                userList.add(new User(res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4), res.getInt(5)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (userList);
    }

    @Override
    public void add(User user) {
        try {
            dconnect.stm.executeQuery("insert into Users(id,username,user_password," +
                    "email, user_role) values(" +
                    "(\"" + user.getId() +"\"," + user.getUserName() + "\"," +
                    user.getPassword() + "\"," + user.getEmail() +
                    "\"," + user.getUserRole() + "\")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(User user) {
        try {
            dconnect.stm.executeQuery("delete from Users where id = " + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(User user) {
        try {
            ResultSet res = dconnect.stm.executeQuery("update Users set username = \"" + user.getUserName() +
                    "\",user_password = " + user.getPassword() +
                    "\",email = " + user.getEmail() +
                    "\",user_role = " + user.getUserRole() +
                    "where id = " + user.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public mError checkUser(User user) {
        mError ret = new mError();
        if (user.getPassword().length() < 6) {
            ret.setErrornum(11);
            ret.setMes("ERROR  - password is too short");
        }
        return ret;
    }
}

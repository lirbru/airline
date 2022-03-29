package MyDao;

import MyModels.Customer;

import MyError.mError;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomersDao implements interfaceDAO<Customer>{

    DaoConnection dconnect = new DaoConnection();
    List<Customer>  customerList = new ArrayList<>();

    @Override
    public Customer get(int id) {
        Customer customer = null;
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from Customers where id = " + id);
            res.next();
            customer = new Customer(res.getInt(1), res.getString(2),
                    res.getString(3), res.getString(4),
                    res.getString(5), res.getString(6),
                    res.getInt(7));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (customer);
    }

    @Override
    public List getAll() {
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from Customers");
            while (res.next()) {
                customerList.add(new Customer(res.getInt(1), res.getString(2),
                        res.getString(3), res.getString(4),
                        res.getString(5), res.getString(6),
                        res.getInt(7)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (customerList);
    }

    @Override
    public void add(Customer customer) {
        try {
            dconnect.stm.executeUpdate("insert into customers(id,first_name,last_name," +
                    "address, phone_no, credit_card, user_id) values(" +
                    "(\"" + customer.getId() +"\"," + customer.getFirstName() + "\"," +
                    customer.getLastName() + "\"," + customer.getAddress() + "\"," +
                    customer.getPhoneNo() + "\"," + customer.getCreditCard() +
                    "\"," + customer.getUserId() + "\")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Customer customer) {
        try {
            dconnect.stm.executeQuery("delete from customer where id = " + customer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        try {
            ResultSet res = dconnect.stm.executeQuery("update customer set first_name = \"" + customer.getFirstName() +
                    "\",last_name = " + customer.getLastName() +
                    "\",address = " + customer.getAddress() +
                    "\",phone_no = " + customer.getPhoneNo() +
                    "\",credit_card = " + customer.getCreditCard() +
                    "\",user_id = " + customer.getUserId() +
                    "where id = " + customer.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public mError checkCustomer(Customer customer) {
        mError ret = new mError();

        if (customer.getFirstName().equals("")) {
            ret.setErrornum(1);
            ret.setMes("no first name");
        }

        return ret;
    }
}

package Facade;

import MyDao.AdminstratorsDao;
import MyDao.AirlineCompanyDao;
import MyDao.CustomersDao;
import MyError.mError;
import MyModels.Adminstrator;
import MyModels.AirlineCompany;
import MyModels.Customer;
import Token.LoginToken;

import java.util.ArrayList;
import java.util.List;

public class AdministratorFacade extends FacadeBase{

    LoginToken token;

    public AdministratorFacade(LoginToken token) {

        this.token = token;
    }

    public List<Customer> get_all_customers() {
        List<Customer> customerList = new ArrayList<>();
        CustomersDao dao = new CustomersDao();
        customerList = dao.getAll();
        return customerList;
    }


    public void add_airline(AirlineCompany airline) {
        //airline.setUserId(token.getId());   //check if needed?
        AirlineCompanyDao dao = new AirlineCompanyDao();
        dao.add(airline);
    }

    public void add_customer(Customer customer) {
        CustomersDao dao = new CustomersDao();
        dao.add(customer);
    }

    public void add_adminstrator(Adminstrator admin) {
        AdminstratorsDao dao = new AdminstratorsDao();
        dao.add(admin);
    }

    public void remove_airline(AirlineCompany airline){
        AirlineCompanyDao dao = new AirlineCompanyDao();
        dao.remove(airline);
    }

    public void remove_customr(Customer customer){
        CustomersDao dao = new CustomersDao();
        mError e = dao.checkCustomer(customer);
        if (e.getErrornum() != 0) {
            System.out.println(e.getMes());
        }
        dao.remove(customer);
    }

    public void remove_adminstrator(Adminstrator admin){
        AdminstratorsDao dao = new AdminstratorsDao();
        dao.remove(admin);
    }
}

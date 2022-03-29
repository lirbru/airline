package Facade;

import MyDao.CustomersDao;
import MyDao.UsersDao;
import MyModels.Customer;
import MyModels.User;
import Token.LoginToken;

import java.util.List;

public class AnonymousFacade extends FacadeBase{

    public FacadeBase login(String username, String password) {
        UsersDao user = new UsersDao();
        List<User> list = user.getAll();
        for(var u : list) {
            if (u.getUserName().equals(username) && u.getPassword().equals(password) ) {
                switch(u.getUserRole()) {
                    case 1:
                        return new AdministratorFacade(new LoginToken(u.getId(), u.getUserName(), u.getUserRole()));
                    case 2:
                        return new CustomerFacade(new LoginToken(u.getId(), u.getUserName(), u.getUserRole()));
                    case 3:
                        return new AirlineFacade(new LoginToken(u.getId(), u.getUserName(), u.getUserRole()));
                    default:
                        return null;
                }
            }
        }
        return null;
    }
    public void add_customer(int id, String first, String last, String address,
                             String phone, String credit_card, int usr_id) {
        Customer customer = new Customer(id, first, last, address, phone, credit_card,usr_id);
        CustomersDao dao = new CustomersDao();
        dao.add(customer);
    }
}

package Facade;

import MyDao.CustomersDao;
import MyDao.TicketsDao;
import MyModels.Customer;
import MyModels.Ticket;
import Token.LoginToken;

import java.util.ArrayList;
import java.util.List;

public class CustomerFacade extends FacadeBase{

    LoginToken token;

    public CustomerFacade(LoginToken token) {
        this.token = token;
    }

    public void update_customer(Customer customer) {
        if (customer.getId() != token.getId() || token.getRole() != 2) {
            System.out.println("customer id incorrect");
            return;
        }
        CustomersDao dao = new CustomersDao();
        dao.update(customer);
    }

    public void add_ticket(Ticket ticket) {
        if (ticket.getCustomrId() != token.getId()) {
            System.out.println("incorrect ticket id");
            return;
        }
        TicketsDao dao = new TicketsDao();
        dao.add(ticket);
    }

    public void remove_ticket(Ticket ticket) {
        if (ticket.getCustomrId() != token.getId() || token.getRole() != 2) {
            System.out.println("incorrect ticket id");
            return;
        }
        TicketsDao dao = new TicketsDao();
        dao.remove(ticket);
    }

    public List<Ticket> get_my_tickets() {
        List<Ticket> ticketList = new ArrayList<>();
        TicketsDao dao = new TicketsDao();
        ticketList = dao.getAllByCustomerId(token.getId());

        return ticketList;
    }
}

package MyDao;

import MyModels.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketsDao implements interfaceDAO<Ticket>{

    DaoConnection dconnect = new DaoConnection();
    List<Ticket> ticketList = new ArrayList<>();

    @Override
    public Ticket get(int id) {
        Ticket t = null;
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from Tickets where id = " + id);
            res.next();
            t = new Ticket(res.getInt(1), res.getInt(2), res.getInt(3));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (t);
    }

    @Override
    public List getAll() {

        try {
            ResultSet res = dconnect.stm.executeQuery("select * from Tickets");
            while (res.next()) {
                ticketList.add(new Ticket(res.getInt(1), res.getInt(2), res.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ticketList);
    }

    @Override
    public void add(Ticket ticket) {
        try {
            dconnect.stm.executeQuery("insert into Tickets(id,flight_id,customer_id)" +
                    " values(" +
                    "(\"" + ticket.getId() +"\"," + ticket.getFlightId() + "\"," +
                    ticket.getCustomrId() + "\")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Ticket ticket) {
        try {
            dconnect.stm.executeQuery("delete from Tickets where id = " + ticket.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Ticket ticket) {
        try {
            ResultSet res = dconnect.stm.executeQuery("update Tickets set flight_id = \"" + ticket.getFlightId() +
                    "\",customer_id = " + ticket.getCustomrId() +
                    "where id = " + ticket.getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Ticket> getAllByCustomerId(int id) {
        try {
            ResultSet res = dconnect.stm.executeQuery("select * from Tickets where customer_id = " + id);
            while (res.next()) {
                ticketList.add(new Ticket(res.getInt(1), res.getInt(2), res.getInt(3)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (ticketList);
    }
}

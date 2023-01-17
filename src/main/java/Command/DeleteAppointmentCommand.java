package Command;

import DAO.DAOException;
import DAO.impl.AppointmentDao;
import DAO.impl.DoctorDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteAppointmentCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {
        int appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
        System.out.println(appointmentId);

        AppointmentDao appointmentDao = new AppointmentDao();
        appointmentDao.delete(appointmentId);


        return "controller?command=appointmentpagecommand&page=1";


    }
}

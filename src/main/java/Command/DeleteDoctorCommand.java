package Command;

import DAO.DAOException;
import DAO.impl.DoctorDao;
import entitys.Doctor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteDoctorCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {

         int doctorid = Integer.parseInt(req.getParameter("deletedoctor"));
        System.out.println(doctorid);

        DoctorDao doctorDao = new DoctorDao();
        doctorDao.delete(doctorid);


        return "controller?command=adminpagecommand&page=1";
    }
}

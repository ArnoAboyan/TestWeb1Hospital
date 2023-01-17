package Command;

import DAO.DAOException;
import DAO.impl.DoctorDao;
import DAO.impl.PatientDao;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeletePatientCommand implements Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {

        int patientid = Integer.parseInt(req.getParameter("deletepatient"));
        System.out.println(patientid);

        PatientDao patientDao = new PatientDao();
        patientDao.delete(patientid);


        return "controller?command=patientlistcommand&page=1";
    }
}

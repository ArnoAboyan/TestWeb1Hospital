package Command;

import DAO.DAOException;
import DAO.impl.DoctorDao;
import DAO.impl.PatientDao;
import entitys.Category;
import entitys.Doctor;
import entitys.Patient;
import entitys.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;

public class AddPatientCommand implements Command{


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        int phone = Integer.parseInt(req.getParameter("phone"));
        Date birthday = Date.valueOf(req.getParameter("birthday"));
        String gender = req.getParameter("gender");


        Patient patient = new Patient();
        patient.setPatientName(name);
        patient.setPatientSurname(surname);
        patient.setPatientPhone(phone);
        patient.setPatientDateOfBirth(birthday);
        patient.setPatientGender(gender);

        System.out.println(patient);

        PatientDao patientDao = new PatientDao();
        patientDao.create(patient);

        return "controller?command=patientlistcommand&page=1";
    }
}

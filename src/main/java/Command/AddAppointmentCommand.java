package Command;



import DAO.DAOException;
import DAO.impl.AppointmentDao;
import DAO.impl.DoctorDao;
import DAO.impl.PatientDao;
import entitys.Appointment;
import entitys.Doctor;
import entitys.Patient;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class AddAppointmentCommand implements Command {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {
        Integer doctorID = Integer.valueOf(req.getParameter("doctor"));
        Integer patientId = Integer.valueOf(req.getParameter("patientid"));
        LocalDateTime appointmentdate = LocalDateTime.parse(req.getParameter("appointmentdata"));

        System.out.println(appointmentdate);

        Appointment appointment = new Appointment();
        appointment.setDoctorId(doctorID);
        appointment.setPatientId(patientId);
        appointment.setAppointmentData(appointmentdate);

        System.out.println(appointment);

        AppointmentDao appointmentDao = new AppointmentDao();
        appointmentDao.create(appointment);


        return "controller?command=patientlistcommand&page=1";

    }
}
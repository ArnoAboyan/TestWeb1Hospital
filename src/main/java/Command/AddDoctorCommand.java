package Command;

import DAO.DAOException;
import DAO.impl.DoctorDao;
import entitys.Category;
import entitys.Doctor;
import entitys.Role;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddDoctorCommand implements Command {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DAOException, CommandException {

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        Integer categoryID = Category.getIDByName(req.getParameter("category"));
        Integer roleID = Role.getIDByName(req.getParameter("role"));



        Doctor doctor = new Doctor();
        doctor.setDoctorName(name);
        doctor.setDoctorSurname(surname);
        doctor.setLogin(login);
        doctor.setPassword(password);
        doctor.setCategory(categoryID);
        doctor.setRole(roleID);

        System.out.println(doctor);

        DoctorDao doctorDao = new DoctorDao();
         doctorDao.create(doctor);




        return "controller?command=adminpagecommand&page=1";
    }
}

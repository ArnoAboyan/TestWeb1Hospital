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
            Doctor doctor = new Doctor();


        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        int categoryID = Category.getIDByName(req.getParameter("category"));
        int roleID = Role.getIDByName(req.getParameter("role"));


        // all english and Cyrillic letters mor from 2 to 20
        if (name.matches("[-a-zA-Zа-яА-ЯЁёЇїІіҐґ]{2,20}")) {
                doctor.setDoctorName(name);
            }else throw new CommandException("The entered name is not correct");


        if (surname.matches("[-a-zA-Zа-яА-ЯЁёЇїІіҐґ]{2,20}")){
                doctor.setDoctorSurname(surname);
        }else throw  new CommandException("The entered name is not correct");

        if (login.matches("[A-Za-z]{3,20}")){
                doctor.setLogin(login);
            }else throw  new CommandException("The entered name is not correct");

        if (password.matches("[a-zA-Z0-9]{3,20}")){
                doctor.setPassword(password);
            }else throw  new CommandException("The entered name is not correct");

        if (categoryID > 0 && categoryID <= 8 ){
                doctor.setCategory(categoryID);
        }else throw  new CommandException("The entered name is not correct");

        if (roleID > 1 && roleID <= 3 ){
                doctor.setRole(roleID);
            }else throw  new CommandException("The entered name is not correct");



        System.out.println(doctor);

        DoctorDao doctorDao = new DoctorDao();
         doctorDao.create(doctor);




        return "controller?command=adminpagecommand&page=1";
    }
}

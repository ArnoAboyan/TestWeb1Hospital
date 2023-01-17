package DAO.impl;

import DAO.DAOException;
import DAO.EntityDAO;
import Util.AttributFinal;
import Util.DBConnection;
import entitys.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class DoctorDao implements EntityDAO<Integer, Doctor> {


    @Override
    public boolean create(Doctor doctor) {
        Connection connection = DBConnection.dbConnect();


        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.ADDDOCTOR);) {

            preparedStatement.setString(1, doctor.getDoctorName());
            preparedStatement.setString(2, doctor.getDoctorSurname());
            preparedStatement.setInt(3, doctor.getCategory().getTitleId());
            preparedStatement.setString(4, doctor.getLogin());
            preparedStatement.setString(5, doctor.getPassword());
            preparedStatement.setInt(6, doctor.getRole().getTitleId());


            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Doctor  getByID(Integer integer) {
        Connection connection = DBConnection.dbConnect();
        Doctor doctor = new Doctor();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.GET_DOCTOR_BY_ID)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            doctor.setDoctorId(resultSet.getInt("doctor_id"));
            doctor.setLogin(resultSet.getString("login"));
            doctor.setPassword(resultSet.getString("password"));
            doctor.setRole(resultSet.getInt("role_id"));
            doctor.setDoctorName(resultSet.getString("doctor_name"));
            doctor.setDoctorSurname(resultSet.getString("doctor_surname"));
            doctor.setCategory(resultSet.getInt("category_id"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctor;

    }

    @Override
    public boolean update(Doctor doctor) {
        return false;
    }

    @Override
    public void delete(Integer doctorid) {

        Connection connection = DBConnection.dbConnect();


        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.DELETEDOCTOR);) {
            preparedStatement.setInt(1, doctorid);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Doctor> getAll() throws DAOException {
        Connection connection = DBConnection.dbConnect();
        List<Doctor> doctorList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.GET_ALL_DOCTOR)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setDoctorName(resultSet.getString("doctor_name"));
                doctor.setDoctorSurname(resultSet.getString("doctor_surname"));
                doctor.setLogin(resultSet.getString("login"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setCategory(resultSet.getInt("category_id"));
                doctor.setRole(resultSet.getInt("role_id"));

                doctorList.add(doctor);
                System.out.println(doctor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return doctorList;
    }

    public Doctor getByLogin(String login) {
        Connection connection = DBConnection.dbConnect();
        Doctor doctor = new Doctor();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.GET_DOCTOR_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            doctor.setDoctorId(resultSet.getInt("doctor_id"));
            doctor.setLogin(resultSet.getString("login"));
            doctor.setPassword(resultSet.getString("password"));
            doctor.setRole(resultSet.getInt("role_id"));
            doctor.setDoctorName(resultSet.getString("doctor_name"));
            doctor.setDoctorSurname(resultSet.getString("doctor_surname"));
            doctor.setCategory(resultSet.getInt("category_id"));


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return doctor;
    }

    public List<Doctor> getDoctorByRole(Integer roleId) throws DAOException {
        Connection connection = DBConnection.dbConnect();
        List<Doctor> doctorList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.GET_DOCTOR_BY_ROLE)) {
            preparedStatement.setInt(1, roleId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setDoctorName(resultSet.getString("doctor_name"));
                doctor.setDoctorSurname(resultSet.getString("doctor_surname"));
                doctor.setLogin(resultSet.getString("login"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setCategory(resultSet.getInt("category_id"));
                doctor.setRole(resultSet.getInt("role_id"));

                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  doctorList;
    }

    public List<Doctor> getAllWithLimitAndOrderBy(int start, int count, String sort) throws DAOException{
        start = start - 1;
        if (start != 0) {
            start = start * count;
        }
        Connection connection = DBConnection.dbConnect();
        List<Doctor> doctorList = new ArrayList<>();
        String query = AttributFinal.SORT_DOCTOR + sort + AttributFinal.LIMIT_DOCTOR;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setDoctorName(resultSet.getString("doctor_name"));
                doctor.setDoctorSurname(resultSet.getString("doctor_surname"));
                doctor.setLogin(resultSet.getString("login"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setCategory(resultSet.getInt("category_id"));
                doctor.setRole(resultSet.getInt("role_id"));

                doctorList.add(doctor);
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get doctor. ", e);
        }
        return doctorList;
    }

    public List<Doctor> getAllWithLimit(int start, int count) throws DAOException {
        start = start - 1;
        if (start != 0) {
            start = start * count;
        }

        Connection connection = DBConnection.dbConnect();
        List<Doctor> doctorList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.GET_ALL_DOCTOR_LIMIT)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);
            ResultSet resultSet = preparedStatement.executeQuery();
            ;
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setDoctorId(resultSet.getInt("doctor_id"));
                doctor.setDoctorName(resultSet.getString("doctor_name"));
                doctor.setDoctorSurname(resultSet.getString("doctor_surname"));
                doctor.setLogin(resultSet.getString("login"));
                doctor.setPassword(resultSet.getString("password"));
                doctor.setCategory(resultSet.getInt("category_id"));
                doctor.setRole(resultSet.getInt("role_id"));

                doctorList.add(doctor);
            }

        } catch (SQLException e) {
            throw new DAOException("Can not get all Doctor. ", e);
        }
        return doctorList;
    }

    public Integer getCountDoctor() throws DAOException {
        int result = 0;

        Connection connection = DBConnection.dbConnect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.COUNT_OF_DOCTOR)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = resultSet.getInt(1);

            }

        } catch (SQLException e) {
            throw new DAOException("Can not get count Patient");
        }

        return result;
    }
}

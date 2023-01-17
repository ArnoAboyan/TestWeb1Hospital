package DAO.impl;

import DAO.DAOException;
import DAO.EntityDAO;
import Util.AttributFinal;
import Util.DBConnection;
import entitys.Appointment;
import entitys.Doctor;
import entitys.Patient;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDao implements EntityDAO<Integer ,Appointment> {
    @Override
    public boolean create(Appointment appointment) {
        Connection connection = DBConnection.dbConnect();


        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.ADDDAPPOINTMENT);) {

            preparedStatement.setInt(1, appointment.getDoctorId());
            preparedStatement.setInt(2, appointment.getPatientId());
            java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf(appointment.getAppointmentData());
            preparedStatement.setTimestamp(3, timestamp);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Appointment getByID(Integer integer) {
        return null;
    }

    @Override
    public boolean update(Appointment appointment) {
        return false;
    }

    @Override
    public void delete(Integer appointmentid) {
        Connection connection = DBConnection.dbConnect();


        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.DELETEAPPOINTMENT);) {
            preparedStatement.setInt(1, appointmentid);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public List<Appointment> getAll() throws DAOException {
        Connection connection = DBConnection.dbConnect();
        List<Appointment> appointmentList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.GET_ALL_APPOINTMENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointment_id"));
                appointment.setDoctorId(resultSet.getInt("doctor_id"));
                appointment.setPatientId(resultSet.getInt("patient_id"));
                appointment.setAppointmentData(Timestamp.valueOf(resultSet.getString("appointments_data")).toLocalDateTime());


                appointmentList.add(appointment);
                System.out.println(appointment);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    public List<Appointment> getAppointmentByDoctorId(Integer integer) throws DAOException {
        Connection connection = DBConnection.dbConnect();

        List<Appointment> appointmentList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.GET_APPOINTMENT_BY_DOCTOR_ID)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointment_id"));
                appointment.setDoctorId(resultSet.getInt("doctor_id"));
                appointment.setPatientId(resultSet.getInt("patient_id"));
                appointment.setAppointmentData(Timestamp.valueOf(resultSet.getString("appointments_data")).toLocalDateTime());


                appointmentList.add(appointment);
                System.out.println(appointment);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return appointmentList;

    }
    public List<Appointment> getAppointmentByPatientAndDoctorId(Integer doctor, Integer patient) throws DAOException {
        Connection connection = DBConnection.dbConnect();
        List<Appointment> appointmentList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.GET_APPOINTMENT_BY_DOCTOR_AND_PATIENT_ID)) {
            preparedStatement.setInt(1, doctor);
            preparedStatement.setInt(2, patient);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointment_id"));
                appointment.setDoctorId(resultSet.getInt("doctor_id"));
                appointment.setPatientId(resultSet.getInt("patient_id"));
                appointment.setAppointmentData(Timestamp.valueOf(resultSet.getString("appointments_data")).toLocalDateTime());


                appointmentList.add(appointment);
                System.out.println(appointment);
            }
            } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    public List<Appointment> getAllWithLimit(int start, int count) throws DAOException{
        start = start - 1;
        if (start != 0) {
            start = start * count;
        }

        Connection connection = DBConnection.dbConnect();
        List<Appointment> appointmentList = new ArrayList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.GET_ALL_APPOINTMENT_LIMIT)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointment_id"));
                appointment.setDoctorId(resultSet.getInt("doctor_id"));
                appointment.setPatientId(resultSet.getInt("patient_id"));
                appointment.setAppointmentData(Timestamp.valueOf(resultSet.getString("appointments_data")).toLocalDateTime());

                appointmentList.add(appointment);
            }

        } catch (SQLException e) {
            throw new DAOException("Can not get all appointments. ", e);
        }
        return appointmentList;
    }

    public List<Appointment> getAllWithLimitAndOrderBy(int start, int count, String sort) throws DAOException {
        start = start - 1;
        if (start != 0) {
            start = start * count;
        }
        Connection connection = DBConnection.dbConnect();
        List<Appointment> appointmentList = new ArrayList<>();
        String query = AttributFinal.SORT_APPOINTMENT + sort + AttributFinal.LIMIT_APPOINTMENT;

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, start);
            preparedStatement.setInt(2, count);
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setAppointmentId(resultSet.getInt("appointment_id"));
                appointment.setDoctorId(resultSet.getInt("doctor_id"));
                appointment.setPatientId(resultSet.getInt("patient_id"));
                appointment.setAppointmentData(Timestamp.valueOf(resultSet.getString("appointments_data")).toLocalDateTime());

                appointmentList.add(appointment);
            }
        } catch (SQLException e) {
            throw new DAOException("Can not get appointments ", e);
        }
        return appointmentList;
    }

    public int getCountPatient() throws DAOException{
        int result = 0;

        Connection connection = DBConnection.dbConnect();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.COUNT_OF_APPOINTMENT)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                result = resultSet.getInt(1);

            }

        } catch (SQLException e) {
            throw new DAOException("Can not get count appointments");
        }

        return result;
    }
}

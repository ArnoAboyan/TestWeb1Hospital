package DAO.impl;

import DAO.DAOException;
import DAO.EntityDAO;
import Util.AttributFinal;
import Util.DBConnection;
import entitys.HospitalCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HospitalCardDao implements EntityDAO<Integer, HospitalCard> {


    @Override
    public boolean create(HospitalCard hospitalCard) throws DAOException {
        Connection connection = DBConnection.dbConnect();

            if (!checkAvailable(hospitalCard.getPatientId(), hospitalCard.getDoctorId())) {

                try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.ADDHOSPITALCARD);) {

                    preparedStatement.setInt(1, hospitalCard.getPatientId());
                    preparedStatement.setInt(2, hospitalCard.getDoctorId());
                    preparedStatement.setString(3, hospitalCard.getDiagnosis());
                    preparedStatement.setString(4, hospitalCard.getProcedures());
                    preparedStatement.setString(5, hospitalCard.getMedications());
                    preparedStatement.setString(6, hospitalCard.getOperations());

                    System.out.println(preparedStatement);
                    preparedStatement.executeUpdate();

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }   update(hospitalCard);

        return true;
    }

    @Override
    public HospitalCard getByID(Integer integer) {
        return null;
    }

    @Override
    public boolean update(HospitalCard hospitalCard) {
        Connection connection = DBConnection.dbConnect();
        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.UPDATE_HOSPITAL_CARD);) {

            preparedStatement.setString(1, hospitalCard.getDiagnosis());
            preparedStatement.setString(2, hospitalCard.getProcedures());
            preparedStatement.setString(3, hospitalCard.getMedications());
            preparedStatement.setString(4, hospitalCard.getOperations());
            preparedStatement.setInt(5, hospitalCard.getPatientId());
            preparedStatement.setInt(6, hospitalCard.getDoctorId());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public void delete(Integer entity) {

    }

    @Override
    public List<HospitalCard> getAll() throws DAOException {
        return null;
    }

    public HospitalCard getByPatientID(Integer integer) throws DAOException {
        Connection connection = DBConnection.dbConnect();
        HospitalCard hospitalCard = new HospitalCard();

        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.GET_HOSPITALCARD_BY_PATIENT_ID)) {
            preparedStatement.setInt(1, integer);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {

                hospitalCard.setHospitalCardId(resultSet.getInt("hospitalcard_id"));
                hospitalCard.setPatientId(resultSet.getInt("patientid"));
                hospitalCard.setDoctorId(resultSet.getInt("doctorid"));
                hospitalCard.setDiagnosis(resultSet.getString("diagnosis"));
                hospitalCard.setProcedures(resultSet.getString("procedures"));
                hospitalCard.setMedications(resultSet.getString("medications"));
                hospitalCard.setOperations(resultSet.getString("operations"));
            }

            System.out.println(hospitalCard);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hospitalCard;
    }


    public Boolean checkAvailable(Integer patient, Integer doctor) throws DAOException {
        Connection connection = DBConnection.dbConnect();


        boolean check = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement(AttributFinal.CHECK_HOSPITALCARD_AVALABILE)) {

            preparedStatement.setInt(1, patient);
            preparedStatement.setInt(2, doctor);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                check = true;
            }






        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return check;

    }
}
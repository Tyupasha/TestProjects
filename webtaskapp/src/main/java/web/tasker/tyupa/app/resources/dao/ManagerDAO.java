package web.tasker.tyupa.app.resources.dao;

import web.tasker.tyupa.app.resources.DataBaseConnector;
import web.tasker.tyupa.app.resources.dto.ManagerDTO;
import web.tasker.tyupa.app.resources.dto.ModelDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManagerDAO implements InterfaceDAO {
    private final String DATA_BASE_TABLE = "managers";

    public ArrayList<ModelDTO> getDataFromDataBaseToDTO(String configFilePath) {
        ArrayList<ModelDTO> managerDTOArrayList = new ArrayList<ModelDTO>();
        DataBaseConnector dataBaseConnector = new DataBaseConnector(configFilePath);
        Connection dataBaseConnection = dataBaseConnector.getDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataBaseConnection.prepareStatement("SELECT manager_id, name, position FROM managers");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                ManagerDTO managerDTO = new ManagerDTO(resultSet.getInt("manager_id"), resultSet.getString("name"),
                        resultSet.getString("position"));
                managerDTOArrayList.add(managerDTO);
            }
        } catch (SQLException sqlException) {
            Logger.getLogger(ManagerDAO.class.getName()).log(Level.SEVERE, "SqlException by getting managers: ", sqlException);
        } finally {
            dataBaseConnector.closeDataBaseConnection(dataBaseConnection, preparedStatement);
        }
        return managerDTOArrayList;
    }

    public void saveDataToDataBase(ModelDTO modelDTO, String configFilePath) {
        ManagerDTO managerDTO = (ManagerDTO) modelDTO;
        DataBaseConnector dataBaseConnector = new DataBaseConnector(configFilePath);
        Connection dataBaseConnection = dataBaseConnector.getDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataBaseConnection.prepareStatement("INSERT INTO " + DATA_BASE_TABLE + " VALUES(?, ? ,?)");
            preparedStatement.setInt(1,managerDTO.getID());
            preparedStatement.setString(2, managerDTO.getName());
            preparedStatement.setString(3, managerDTO.getPosition());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            Logger.getLogger(ManagerDAO.class.getName()).log(Level.SEVERE, "SqlException by inserting manager: ", sqlException);
        } finally {
            dataBaseConnector.closeDataBaseConnection(dataBaseConnection, preparedStatement);
        }
    }

    public void deleteDataFromDataBase(ModelDTO modelDTO, String configFilePath) {
        DataBaseConnector dataBaseConnector = new DataBaseConnector(configFilePath);
        Connection dataBaseConnection = dataBaseConnector.getDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataBaseConnection.prepareStatement("DELETE " + DATA_BASE_TABLE + " WHERE id = ?");
            preparedStatement.setInt(1, modelDTO.getID());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            Logger.getLogger(ManagerDAO.class.getName()).log(Level.SEVERE, "SqlException by inserting manager: ", sqlException);
        } finally {
            dataBaseConnector.closeDataBaseConnection(dataBaseConnection, preparedStatement);
        }
    }

    public void updateDataInDataBase(ModelDTO modelDTO, String configFilePath) {
        ManagerDTO managerDTO = (ManagerDTO) modelDTO;
        DataBaseConnector dataBaseConnector = new DataBaseConnector(configFilePath);
        Connection dataBaseConnection = dataBaseConnector.getDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataBaseConnection.prepareStatement("UPDATE " + DATA_BASE_TABLE + " SET " +
                    "name = ?, position = ? WHERE id = ?");
            preparedStatement.setString(1, managerDTO.getName());
            preparedStatement.setString(2, managerDTO.getPosition());
            preparedStatement.setInt(3, managerDTO.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            Logger.getLogger(ManagerDAO.class.getName()).log(Level.SEVERE, "SqlException by updating manager: ", sqlException);
        }
    }
}

package web.tasker.tyupa.app.resources.dao;

import web.tasker.tyupa.app.resources.DataBaseConnector;
import web.tasker.tyupa.app.resources.dto.ManagerDTO;
import web.tasker.tyupa.app.resources.dto.ModelDTO;
import web.tasker.tyupa.app.resources.dto.TaskDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskDAO implements InterfaceDAO {
    private final String DATA_BASE_TABLE = "tasks";

    public ArrayList<ModelDTO> getDataFromDataBaseToDTO(String configFilePath) {
        ArrayList<ModelDTO> taskDTOArrayList = new ArrayList<ModelDTO>();
        DataBaseConnector dataBaseConnector = new DataBaseConnector(configFilePath);
        Connection dataBaseConnection = dataBaseConnector.getDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataBaseConnection.prepareStatement(
                    "SELECT task_id, name, manager, dateStart, dateFinish, hours, status FROM tasks");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                TaskDTO taskDTO = new TaskDTO(resultSet.getInt("task_id"), resultSet.getString("name"),
                        resultSet.getString("manager"), resultSet.getDate("date_start"), resultSet.getDate("date_finish"),
                        resultSet.getInt("hours"), resultSet.getString("status"));
                taskDTOArrayList.add(taskDTO);
            }
        } catch (SQLException sqlException) {
            Logger.getLogger(TaskDAO.class.getName()).log(Level.SEVERE, "SqlException by getting Tasks: ", sqlException);
        } finally {
            dataBaseConnector.closeDataBaseConnection(dataBaseConnection, preparedStatement);
        }
        return taskDTOArrayList;
    }

    public void saveDataToDataBase(ModelDTO modelDTO, String configFilePath) {
        TaskDTO taskDTO = (TaskDTO) modelDTO;
        DataBaseConnector dataBaseConnector = new DataBaseConnector(configFilePath);
        Connection dataBaseConnection = dataBaseConnector.getDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataBaseConnection.prepareStatement("INSERT INTO " + DATA_BASE_TABLE +
                    " VALUES (?, ?, ?, ?, ?, ?, ?");
            preparedStatement.setInt(1, taskDTO.getID());
            preparedStatement.setString(2, taskDTO.getName());
            preparedStatement.setString(3, taskDTO.getManager());
            preparedStatement.setDate(4, taskDTO.getDateStart());
            preparedStatement.setDate(5, taskDTO.getDateFinish());
            preparedStatement.setInt(6, taskDTO.getHours());
            preparedStatement.setString(7, taskDTO.getStatus());
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
            preparedStatement = dataBaseConnection.prepareStatement("DELETE " + DATA_BASE_TABLE + " WHERE id = ?;");
            preparedStatement.setInt(1,modelDTO.getID());
            preparedStatement.execute();
        } catch (SQLException sqlException) {
            Logger.getLogger(ManagerDAO.class.getName()).log(Level.SEVERE, "SqlException by inserting task: ", sqlException);
        } finally {
            dataBaseConnector.closeDataBaseConnection(dataBaseConnection, preparedStatement);
        }
    }

    public void updateDataInDataBase(ModelDTO modelDTO, String configFilePath) {
        TaskDTO taskDTO = (TaskDTO) modelDTO;
        DataBaseConnector dataBaseConnector = new DataBaseConnector(configFilePath);
        Connection dataBaseConnection = dataBaseConnector.getDataBaseConnection();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = dataBaseConnection.prepareStatement("UPDATE " + DATA_BASE_TABLE + " SET "
                    + "name = ?, manager = ?, date_start = ?, date_finish = ?, hours = ?, status = ? WHERE id = ?");
            preparedStatement.setString(1, taskDTO.getName());
            preparedStatement.setString(2, taskDTO.getManager());
            preparedStatement.setDate(3, taskDTO.getDateStart());
            preparedStatement.setDate(4, taskDTO.getDateFinish());
            preparedStatement.setInt(5, taskDTO.getHours());
            preparedStatement.setString(6, taskDTO.getStatus());
            preparedStatement.setInt(7, taskDTO.getID());
            preparedStatement.executeUpdate();
        } catch (SQLException sqlException) {
            Logger.getLogger(ManagerDAO.class.getName()).log(Level.SEVERE, "SqlException by updating task: ", sqlException);
        }
    }
}

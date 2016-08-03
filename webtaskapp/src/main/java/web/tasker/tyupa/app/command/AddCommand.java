package web.tasker.tyupa.app.command;

import web.tasker.tyupa.app.resources.dao.ManagerDAO;
import web.tasker.tyupa.app.resources.dao.TaskDAO;
import web.tasker.tyupa.app.resources.dto.ModelDTO;
import web.tasker.tyupa.app.resources.dto.TaskDTO;

public class AddCommand implements InterfaceCommand {
    public void execute(ModelDTO modelDTO, String fileConfigPath) {
        if(modelDTO instanceof TaskDTO) {
            TaskDAO taskDAO = new TaskDAO();
            taskDAO.saveDataToDataBase(modelDTO, fileConfigPath);
        } else {
            ManagerDAO managerDAO = new ManagerDAO();
            managerDAO.saveDataToDataBase(modelDTO, fileConfigPath);
        }
    }
}

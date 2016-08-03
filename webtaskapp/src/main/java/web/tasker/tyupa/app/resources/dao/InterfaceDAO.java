package web.tasker.tyupa.app.resources.dao;

import com.sun.javafx.sg.prism.NGShape;
import com.sun.org.apache.xpath.internal.operations.Mod;
import web.tasker.tyupa.app.resources.dto.ModelDTO;

import java.util.ArrayList;


public interface InterfaceDAO {
    ArrayList<ModelDTO> getDataFromDataBaseToDTO(String configFilePath);
    void saveDataToDataBase(ModelDTO modelDTO, String configFilePath);
    void updateDataInDataBase(ModelDTO modelDTO, String configFilePath);
    void deleteDataFromDataBase(ModelDTO modelDTO, String configFilePath);

}

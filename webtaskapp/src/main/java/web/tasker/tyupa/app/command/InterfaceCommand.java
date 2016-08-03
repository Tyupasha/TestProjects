package web.tasker.tyupa.app.command;

import web.tasker.tyupa.app.resources.dto.ModelDTO;

import java.util.ArrayList;

public interface InterfaceCommand {
    public void execute(ModelDTO modelDTO, String fileConfigPath);
}

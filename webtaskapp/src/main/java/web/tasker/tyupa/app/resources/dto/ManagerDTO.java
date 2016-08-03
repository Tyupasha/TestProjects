package web.tasker.tyupa.app.resources.dto;

public class ManagerDTO extends ModelDTO {
    private String position;

    protected void setPosition(String position) {
        this.position = position;
    }

    public String getPosition() {
        return this.position;
    }

    public ManagerDTO(int id, String name, String position) {
        super(id, name);
        this.position = position;
    }

    @Override
    public String toString() {
        return "ID: " + this.id + " Name: " + this.name + " Position: " + this.position;
    }
}

package web.tasker.tyupa.app.resources.dto;

/**
 * Created by Dima on 7/29/2016.
 */
public class ModelDTO {
    protected int id;
    protected String name;

    public ModelDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getID() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
}

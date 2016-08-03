package web.tasker.tyupa.app.resources.dto;

import com.sun.javafx.sg.prism.NGShape;

import java.sql.Date;

/**
 * Created by Dima on 7/28/2016.
 */
public class TaskDTO extends ModelDTO {
    private String manager;
    private Date dateStart;
    private Date dateFinish;
    private int hours;
    private String status;


    protected void setManager(String manager) {
        this.manager = manager;
    }
    protected void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }
    protected void setDateFinish(Date dateFinish) {
        this.dateFinish = dateFinish;
    }
    protected void setHours(int hours) {
        this.hours = hours;
    }
    protected void setStatus(String status) {
        this.status = status;
    }


    public String getManager() {
        return this.manager;
    }
    public Date getDateStart() {
        return this.dateStart;
    }
    public Date getDateFinish() {
        return this.dateFinish;
    }
    public int getHours() {
        return this.hours;
    }
    public String getStatus() {
        return this.status;
    }

    public TaskDTO(int id, String name, String manager, Date dateStart, Date dateFinish, int hours, String status) {
        super(id, name);
        this.manager = manager;
        this.dateStart = dateStart;
        this.dateFinish = dateFinish;
        this.hours = hours;
        this.status = status;
    }
}

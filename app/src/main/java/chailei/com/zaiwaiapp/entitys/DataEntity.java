package chailei.com.zaiwaiapp.entitys;

import java.io.Serializable;

/**
 * Created by Administrator on 16-1-19.
 */
public class DataEntity implements Serializable{
    private String title;
    private String type;
    private String user;

    public DataEntity(String title, String type, String user) {
        this.title = title;
        this.type = type;
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}

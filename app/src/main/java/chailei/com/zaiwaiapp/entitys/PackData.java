package chailei.com.zaiwaiapp.entitys;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 16-1-23.
 */
public class PackData implements Serializable {

    private int id;
    private String icon_url;
    private String name;
    private String time;
    private List<HotEntity.CustomerFeedListEntity.PictureListEntity> list;
    private String type;
    private String user;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PackData() {
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public List<HotEntity.CustomerFeedListEntity.PictureListEntity> getList() {
        return list;
    }

    public void setList(List<HotEntity.CustomerFeedListEntity.PictureListEntity> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PackData{" +
                "id=" + id +
                ", icon_url='" + icon_url + '\'' +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", list=" + list +
                ", type='" + type + '\'' +
                ", user='" + user + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

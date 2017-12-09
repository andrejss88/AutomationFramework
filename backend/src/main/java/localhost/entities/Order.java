package localhost.entities;

import java.util.List;

public class Order {

    private String desc;
    private List<String> items;

    public Order(){}

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}

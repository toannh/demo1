package vn.chodientu.entity.form;

import java.util.List;
import vn.chodientu.entity.db.UpSchedule;

public class UpScheduleForm {

    private List<UpSchedule> upSchedule;
    private int type;

    public List<UpSchedule> getUpSchedule() {
        return upSchedule;
    }

    public void setUpSchedule(List<UpSchedule> upSchedule) {
        this.upSchedule = upSchedule;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}

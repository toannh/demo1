package vn.chodientu.component;

import com.google.gson.Gson;
import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vn.chodientu.entity.db.RealTime;

/**
 *
 * @author thanhvv
 */
@Component
public class RealTimeClient {

    @Autowired
    private Gson gson;

    public void push(RealTime realTime) {
        Pusher pusher = new Pusher("89582", "d656a076aec85c984bff", "6a0fdc95518cb9f778c7");
        pusher.trigger(realTime.getChannel(), realTime.getEvent().toString(), gson.toJson(realTime.getData()));
    }
}

package vn.chodientu.service;

import com.google.gson.Gson;
import java.util.HashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import vn.chodientu.component.RealTimeClient;
import vn.chodientu.entity.db.RealTime;
import vn.chodientu.entity.enu.RealTimeEvent;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.RealTimeRepository;

@Service
public class RealTimeService {
    
    @Autowired
    private RealTimeClient realTimeClient;
    @Autowired
    private RealTimeRepository realTimeRepository;
    @Autowired
    private Viewer viewer;
    @Autowired
    private Gson gson;
    
    @Scheduled(fixedDelay = 1000)
    public void run() {
        while (true) {
            RealTime realTime = realTimeRepository.getForSend();
            if (realTime == null) {
                break;
            }
            this.process(realTime);
        }
        
    }
    
    @Async
    private void process(RealTime realTime) {
        realTimeClient.push(realTime);
    }
    
    public void add(String message, String receiverId, String link, String text, String photo) {
        try {
            HashMap<String, String> data = new HashMap<>();
            if (photo != null && !photo.equals("")) {
                data.put("photo", "photo");
            }
            if (link != null && !link.equals("")) {
                data.put("link", link);
            }
            if (text != null && !text.equals("")) {
                data.put("text", text);
            }
            this.create(RealTimeEvent.MESSAGE, receiverId, new Response(true, message, data));
        } catch (Exception e) {
        }
    }
    
    private RealTime create(RealTimeEvent event, String channel, Response response) {
        RealTime realTime = new RealTime();
        realTime.setData(response);
        realTime.setChannel(channel);
        realTime.setEvent(event);
        realTime.setTime(System.currentTimeMillis());
        realTime.setRun(false);
        realTimeRepository.save(realTime);
        return realTime;
    }
}

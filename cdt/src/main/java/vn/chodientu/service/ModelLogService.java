package vn.chodientu.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.ModelLog;
import vn.chodientu.repository.ModelLogRepository;

@Service
public class ModelLogService {

   @Autowired
   private ModelLogRepository modelLogRepository;
/**
 * Lấy thông tin người sửa model lần cuối
 * @param id
 * @return
 * @throws Exception 
 */
    public ModelLog getLastLog(String id) throws Exception {
       ModelLog lastLog = modelLogRepository.getLastLog(id);
       if(lastLog==null){
           throw  new Exception("Người sửa model trước không tồn tại");
       }
       return lastLog;
    }
   
   


   
}

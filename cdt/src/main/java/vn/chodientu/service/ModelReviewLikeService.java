package vn.chodientu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.ModelReviewLike;
import vn.chodientu.entity.db.ModelReview;
import vn.chodientu.entity.output.Response;
import vn.chodientu.repository.ModelReviewLikeRepository;
import vn.chodientu.repository.ModelReviewRepository;
import vn.chodientu.repository.UserRepository;

/**
 *
 * @author Admin
 */
@Service
public class ModelReviewLikeService {

    @Autowired
    private ModelReviewLikeRepository modelCommentLikeRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelReviewRepository modelReviewRepository;

    /**
     * Import model comment từ phiên bản cũ
     *
     * @param modelCommentLikes
     */
    @Async
    public void migrate(final List<ModelReviewLike> modelCommentLikes) {
        for (ModelReviewLike modelCommentLike : modelCommentLikes) {
            if (modelReviewRepository.exists(modelCommentLike.getCommentId()) && userRepository.exists(modelCommentLike.getUserId())) {
                modelCommentLikeRepository.save(modelCommentLike);
            }
        }
    }

    /**
     * Thêm mới like
     *
     * @param modelCommentLike
     * @return
     */
    public Response add(ModelReviewLike modelCommentLike) {
        ModelReview modelReview = modelReviewRepository.find(modelCommentLike.getCommentId());
        if (modelReview == null) {
            return new Response(false, "Comment không tồn tại");
        }
        if (!userRepository.exists(modelCommentLike.getUserId())) {
            return new Response(false, "Người dùng không tồn tại");
        }
        ModelReviewLike commentLike = modelCommentLikeRepository.getLikeByCommnetAndUser(modelCommentLike.getUserId(),modelCommentLike.getCommentId());
        
        if(commentLike == null){
            modelCommentLike.setId(modelCommentLikeRepository.genId());
            modelCommentLikeRepository.save(modelCommentLike);
            modelReview.setLike(modelReview.getLike()+1);
        }else{
            modelCommentLikeRepository.delete(commentLike);
            modelReview.setLike(modelReview.getLike()-1);
        }
        modelReviewRepository.save(modelReview);
        
        return new Response(true, "Thành công",modelReview);   
    }

}

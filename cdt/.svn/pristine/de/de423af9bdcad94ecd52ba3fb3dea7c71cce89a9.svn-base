package vn.chodientu.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.CashTransaction;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.db.ModelReview;
import vn.chodientu.entity.enu.CashTransactionType;
import vn.chodientu.entity.input.ModelReviewSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.ModelRepository;
import vn.chodientu.repository.ModelReviewRepository;
import vn.chodientu.repository.UserRepository;
import vn.chodientu.util.TextUtils;
import vn.chodientu.util.UrlUtils;
import static vn.chodientu.util.UrlUtils.item;

/**
 *
 * @author Admin
 */
@Service
public class ModelReviewService {

    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CashService cashService;
    @Autowired
    private SearchIndexService searchIndexService;
    @Autowired
    private ModelReviewRepository modelReviewRepository;
    @Autowired
    private Viewer viewer;

    /**
     * Import model comment từ phiên bản cũ
     *
     * @param modelReviews
     */
    @Async
    public void migrate(final List<ModelReview> modelReviews) {
        for (ModelReview modelReview : modelReviews) {
            if (modelRepository.exists(modelReview.getModelId()) && userRepository.exists(modelReview.getUserId())) {
                modelReviewRepository.save(modelReview);
                Model model = modelRepository.find(modelReview.getModelId());
                float point = (model.getReviewCount() * model.getReviewScore() + modelReview.getPoint()) / (model.getReviewCount() + 1);
                model.setReviewScore(point);
                model.setReviewCount(model.getReviewCount() + 1);
                modelRepository.save(model);
                searchIndexService.processIndexModel(model);
            }
        }
    }

    /**
     * Lấy model review theo điều kiện
     *
     * @param modelReviewSearch
     * @return
     */
    public DataPage<ModelReview> search(ModelReviewSearch modelReviewSearch) {
        Criteria cri = new Criteria();

        if (modelReviewSearch.getActive() == 1) {
            cri.and("active").is(true);
        }
        if (modelReviewSearch.getActive() == 2) {
            cri.and("active").is(false);
        }
        if (modelReviewSearch.getPageIndex() < 0) {
            modelReviewSearch.setPageIndex(0);
        }
        if (modelReviewSearch.getPageSize() < 1) {
            modelReviewSearch.setPageSize(1);
        }
        if (modelReviewSearch.getModelId() != null && !"".equals(modelReviewSearch.getModelId())) {
            cri.and("modelId").is(modelReviewSearch.getModelId());
        }
        String order = "time";
        if (modelReviewSearch.getOrderBy() == 1) {
            order = "like";
        }
        DataPage<ModelReview> modelReviewPage = new DataPage<>();
        modelReviewPage.setDataCount(modelReviewRepository.count(new Query(cri)));
        modelReviewPage.setPageIndex(modelReviewSearch.getPageIndex());
        modelReviewPage.setPageSize(modelReviewSearch.getPageSize());
        modelReviewPage.setPageCount(modelReviewPage.getDataCount() / modelReviewSearch.getPageSize());
        if (modelReviewPage.getDataCount() % modelReviewSearch.getPageSize() != 0) {
            modelReviewPage.setPageCount(modelReviewPage.getPageCount() + 1);
        }

        List<ModelReview> list = modelReviewRepository.list(cri, new PageRequest(modelReviewSearch.getPageIndex(), modelReviewSearch.getPageSize(), Sort.Direction.DESC, order));
        modelReviewPage.setData(list);

        return modelReviewPage;
    }

    /**
     * Thêm mới bình luận đánh giá
     *
     * @param modelReview
     * @return
     */
    public Response add(ModelReview modelReview) {
        Model model = modelRepository.find(modelReview.getModelId());
        if (model == null) {
            return new Response(false, "Model không tồn tại");
        }
        if (!userRepository.exists(modelReview.getUserId())) {
            return new Response(false, "Người dùng không tồn tại");
        }
        long count = modelReviewRepository.countUserAndModel(modelReview.getModelId(), modelReview.getUserId());
        if (count <= 0) {
            modelReview.setActive(true);
            modelReview.setId(modelReviewRepository.genId());
            modelReview.setTime(System.currentTimeMillis());
            if (modelReview.getPoint() <= 0) {
                modelReview.setPoint(5);
            }
            modelReviewRepository.save(modelReview);

            float point = (model.getReviewCount() * model.getReviewScore() + modelReview.getPoint()) / (model.getReviewCount() + 1);
            model.setReviewScore(point);
            model.setReviewCount(model.getReviewCount() + 1);
            if (modelReview.isRecommended()) {
                model.setRecommendedCount(model.getRecommendedCount() + 1);
            }
            modelRepository.save(model);
            searchIndexService.processIndexModel(model);

            CashTransaction cashTransaction = new CashTransaction();
            cashTransaction.setAmount(200);
            cashTransaction.setSpentId(modelReview.getId());
            cashTransaction.setSpentQuantity(1);
            cashTransaction.setType(CashTransactionType.COMMENT_MODEL_REWARD);
            cashTransaction.setUserId(modelReview.getUserId());

            try {
                cashService.reward(CashTransactionType.COMMENT_MODEL_REWARD, viewer.getUser().getId(), model.getId(), "/model/" + model.getId() + "/" + TextUtils.createAlias(model.getName()) + ".html#comment=" + modelReview.getId() + "", modelReview.getContent());
                return new Response(true, "Đánh giá thành công. 200 xèng đã được cộng vào tài khoản của bạn.");
            } catch (Exception e) {
                return new Response(true, "COMMENT_MODEL_FAIL");
            }

        } else {
            return new Response(false, "Bạn chỉ được đánh giá model này một lần");
        }
    }

    /**
     * Kiểm tra người dùng đã từng viết đánh giá hay chưa
     *
     * @param userId
     * @param modelId
     * @return
     */
    public long checkReview(String userId, String modelId) {
        long count = modelReviewRepository.countUserAndModel(modelId, userId);
        return count;
    }
}

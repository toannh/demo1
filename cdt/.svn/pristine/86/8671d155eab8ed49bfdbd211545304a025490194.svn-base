package vn.chodientu.service;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.entity.db.Tag;
import vn.chodientu.entity.input.TagSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.TagRepository;
import vn.chodientu.util.TextUtils;

@Service
public class TagService {

    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private Viewer viewer;
    @Autowired
    private HttpServletRequest request;

    public void add(String keyword) {
        try {
            if (viewer.getUser() == null) {
                return;
            }
            Tag tag = tagRepository.find(TextUtils.createAlias(keyword), viewer.getUser().getId());
            if (tag == null) {
                tag = new Tag();
                tag.setId(TextUtils.createAlias(keyword));
                tag.setKeyword(keyword);
                tag.setCreateTime(System.currentTimeMillis());
                tag.setUserId(viewer.getUser().getId());
            }
            tag.setUpdateTime(System.currentTimeMillis());
            tag.setQuantity(tag.getQuantity() + 1);
            tag.setIp(TextUtils.getClientIpAddr(request));
            tagRepository.save(tag);
        } catch (Exception e) {
        }
    }

    public DataPage<Tag> search(TagSearch search) {
        DataPage<Tag> dataPage = new DataPage<>();
        Criteria cri = new Criteria();
        cri.and("userId").is(search.getUserId());
        if (search.getKeyword() != null && !search.getKeyword().equals("")) {
            cri.and("keyword").regex(search.getKeyword());
        }
        if (search.getCreateTimeTo() <= search.getCreateTimeFrom()) {
            if (search.getCreateTimeFrom() > 0) {
                cri.and("createTime").gte(search.getCreateTimeFrom());
            }
        } else if (search.getCreateTimeTo() > search.getCreateTimeFrom()) {
            cri.and("createTime").gte(search.getCreateTimeFrom()).lte(search.getCreateTimeTo());
        }

        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Query query = new Query(cri);
        dataPage.setDataCount(tagRepository.count(query));
        dataPage.setPageIndex(search.getPageIndex());
        dataPage.setPageSize(search.getPageSize());
        dataPage.setPageCount(dataPage.getDataCount() / search.getPageSize());
        if (dataPage.getDataCount() % search.getPageSize() != 0) {
            dataPage.setPageCount(dataPage.getPageCount() + 1);
        }
        dataPage.setData(tagRepository.find(query.limit(search.getPageSize()).skip(search.getPageIndex() * search.getPageSize()).with(sort)));
        return dataPage;
    }

}

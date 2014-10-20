package vn.chodientu.service;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import vn.chodientu.component.Validator;
import vn.chodientu.entity.db.Footerkeyword;
import vn.chodientu.entity.input.FooterKeywordSearch;
import vn.chodientu.entity.output.DataPage;
import vn.chodientu.entity.output.Response;
import vn.chodientu.entity.web.Viewer;
import vn.chodientu.repository.FooterKeywordRepository;

/**
 * Business service cho xu hướng tìm kiếm
 *
 * @author Phuongdt
 * @since Jun 14, 2013
 */
@Service
public class FooterKeywordService {

    @Autowired
    private FooterKeywordRepository footerKeywordRepository;
    @Autowired
    private Validator validator;
    @Autowired
    private Viewer viewer;

    /**
     * Thêm từ khóa mới vào xu hướng tìm kiếm
     *
     * @param footerKeyword
     * @return
     */
    public Response add(Footerkeyword footerKeyword) {
        Map<String, String> errors = validator.validate(footerKeyword);
        if (footerKeyword.getUrl() != null && !footerKeyword.getUrl().trim().equals("") && !footerKeyword.getUrl().contains("chodientu.vn")) {
            errors.put("url", "Đường dẫn đến phải từ chợ điện tử (http://chodientu.vn)");
        }
        boolean check = footerKeywordRepository.checkKeyword(footerKeyword.getKeyword());
        if (footerKeywordRepository.checkKeyword(footerKeyword.getKeyword())) {
            errors.put("keyword", "Từ khóa này đã tồn tại!");
        }
        if (!errors.isEmpty()) {
            return new Response(false, null, errors);
        } else {
            footerKeyword.setId(footerKeywordRepository.genId());
            footerKeyword.setActive(true);
            footerKeywordRepository.save(footerKeyword);
            return new Response(true, "Đã thêm thành công", footerKeyword);
        }

    }

    /**
     * *
     * Sửa từ khóa
     *
     * @param footerKeyword
     * @return
     */
    public Response edit(Footerkeyword footerKeyword) {
        Map<String, String> errors = validator.validate(footerKeyword);
        if (!footerKeyword.getUrl().contains("http://chodientu.vn")) {
            errors.put("url", "Đường dẫn đến phải từ chợ điện tử");
        }
        if (!errors.isEmpty()) {
            return new Response(false, null, errors);
        } else {
            Footerkeyword fk = footerKeywordRepository.find(footerKeyword.getId());
            fk.setKeyword(footerKeyword.getKeyword());
            fk.setPosition(footerKeyword.getPosition());
            fk.setUrl(footerKeyword.getUrl());
            footerKeywordRepository.save(fk);
            return new Response(true, "Đã sửa thành công");
        }

    }

    /**
     * *
     * Thay đổi trạng thái từ khóa
     *
     * @param id
     * @return
     */
    public Response changeActive(String id) {
        Footerkeyword fk = footerKeywordRepository.find(id);
        if (fk == null) {
            return new Response(false, "Không tồn tại từ khóa này");
        } else {
            fk.setActive(!fk.isActive());
            footerKeywordRepository.save(fk);
            return new Response(true, "Thay đổi trạng thái hiển thị thành công", fk);

        }

    }

    /**
     * Thay đổi trạng thái phổ biến của từ khóa
     *
     * @param id
     * @return
     */
    public Response changeCommon(String id) {
        Footerkeyword fk = footerKeywordRepository.find(id);
        if (fk == null) {
            return new Response(false, "Không tồn tại từ khóa này");
        } else {
            fk.setCommon(!fk.isCommon());
            footerKeywordRepository.save(fk);
            return new Response(true, "Thay đổi trạng thái phổ biến thành công", fk);

        }

    }

    /**
     * *
     * Sắp xếp vị trí từ khóa
     *
     * @param id
     * @param value
     * @return
     */
    public Response changePosition(String id, int value) {
        Footerkeyword fk = footerKeywordRepository.find(id);
        if (fk == null) {
            return new Response(false, "Không tồn tại từ khóa này");
        } else {
            fk.setPosition(value);
            footerKeywordRepository.save(fk);
            return new Response(true, "Sắp xếp vị trí thành công");

        }

    }

    /**
     * *
     * Thay đổi tên từ khóa
     *
     * @param id
     * @param value
     * @return
     */
    public Response changeKeyword(String id, String value) {
        Footerkeyword fk = footerKeywordRepository.find(id);
        if (fk == null) {
            return new Response(false, "Không tồn tại từ khóa này");
        } else {
            fk.setKeyword(value);
            footerKeywordRepository.save(fk);
            return new Response(true, "Lưu lại thành công");

        }

    }

    /**
     * *
     * Thay đổi Url
     *
     * @param id
     * @param url
     * @return
     */
    public Response changeUrl(String id, String url) {
        Footerkeyword fk = footerKeywordRepository.find(id);
        if (url == null || url.trim().equals("") || url.contains("chodientu.vn")) {
            return new Response(false, "Url không được để trống và Link phải dẫn từ chodientu.vn");
        }
        if (fk == null) {
            return new Response(false, "Không tồn tại từ khóa này");
        } else {
            fk.setUrl(url);
            footerKeywordRepository.save(fk);
            return new Response(true, "Lưu lại thành công");
        }

    }

    /**
     * Xóa từ khóa
     *
     * @param id
     * @return
     */
    public Response del(String id) {
        Footerkeyword fk = footerKeywordRepository.find(id);
        if (fk == null) {
            return new Response(false, "Không tồn tại từ khóa này");
        } else {

            footerKeywordRepository.delete(id);
            return new Response(true, "Đã xóa thành công");
        }

    }

    /**
     * *
     * Tìm kiếm xu hướng tìm kiếm
     *
     * @param keywordSearch
     * @return
     */
    public DataPage<Footerkeyword> search(FooterKeywordSearch keywordSearch) {
        Criteria cri = new Criteria();
        if (keywordSearch.getCommon() > 0) {
            if (keywordSearch.getCommon() == 1) {
                cri.and("common").is(keywordSearch.getCommon() == 1);
            } else {
                cri.and("common").is(false);
            }
        }
        if (keywordSearch.getKeyword() != null && !keywordSearch.getKeyword().equals("")) {
            cri.and("keyword").regex(keywordSearch.getKeyword());
        }
        if (keywordSearch.getUrl() != null && !keywordSearch.getUrl().equals("")) {
            cri.and("url").regex(keywordSearch.getUrl());
        }
        Sort sort = new Sort(Sort.Direction.ASC, "position");
        Query query = new Query(cri);
        query.with(sort);
        query.skip(keywordSearch.getPageIndex() * keywordSearch.getPageSize()).limit(keywordSearch.getPageSize());
        DataPage<Footerkeyword> page = new DataPage<>();
        page.setPageSize(keywordSearch.getPageSize());
        page.setPageIndex(keywordSearch.getPageIndex());
        if (page.getPageSize() <= 0) {
            page.setPageSize(2);
        }
        page.setDataCount(footerKeywordRepository.count(query));
        page.setPageCount(page.getDataCount() / page.getPageSize());
        if (page.getDataCount() % page.getPageSize() != 0) {
            page.setPageCount(page.getPageCount() + 1);
        }
        page.setData(footerKeywordRepository.find(query));
        return page;
    }

    /**
     * *
     * Lấy tất cả từ khóa xu hướng tìm kiếm
     *
     * @return
     */
    public List<Footerkeyword> list() {
        Criteria cri = new Criteria();
        cri.and("active").is(true);
        cri.and("common").is(false);
        Query query = new Query(cri).with(new Sort(Sort.Direction.ASC, "position"));
        return footerKeywordRepository.find(query);
    }

    /**
     * Lấy ra 40 từ khóa phổ biến hiển thị trang danh mục, tìm kiếm
     *
     * @return
     */
    public List<Footerkeyword> listkeyWord() {
        Criteria cri = new Criteria();
        cri.and("active").is(true);
        cri.and("common").is(true);
        Query query = new Query(cri).limit(40).with(new Sort(Sort.Direction.ASC, "position"));
        return footerKeywordRepository.find(query);
    }

}

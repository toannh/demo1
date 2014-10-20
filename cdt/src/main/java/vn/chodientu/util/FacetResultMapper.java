package vn.chodientu.util;

import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.SearchResultMapper;

/**
 * @since Jun 6, 2014
 * @author Phu
 */
public class FacetResultMapper implements SearchResultMapper {

    @Override
    public <T> FacetedPage<T> mapResults(SearchResponse response, Class<T> clazz, Pageable pageable) {
        vn.chodientu.entity.search.FacetResult result = new vn.chodientu.entity.search.FacetResult();
        result.setResponse(response);
        return result;
    }

}
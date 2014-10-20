package vn.chodientu.entity.search;

import java.util.Iterator;
import java.util.List;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.facet.Facet;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.FacetedPage;

/**
 * @since Jun 6, 2014
 * @author Phu
 */
public class FacetResult implements FacetedPage {

    private SearchResponse response;

    public void setResponse(SearchResponse response) {
        this.response = response;
    }

    public <T extends Facet> T getFacet(Class<T> type, String name) {
        return response.getFacets().facet(type, name);
    }

    @Override
    public boolean hasFacets() {
        return true;
    }

    @Override
    public List<org.springframework.data.elasticsearch.core.facet.FacetResult> getFacets() {
        return null;
    }

    @Override
    public org.springframework.data.elasticsearch.core.facet.FacetResult getFacet(String name) {
        return null;
    }

    @Override
    public int getNumber() {
        return 0;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public int getTotalPages() {
        return 0;
    }

    @Override
    public int getNumberOfElements() {
        return 0;
    }

    @Override
    public long getTotalElements() {
        return response.getHits().getTotalHits();
    }

    @Override
    public boolean hasPreviousPage() {
        return false;
    }

    @Override
    public boolean isFirstPage() {
        return false;
    }

    @Override
    public boolean hasNextPage() {
        return false;
    }

    @Override
    public boolean isLastPage() {
        return false;
    }

    @Override
    public Pageable nextPageable() {
        return null;
    }

    @Override
    public Pageable previousPageable() {
        return null;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public List getContent() {
        return null;
    }

    @Override
    public boolean hasContent() {
        return false;
    }

    @Override
    public Sort getSort() {
        return null;
    }

    @Override
    public boolean isFirst() {
        return false;
    }

    @Override
    public boolean isLast() {
        return false;
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}

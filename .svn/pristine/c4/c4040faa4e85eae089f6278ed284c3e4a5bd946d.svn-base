/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.repository;

import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.action.search.SearchType;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Item;
import vn.chodientu.entity.search.FacetResult;
import vn.chodientu.entity.search.ItemSearch;
import vn.chodientu.util.FacetResultMapper;

/**
 *
 * @author ThuNguyen
 */
@Repository
public class ItemSearchRepository extends BaseSearchRepository<ItemSearch> {
    
    public ItemSearchRepository() {
        super(ItemSearch.class);
    }
    
    public void index(Item item) {
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId(item.getId());
        indexQuery.setObject(new ItemSearch(item));
        elasticsearchTemplate.index(indexQuery);
    }
    
    public void index(List<Item> items) {
        List<IndexQuery> indexQuerys = new ArrayList<>();
        for (Item item : items) {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(item.getId());
            indexQuery.setObject(new ItemSearch(item));
            indexQuerys.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(indexQuerys);
    }
    
    public FacetedPage<ItemSearch> search(NativeSearchQuery query) {
        return elasticsearchTemplate.queryForPage(query, entityClass);
    }
    
    public FacetResult getFacets(NativeSearchQuery query) {
        query.setSearchType(SearchType.COUNT);
        return (FacetResult) elasticsearchTemplate.queryForPage(query, entityClass, new FacetResultMapper());
    }
}

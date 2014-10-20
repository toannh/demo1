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
import org.springframework.data.elasticsearch.core.query.UpdateQuery;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Model;
import vn.chodientu.entity.search.FacetResult;
import vn.chodientu.entity.search.ModelSearch;
import vn.chodientu.util.FacetResultMapper;

/**
 *
 * @author ThuNguyen
 */
@Repository
public class ModelSearchRepository extends BaseSearchRepository<ModelSearch> {

    public ModelSearchRepository() {
        super(ModelSearch.class);
    }

    public void index(Model model) {
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId(model.getId());
        indexQuery.setObject(new ModelSearch(model));
        elasticsearchTemplate.index(indexQuery);
    }

    public void index(List<Model> models) {
        List<IndexQuery> indexQuerys = new ArrayList<>();
        for (Model model : models) {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(model.getId());
            indexQuery.setObject(new ModelSearch(model));
            indexQuerys.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(indexQuerys);
    }

    public FacetedPage<ModelSearch> search(NativeSearchQuery query) {
        return elasticsearchTemplate.queryForPage(query, entityClass);
    }

    public FacetResult getFacets(NativeSearchQuery query) {
        query.setSearchType(SearchType.COUNT);
        return (FacetResult) elasticsearchTemplate.queryForPage(query, entityClass, new FacetResultMapper());
    }
}

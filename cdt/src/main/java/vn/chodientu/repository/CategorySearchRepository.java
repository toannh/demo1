/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.repository;

import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.index.query.QueryBuilder;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Category;
import vn.chodientu.entity.search.CategorySearch;
import vn.chodientu.util.TextUtils;

/**
 *
 * @author ThuNguyen
 */
@Repository
public class CategorySearchRepository extends BaseSearchRepository<CategorySearch> {

    public CategorySearchRepository() {
        super(CategorySearch.class);
    }

    public void index(List<Category> categories) {
        List<IndexQuery> indexQuerys = new ArrayList<>();
        for (Category category : categories) {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(category.getId());
            indexQuery.setObject(new CategorySearch(category.getId(), TextUtils.removeDiacritical(category.getName() + " " + category.getMetaDescription() == null ? "" : category.getMetaDescription()), category.isActive(), category.isLeaf()));
            indexQuerys.add(indexQuery);
        }
        elasticsearchTemplate.bulkIndex(indexQuerys);
    }

    public void index(Category category) {
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId(category.getId());
        indexQuery.setObject(new CategorySearch(category.getId(), TextUtils.removeDiacritical(category.getName() + " " + category.getMetaDescription() == null ? "" : category.getMetaDescription()), category.isActive(), category.isLeaf()));
        elasticsearchTemplate.index(indexQuery);
    }

    public List<String> search(QueryBuilder queryBuilder) {
        try {
            return elasticsearchTemplate.queryForIds(new NativeSearchQuery(queryBuilder));
        } catch (Exception ex) {
            return new ArrayList<>();
        }
    }
}

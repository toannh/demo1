/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.chodientu.repository;

import java.util.ArrayList;
import java.util.List;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.data.elasticsearch.core.FacetedPage;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Repository;
import vn.chodientu.entity.db.Manufacturer;
import vn.chodientu.entity.search.ManufacturerSearch;
import vn.chodientu.util.TextUtils;

/**
 *
 * @author ThuNguyen
 */
@Repository
public class ManufacturerSearchRepository extends BaseSearchRepository<ManufacturerSearch> {

    public ManufacturerSearchRepository() {
        super(ManufacturerSearch.class);
    }

    public void index(List<Manufacturer> manufacturers) {
        List<IndexQuery> indexQuerys = new ArrayList<>();

        for (Manufacturer man : manufacturers) {
            IndexQuery indexQuery = new IndexQuery();
            indexQuery.setId(man.getId());
            indexQuery.setObject(new ManufacturerSearch(man.getId(),
                    TextUtils.removeDiacritical(man.getName() + " " + man.getDescription() == null ? "" : man.getDescription()),
                    man.getName(),
                    man.getMappedCategoryIds(),
                    man.isActive(),man.isGlobal())
            );
            indexQuerys.add(indexQuery);
        }

        elasticsearchTemplate.bulkIndex(indexQuerys);
    }

    public void index(Manufacturer manufacturer) {
        IndexQuery indexQuery = new IndexQuery();
        indexQuery.setId(manufacturer.getId());
        indexQuery.setObject(new ManufacturerSearch(manufacturer.getId(),
                TextUtils.removeDiacritical(manufacturer.getName() + " " + manufacturer.getDescription() == null ? "" : manufacturer.getDescription()),
                manufacturer.getName(),
                manufacturer.getMappedCategoryIds(),
                manufacturer.isActive(),
                manufacturer.isGlobal())
        );
        elasticsearchTemplate.index(indexQuery);
    }

    public FacetedPage<ManufacturerSearch> search(NativeSearchQuery query) {
        return elasticsearchTemplate.queryForPage(query, entityClass);
    }

}

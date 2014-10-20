package vn.chodientu.repository;

import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

/**
 * @param <E>
 * @since May 30, 2014
 * @author Phu
 */
public class BaseSearchRepository<E> {

    @Autowired
    protected ElasticsearchTemplate elasticsearchTemplate;
    protected Class<E> entityClass;

    public BaseSearchRepository(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public void preIndex() {
        elasticsearchTemplate.deleteIndex(entityClass);
        elasticsearchTemplate.createIndex(entityClass);
        elasticsearchTemplate.putMapping(entityClass);
        elasticsearchTemplate.refresh(entityClass, true);
    }

    public void delete(String id) {
        try {
            elasticsearchTemplate.delete(entityClass, id);
        } catch (Exception ex) {
        }
    }

    public long count() {
        try {
            return elasticsearchTemplate.count(new NativeSearchQuery(new MatchAllQueryBuilder()), entityClass);
        } catch (Exception ex) {
            return 0;
        }
    }
}

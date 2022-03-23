package com.alexsei.itransition.service;

import com.alexsei.itransition.model.Review;
import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Service
public class HibernateSearchService {



    private final EntityManager centityManager;

    public HibernateSearchService(EntityManager centityManager) {
        this.centityManager = centityManager;
    }

//
//    public void initializeHibernateSearch() {
//
//
//    }

    @Transactional
    public List<Review> fuzzySearch(String searchTerm) {
        try {
            FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(centityManager);
        QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Review.class).get();
        Query luceneQuery = qb.keyword().fuzzy().withEditDistanceUpTo(1).withPrefixLength(1).onFields("text")
                .matching(searchTerm).createQuery();
        javax.persistence.Query jpaQuery = fullTextEntityManager.createFullTextQuery(luceneQuery, Review.class);
        List<Review> review = null;
        try {
            review = jpaQuery.getResultList();
        } catch (NoResultException nre) {
        }
        return review;
    }
}

package hello.repository;

import hello.bean.*;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by b.bassac on 24/05/2016.
 */
@Repository
@Transactional
public class CollectionRepositoryImpl implements CollectionRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Bd getBdFromId(long id) {
        return entityManager.find(Bd.class, id);
    }

    @Override
    public List<Bd> getAllBd() {
        return entityManager.createQuery("SELECT b FROM Bd b").getResultList();
    }

    @Override
    @Cacheable("collection")
    public Collection getCollection() {
        List<Collection> resultList = entityManager.createQuery("SELECT c FROM Collection c order by c.id ASC").getResultList();
        return  resultList.isEmpty()? null : resultList.get(0);
    }

    @Override
    public void createCollection(Collection c) {
        entityManager.persist(c);
    }

    @Override
    public Serie getSerieFromId(Long id) {
        return entityManager.find(Serie.class, id);
    }

    @Override
    public List<Serie> getAllSeries() {
        return entityManager.createQuery("SELECT s FROM Serie s").getResultList();
    }

    @Override
    @CacheEvict("collection")
    public DeleteResult deleteCollection() {
        DeleteResult result = new DeleteResult();
        result.setBdDeleted(entityManager.createQuery("DELETE FROM Bd").executeUpdate());
        result.setSerieDeleted(entityManager.createQuery("DELETE FROM Serie").executeUpdate());
        result.setCollectionDeleted(entityManager.createQuery("DELETE FROM Collection").executeUpdate());
        return result;
    }

    @Override
    public List<BdManquante> getAllBdManquantes() {
        List<Serie> series = getAllSeries();
        List<BdManquante> toReturn=new ArrayList<>();
        for(Serie serie : series){
            if (serie.getListManquante()!= null && serie.getListManquante().size()>0){
                for (Bd bd : serie.getListManquante()) {
                    BdManquante item = new BdManquante();
                    item.setSerieId(serie.getId());
                    item.setSerieName(serie.getNom());
                    item.setEditeur(serie.getEditeur());
                    item.setBdid(bd.getId());
                    if (!StringUtils.isEmpty(bd.getCouvertureUrl()))
                    {
                        item.setUrlImage(bd.getCouvertureUrl());
                    }else {
                        item.setUrlImage(serie.getImageUrl());
                    }

                    item.setTitre(bd.getTitre());
                    item.setNumero(bd.getNumero());
                    toReturn.add(item);
                }

            }
        }
        return toReturn;
    }

    @Override
    @CacheEvict("collection")
    public Long switchBDAsPossede(Long bdId) {
        Serie serie = (Serie) entityManager
                .createQuery("SELECT bd.serie FROM Bd bd WHERE bd.id=:bdId")
                .setParameter("bdId", bdId)
                .getSingleResult();

        Bd selectedBd = null;
        for (Bd bd : serie.getListManquante()) {
            if (bd.getId().equals(bdId)) {
                selectedBd = bd;
                break;
            }
        }
        if (selectedBd != null) {
            serie.getListManquante().remove(selectedBd);
            serie.getListPossede().add(selectedBd);
            entityManager.merge(serie);
            entityManager.flush();
        }

        return selectedBd.getId();
    }
}

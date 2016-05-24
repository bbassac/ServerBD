package hello.repository;

import hello.bean.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by b.bassac on 24/05/2016.
 */
public interface CollectionRepositoryCustom {

    Bd getBdFromId(long id);

    List<Bd> getAllBd();

    Collection getCollection();

    void createCollection(Collection c);

    Serie getSerieFromId(Long id);

    List<Serie> getAllSeries();

    DeleteResult deleteCollection();

    List<BdManquante> getAllBdManquantes();
}
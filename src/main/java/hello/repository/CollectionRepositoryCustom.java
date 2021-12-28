package hello.repository;

import hello.bean.*;

import java.io.IOException;
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

    List<BdManquante> getAllBdManquantes();

    Long switchBDAsPossede(Long id);

    byte[] exportCollectionToExcel() throws IOException;

    byte[] exportAllCollectionToExcel()throws IOException;
}
package hello.repository;


import hello.bean.*;

import org.springframework.stereotype.Repository;

import org.springframework.util.StringUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by b.bassac on 24/05/2016.
 */
@Repository
public class CollectionRepositoryImpl implements CollectionRepositoryCustom {

    Collection cache;

    @Override
    public Bd getBdFromId(long id) {

        for (Serie s : cache.getListeSerie()){
                for (Bd m : s.getListManquante()){
                    if (m.getId().equals(id)){
                        return m;
                    }
                }
                for (Bd b : s.getListPossede()){
                    if (b.getId().equals(id)){
                        return b;
                    }
                }
        }
        throw new BookNotFoundException();
    }

    @Override
    public List<Bd> getAllBd() {
        List<Bd> toReturn = new ArrayList<>();
        for (Serie s : cache.getListeSerie()){
            toReturn.addAll(s.getListManquante());
            toReturn.addAll(s.getListPossede());
        }
        return toReturn;
    }

    @Override
    public Collection getCollection() {
        return cache;
    }

    @Override
    public void createCollection(Collection c) {
        cache=c;
    }


    @Override
    public Serie getSerieFromId(Long id) {

        Serie s = cache.getListeSerie().stream().filter(serie -> serie.getId().equals(id)).findAny().orElse(null);

        return s;
    }

    @Override
    public List<Serie> getAllSeries() {
        return cache.getListeSerie();
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
    public Long switchBDAsPossede(Long bdId) {

        for (Serie s : cache.getListeSerie()) {
            for (Bd b : s.getListManquante()) {
                if (b.getId().equals(bdId)) {
                    s.getListPossede().add(b);
                    s.getListManquante().remove(b);
                    return b.getId();
                }
            }
        }
        throw new BookNotFoundException();
    }




    @Override
    public byte[] exportCollectionToExcel() throws IOException {
        return new ExcelExporter().exportCollectionToExcel(cache);
    }

    @Override
    public byte[] exportAllCollectionToExcel() throws IOException {
        return new ExcelExporter().exportAllCollectionToExcel(cache);
    }


}

package hello.repository;

import hello.LogUtils;
import hello.bean.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by b.bassac on 24/05/2016.
 */
@Repository
@Transactional
public class CollectionRepositoryImpl implements CollectionRepositoryCustom {

    public static final String CRLF = System.getProperty("line.separator");
    @PersistenceContext
     EntityManager entityManager;

    CustomCache cache = new CustomCache();

    @Override
    public Bd getBdFromId(long id) {
        return entityManager.find(Bd.class, id);
    }

    @Override
    public List<Bd> getAllBd() {
        return entityManager.createQuery("SELECT b FROM Bd b").getResultList();
    }

    @Override
    public Collection getCollection() {
       if(cache.isEmpty(CustomCache.COLLECTION_CACHE)){
           cache.putIn(CustomCache.COLLECTION_CACHE,getCollectionFromBdd());
       }
        return cache.getCache(CustomCache.COLLECTION_CACHE);
    }

    @Override
    public void createCollection(Collection c) {
        entityManager.persist(c);
        cache.putIn(CustomCache.COLLECTION_CACHE,getCollectionFromBdd());
    }

    private Collection getCollectionFromBdd(){
        LogUtils.warn("Get Collection from Database");
        Query query = entityManager.createQuery("SELECT c FROM Collection c order by c.id ASC");
        List<Collection> res = query.getResultList();
        return res.isEmpty() ? null : res.get(0);
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
    public DeleteResult deleteCollection() {
        DeleteResult result = new DeleteResult();
        result.setBdDeleted(entityManager.createQuery("DELETE FROM Bd").executeUpdate());
        result.setSerieDeleted(entityManager.createQuery("DELETE FROM Serie").executeUpdate());
        result.setCollectionDeleted(entityManager.createQuery("DELETE FROM Collection").executeUpdate());
        cache.clearCache(CustomCache.COLLECTION_CACHE);
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
        cache.clearCache(CustomCache.COLLECTION_CACHE);
        return selectedBd.getId();
    }

    @Override
    public byte[] exportCollectionToExcel() throws IOException {
        Collection coll = getCollection();
        Workbook wb = new HSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();

        fillBody(wb, createHelper,coll);

        // Write the output to a file
        ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
        wb.write(fileOut);
        fileOut.close();
        return fileOut.toByteArray();
    }

    private void fillBody(Workbook wb, CreationHelper createHelper,Collection coll) {
        Sheet sheet = wb.createSheet("Listing");

        int lineIndex = 0;
        // Create a row and put some cells in it. Rows are 0 based.
        Row rowTitles = sheet.createRow((short) lineIndex);

        //Headers
        int indexId = 1;
        int indexNom = 2;
        int indexEditeur = 3;
        int indexFini = 4;
        int indexPossede = 5;
        int indexManquante = 6;
        int indexUrl = 7;
        rowTitles.createCell(indexId).setCellValue(createHelper.createRichTextString("ID"));
        rowTitles.createCell(indexNom).setCellValue(createHelper.createRichTextString("Nom"));
        rowTitles.createCell(indexEditeur).setCellValue(createHelper.createRichTextString("Editeur"));
        rowTitles.createCell(indexFini).setCellValue(createHelper.createRichTextString("Fini"));
        rowTitles.createCell(indexPossede).setCellValue(createHelper.createRichTextString("Possede"));
        rowTitles.createCell(indexManquante).setCellValue(createHelper.createRichTextString("Manquante"));
        rowTitles.createCell(indexUrl).setCellValue(createHelper.createRichTextString("Image Url"));
        lineIndex++;

        for (Serie serie : coll.getListeSerie()) {
            Row row = sheet.createRow((short) lineIndex);
            row.createCell(indexId).setCellValue(createHelper.createRichTextString(String.valueOf(serie.getId())));
            row.createCell(indexNom).setCellValue(createHelper.createRichTextString(serie.getNom()));
            row.createCell(indexEditeur).setCellValue(createHelper.createRichTextString(serie.getEditeur()));
            row.createCell(indexFini).setCellValue(createHelper.createRichTextString(serie.isFini() ? "x" : ""));
            StringBuilder sbPossede = new StringBuilder();
            for (Bd possede : serie.getListPossede()) {
                sbPossede.append(possede.getNumero() + " " + possede.getTitre() + System.getProperty("line.separator"));
            }
            Cell cellPossede = row.createCell(indexPossede);

            cellPossede.getCellStyle().setWrapText(true);
            cellPossede.setCellValue(sbPossede.toString());

            StringBuilder sbmanquante = new StringBuilder();
            for (Bd manquante : serie.getListManquante()) {
                sbmanquante.append(manquante.getNumero() + " " + manquante.getTitre() + CRLF);
            }
            Cell cellManquante = row.createCell(indexManquante);
            cellManquante.getCellStyle().setWrapText(true);
            cellManquante.setCellValue(sbmanquante.toString());

            row.createCell(indexUrl).setCellValue(createHelper.createRichTextString(serie.getImageUrl()));
            row.setHeight((short) -1);
            lineIndex++;
        }
        for (int i = 0; i <= 7; i++) {
            sheet.autoSizeColumn(i);
        }
    }
}

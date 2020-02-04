package hello.repository;

import hello.bean.Bd;
import hello.bean.Collection;
import hello.bean.Serie;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

class ExcelExporter {
    private static final String CRLF = System.getProperty("line.separator");

    byte[] exportCollectionToExcel(Collection coll) throws IOException {

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
        int indexIsbn = 8;
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
            row.createCell(indexFini).setCellValue(createHelper.createRichTextString(serie.getListManquante().size()==0 ? "x" : ""));
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

    private void fillBodyWithListing(Workbook wb, CreationHelper createHelper,Collection coll) {
        Sheet sheet = wb.createSheet("Listing");

        int lineIndex = 0;
        // Create a row and put some cells in it. Rows are 0 based.
        Row rowTitles = sheet.createRow((short) lineIndex);

        //Headers
        int indexId = 1;
        int indexSerie = 2;
        int indexEditeur = 3;
        int indexNumeroBd = 4;
        int indexPossede = 5;
        int indexNomBd = 6;
        int indexUrl = 7;
        int indexIsbn = 8;
        rowTitles.createCell(indexId).setCellValue(createHelper.createRichTextString("ID"));
        rowTitles.createCell(indexSerie).setCellValue(createHelper.createRichTextString("Serie"));
        rowTitles.createCell(indexEditeur).setCellValue(createHelper.createRichTextString("Editeur"));
        rowTitles.createCell(indexNumeroBd).setCellValue(createHelper.createRichTextString("Numero"));
        rowTitles.createCell(indexPossede).setCellValue(createHelper.createRichTextString("Possedée"));
        rowTitles.createCell(indexNomBd).setCellValue(createHelper.createRichTextString("Nom"));
        rowTitles.createCell(indexUrl).setCellValue(createHelper.createRichTextString("Url"));
        rowTitles.createCell(indexIsbn).setCellValue(createHelper.createRichTextString("Isbn"));
        lineIndex++;

        for (Serie serie : coll.getListeSerie()) {


            StringBuilder sbPossede = new StringBuilder();
            for (Bd possede : serie.getListPossede()) {
                Row row = sheet.createRow((short) lineIndex);
                row.createCell(indexId).setCellValue(createHelper.createRichTextString(String.valueOf(serie.getId())));
                row.createCell(indexSerie).setCellValue(createHelper.createRichTextString(serie.getNom()));
                row.createCell(indexEditeur).setCellValue(createHelper.createRichTextString(serie.getEditeur()));
                row.createCell(indexNumeroBd).setCellValue(createHelper.createRichTextString(possede.getNumero()));
                row.createCell(indexPossede).setCellValue(createHelper.createRichTextString("X"));
                row.createCell(indexNomBd).setCellValue(createHelper.createRichTextString(possede.getTitre()));
                row.createCell(indexUrl).setCellValue(createHelper.createRichTextString(possede.getCouvertureUrl()));
                row.createCell(indexIsbn).setCellValue(createHelper.createRichTextString(possede.getIsbn()));

               lineIndex++;
            }


            StringBuilder sbmanquante = new StringBuilder();
            for (Bd manquante : serie.getListManquante()) {
                Row row = sheet.createRow((short) lineIndex);
                row.createCell(indexId).setCellValue(createHelper.createRichTextString(String.valueOf(serie.getId())));
                row.createCell(indexSerie).setCellValue(createHelper.createRichTextString(serie.getNom()));
                row.createCell(indexEditeur).setCellValue(createHelper.createRichTextString(serie.getEditeur()));
                row.createCell(indexNumeroBd).setCellValue(createHelper.createRichTextString(manquante.getNumero()));
                row.createCell(indexPossede).setCellValue(createHelper.createRichTextString(""));
                row.createCell(indexNomBd).setCellValue(createHelper.createRichTextString(manquante.getTitre()));
                row.createCell(indexUrl).setCellValue(createHelper.createRichTextString(manquante.getCouvertureUrl()));
                row.createCell(indexIsbn).setCellValue(createHelper.createRichTextString(manquante.getIsbn()));
                lineIndex++;
            }


        }
        for (int i = 0; i <= 7; i++) {
            sheet.autoSizeColumn(i);
        }
    }


    public byte[] exportAllCollectionToExcel(Collection coll) throws IOException {
        Workbook wb = new HSSFWorkbook();
        //Workbook wb = new XSSFWorkbook();
        CreationHelper createHelper = wb.getCreationHelper();

        fillBodyWithListing(wb, createHelper,coll);

        // Write the output to a file
        ByteArrayOutputStream fileOut = new ByteArrayOutputStream();
        wb.write(fileOut);
        fileOut.close();
        return fileOut.toByteArray();
    }
}

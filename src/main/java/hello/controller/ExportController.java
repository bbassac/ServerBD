package hello.controller;

import hello.bean.*;
import org.apache.poi.util.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@RestController
@RequestMapping("/export")
public class ExportController extends AbstractController{

    public static final String CRLF = System.getProperty("line.separator");
    static final String APPLICATION_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET,produces = "application/vnd.ms-excel")
    @ResponseBody
    public byte[] getCollections(HttpServletResponse response) throws IOException {

        Collection coll = customRepo.getCollection();
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
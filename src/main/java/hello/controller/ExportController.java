package hello.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
public class ExportController extends AbstractController{

    @CrossOrigin
    @RequestMapping(path="/export", method = RequestMethod.GET,produces = "application/vnd.ms-excel")
    @ResponseBody
    public byte[] getCollections() throws IOException {

        return customRepo.exportCollectionToExcel();
    }

    @CrossOrigin
    @RequestMapping(path="/listing", method = RequestMethod.GET,produces = "application/vnd.ms-excel")
    @ResponseBody
    public byte[] getAllCollections() throws IOException {

        return customRepo.exportAllCollectionToExcel();
    }


}
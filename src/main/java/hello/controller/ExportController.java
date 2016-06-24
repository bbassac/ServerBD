package hello.controller;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/export")
public class ExportController extends AbstractController{

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET,produces = "application/vnd.ms-excel")
    @ResponseBody
    public byte[] getCollections() throws IOException {

        return customRepo.exportCollectionToExcel();
    }



}
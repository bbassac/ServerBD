package hello.controller;

import hello.bean.SearchBD;
import hello.repository.SearchCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/search")
public class SearchController {

    @Autowired
    SearchCustom searchCustom;


    @CrossOrigin
    @RequestMapping(value = "/isbn/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SearchBD getBookFromIsbn(@PathVariable("id") String id)  {

        return searchCustom.getBookFromIsbn(id);
    }

    @CrossOrigin
    @RequestMapping(value = "/eanisbn/{id}", method = RequestMethod.GET)
    @ResponseBody
    public SearchBD getBookFromEanIsbn(@PathVariable("id") String id)  {

        return searchCustom.getBookFromEanIsbn(id);

    }

}
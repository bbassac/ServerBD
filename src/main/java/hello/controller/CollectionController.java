package hello.controller;

import hello.bean.Collection;
import hello.bean.CollectionBuilder;
import hello.bean.DeleteResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collection")
public class CollectionController extends AbstractController{


    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection getCollections() {

        return customRepo.getCollection();
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public String createCollection() {
        Collection collection = CollectionBuilder.getCollection();
        customRepo.createCollection(collection);
        return "ok";
    }

}
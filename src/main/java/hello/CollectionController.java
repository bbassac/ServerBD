package hello;

import hello.bean.Collection;
import hello.bean.CollectionBuilder;
import hello.repository.CollectionRepositoryCustom;
import hello.repository.CollectionRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CollectionController extends AbstractController{


    @Autowired
    private CollectionRepositoryCustom customRepo;

    @CrossOrigin
    @RequestMapping(value = "/collections",method = RequestMethod.GET)
    @ResponseBody
    public Collection getCollections() {

        return customRepo.getCollection();
    }

    @CrossOrigin
    @RequestMapping(value="/collections",method = RequestMethod.POST)
    @ResponseBody
    public String createCollection() {
        Collection collection = CollectionBuilder.getCollection(false);
        customRepo.createCollection(collection);
        return "ok";
    }



}
package hello;

import hello.bean.Collection;
import hello.bean.CollectionBuilder;
import hello.bean.DeleteResult;
import hello.repository.CollectionRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
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
        Collection collection = CollectionBuilder.getCollection(false);
        customRepo.createCollection(collection);
        return "ok";
    }

    @CrossOrigin
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public DeleteResult deleteCollection() {
        DeleteResult result = customRepo.deleteCollection();
        return result;
    }


}
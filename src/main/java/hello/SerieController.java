package hello;

import hello.bean.BdManquante;
import hello.bean.Serie;
import hello.repository.CollectionRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/series")
public class SerieController extends AbstractController{

    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Serie> getSeries() {

        return customRepo.getAllSeries();
    }

    @CrossOrigin
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Serie getSerie(@PathVariable("id") Long id) {

        return customRepo.getSerieFromId(id);
    }

}
package hello.controller;

import hello.bean.Bd;
import hello.bean.BdManquante;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bds")
public class BdController extends AbstractController{



    @CrossOrigin
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public List<Bd> getBds() {

        return customRepo.getAllBd();
    }

    @CrossOrigin
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Bd getBd(@PathVariable("id") Long id) {

        return customRepo.getBdFromId(id);
    }


    @CrossOrigin
    @RequestMapping(value="/manquantes")
    @ResponseBody
    public List<BdManquante> getManquantes() {

        return customRepo.getAllBdManquantes();
    }



}
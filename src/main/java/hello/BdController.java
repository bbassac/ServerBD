package hello;

import hello.bean.Bd;
import hello.bean.BdManquante;
import hello.repository.CollectionRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BdController extends AbstractController{

    @Autowired
    private CollectionRepositoryCustom customRepo;

    @CrossOrigin
    @RequestMapping("/bds")
    @ResponseBody
    public List<Bd> getBds() {

        return customRepo.getAllBd();
    }

    @CrossOrigin
    @RequestMapping("/bds/manquantes")
    @ResponseBody
    public List<BdManquante> getManquantes() {

        return customRepo.getAllBdManquantes();
    }



}
package hello.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/switch")
public class SwitchController extends AbstractController{



    @CrossOrigin
    @RequestMapping(value = "{id}",method = RequestMethod.GET)
    @ResponseBody
    public Long getSerie(@PathVariable("id") Long id) {

        return customRepo.switchBDAsPossede(id);
    }

}
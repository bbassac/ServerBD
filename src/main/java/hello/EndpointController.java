package hello;

import hello.bean.Bd;
import hello.repository.IBdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class EndpointController {

    @Autowired
    private IBdRepository repo;

    /**
     * GET /get-by-email  --> Return the id for the user having the passed
     * email.
     */
    @RequestMapping("/bd")
    @ResponseBody
    public String getByEmail() {

        return "Hello";
    }

    @RequestMapping("/bds")
    @ResponseBody
    public List<Bd> getByTitre(String titre) {

        return toList(repo.findAll());
    }

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}
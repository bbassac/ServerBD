package hello;

import hello.repository.CollectionRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Created by b.bassac on 24/05/2016.
 */
public class AbstractController {

    @Autowired
    CollectionRepositoryCustom customRepo;

    public static <T> List<T> toList(final Iterable<T> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
    }
}

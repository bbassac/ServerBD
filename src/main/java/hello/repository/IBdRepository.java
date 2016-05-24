package hello.repository;

import hello.bean.Bd;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by b.bassac on 24/05/2016.
 */
public interface IBdRepository extends CrudRepository<Bd, Long> {

    List<Bd> findByTitre(String titre);

}
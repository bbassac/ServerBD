package hello.bean;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


import java.util.ArrayList;
import java.util.List;


@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Collection {

    private Long id;
    @JsonManagedReference
    private List<Serie> listeSerie;

    public Collection() {
        listeSerie = new ArrayList<>(20);
    }


    public List<Serie> getListeSerie() {
        return listeSerie;
    }

    public void setListeSerie(List<Serie> listeSerie) {
        this.listeSerie = listeSerie;
    }


    public void addSerie(Serie serie) {
        serie.setCollection(this);
        listeSerie.add(serie);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

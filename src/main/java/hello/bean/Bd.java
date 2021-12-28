package hello.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;




/**
 * Created by b.bassac on 12/01/2015.
 */

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Bd implements Comparable {

    private Long id;

    private String titre;

    private String numero;

    private String couvertureUrl;

    private String isbn;

    @JsonBackReference
    private Serie serie;

    public Bd() {
    }

    public Bd(Long id, String numero, String titre, String url) {
        this.id = id;
        this.numero = numero;
        this.titre = titre;
        this.couvertureUrl = url;
    }

    public Bd(Long id, String numero, String titre,String isbn, String url) {
        this.id = id;
        this.numero = numero;
        this.titre = titre;
        this.couvertureUrl = url;
        this.isbn = isbn;
    }

    public String getCouvertureUrl() {
        return couvertureUrl;
    }

    public void setCouvertureUrl(String couvertureUrl) {
        this.couvertureUrl = couvertureUrl;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public int compareTo(Object o) {
        return this.getId().compareTo(((Bd) o).getId());
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}

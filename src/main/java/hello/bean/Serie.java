package hello.bean;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SERIE")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Serie {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOM")
    private String nom;
    @Column(name = "FINI")
    private boolean fini = false;
    @Column(name = "IMAGE_URL")
    private String imageUrl;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    @JsonManagedReference
    private List<Bd> listPossede;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(nullable = true)
    @JsonManagedReference
    private List<Bd> listManquante;
    @Column(name = "EDITEUR")
    private String editeur;

    @ManyToOne
    @JsonBackReference
    private Collection collection;

    // Must have no-argument constructor
    public Serie() {

    }

    public Serie(String nom) {
        this.nom = nom;
        this.id = CollectionBuilder.next(CollectionBuilder.withId);
        listPossede = new ArrayList<>();
        listManquante = new ArrayList<>();
    }

    public List<Bd> getListPossede() {
        return listPossede;
    }

    public void setListPossede(ArrayList<Bd> listPossede) {
        this.listPossede = listPossede;
    }

    public List<Bd> getListManquante() {
        return listManquante;
    }

    public void setListManquante(ArrayList<Bd> listManquante) {
        this.listManquante = listManquante;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Serie addPossede( String numero, String nom, String urlVignette){

        Bd bd = new Bd(CollectionBuilder.next(CollectionBuilder.withId), numero, nom, urlVignette);
        this.listPossede.add(bd);
        bd.setSerie(this);
        return this;
    }

    public Serie addManquante(String numero, String nom, String urlVignette){
        Bd bd = new Bd(CollectionBuilder.next(CollectionBuilder.withId), numero, nom, urlVignette);
        this.listManquante.add(bd);
        bd.setSerie(this);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isFini() {
        return fini;
    }

    public void setFini(boolean fini) {
        this.fini = fini;
    }

    public Serie withImageUrl(String url) {
        this.setImageUrl(url);
        return this;
    }

    public Serie withEditeur(String editeur) {
        this.setEditeur(editeur);
        return this;
    }

    public Serie withFini(boolean fini) {
        this.setFini(fini);
        return this;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    public Collection getCollection() {
        return collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }
}

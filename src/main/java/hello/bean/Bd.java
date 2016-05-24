package hello.bean;

import javax.persistence.*;

/**
 * Created by b.bassac on 12/01/2015.
 */
@Entity
@Table(name = "BD")
public class Bd {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "TITRE")
    private String titre;
    @Column(name = "NUMERO")
    private String numero;
    @Column(name = "COUVERTURE_URL")
    private String couvertureUrl;

    public Bd() {
    }

    public Bd(Long id, String numero, String titre, String url) {
        this.id = id;
        this.numero = numero;
        this.titre = titre;
        this.couvertureUrl = url;
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

}

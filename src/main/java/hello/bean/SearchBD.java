package hello.bean;

public class SearchBD {

    private String serie;
    private String tome;
    private String Titre;
    private String numero;
    private String isbn;
    private String auteur;
    private String notes;
    public String getAnneePublication() {
        return anneePublication;
    }

    public void setAnneePublication(String anneePublication) {
        this.anneePublication = anneePublication;
    }

    private String anneePublication;

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteurN) {
        this.auteur = auteurN;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getTome() {
        return tome;
    }

    public void setTome(String tome) {
        this.tome = tome;
    }

    public String getTitre() {
        return Titre;
    }

    public void setTitre(String titre) {
        Titre = titre;
    }

    public String getEditeur() {
        return editeur;
    }

    public void setEditeur(String editeur) {
        this.editeur = editeur;
    }

    private String editeur;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}

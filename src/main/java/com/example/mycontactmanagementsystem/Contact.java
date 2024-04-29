package com.example.mycontactmanagementsystem;

public class Contact {
    private Integer IDcontact;
    private String nom;
    private String prenom;
    private String Telephone1;
    private String Telephone2;
    private String Adresse;
    private String EmailPersonnel;
    private String EmailProfessionel;
    private String Genre;
    private Integer idGroup;

    public Contact(Integer IDcontact, String nom, String prenom, String Telephone1, String Telephone2, String Adresse, String EmailPersonnel, String EmailProfessionel, String Genre, Integer idGroup) {
        this.IDcontact = IDcontact;
        this.nom = nom;
        this.prenom = prenom;
        this.Telephone1 = Telephone1;
        this.Telephone2 = Telephone2;
        this.Adresse = Adresse;
        this.EmailPersonnel = EmailPersonnel;
        this.EmailProfessionel = EmailProfessionel;
        this.Genre = Genre;
        this.idGroup = idGroup;
    }

    public Contact(Integer IDcontact, String nom, String prenom, String Adresse, String Genre, Integer idGroup) {
        this.IDcontact = IDcontact;
        this.nom = nom;
        this.prenom = prenom;
        this.Adresse = Adresse;
        this.Genre = Genre;
        this.idGroup = idGroup;
    }

    public Integer getIDcontact() {
        return this.IDcontact;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getTelephone1() {
        return this.Telephone1;
    }

    public String getTelephone2() {
        return this.Telephone2;
    }

    public String getAdresse() {
        return this.Adresse;
    }

    public String getEmailPersonnel() {
        return this.EmailPersonnel;
    }

    public String getEmailProfessionel() {
        return this.EmailProfessionel;
    }

    public String getGenre() {
        return this.Genre;
    }

    public Integer getIdGroup() {
        return this.idGroup;
    }
}

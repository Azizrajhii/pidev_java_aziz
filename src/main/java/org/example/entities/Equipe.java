package org.example.entities;

public class Equipe {
    private int id;
    private String nom;
    private int maxMembers;
    private String logo;
    private int ownerId;

    public Equipe() {
    }

    public Equipe(int id, String nom, int maxMembers, String logo, int ownerId) {
        this.id = id;
        this.nom = nom;
        this.maxMembers = maxMembers;
        this.logo = logo;
        this.ownerId = ownerId;
    }

    public Equipe(String nom, int maxMembers, String logo, int ownerId) {
        this.nom = nom;
        this.maxMembers = maxMembers;
        this.logo = logo;
        this.ownerId = ownerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Equipe{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", maxMembers=" + maxMembers +
                ", ownerId=" + ownerId +
                '}';
    }
}

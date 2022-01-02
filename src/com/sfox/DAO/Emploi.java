package com.sfox.DAO;

import java.sql.Date;

public class Emploi {
        private String titre;
        private String societe;
        private String description;
        private String ville;
        private Date datePublication;
        private Date dateFin;
        private String secteurActivite;
        private String fonction;
        private String experience;
        private String niveauEtude;
        private String typeContrat;


        public String getTitre() {
                return titre;
        }

        public void setTitre(String titre) {
                this.titre = titre;
        }

        public String getSociete() {
                return societe;
        }

        public void setSociete(String societe) {
                this.societe = societe;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getVille() {
                return ville;
        }

        public void setVille(String ville) {
                this.ville = ville;
        }

        public Date getDatePublication() {
                return datePublication;
        }

        public void setDatePublication(Date datePublication) {
                this.datePublication = datePublication;
        }

        public Date getDateFin() {
                return dateFin;
        }

        public void setDateFin(Date dateFin) {
                this.dateFin = dateFin;
        }

        public String getSecteurActivite() {
                return secteurActivite;
        }

        public void setSecteurActivite(String secteurActivite) {
                this.secteurActivite = secteurActivite;
        }

        public String getFonction() {
                return fonction;
        }

        public void setFonction(String fonction) {
                this.fonction = fonction;
        }

        public String getExperience() {
                return experience;
        }

        public void setExperience(String experience) {
                this.experience = experience;
        }

        public String getNiveauEtude() {
                return niveauEtude;
        }

        public void setNiveauEtude(String niveauEtude) {
                this.niveauEtude = niveauEtude;
        }

        public String getTypeContrat() {
                return typeContrat;
        }

        public void setTypeContrat(String typeContrat) {
                this.typeContrat = typeContrat;
        }

        @Override
        public String toString() {
                return "Emploi{" +
                        "titre='" + titre + '\'' +
                        ", description='" + description + '\'' +
                        ", ville='" + ville + '\'' +
                        ", datePublication='" + datePublication + '\'' +
                        ", dateFin='" + dateFin + '\'' +
                        ", secteurActivite='" + secteurActivite + '\'' +
                        ", fonction='" + fonction + '\'' +
                        ", experience='" + experience + '\'' +
                        ", niveauEtude='" + niveauEtude + '\'' +
                        ", typeContrat='" + typeContrat + '\'' +
                        '}';
        }

        public void Afficher(){
                System.out.println(this.toString());
        }
}

package com.sfox.Transform;

import com.sfox.DAO.Emploi;
import com.sfox.Load.Loader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.Connection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Transformer {

    public void toDb(ArrayList<Emploi> emplois){
        Loader load= new Loader();
        Connection con =  load.connectDB();
        for(Emploi emp : emplois){
            load.insertEmploi(con,emp);
        }
    }

    public String getTitre(String h2){
        int index = h2.trim().indexOf("|");
        String titre = h2.substring(0,index).trim().replace("-", " ").replace(" ","_").replace("(","").replace(")","").trim();
        return titre;
    }

    public String getSociete(Element el){
        Element societeel = el.getElementsByClass("photo").get(0);
        String societe = societeel.attr("alt").trim().replace("et ","").replace(" ","_").trim();
       return societe;
    }
    public String getVille(String h2){
        int index = h2.trim().indexOf("|");
        String titre = h2.substring(0,index);
        String ville = h2.trim().replace(titre+"|" ,"").replace("(Maroc)","").replace(" et région","").replace(" (Technopolis)","").trim().toUpperCase() ;
            if(ville.equals("CASABLANCA - MAÂRIF")){
                ville=ville.replace(" - MAÂRIF","").trim();
            }
            if(ville.contains("CDI |")){
                ville=ville.replace("CDI |", "").trim();
            }
            if(ville.equals("RABAT / SALÉ TECHNOPOLIS")){
                ville = ville.replace(" / ","_").replace("TECHNOPOLIS","").trim();
            }
            if(ville.contains("/")){
                   ville=ville.replace("/","_").replace(" ","").trim();
               }
            if(ville.equals("RABAT TECHNOPOLIS")){
                ville = ville.replace("TECHNOPOLIS","").trim();
            }
            if(ville.equals("RABAT-TECHNOPOLIS")){
                ville = ville.replace("-TECHNOPOLIS","").trim();
            }
            if(ville.equals("SALÉ TECHNOPOLIS")){
                ville = ville.replace("TECHNOPOLIS","").trim();
            }
            if(ville.equals("TECHNOPOLIS - SALÉ")){
                ville = ville.replace("TECHNOPOLIS - ","").trim();
            }
            if(ville.equals("SALÉ, TECHNOPOLIS")){
                ville = ville.replace("SALÉ, TECHNOPOLIS","SALÉ").trim();
            }
            if(ville.contains("FRANCE")){
                ville = ville.replace(ville,"FRANCE").trim();
            }
            if(ville.equals("TECHNOPOLIS, SALA AL JADIDA")){
                ville = ville.replace("TECHNOPOLIS, SALA AL JADIDA","SALA AL JADIDA").trim();
            }
            if(ville.equals("CASABLANCA - BOUSKOURA")){
                ville = ville.replace("CASABLANCA - BOUSKOURA","CASABLANCA").trim();
            }
            if(ville.equals("TECHNOPOLIS, SALÉ")){
                ville=ville.replace("TECHNOPOLIS, ","").trim();
            }
            if(ville.equals("TECHNOPOLIS - CASANEARSHORE")){
                ville=ville.replace(" - CASANEARSHORE","").trim();
            }
            if(ville.equals("BEN GUERIR")){
                ville=ville.replace("BEN GUERIR","BENGUERIR").trim();
            }
            if(ville.equals("SALÉ - TECHNOPOLIS")){
                ville = ville.replace("SALÉ - TECHNOPOLIS","SALÉ").trim();
            }
            if(ville.contains("MARRAKECH")){
                ville="MARRAKECH";
            }
            if(ville.contains(" - ") ){
                ville=ville.replace(" - ","_").trim();
            }
            if(ville.contains("-") ){
                ville=ville.replace("-","_").trim();
            }
            if(ville.equals("MAROC")){
                ville = ville.replace("MAROC","TOUT LE MAROC");
            }

        return ville;
    }

    public String getSecteurAct(Element el ){
        String secteur = el.child(0).getElementsByTag("a").text().replace(" / ", "_").trim().replace(" " , "_");
        return secteur;
    }

    public String getFonction(Element el){
        String fonction = el.child(1).getElementsByTag("a").text().replace(" / ", "_").trim();
        return fonction;
    }
    public String getExperience(Element el){
        String experience = el.child(2).getElementsByTag("a").text().replace(" à ","_").replace("De ","").replace(" ans","").replace("Moins de 1 an", "Moins_1").trim();
        return experience;
    }
    public String getNiveau(Element el){
        String niveau = el.child(3).getElementsByTag("a").text().replace("et plus","").trim();
        return niveau;
    }

    public String getTypeContrat(Element el){
        String contrat = el.child(4).getElementsByTag("a").text().trim();
        return contrat;
    }

    public java.sql.Date getDatePub(Element el) throws ParseException {
        Element date= el.getElementsByClass("date").get(0);
        String datePub =date.child(1).text().replace("/","-");;
        SimpleDateFormat formatter1=new SimpleDateFormat("dd-mm-yyyy");
        Date publieDate = formatter1.parse(datePub);
        java.sql.Date datPub = new java.sql.Date(publieDate.getTime());
        return datPub;
    }
    public java.sql.Date getDateFin(Element el) throws ParseException {
        Element date= el.getElementsByClass("date").get(0);
        String dateFin = date.child(2).text().replace("/","-");
        SimpleDateFormat formatter1=new SimpleDateFormat("dd-mm-yyyy");
        Date publieDate = formatter1.parse(dateFin);
        java.sql.Date datFin = new java.sql.Date(publieDate.getTime());
        return datFin;
    }

    public String getDescription(Element el){
        Element descriptionDiv = el.getElementsByClass("info").get(1);
        String description = descriptionDiv.child(1).text().trim();
        return description;
    }

}

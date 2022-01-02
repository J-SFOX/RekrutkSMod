package com.sfox.Extract;

import com.sfox.DAO.Emploi;
import com.sfox.Transform.Transformer;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.ElementType;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;

public class Scraper {
/*
    li class=post-id
        div container wo class
            div contains company logo
            div class=col-sm-2 col-xs-12
                div class= section
                    h2
                      ->  a contains job title '|' ville + link to the job page
                    a dooesnt help
                    div class=holder
                        div class=info
                            span -> job description
                        em class = date
                            de :
                            span date pub
                            au
                            span date fin
                                |
                             numero de poste proposee : num
                        div class=info
                            ul
                                li a first link+  secteur d'activite :
                                li a first link + fonction
                                li a Experience
                                li a niveau d'etude
                                li a type de contrat
 */

    public Document Scrap(String path){
        try{
            Document doc = Jsoup.connect(path).get();
            return doc;
        }catch(IOException ioe){
            System.out.println("IO ERROR :" + ioe.getMessage());
            ioe.printStackTrace();
            return null;
        }
    }

    public Elements getJobPost(Document doc){
            try{
                Elements repositories = doc.getElementsByClass("post-id");
                return repositories;
            }catch(Exception ex ){
                System.out.println("GET JOB ERROR "+  ex.getMessage());
                ex.printStackTrace();
                return null;
            }
    }

    public int getNbrPage(Document doc){
        try{
            String tst = doc.getElementsByClass("jobs").text();
            int indexb = tst.indexOf("Page");
            int indexf = tst.indexOf("sur");
            String removed = (String) tst.subSequence(indexb,indexf+3);
            String rm= tst.replace(removed," ");
            int lent = rm.length();
            String nbrs = rm.substring(lent/2, lent).trim();
            int nbr = Integer.parseInt(nbrs);
            return nbr;
        }catch(Exception e){
            System.out.println("GET NBR Page Error : "+ e.getMessage());
            return -1 ;
        }
    }

    public ArrayList<Emploi> getDataFromPost(Elements els){
        try{
            ArrayList<Emploi> data = new ArrayList<>();
            for (Element el : els){
                Emploi emp = new Emploi();
                getInfoinEmploi(el,emp);
                data.add(emp);
            }
            return data;
        }catch(Exception ex){
            System.out.println("GET DATA ERROR :" +ex.getMessage());
            return null;
        }
    }

    public void getInfoinEmploi(Element el , Emploi emp) throws ParseException {
        Element infoEle = el.getElementsByTag("ul").get(0);
        String H2 = getH2(el);
        Transformer tr =new Transformer();
        emp.setTitre(tr.getTitre(H2));
        emp.setVille(tr.getVille(H2));
        emp.setSociete(tr.getSociete(el));
        emp.setDatePublication(tr.getDatePub(el));
        emp.setDateFin(tr.getDateFin(el));
        emp.setDescription(tr.getDescription(el));
        emp.setSecteurActivite(tr.getSecteurAct(infoEle));
        emp.setFonction(tr.getFonction(infoEle));
        emp.setExperience(tr.getExperience(infoEle));
        emp.setNiveauEtude(tr.getNiveau(infoEle));
        emp.setTypeContrat(tr.getTypeContrat(infoEle));
    }


    public String getH2(Element el ){
        String h2 = el.getElementsByTag("h2").text();
        return h2;
    }

}

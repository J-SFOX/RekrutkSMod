package com.sfox.ModeL;

import com.sfox.Load.Loader;
import weka.core.Instances;
import weka.experiment.InstanceQuery;

import javax.swing.*;
import javax.xml.transform.Result;
import java.sql.Array;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DATALoader {

    public Instances retrieveData(String table){
       try{
           InstanceQuery query;
           query = new InstanceQuery();
           query.setDatabaseURL("jdbc:mysql://localhost:3306/rekrutDB");
           query.setUsername("root");
           query.setPassword("");
           query.setQuery("select * from "+table);
           Instances data = query.retrieveInstances();
//           data.setClassIndex(data.numAttributes() - 1);
//           System.out.println(data.numInstances() + " instances loaded.");
           return data;
//           System.out.println(data);
       }catch(Exception ex){
           ex.printStackTrace();
           return null;
       }
    }
    public void fillOutTest(){
        Loader load= new Loader();
        Connection con = load.connectDB();
        int delete = load.deleteAll(con,"Test");
        if(delete == 1){
            System.out.println("TEst FilledOut correctly");
        }
    }
    public void fillTest(){
        Loader load= new Loader();
        Connection con = load.connectDB();
        int insert = load.insertTest(con);
        if(insert==1){
            System.out.println("TEst Filled correctly");
        }

    }


    public DefaultComboBoxModel getVilleList(){
       try{
           ArrayList<String> villes = new ArrayList<>();
           Loader load = new Loader();
           Connection con = load.connectDB();
           ResultSet rs = load.specSelect(con , "ville");
           while(rs.next()){
               villes.add(rs.getString("ville"));
           }
           DefaultComboBoxModel model;
           model = new DefaultComboBoxModel(villes.toArray());

           return model;
       }catch(SQLException sEx){
           System.out.println(sEx.getMessage());
           return null;
       }
        //        return villes;
     }
    public DefaultComboBoxModel getTitreList()  {
        try{
            ArrayList<String> titres = new ArrayList<>();
            Loader load = new Loader();
            Connection con = load.connectDB();
            ResultSet rs = load.specSelect(con , "titre");
            while(rs.next()){
                titres.add(rs.getString("titre"));
            }
            DefaultComboBoxModel model;
            model = new DefaultComboBoxModel(titres.toArray());
            return model;
        }catch(SQLException sEx){
            System.out.println(sEx.getMessage());
            return null;
        }

    }

    public DefaultComboBoxModel getSocieteList()  {
        try{
            ArrayList<String> societes = new ArrayList<>();
            Loader load = new Loader();
            Connection con = load.connectDB();
            ResultSet rs = load.specSelect(con , "societe");
            while(rs.next()){
                societes.add(rs.getString("societe"));
            }
            DefaultComboBoxModel model;
            model = new DefaultComboBoxModel(societes.toArray());
            return model;
        }catch(SQLException sEx){
            System.out.println(sEx.getMessage());
            return null;
        }

    }

     public DefaultComboBoxModel getFonctionList(){
         try{
             ArrayList<String> fonctions = new ArrayList<>();
             Loader load = new Loader();
             Connection con = load.connectDB();
             ResultSet rs = load.specSelect(con , "fonction");
             while(rs.next()){
                 fonctions.add(rs.getString("fonction"));
             }
             DefaultComboBoxModel model;
             model = new DefaultComboBoxModel(fonctions.toArray());
             return model;
         }catch(SQLException sEx){
             System.out.println(sEx.getMessage());
             return null;
         }
     }

    public DefaultComboBoxModel getExperienceList(){
        try{
            ArrayList<String> exper = new ArrayList<>();
            Loader load = new Loader();
            Connection con = load.connectDB();
            ResultSet rs = load.specSelect(con , "experience");
            while(rs.next()){
                exper.add(rs.getString("experience"));
            }
            DefaultComboBoxModel model;
            model = new DefaultComboBoxModel(exper.toArray());
            return model;
        }catch(SQLException sEx){
            System.out.println(sEx.getMessage());
            return null;
        }
    }

    public DefaultComboBoxModel getNivEtudeList(){
        try{
            ArrayList<String> nivEtuds = new ArrayList<>();
            Loader load = new Loader();
            Connection con = load.connectDB();
            ResultSet rs = load.specSelect(con , "niveauetude");
            while(rs.next()){
                nivEtuds.add(rs.getString("niveauetude"));
            }
            DefaultComboBoxModel model;
            model = new DefaultComboBoxModel(nivEtuds.toArray());
            return model;
        }catch(SQLException sEx){
            System.out.println(sEx.getMessage());
            return null;
        }
    }

    public DefaultComboBoxModel getSecteurList(){
        try{
            ArrayList<String> secteurs = new ArrayList<>();
            Loader load = new Loader();
            Connection con = load.connectDB();
            ResultSet rs = load.specSelect(con , "secteur");
            while(rs.next()){
                secteurs.add(rs.getString("secteur"));
            }
            DefaultComboBoxModel model;
            model = new DefaultComboBoxModel(secteurs.toArray());
            return model;
        }catch(SQLException sEx){
            System.out.println(sEx.getMessage());
            return null;
        }
    }

    public DefaultComboBoxModel getTypeContratList(){
        try{
            ArrayList<String> typecont = new ArrayList<>();
            Loader load = new Loader();
            Connection con = load.connectDB();
            ResultSet rs = load.specSelect(con , "typecontrat");
            while(rs.next()){
                typecont.add(rs.getString("typecontrat"));
            }
            DefaultComboBoxModel model;
            model = new DefaultComboBoxModel(typecont.toArray());
            return model;
        }catch(SQLException sEx){
            System.out.println(sEx.getMessage());
            return null;
        }
    }
}

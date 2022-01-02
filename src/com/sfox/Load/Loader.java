package com.sfox.Load;

import com.sfox.DAO.Emploi;

import java.sql.*;

public class Loader {

    public Connection connectDB(){
        try{
            String url = "jdbc:mysql://localhost:3306/rekrutDB";
            String user = "root";
            String pwd = "";
            Connection con = DriverManager.getConnection(url, user, pwd);
            System.out.println("DATABASE Connected Successfuly\n\n\n");
            return con;
        }catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultSet Selection (Connection con){
        try{
            String sqlSelect = "SELECT * FROM Emploi";
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery(sqlSelect);
            return rs;
        }catch(Exception e){
            System.out.println("Selection ERO " + e.getMessage());
            return null;
        }
    }

    public ResultSet specSelect(Connection con , String field){
        try{
            String sqlSelectt = "SELECT DISTINCT "+field+" FROM Emploi;";
            Statement smt = con.createStatement();
            ResultSet rs = smt.executeQuery(sqlSelectt);
            return rs;
        }catch(SQLException sEx){
            System.out.println(" Spec Select ERO : " + sEx.getMessage());
            return null;
        }
    }


    public int insertEmploi(Connection con, Emploi emp){
        try{
            String sqlInsert = "INSERT INTO Emploi (titre,societe,description,ville,datepublication,datefin,secteur,fonction,experience,niveauetude,typecontrat) VALUES (\""+emp.getTitre()+"\",\""+emp.getSociete()+"\",\""+emp.getDescription()+"\",\""+emp.getVille()+"\",'"+emp.getDatePublication()+"','"+emp.getDateFin()+"',\""+emp.getSecteurActivite()+"\",\""+emp.getFonction()+"\",\""+emp.getExperience()+"\",\""+emp.getNiveauEtude()+"\",\""+emp.getTypeContrat()+"\");";
            Statement smt =con.createStatement();
            int X = smt.executeUpdate(sqlInsert);
            return X;
        }catch(SQLException sEx){
            System.out.println("Insertion ERO : " + sEx.getMessage());
            return -1;
        }
    }


    public int deleteAll( Connection con, String table){
        try {
            String sqlDel = "DELETE FROM "+table+" WHERE 1;";
            String sqlResetIncrement ="ALTER TABLE "+table+" AUTO_INCREMENT = 1";
            Statement smt = con.createStatement();
            int x = smt.executeUpdate(sqlDel);
            Statement smtt = con.createStatement();
            smtt.executeUpdate(sqlResetIncrement);
            return x;
        }catch (SQLException sEx){
            System.out.println("DELETE ERO : " +sEx.getMessage());
            return -1;
        }
    }


    public int insertTest(Connection con){
        try{
            String sqlInsert = "INSERT INTO Test SELECT secteur,ville,niveauetude,experience,fonction,societe,typecontrat  FROM Emploi WHERE societe != '';";
            Statement smt =con.createStatement();
            int X = smt.executeUpdate(sqlInsert);
            return X;
        }catch(SQLException sEx){
            System.out.println("TEST Insertion ERO : " + sEx.getMessage());
            return -1;
        }
    }



}

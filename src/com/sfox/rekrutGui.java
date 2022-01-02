package com.sfox;

import com.sfox.DAO.Emploi;
import com.sfox.Extract.Scraper;
import com.sfox.Load.Loader;
import com.sfox.ModeL.DATALoader;
import com.sfox.Transform.Transformer;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class rekrutGui extends JFrame{
    private JTextField linkField;
    private JTextField keywordField;
    private JButton scraperButton;
    private JButton deleteButton;
    private JTable showTable;
    private JPanel panell;
    private JButton predictionButton;

    public rekrutGui() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setContentPane(panell);
            this.setSize(1500,700);
//            this.pack();
        Loader load = new Loader();
        Connection con = load.connectDB();
        updateTable(con,load);
        try{
            con.close();
        }catch(SQLException sEx){
            System.out.println("SQL CLOSE ERROR " + sEx.getMessage());
        }
        scraperButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader load =new Loader();
                Connection con = load.connectDB();
                String keyword = keywordField.getText();
                String path = "https://www.rekrute.com/offres.html?s=2&p=1&o=1&query="+keyword+"&keyword="+keyword+"&st=d";
                Scraper scraper = new Scraper();
                Document doc = scraper.Scrap(path);
                Transformer transform = new Transformer();
                for(int i=0 ; i <scraper.getNbrPage(doc) ;i++){
                    String paths = "https://www.rekrute.com/offres.html?s=2&p="+i+"&o=1&query="+keyword+"&keyword="+keyword+"&st=d";
                    Document docs = scraper.Scrap(paths);
                    Elements els = scraper.getJobPost(docs);
                    ArrayList<Emploi> emps= scraper.getDataFromPost(els);
                    transform.toDb(emps);
                }
                updateTable(con,load);
                try{
                    con.close();
                }catch(SQLException sEx){
                    System.out.println("SQL CLOSE scrap ERROR : "+sEx.getMessage());
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Loader load =new Loader();
                Connection con = load.connectDB();
                load.deleteAll(con,"Emploi");
                updateTable(con,load);
                try{
                    con.close();
                }catch(SQLException sEx){
                    System.out.println("SQL Delete CLOSE ERROR "+ sEx.getMessage());
                }
            }
        });
        predictionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DATALoader dload =new DATALoader();
                dload.fillTest();
                PredictionGUI pred = new PredictionGUI("Prediciton Panel");
                pred.setVisible(true);
                dispose();
            }
        });
    }


    public void updateTable (Connection con , Loader db){
        ResultSet rs = db.Selection(con);
        try {
            DefaultTableModel model = new DefaultTableModel(null, new String[]{"ID", "Titre"," Societe", "Description", "ville","datepub","datefin",  "secteur", "fonction","exper","netude", "Tcontrat"});
            while(rs.next()){
                model.addRow(new Object[]{rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12)});
            }
            showTable.setModel(model);


        }catch (SQLException sex){
            System.out.println("SQL ERROR : " +sex.getMessage());
        }

    }
}

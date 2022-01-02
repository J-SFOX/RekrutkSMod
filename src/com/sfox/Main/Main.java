package com.sfox.Main;

import com.sfox.DAO.Emploi;
import com.sfox.Extract.Scraper;
import com.sfox.Load.Loader;
import com.sfox.ModeL.DATALoader;
import com.sfox.PredictionGUI;
import com.sfox.Transform.Transformer;
import com.sfox.rekrutGui;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main extends JFrame {
    public static void main(String[] args) throws SQLException {
        JFrame mainDirect = new rekrutGui();
        mainDirect.setVisible(true);
    }
}

//variabl quantitaif => regression 

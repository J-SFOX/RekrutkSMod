package com.sfox.ModeL;
/**
 * Chapter 3: Classification Task
 *
 * This example shows how to load the data, select features, implement a basic classifier in Weka,
 * and evaluate classifier performance. This task uses the ZOO dataset.
 *
 * @author Bostjan Kaluza, http://bostjankaluza.net
 */

import java.util.Random;

import javax.swing.JFrame;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.classifiers.evaluation.ThresholdCurve;
import weka.classifiers.trees.J48;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;
import weka.gui.visualize.PlotData2D;
import weka.gui.visualize.ThresholdVisualizePanel;
import weka.attributeSelection.AttributeSelection;
import weka.attributeSelection.InfoGainAttributeEval;
import weka.attributeSelection.Ranker;

public class ClassificationTask {
    public static Instances removeAttribut(Instances data, String x) throws Exception {
        String[] opts = new String[] { "-R", x };
        Remove remove = new Remove();
        remove.setOptions(opts);
        remove.setInputFormat(data);
        data = Filter.useFilter(data, remove);
        return data;
    }

    public J48 classify (Instances data) throws Exception {
        String[] options = new String[1];
        options[0] = "-U";
        J48 tree = new J48();
        tree.setOptions(options);
        tree.buildClassifier(data);
        return tree;
    }
    public void visualizeTree(J48 tree) throws Exception {
        TreeVisualizer tv = new TreeVisualizer(null, tree.graph(),
                new PlaceNode2());
        JFrame frame = new javax.swing.JFrame("Tree Visualizer");
        frame.setSize(1600, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(tv);
        frame.setVisible(true);
        tv.fitToScreen();
    }

    public void evaluate(Instances data) throws Exception {
        Classifier cl = new J48();
        Evaluation eval_roc = new Evaluation(data);
        eval_roc.crossValidateModel(cl, data, 10, new Random(1), new Object[] {});
        System.out.println("evall summaty string FKKK\n"+eval_roc.toSummaryString());

        // Confusion matrix
        System.out.println(eval_roc.toMatrixString());
    }

}


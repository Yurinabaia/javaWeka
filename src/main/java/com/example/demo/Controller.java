package com.example.demo;


import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.*;
import weka.gui.treevisualizer.PlaceNode2;
import weka.gui.treevisualizer.TreeVisualizer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private  Window stage;
    @FXML
    private  TextArea textDados;
    @FXML
    private  Label labelArvore;
    @FXML
    private Label labelMatrizConfusao;
    @FXML
    private  TextArea labelImprimirArvore;
    @FXML
    private TextArea matrix;
    @FXML
    private Button arvDecisao;
    @FXML
    private Button buttonNaive;
    @FXML
    private Button buttonJ48;
    @FXML
    private Button buttonIBK;
    @FXML
    private Button buttonZeroR;
    @FXML
    private Button buttonKStar;
    @FXML
    private Button buttonLMT;
    @FXML
    private Button buttonLWL;
    @FXML
    private Button buttonREPTree;













    SwingNode sw = new SwingNode();







    public void limparDados ()
    {
        labelImprimirArvore.setText("");
        textDados.setText("");
        labelImprimirArvore.setVisible(false);
        textDados.setVisible(false);
        labelArvore.setVisible(false);
        matrix.setVisible(false);
        arvDecisao.setVisible(false);

    }
    //Algoritmos Lazy
    //  public void pressBayse  (ActionEvent event) == botão
     public void pressBayse ()
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);

           AlgoritmoBayesiano algBasin = new AlgoritmoBayesiano(arq.getDados(), arq.getQuantidadeDeAtributos());

            try {
                limparDados();
                textDados.setVisible(true);
                matrix.setVisible(true);
                labelMatrizConfusao.setVisible(true);

                algBasin.lazyBaysiano();
                matrix.setText(algBasin.getEvDados().toMatrixString(""));

                String predicao = algBasin.getPredicao() != 0.0 ? "Predição:" + algBasin.getPredicao() : "";
                textDados.setText(
                        "Atributos do data set " + algBasin.getEvDados().getHeader()
                                + "\n" +algBasin.getInfoBayer().toString() + "\n" + predicao +
                                "\n" + algBasin.getEvDados().toSummaryString()
                                + "\n--> Total de instancia " + algBasin.getEvDados().numInstances()
                                + "\n--> Instancias corretas: " + algBasin.getEvDados().correct()
                                + "\n Details " + algBasin.getEvDados().toClassDetailsString()
                                + "\n Revision " + algBasin.getEvDados().getRevision()
                                + "\n Batch size " + algBasin.getInfoBayer().getBatchSize()
                                + "\n Predição " + algBasin.getEvDados().predictions()
                        );

            } catch (Exception ex) {
                limparDados ();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error, este arquivo não é um dataset  !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }
    public void pressIBK ()
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);
            //arq.imprimeDados();


            AlgoritmoIBK algIbk = new AlgoritmoIBK(arq.getDados(), arq.getQuantidadeDeAtributos());
            try {
                limparDados();
                algIbk.lazyIbk();
                matrix.setText(algIbk.getEvoInicial().toMatrixString(""));
                matrix.setVisible(true);
                labelMatrizConfusao.setVisible(true);


                textDados.setVisible(true);
                textDados.setText(
                        "Atributos do data set " + algIbk.getEvoInicial().getHeader()
                                + "\n" + algIbk.getDadosIbk()
                        + "\n" + algIbk.getEvoInicial().toSummaryString()
                                + "\n--> Total de instancia " + algIbk.getEvoInicial().numInstances()
                                + "\n--> Instancias corretas: " + algIbk.getEvoInicial().correct()
                        + "\n Details " + algIbk.getEvoInicial().toClassDetailsString()
                        + "\n Revision " + algIbk.getEvoInicial().getRevision()
                        + "\n Batch size " + algIbk.getDadosIbk().getBatchSize()
                        + "\n Predição " + algIbk.getEvoInicial().predictions()
                );


            } catch (Exception ex) {
                limparDados ();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro inexperado !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }

    public void pressKStar ()
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);
            //arq.imprimeDados();


            AlgoritmoKStar algKStar = new AlgoritmoKStar(arq.getDados(), arq.getQuantidadeDeAtributos());
            try {
                limparDados();
                algKStar.lazyIbk();
                matrix.setText(algKStar.getEvoInicial().toMatrixString(""));
                matrix.setVisible(true);
                labelMatrizConfusao.setVisible(true);


                textDados.setVisible(true);
                textDados.setText(
                        "Atributos do data set " + algKStar.getEvoInicial().getHeader()
                                + "\n" + algKStar.getDadosKStar()
                                + "\n" + algKStar.getEvoInicial().toSummaryString()
                                + "\n--> Total de instancia " + algKStar.getEvoInicial().numInstances()
                                + "\n--> Instancias corretas: " + algKStar.getEvoInicial().correct()
                                + "\n Details " + algKStar.getEvoInicial().toClassDetailsString()
                                + "\n Revision " + algKStar.getEvoInicial().getRevision()
                                + "\n Batch size " + algKStar.getDadosKStar().getBatchSize()
                                + "\n Predição " + algKStar.getEvoInicial().predictions()
                );

            } catch (Exception ex) {
                limparDados ();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro inexperado !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }

    public void pressKLWL ()
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);
            //arq.imprimeDados();


            AlgoritmoLWL algLWL = new AlgoritmoLWL(arq.getDados(), arq.getQuantidadeDeAtributos());
            try {
                limparDados();
                algLWL.lazyIbk();
                matrix.setText(algLWL.getEvoInicial().toMatrixString(""));
                matrix.setVisible(true);
                labelMatrizConfusao.setVisible(true);


                textDados.setVisible(true);
                textDados.setText(
                        "Atributos do data set " + algLWL.getEvoInicial().getHeader()
                                + "\n" + algLWL.getDadosLWL()
                                + "\n" + algLWL.getEvoInicial().toSummaryString()
                                + "\n--> Total de instancia " + algLWL.getEvoInicial().numInstances()
                                + "\n--> Instancias corretas: " + algLWL.getEvoInicial().correct()
                                + "\n Details " + algLWL.getEvoInicial().toClassDetailsString()
                                + "\n Revision " + algLWL.getEvoInicial().getRevision()
                                + "\n Batch size " + algLWL.getDadosLWL().getBatchSize()
                                + "\n Predição " + algLWL.getEvoInicial().predictions()
                );

            } catch (Exception ex) {
                limparDados ();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro inexperado !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }


    //Algoritmos Arvores
    // public void pressJ48 (ActionEvent event)
    public void pressJ48 ()
    {
        ToggleButton button = new ToggleButton("Button #" );

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);
           arq.imprimeDados();


            AlgoritmoJ48 algJ48 = new AlgoritmoJ48(arq.getDados());

            try {
                limparDados();
                algJ48.arvoreDeDecisaoJ48();
                matrix.setText(algJ48.getEvaInicial().toMatrixString(""));
                matrix.setVisible(true);
                labelMatrizConfusao.setVisible(true);



                labelArvore.setVisible(true);
                labelArvore.setText("Informações Do Algoritmo J48");
                labelImprimirArvore.setVisible(true);
                labelImprimirArvore.setText(
                        "Atributos do data set " + algJ48.getEvaInicial().getHeader()
                                + "\n" + algJ48.getArvore().toString()
                                + "\n" +algJ48.getEvaInicial().toSummaryString()
                                + "\n--> Total de instancia " + algJ48.getEvaInicial().numInstances()
                                + "\n--> Instancias corretas: " + algJ48.getEvaInicial().correct()
                                        + "\n Details " + algJ48.getEvaInicial().toClassDetailsString()
                                        + "\n Revision " + algJ48.getEvaInicial().getRevision()
                                        + "\n Batch size " + algJ48.getArvore().getBatchSize()
                                        + "\n Predição " + algJ48.getEvaInicial().predictions()
                );
                String graph = algJ48.getArvore().graph();
                SwingUtilities.invokeLater(() -> {
                    TreeVisualizer treeVisualizer = new TreeVisualizer(null, graph, new PlaceNode2());
                    treeVisualizer.setPreferredSize(new Dimension(600, 500));
                    sw.setContent(treeVisualizer);
                });
                arvDecisao.setVisible(true);




            } catch (Exception ex) {
                matrix.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error, este arquivo não é um dataset  !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }


    public void pressZeroR ()
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);
            arq.imprimeDados();


            AlgoritmoZeroR algZeroR = new AlgoritmoZeroR(arq.getDados());

            try {
                limparDados();
                algZeroR.zeroR();
                matrix.setText(algZeroR.getEvaInicial().toMatrixString(""));
                matrix.setVisible(true);
                labelMatrizConfusao.setVisible(true);


                labelArvore.setVisible(true);
                labelArvore.setText("Informações Do Algoritmo Zero R");
                labelImprimirArvore.setVisible(true);
                labelImprimirArvore.setText(
                        "Atributos do data set " + algZeroR.getEvaInicial().getHeader()
                                + "\n " +algZeroR.getDadosZeroR().toString()
                                + "\n" + algZeroR.getEvaInicial().toSummaryString()
                                + "\n--> Total de instancia " + algZeroR.getEvaInicial().numInstances()
                                + "\n--> Instancias corretas: " + algZeroR.getEvaInicial().correct()
                        + "\n Details " + algZeroR.getEvaInicial().toClassDetailsString()
                        + "\n Revision " + algZeroR.getEvaInicial().getRevision()
                        + "\n Batch size " + algZeroR.getDadosZeroR().getBatchSize()
                        + "\n Predição " + algZeroR.getEvaInicial().predictions()
                        //+ "\n " + algZeroR.getEvaInicial().getPluginMetrics()
                );

            } catch (Exception ex) {
                limparDados ();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error,  !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }


    public void pressREPTree ()
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);
            arq.imprimeDados();


            AlgoritmoREPTree algREPTree = new AlgoritmoREPTree(arq.getDados());

            try {
                limparDados();
                algREPTree.zeroR();
                matrix.setText(algREPTree.getEvaInicial().toMatrixString(""));
                matrix.setVisible(true);
                labelMatrizConfusao.setVisible(true);


                labelArvore.setVisible(true);
                labelArvore.setText("Informações Do Algoritmo REPTree");
                labelImprimirArvore.setVisible(true);
                labelImprimirArvore.setText(
                        "Atributos do data set " + algREPTree.getEvaInicial().getHeader()
                                + "\n " +algREPTree.getDadosREPTree().toString()
                                + "\n" + algREPTree.getEvaInicial().toSummaryString()
                                + "\n--> Total de instancia " + algREPTree.getEvaInicial().numInstances()
                                + "\n--> Instancias corretas: " + algREPTree.getEvaInicial().correct()
                                + "\n Details " + algREPTree.getEvaInicial().toClassDetailsString()
                                + "\n Revision " + algREPTree.getEvaInicial().getRevision()
                                + "\n Batch size " + algREPTree.getDadosREPTree().getBatchSize()
                                + "\n Predição " + algREPTree.getEvaInicial().predictions()

                        //+ "\n " + algZeroR.getEvaInicial().getPluginMetrics()
                );
                String graph = algREPTree.getDadosREPTree().graph();
                SwingUtilities.invokeLater(() -> {
                    TreeVisualizer treeVisualizer = new TreeVisualizer(null, graph, new PlaceNode2());
                    treeVisualizer.setPreferredSize(new Dimension(600, 500));
                    sw.setContent(treeVisualizer);
                });
                arvDecisao.setVisible(true);

            } catch (Exception ex) {
                limparDados ();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error,  !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }


    public void pressLMT ()
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);
            arq.imprimeDados();


            AlgoritmoLMT algLMT = new AlgoritmoLMT(arq.getDados());

            try {
                limparDados();
                algLMT.zeroR();
                matrix.setText(algLMT.getEvaInicial().toMatrixString(""));
                matrix.setVisible(true);
                labelMatrizConfusao.setVisible(true);


                labelArvore.setVisible(true);
                labelArvore.setText("Informações Do Algoritmo LMT");
                labelImprimirArvore.setVisible(true);
                labelImprimirArvore.setText(
                        "Atributos do data set " + algLMT.getEvaInicial().getHeader()
                                + "\n " +algLMT.getDadosLMT().toString()
                                + "\n" + algLMT.getEvaInicial().toSummaryString()
                                + "\n--> Total de instancia " + algLMT.getEvaInicial().numInstances()
                                + "\n--> Instancias corretas: " + algLMT.getEvaInicial().correct()
                                + "\n Details " + algLMT.getEvaInicial().toClassDetailsString()
                                + "\n Revision " + algLMT.getEvaInicial().getRevision()
                                + "\n Batch size " + algLMT.getDadosLMT().getBatchSize()
                                + "\n Predição " + algLMT.getEvaInicial().predictions()
                        //+ "\n " + algZeroR.getEvaInicial().getPluginMetrics()
                );
                String graph = algLMT.getDadosLMT().graph();
                SwingUtilities.invokeLater(() -> {
                    TreeVisualizer treeVisualizer = new TreeVisualizer(null, graph, new PlaceNode2());
                    treeVisualizer.setPreferredSize(new Dimension(600, 500));
                    sw.setContent(treeVisualizer);
                });
                arvDecisao.setVisible(true);

            } catch (Exception ex) {
                limparDados ();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error,  !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }


    //Avore de decisão
    public void pressArboreDecisao ()
    {
        arvoreDecisao();
    }


    public void arvoreDecisao()
    {
        StackPane secondaryLayout = new StackPane(sw);


        Scene secondScene = new Scene(secondaryLayout,1000,636 );

        // New window (Stage)
        Stage newWindow = new Stage();
        newWindow.setTitle("Arvore de decisão");
        newWindow.setScene(secondScene);


        newWindow.show();
    }

    //Arquivo
    public File abrirArquivo()
    {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser);

        fileChooser.setTitle("Open Resource File");
        return fileChooser.showOpenDialog(stage);
    }
    private static void configureFileChooser(
            final FileChooser fileChooser) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Arff", "*.arff"),
                new FileChooser.ExtensionFilter("Csv", "*.csv")
        );
    }

    private Arquivo lerDados(File selectedFile)
    {
        System.out.println(selectedFile.getName());
        System.out.println(selectedFile.getAbsolutePath());
        Arquivo arq = new Arquivo(selectedFile.getAbsolutePath());
        try {
            arq.leDados();
        } catch (Exception ex) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Arquivo corrompido  !!", ButtonType.OK);
            alert.showAndWait();
            ex.printStackTrace();
            limparDados();
        }
        return arq;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelMatrizConfusao.setVisible(false);
        labelArvore.setVisible(false);


        //TextArea
        matrix.setEditable(false);
        matrix.setVisible(false);

        textDados.setEditable(false);
        textDados.setVisible(false);

        labelImprimirArvore.setEditable(false);
        labelImprimirArvore.setVisible(false);


        //buttons
        String styleButton = "-fx-padding: 8 15 15 15;\n" +
                "                -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "                -fx-background-radius: 8;\n" +
                "                -fx-background-color:\n" +
                "                linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
                "        #9d4024,\n" +
                "        #d86e3a,\n" +
                "                        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n" +
                "                -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "                -fx-font-weight: bold;\n" +
                "                -fx-font-size: 1.1em;";
        arvDecisao.setStyle(styleButton);
        arvDecisao.setVisible(false);


        String styleButtonGeral = "-fx-background-color: \n" +
                "        #c3c4c4,\n" +
                "        linear-gradient(#d6d6d6 50%, white 100%),\n" +
                "        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);\n" +
                "    -fx-background-radius: 30;\n" +
                "    -fx-background-insets: 0,1,1;\n" +
                "    -fx-text-fill: black;\n" +
                "    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );";

        buttonNaive.setStyle(styleButtonGeral);
        buttonJ48.setStyle(styleButtonGeral);
        buttonIBK.setStyle(styleButtonGeral);
        buttonZeroR.setStyle(styleButtonGeral);
        buttonKStar.setStyle(styleButtonGeral);
        buttonLMT.setStyle(styleButtonGeral);
        buttonLWL.setStyle(styleButtonGeral);
        buttonREPTree.setStyle(styleButtonGeral);


    }
}
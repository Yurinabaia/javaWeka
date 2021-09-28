package com.example.demo;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.*;



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






    public void limparDados ()
    {
        labelImprimirArvore.setText("");
        textDados.setText("");
        labelImprimirArvore.setVisible(false);
        textDados.setVisible(false);
        labelArvore.setVisible(false);
        matrix.setVisible(false);

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
                labelArvore.setText("Arvoré de decisão:");
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
                labelArvore.setText("Informações algoritmo Zero R");
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
                labelArvore.setText("Informações algoritmo REPTree");
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
                labelArvore.setText("Informações algoritmo LMT");
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

            } catch (Exception ex) {
                limparDados ();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error,  !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }

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

    }
}
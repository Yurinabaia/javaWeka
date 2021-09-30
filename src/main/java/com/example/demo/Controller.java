package com.example.demo;


import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.*;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
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
    @FXML
    private Button butttonClassifcar;
    @FXML
    private TextField textFieldUser;
    @FXML
    private TextArea textAreaResult;

    private AlgoritmoBayesiano algoritmoBayesiano;
    private AlgoritmoIBK algoritmoIBK;
    private AlgoritmoKStar algoritmoKStar;
    private AlgoritmoLWL algoritmoLWL;

    private AlgoritmoJ48 algoritmoJ48;
    private AlgoritmoZeroR algoritmoZeroR;
    private AlgoritmoREPTree algoritmoREPTree;
    private AlgoritmoLMT algoritmoLMT;

    private int algoritmoSelecionado;











    SwingNode sw = new SwingNode();







    public void limparDados ()
    {
        labelImprimirArvore.setText("");
        textDados.setText("");
        textFieldUser.setText("");
        textAreaResult.setText("");

        textAreaResult.setVisible(false);
        textFieldUser.setVisible(false);
        labelImprimirArvore.setVisible(false);
        textDados.setVisible(false);
        labelArvore.setVisible(false);
        matrix.setVisible(false);
        arvDecisao.setVisible(false);
        butttonClassifcar.setVisible(false);
        labelMatrizConfusao.setVisible(false);

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

                textFieldUser.setVisible(true);

                butttonClassifcar.setVisible(true);
                this.algoritmoBayesiano = algBasin;
                this.algoritmoSelecionado = 1;

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
                textFieldUser.setVisible(true);

                butttonClassifcar.setVisible(true);
                this.algoritmoIBK = algIbk;
                this.algoritmoSelecionado = 2;


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
                textFieldUser.setVisible(true);

                butttonClassifcar.setVisible(true);
                this.algoritmoKStar = algKStar;
                this.algoritmoSelecionado = 3;

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
                textFieldUser.setVisible(true);

                butttonClassifcar.setVisible(true);
                this.algoritmoLWL = algLWL;
                this.algoritmoSelecionado = 4;

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


            AlgoritmoJ48 algJ48 = new AlgoritmoJ48(arq.getDados(), arq.getQuantidadeDeAtributos());

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
                textFieldUser.setVisible(true);

                butttonClassifcar.setVisible(true);
                this.algoritmoJ48 = algJ48;
                this.algoritmoSelecionado = 5;



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


            AlgoritmoZeroR algZeroR = new AlgoritmoZeroR(arq.getDados(),arq.getQuantidadeDeAtributos());

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
                textFieldUser.setVisible(true);

                butttonClassifcar.setVisible(true);
                this.algoritmoZeroR = algZeroR;
                this.algoritmoSelecionado = 6;

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


            AlgoritmoREPTree algREPTree = new AlgoritmoREPTree(arq.getDados(),arq.getQuantidadeDeAtributos());

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

                textFieldUser.setVisible(true);

                butttonClassifcar.setVisible(true);
                this.algoritmoREPTree = algREPTree;
                this.algoritmoSelecionado = 7;

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


            AlgoritmoLMT algLMT = new AlgoritmoLMT(arq.getDados(),arq.getQuantidadeDeAtributos());

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

                textFieldUser.setVisible(true);

                butttonClassifcar.setVisible(true);
                this.algoritmoLMT = algLMT;
                this.algoritmoSelecionado = 8;

            } catch (Exception ex) {
                limparDados ();
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error,  !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }

    //Classificar dados
    public void pressClassificar()  {

        textAreaResult.setVisible(true);
        try {
            this.classificar();
        }
        catch (Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Os dados de classificação não é compativel com dataset !!", ButtonType.OK);
            alert.showAndWait();
            ex.printStackTrace();
            textAreaResult.setText("");
            textFieldUser.setText("");
            butttonClassifcar.setVisible(false);
        }
    }

    public void classificar() throws Exception
    {
        String[] separador = textFieldUser.getText().split(",");
        double pred;
        Attribute atribute;
        String predClass;

        switch (this.algoritmoSelecionado) {
            case 1 -> {
                Instance instanceBayse = new DenseInstance(this.algoritmoBayesiano.getQuantidadeDeAtributos());
                instanceBayse.setDataset(this.algoritmoBayesiano.getDados());
                for (int i = 0; i < separador.length; i++) {
                    if (verificarInteiro(separador[i]))
                        instanceBayse.setValue(i, Double.parseDouble(separador[i]));
                    else
                        instanceBayse.setValue(i, separador[i]);
                }
                pred = this.algoritmoBayesiano.getInfoBayer().classifyInstance(instanceBayse);
                System.out.println("Predição: " + separador.length);


                //Classificando dados
                atribute = this.algoritmoBayesiano.getDados().attribute(separador.length);
                predClass = atribute.value((int) pred);
                textAreaResult.setText("Predição: " + pred + "\n" + "Classificação " + predClass);
            }
            case 2 -> {
                Instance instanceIBK = new DenseInstance(this.algoritmoIBK.getQuantidadeDeAtributos());
                instanceIBK.setDataset(this.algoritmoIBK.getDados());
                for (int i = 0; i < separador.length; i++) {
                    if (verificarInteiro(separador[i]))
                        instanceIBK.setValue(i, Double.parseDouble(separador[i]));
                    else
                        instanceIBK.setValue(i, separador[i]);
                }
                pred = this.algoritmoIBK.getDadosIbk().classifyInstance(instanceIBK);
                System.out.println("Predição: " + separador.length);


                //Classificando dados
                atribute = this.algoritmoIBK.getDados().attribute(separador.length);
                predClass = atribute.value((int) pred);
                textAreaResult.setText("Predição: " + pred + "\n" + "Classificação " + predClass);
            }
            case 3 -> {
                Instance instanceKstar = new DenseInstance(this.algoritmoKStar.getQuantidadeDeAtributos());
                instanceKstar.setDataset(this.algoritmoKStar.getDados());
                for (int i = 0; i < separador.length; i++) {
                    if (verificarInteiro(separador[i]))
                        instanceKstar.setValue(i, Double.parseDouble(separador[i]));
                    else
                        instanceKstar.setValue(i, separador[i]);
                }
                pred = this.algoritmoKStar.getDadosKStar().classifyInstance(instanceKstar);
                System.out.println("Predição: " + separador.length);


                //Classificando dados
                atribute = this.algoritmoKStar.getDados().attribute(separador.length);
                predClass = atribute.value((int) pred);
                textAreaResult.setText("Predição: " + pred + "\n" + "Classificação " + predClass);
            }
            case 4 -> {
                Instance instanceLWL = new DenseInstance(this.algoritmoLWL.getQuantidadeDeAtributos());
                instanceLWL.setDataset(this.algoritmoLWL.getDados());
                for (int i = 0; i < separador.length; i++) {
                    if (verificarInteiro(separador[i]))
                        instanceLWL.setValue(i, Double.parseDouble(separador[i]));
                    else
                        instanceLWL.setValue(i, separador[i]);
                }
                pred = this.algoritmoLWL.getDadosLWL().classifyInstance(instanceLWL);
                System.out.println("Predição: " + separador.length);


                //Classificando dados
                atribute = this.algoritmoLWL.getDados().attribute(separador.length);
                predClass = atribute.value((int) pred);
                textAreaResult.setText("Predição: " + pred + "\n" + "Classificação " + predClass);
            }
            case 5 -> {
                Instance instanceJ48 = new DenseInstance(this.algoritmoJ48.getQuantidadeDeAtributos());
                instanceJ48.setDataset(this.algoritmoJ48.getDados());
                for (int i = 0; i < separador.length; i++) {
                    if (verificarInteiro(separador[i]))
                        instanceJ48.setValue(i, Double.parseDouble(separador[i]));
                    else
                        instanceJ48.setValue(i, separador[i]);
                }
                pred = this.algoritmoJ48.getArvore().classifyInstance(instanceJ48);
                System.out.println("Predição: " + separador.length);


                //Classificando dados
                atribute = this.algoritmoJ48.getDados().attribute(separador.length);
                predClass = atribute.value((int) pred);
                textAreaResult.setText("Predição: " + pred + "\n" + "Classificação " + predClass);
            }
            case 6 -> {
                Instance instanceZeroR = new DenseInstance(this.algoritmoZeroR.getQuantidadeDeAtributos());
                instanceZeroR.setDataset(this.algoritmoZeroR.getDados());
                for (int i = 0; i < separador.length; i++) {
                    if (verificarInteiro(separador[i]))
                        instanceZeroR.setValue(i, Double.parseDouble(separador[i]));
                    else
                        instanceZeroR.setValue(i, separador[i]);
                }
                pred = this.algoritmoZeroR.getDadosZeroR().classifyInstance(instanceZeroR);
                System.out.println("Predição: " + separador.length);


                //Classificando dados
                atribute = this.algoritmoZeroR.getDados().attribute(separador.length);
                predClass = atribute.value((int) pred);
                textAreaResult.setText("Predição: " + pred + "\n" + "Classificação " + predClass);
            }
            case 7 -> {
                Instance instanceREPTree = new DenseInstance(this.algoritmoREPTree.getQuantidadeDeAtributos());
                instanceREPTree.setDataset(this.algoritmoREPTree.getDados());
                for (int i = 0; i < separador.length; i++) {
                    if (verificarInteiro(separador[i]))
                        instanceREPTree.setValue(i, Double.parseDouble(separador[i]));
                    else
                        instanceREPTree.setValue(i, separador[i]);
                }
                pred = this.algoritmoREPTree.getDadosREPTree().classifyInstance(instanceREPTree);
                System.out.println("Predição: " + separador.length);


                //Classificando dados
                atribute = this.algoritmoREPTree.getDados().attribute(separador.length);
                predClass = atribute.value((int) pred);
                textAreaResult.setText("Predição: " + pred + "\n" + "Classificação " + predClass);
            }
            case 8 -> {
                Instance instanceLMT = new DenseInstance(this.algoritmoLMT.getQuantidadeDeAtributos());
                instanceLMT.setDataset(this.algoritmoLMT.getDados());
                for (int i = 0; i < separador.length; i++) {
                    if (verificarInteiro(separador[i]))
                        instanceLMT.setValue(i, Double.parseDouble(separador[i]));
                    else
                        instanceLMT.setValue(i, separador[i]);
                }
                pred = this.algoritmoLMT.getDadosLMT().classifyInstance(instanceLMT);
                System.out.println("Predição: " + separador.length);


                //Classificando dados
                atribute = this.algoritmoLMT.getDados().attribute(separador.length);
                predClass = atribute.value((int) pred);
                textAreaResult.setText("Predição: " + pred + "\n" + "Classificação " + predClass);
            }
        }



    }

    private boolean verificarInteiro(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
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
        labelArvore.setVisible(false);
        labelMatrizConfusao.setVisible(false);


        textFieldUser.setPromptText("Digite a entrada de dados do data set separado por virgula," +
                "\n\r os dados de entrada deve conter todos os atributos" +
                "\n\r menos o atributo de classificação." +
                "\n\r Exemplo: 23,F,HIGH,HIGH,25.355");
        //TextArea
        matrix.setEditable(false);
        matrix.setVisible(false);

        textDados.setEditable(false);
        textDados.setVisible(false);

        labelImprimirArvore.setEditable(false);
        labelImprimirArvore.setVisible(false);

        textAreaResult.setVisible(false);
        textAreaResult.setEditable(false);

        textFieldUser.setVisible(false);
        //buttons
        String styleButton = """
                -fx-padding: 8 15 15 15;
                                -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;
                                -fx-background-radius: 8;
                                -fx-background-color:
                                linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),
                        #9d4024,
                        #d86e3a,
                                        radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);
                                -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );
                                -fx-font-weight: bold;
                                -fx-font-size: 1.1em;""";
        arvDecisao.setStyle(styleButton);
        arvDecisao.setVisible(false);


        String styleButtonGeral = """
                -fx-background-color: #c3c4c4,
                        linear-gradient(#d6d6d6 50%, white 100%),
                        radial-gradient(center 50% -40%, radius 200%, #e6e6e6 45%, rgba(230,230,230,0) 50%);
                    -fx-background-radius: 30;
                    -fx-background-insets: 0,1,1;
                    -fx-text-fill: black;
                    -fx-effect: dropshadow( three-pass-box , rgba(0,0,0,0.6) , 3, 0.0 , 0 , 1 );""";

        buttonNaive.setStyle(styleButtonGeral);
        buttonJ48.setStyle(styleButtonGeral);
        buttonIBK.setStyle(styleButtonGeral);
        buttonZeroR.setStyle(styleButtonGeral);
        buttonKStar.setStyle(styleButtonGeral);
        buttonLMT.setStyle(styleButtonGeral);
        buttonLWL.setStyle(styleButtonGeral);
        buttonREPTree.setStyle(styleButtonGeral);


        butttonClassifcar.setVisible(false);

    }
}
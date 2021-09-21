package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import model.*;
import org.w3c.dom.Text;
import weka.classifiers.rules.ZeroR;


import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private  Window stage;
    @FXML
    private Label labelMatrizConfusao;
    @FXML
    private GridPane gridMatrixConfuse =new GridPane();
    @FXML
    private  TextArea textDados;
    @FXML
    private  Label labelArvore;
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
    }
    //Algoritmos Lazy
     public void pressBayse (ActionEvent event)
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);

           AlgoritmoBayesiano algBasin = new AlgoritmoBayesiano(arq.getDados(), arq.getQuantidadeDeAtributos());

            try {
                limparDados();
                textDados.setVisible(true);

                metodoMatrizConfusao(algBasin.lazyBaysiano());
                String predicao = algBasin.getPredicao() != 0.0 ? "Predição:" + String.valueOf(algBasin.getPredicao()) : "";
                textDados.setText(algBasin.getInfoBayer().toString() + "\n" + predicao +
                                "\n" + algBasin.getEvDados().toSummaryString()
                                + "\n-->Instancias corretas: " + algBasin.getEvDados().correct()

                        );

            } catch (Exception ex) {
                labelMatrizConfusao.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error, este arquivo não é um dataset  !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }
    public void pressIBK (ActionEvent event)
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);
            //arq.imprimeDados();


            AlgoritmoIBK algIbk = new AlgoritmoIBK(arq.getDados(), arq.getQuantidadeDeAtributos());
            try {
                limparDados();
                metodoMatrizConfusao(algIbk.lazyIbk());
                textDados.setVisible(true);
                textDados.setText(algIbk.getDadosIbk() + "\n" + algIbk.getEvoInicial().toSummaryString() + "\n-->Instancias corretas: " +
                        algIbk.getEvoInicial().correct());

            } catch (Exception ex) {
                labelMatrizConfusao.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Erro inexperado !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }


    //Algoritmos Arvores
    public void pressJ48 (ActionEvent event)
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);
           arq.imprimeDados();


            AlgoritmoJ48 algJ48 = new AlgoritmoJ48(arq.getDados());

            try {
                limparDados();
                metodoMatrizConfusao(algJ48.arvoreDeDecisaoJ48());
                labelArvore.setVisible(true);
                labelArvore.setText("Arvoré de decisão:");
                labelImprimirArvore.setVisible(true);
                labelImprimirArvore.setText(algJ48.getArvore().toString()
                                + "\n" +algJ48.getEvaInicial().toSummaryString()
                                + "\n-->Instancias corretas: " + algJ48.getEvaInicial().correct());
            } catch (Exception ex) {
                labelMatrizConfusao.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.ERROR, "Error, este arquivo não é um dataset  !!", ButtonType.OK);
                alert.showAndWait();
                ex.printStackTrace();
            }
        }
    }

    public void pressZeroR (ActionEvent event)
    {

        File selectedFile = abrirArquivo();
        if (selectedFile != null) {
            Arquivo arq = lerDados(selectedFile);
            arq.imprimeDados();


            AlgoritmoZeroR algZeroR = new AlgoritmoZeroR(arq.getDados());

            try {
                limparDados();
                metodoMatrizConfusao(algZeroR.zeroR());
                labelArvore.setVisible(true);
                labelArvore.setText("Informações Zero R");
                labelImprimirArvore.setVisible(true);
                labelImprimirArvore.setText(algZeroR.getDadosZeroR().toString() + "\n" +
                        algZeroR.getEvaInicial().toSummaryString()
                        + "\n-->Instancias corretas: " + algZeroR.getEvaInicial().correct());
                matrix.setText(algZeroR.getEvaInicial().toMatrixString());

            } catch (Exception ex) {
                labelMatrizConfusao.setVisible(false);
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
        File selectedFile = fileChooser.showOpenDialog(stage);
        return selectedFile;
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

    public  Arquivo lerDados(File selectedFile)
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
            labelMatrizConfusao.setVisible(false);
        }
        return arq;
    }

    public  void metodoMatrizConfusao(double[][] matriz) throws Exception {
        labelMatrizConfusao.setVisible(true);

        gridMatrixConfuse.getChildren().clear();
        RowConstraints rc = new RowConstraints();
        rc.setPercentHeight(100d / matriz.length);
        ColumnConstraints cc = new ColumnConstraints();
        cc.setPercentWidth(100d /  matriz.length);
        gridMatrixConfuse.getRowConstraints().add(rc);
        gridMatrixConfuse.getColumnConstraints().add(cc);

        String alfabeto = "ABCDEFGHIJKLMNOPQRSTUVXZWYZ";
        for (int j = 0; j < matriz.length; j++)
        {
            System.out.print("aaaa"+String.valueOf(alfabeto.charAt(j)));

            Label val = new Label();
            val.setText(String.valueOf(alfabeto.charAt(j)));
            val.setAlignment(Pos.CENTER);
            val.setPrefWidth(100);
            gridMatrixConfuse.setRowIndex(val,0);
            gridMatrixConfuse.setColumnIndex(val,j);
            gridMatrixConfuse.getChildren().add(val);
        }

        for (int i = 0; i < matriz.length; i++)
        {
            for (int j = 0; j < matriz.length; j++)
            {
                System.out.print(matriz[i][j] + "|");
                TextField val = new TextField();
                val.setText(String.valueOf(matriz[i][j]));
                val.setAlignment(Pos.CENTER);
                val.setPrefWidth(100);
                val.setEditable(false);
                gridMatrixConfuse.setRowIndex(val,i+1);
                gridMatrixConfuse.setColumnIndex(val,j);
                gridMatrixConfuse.getChildren().add(val);
            }
            System.out.println(" ");
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        labelMatrizConfusao.setVisible(false);
        labelArvore.setVisible(false);


        //TextArea
        textDados.setEditable(false);
        textDados.setVisible(false);
        labelImprimirArvore.setEditable(false);
        labelImprimirArvore.setVisible(false);

    }
}
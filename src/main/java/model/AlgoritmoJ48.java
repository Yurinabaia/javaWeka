package model;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.util.Random;

public class AlgoritmoJ48 {
    private final Instances dados;
    private final int quantidadeDeAtributos;
    private J48 arvore;
    private Evaluation evaInicial;
    private  Evaluation evaCruzada;

    public J48 getArvore() {
        return arvore;
    }

    public Evaluation getEvaInicial() {
        return evaInicial;
    }

    public Evaluation getEvaCruzada() {
        return evaCruzada;
    }

    public int getQuantidadeDeAtributos() {
        return quantidadeDeAtributos;
    }

    public Instances getDados() {
        return dados;
    }

    public  AlgoritmoJ48 (Instances dados, int quantidadeDeAtributos)
    {
        this.dados = dados;
        this.quantidadeDeAtributos = quantidadeDeAtributos;
    }


    public void arvoreDeDecisaoJ48() throws Exception
    {
        J48 tree=new J48();
        tree.buildClassifier(dados);
        System.out.println(tree);
        this.arvore = tree;
        System.out.println("Avaliacao inicial:\n");
        Evaluation avaliacao;

        /*Instance instance = new DenseInstance(quantidadeDeAtributos);
        instance.setDataset(dados);
        instance.setValue(0,26);//Idade
        instance.setValue(1,0);//Sex

        instance.setValue(2,1);//BPHEIGH
        instance.setValue(3,0);//BPNORMAL
        instance.setValue(4,0);//BPLOW

        instance.setValue(5,0);//COLESTEROL

        instance.setValue(6,12.307);//Potassio

        double pred=tree.classifyInstance(instance);
        System.out.println("Predição: "+pred);


        //Classificando dados
        Attribute a=dados.attribute(7);
        String predClass=a.value((int)pred);
        System.out.println("XXXX: "+predClass);*/


        avaliacao=new Evaluation(dados);
        avaliacao.evaluateModel(tree,dados);
        System.out.println("-->Instancias corretas:"+avaliacao.correct()+"\n");
        System.out.println("Avaliacao cruzada:\n");
        this.evaInicial = avaliacao;
        Evaluation avalCruzada;
        avalCruzada=new Evaluation(dados);
        avalCruzada.crossValidateModel(tree, dados, 10, new Random(1));
        System.out.println("-->Instancias corretas CV:"+avalCruzada.correct()+"\n");
        this.evaCruzada = avalCruzada;




/*
        double[][] doubles = avalCruzada.confusionMatrix();
        for (int i = 0; i < doubles.length; i++)
        {
            for (int j = 0; j < doubles[i].length; j++)
            {
                System.out.print(doubles[i][j] + "|");
            }
            System.out.println(" ");
        }
 */
        //avalCruzada.confusionMatrix();


        // display classifier
        /*final javax.swing.JFrame jf = new javax.swing.JFrame("Weka Classifier Tree Visualizer: J48");
        jf.setSize(500,400);
        jf.getContentPane().setLayout(new BorderLayout());


        TreeVisualizer tv = new TreeVisualizer(null,
                tree.graph(),
                new PlaceNode2());



        jf.getContentPane().add(tv, BorderLayout.CENTER);
        jf.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                jf.dispose();
            }
        });

        jf.setVisible(true);
        tv.fitToScreen();*/
    }
}

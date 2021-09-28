package model;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.J48;
import weka.core.Instances;

import java.util.Random;

public class AlgoritmoJ48 {
    private final Instances dados;
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

    public  AlgoritmoJ48 (Instances dados)
    {
        this.dados = dados;
    }


    public void arvoreDeDecisaoJ48() throws Exception
    {
        J48 tree=new J48();
        tree.buildClassifier(dados);
        System.out.println(tree);
        this.arvore = tree;

        System.out.println("Avaliacao inicial:\n");
        Evaluation avaliacao;

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
    }

}

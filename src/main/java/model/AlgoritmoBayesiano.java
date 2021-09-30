package model;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;


public class AlgoritmoBayesiano {
    private final Instances dados;
    private final int quantidadeDeAtributos;
    private double predicao;
    private  String predicaConfusao;
    private NaiveBayes infoBayer;
    private Evaluation evDados;

    public double getPredicao() {
        return predicao;
    }

    public String getPredicaConfusao() {
        return predicaConfusao;
    }

    public Instances getDados() {
        return dados;
    }

    public int getQuantidadeDeAtributos() {
        return quantidadeDeAtributos;
    }

    public NaiveBayes getInfoBayer() {
        return infoBayer;
    }

    public Evaluation getEvDados() {
        return evDados;
    }

    public  AlgoritmoBayesiano (Instances dados, int quantidadeDeAtributos)
    {
        this.dados = dados;
        this.quantidadeDeAtributos = quantidadeDeAtributos;
    }

    public void lazyBaysiano() throws Exception {

        NaiveBayes bayer = new NaiveBayes();
        Instances s = new Instances(dados);
        Instance val = new DenseInstance(quantidadeDeAtributos);
        val.setDataset(dados);
        bayer.buildClassifier(s);

        double bd =  bayer.classifyInstance(val);
        System.out.println("Predição:"+bd);
        this.predicao = bd;

        Attribute a=dados.attribute(4);
        String predClass=a.value((int)bd);
        System.out.println("Predição:"+predClass);
        this.predicaConfusao = predClass;


        Evaluation vl = new Evaluation(s);
        vl.evaluateModel(bayer, s);

        System.out.println("**Bayes Naive  e seu dataset **");
        System.out.println(vl.toSummaryString());
        this.evDados = vl;

        System.out.print(" A expressão dos dados é: ");
        System.out.println(bayer);
        this.infoBayer = bayer;
       /* for (int i = 0; i < dados.numInstances(); i++) {
            System.out.println(dados.instance(i));
            double index = bayer.classifyInstance(dados.instance(i));
            String className = s.attribute(0).value((int) index);
            System.out.println(className);
        }*/

        System.out.println("-->Instancias corretas:"+vl.correct()+"\n");

       // vl.confusionMatrix();
    }

}

package model;

import weka.classifiers.Evaluation;
import weka.classifiers.lazy.KStar;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class AlgoritmoKStar {
    private final Instances dados;
    private final int quantidadeDeAtributos;
    private Evaluation evoInicial;
    private KStar dadosKStar;

    public Evaluation getEvoInicial() {
        return evoInicial;
    }

    public KStar getDadosKStar() {
        return dadosKStar;
    }


    public  AlgoritmoKStar (Instances dados, int quantidadeDeAtributos)
    {
        this.dados = dados;
        this.quantidadeDeAtributos = quantidadeDeAtributos;
    }

    public void lazyIbk() throws Exception{
        KStar kStar=new KStar();
        kStar.buildClassifier(dados);
        Instances multKstar = new Instances(dados);
        Instance new_list=new DenseInstance(quantidadeDeAtributos);
        new_list.setDataset(dados);

        /*new_list.setValue(0,7.2);
        new_list.setValue(1,3.5);
        new_list.setValue(2,8.2);
        new_list.setValue(3,5.2);

        double pred=k3.classifyInstance(new_list);
        System.out.println("Predição: "+pred);
        Attribute a=dados.attribute(4);
        String predClass=a.value((int)pred);
        System.out.println("Predição:"+predClass);*/

        Evaluation kstarEvolution = new Evaluation(multKstar);
        kstarEvolution.evaluateModel(kStar,dados);

        System.out.println("** IBK  **");
        System.out.println(kstarEvolution.toSummaryString());
        this.evoInicial = kstarEvolution;

        System.out.print(" A expressão da entrada de dados ");
        System.out.println(kStar);
        this.dadosKStar = kStar;
       /* for (int i = 0; i < dados.numInstances(); i++) {
            System.out.println(dados.instance(i));
            double index = bayer.classifyInstance(dados.instance(i));
            String className = s.attribute(0).value((int) index);
            System.out.println(className);
        }*/

        System.out.println("-->Instancias corretas:"+kstarEvolution.correct()+"\n");


    }
}

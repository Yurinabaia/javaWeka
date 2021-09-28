package model;

import weka.classifiers.Evaluation;
import weka.classifiers.lazy.LWL;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class AlgoritmoLWL {
    private final Instances dados;
    private final int quantidadeDeAtributos;
    private Evaluation evoInicial;
    private LWL dadosLWL;

    public Evaluation getEvoInicial() {
        return evoInicial;
    }

    public LWL getDadosLWL() {
        return dadosLWL;
    }


    public  AlgoritmoLWL (Instances dados, int quantidadeDeAtributos)
    {
        this.dados = dados;
        this.quantidadeDeAtributos = quantidadeDeAtributos;
    }

    public void lazyIbk() throws Exception{
        LWL lwl=new LWL();
        lwl.buildClassifier(dados);
        Instances multLWL = new Instances(dados);
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

        Evaluation lwlEvolution = new Evaluation(multLWL);
        lwlEvolution.evaluateModel(lwl,dados);

        System.out.println("** IBK  **");
        System.out.println(lwlEvolution.toSummaryString());
        this.evoInicial = lwlEvolution;

        System.out.print(" A expressão da entrada de dados ");
        System.out.println(lwl);
        this.dadosLWL = lwl;
       /* for (int i = 0; i < dados.numInstances(); i++) {
            System.out.println(dados.instance(i));
            double index = bayer.classifyInstance(dados.instance(i));
            String className = s.attribute(0).value((int) index);
            System.out.println(className);
        }*/

        System.out.println("-->Instancias corretas:"+lwlEvolution.correct()+"\n");


    }
}

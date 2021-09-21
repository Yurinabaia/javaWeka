package model;

import weka.classifiers.Evaluation;
import weka.classifiers.lazy.IBk;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;

public class AlgoritmoIBK {
    private Instances dados;
    private int quantidadeDeAtributos;
    private Evaluation evoInicial;
    private IBk dadosIbk;

    public Evaluation getEvoInicial() {
        return evoInicial;
    }

    public IBk getDadosIbk() {
        return dadosIbk;
    }


    public  AlgoritmoIBK (Instances dados, int quantidadeDeAtributos)
    {
        this.dados = dados;
        this.quantidadeDeAtributos = quantidadeDeAtributos;
    }

    public double[][] lazyIbk() throws Exception{
        IBk k3=new IBk(quantidadeDeAtributos);
        k3.buildClassifier(dados);
        Instances multIbk = new Instances(dados);
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

        Evaluation ibkEvolution = new Evaluation(multIbk);
        ibkEvolution.evaluateModel(k3,dados);

        /** Print the algorithm summary */
        System.out.println("** IBK  **");
        System.out.println(ibkEvolution.toSummaryString());
        this.evoInicial = ibkEvolution;

        System.out.print(" A expressão da entrada de dados ");
        System.out.println(k3);
        this.dadosIbk = k3;
       /* for (int i = 0; i < dados.numInstances(); i++) {
            System.out.println(dados.instance(i));
            double index = bayer.classifyInstance(dados.instance(i));
            String className = s.attribute(0).value((int) index);
            System.out.println(className);
        }*/

        System.out.println("-->Instancias corretas:"+ibkEvolution.correct()+"\n");

        return ibkEvolution.confusionMatrix();
    }
}

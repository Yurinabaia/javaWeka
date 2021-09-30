package model;

import weka.classifiers.Evaluation;
import weka.classifiers.rules.ZeroR;
import weka.core.Instances;

public class AlgoritmoZeroR {

    private final Instances dados;
    private ZeroR dadosZeroR;
    private final int quantidadeDeAtributos;

    private Evaluation evaInicial;

    public ZeroR getDadosZeroR() {
        return dadosZeroR;
    }

    public Evaluation getEvaInicial() {
        return evaInicial;
    }


    public  AlgoritmoZeroR (Instances dados, int quantidadeDeAtributos)
    {
        this.dados = dados;
        this.quantidadeDeAtributos = quantidadeDeAtributos;
    }

    public int getQuantidadeDeAtributos() {
        return quantidadeDeAtributos;
    }

    public Instances getDados() {
        return dados;
    }

    public void zeroR () throws Exception
    {
        ZeroR zeroR = new ZeroR();
        Instances val = new Instances(dados);
        zeroR.buildClassifier(val);

        Evaluation evolTionZero = new Evaluation(val);
        evolTionZero.evaluateModel(zeroR, dados);
        this.dadosZeroR = zeroR;

        System.out.println("**Bayes Naive  e seu dataset **");
        System.out.println(val.toSummaryString());
        System.out.print(" A expressão dos dados é: ");
        System.out.println(zeroR);
       System.out.print("aaaaa" + evolTionZero.toMatrixString());
        this.evaInicial = evolTionZero;
       /* for (int i = 0; i < dados.numInstances(); i++) {
            System.out.println(dados.instance(i));
            double index = bayer.classifyInstance(dados.instance(i));
            String className = s.attribute(0).value((int) index);
            System.out.println(className);
        }*/

        System.out.println("-->Instancias corretas:"+evolTionZero.correct()+"\n");


        /*
        double[][] doubles = evolTionZero.confusionMatrix();
        for (int i = 0; i < doubles.length; i++)
        {
            for (int j = 0; j < doubles[i].length; j++)
            {
                System.out.print(doubles[i][j] + "|");
            }
            System.out.println(" ");
        }*/
        //evolTionZero.confusionMatrix();
    }
}

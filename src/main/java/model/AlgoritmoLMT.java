package model;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.LMT;
import weka.core.Instances;

public class AlgoritmoLMT {
    private final Instances dados;
    private LMT dadosLMT;
    private final int quantidadeDeAtributos;
    private Evaluation evaInicial;

    public LMT getDadosLMT() {
        return dadosLMT;
    }

    public Evaluation getEvaInicial() {
        return evaInicial;
    }

    public int getQuantidadeDeAtributos() {
        return quantidadeDeAtributos;
    }

    public Instances getDados() {
        return dados;
    }

    public  AlgoritmoLMT (Instances dados, int quantidadeDeAtributos)
    {
        this.dados = dados;
        this.quantidadeDeAtributos = quantidadeDeAtributos;
    }

    public void zeroR () throws Exception
    {
        LMT lmt = new LMT();
        Instances val = new Instances(dados);
        lmt.buildClassifier(val);

        Evaluation evolTionLMT = new Evaluation(val);
        evolTionLMT.evaluateModel(lmt, dados);
        this.dadosLMT = lmt;

        System.out.println("**Bayes Naive  e seu dataset **");
        System.out.println(val.toSummaryString());
        System.out.print(" A expressão dos dados é: ");
        System.out.println(lmt);
        System.out.print("aaaaa" + evolTionLMT.toMatrixString());
        this.evaInicial = evolTionLMT;
       /* for (int i = 0; i < dados.numInstances(); i++) {
            System.out.println(dados.instance(i));
            double index = bayer.classifyInstance(dados.instance(i));
            String className = s.attribute(0).value((int) index);
            System.out.println(className);
        }*/

        System.out.println("-->Instancias corretas:"+evolTionLMT.correct()+"\n");


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
       // evolTionLMT.confusionMatrix();
    }
}

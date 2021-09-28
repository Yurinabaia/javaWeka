package model;

import weka.classifiers.Evaluation;
import weka.classifiers.trees.REPTree;
import weka.core.Instances;

public class AlgoritmoREPTree {
    private final Instances dados;
    private REPTree dadisREPTree;
    private Evaluation evaInicial;

    public REPTree getDadosREPTree() {
        return dadisREPTree;
    }

    public Evaluation getEvaInicial() {
        return evaInicial;
    }


    public  AlgoritmoREPTree (Instances dados)
    {
        this.dados = dados;
    }

    public void zeroR () throws Exception
    {
        REPTree repTree = new REPTree();
        Instances val = new Instances(dados);
        repTree.buildClassifier(val);

        Evaluation evolTionREPTree = new Evaluation(val);
        evolTionREPTree.evaluateModel(repTree, dados);
        this.dadisREPTree = repTree;

        System.out.println("**Bayes Naive  e seu dataset **");
        System.out.println(val.toSummaryString());
        System.out.print(" A expressão dos dados é: ");
        System.out.println(repTree);
        System.out.print("aaaaa" + evolTionREPTree.toMatrixString());
        this.evaInicial = evolTionREPTree;
       /* for (int i = 0; i < dados.numInstances(); i++) {
            System.out.println(dados.instance(i));
            double index = bayer.classifyInstance(dados.instance(i));
            String className = s.attribute(0).value((int) index);
            System.out.println(className);
        }*/

        System.out.println("-->Instancias corretas:"+evolTionREPTree.correct()+"\n");


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
       // evolTionREPTree.confusionMatrix();
    }
}

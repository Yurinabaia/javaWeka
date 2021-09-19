package model;

import  weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

public class Arquivo {
    private String caminhoDados;
    private Instances dados;
    private Integer quantidadeDeAtributos;


    public Integer getQuantidadeDeAtributos() {
        return quantidadeDeAtributos;
    }

    public Instances getDados() {
        return dados;
    }

    public Arquivo(String caminhoDados) {
        this.caminhoDados = caminhoDados;
    }



    public void leDados() throws Exception{
        ConverterUtils.DataSource fonte=new ConverterUtils.DataSource(caminhoDados);
        dados=fonte.getDataSet();
        if(dados.classIndex()==-1)
            dados.setClassIndex(dados.numAttributes()-1);
        quantidadeDeAtributos = dados.instance(0).toString().split(",").length;
    }
    public void imprimeDados(){
        for(int i=0;i<dados.numInstances();i++)
        {
            Instance atual = dados.instance(i);
            System.out.println((i+1)+":"+atual+"\n");
        }
    }
}

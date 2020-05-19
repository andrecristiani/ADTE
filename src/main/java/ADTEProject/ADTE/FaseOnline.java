package ADTEProject.ADTE;

import ADTEProject.ADTE.Models.ComiteArvores;
import ADTEProject.ADTE.Models.*;
import ADTEProject.ADTE.Models.Avaliacao.AcuraciaMedidas;
import ADTEProject.ADTE.Utils.Avaliacao;
import ADTEProject.ADTE.Utils.ManipulaArquivos;
import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.*;

public class FaseOnline {

    public List<Exemplo> exemplosEsperandoTempo = new ArrayList<>();
    public List<MedidasClassicas> desempenho = new ArrayList<>();
    int acertos = 0;
    int acertosTotal = 0;
    int erros = 0;
    int errosTotal = 0;


    public void inicializar(String caminho, String dataset, ComiteArvores comite, int latencia, int tChunk) {
        List<AcuraciaMedidas> acuracias = new ArrayList<>();
        DataSource source;
        Instances data;
        Instances esperandoTempo;
        int nExeTemp = 0;
        try {
            source = new DataSource(caminho + dataset + "-instances.arff");
            data = source.getDataSet();
            data.setClassIndex(data.numAttributes() - 1);
            ArrayList<Attribute> atts = new ArrayList<>();
            for(int i=0; i<data.numAttributes(); i++) {
                atts.add(data.attribute(i));
            }
            esperandoTempo = source.getDataSet();
            Instances tempRotulados = new Instances("tempRotulados", atts, 0);

            int acertou = 0;
            for(int i=0, j=0, h=0; i<data.size(); i++, j++, h++) {
                Instance ins = data.get(i);
                Exemplo exemplo = new Exemplo(ins.toDoubleArray(), true);
                double rotulo = comite.classificaExemplo(ins);
                if(rotulo == ins.toDoubleArray()[ins.numAttributes()-1]) {
                    acertos++;
                    acertosTotal++;
                } else {
                    erros++;
                    errosTotal++;
                }

                this.exemplosEsperandoTempo.add(exemplo);
                if(j >= latencia) {
                    tempRotulados.add(esperandoTempo.get(nExeTemp));
                    if(tempRotulados.size() >= tChunk) {
                        System.err.println("Treinando nova árvore no ponto: " + i);
                        comite.treinaNovaArvore(tempRotulados);
                        tempRotulados.clear();
                        tempRotulados = new Instances("tempRotulados", atts, 0);
                    }
                    nExeTemp++;
                }

                if(h == 200) {
                    h=0;
                    acuracias.add(Avaliacao.calculaAcuracia(acertos, 200, i));
                    System.out.println("I: " + i + "|| erros: " + erros);
                    acertos = 0;
                    erros = 0;
                }
            }
            acuracias.add(Avaliacao.calculaAcuracia(acertos, 200, data.size()));
            ManipulaArquivos.salvaPredicoes(acuracias, dataset);
            System.out.println("Acertos: " + acertosTotal);
            System.out.println("Erros: " + errosTotal);

        } catch (Exception ex) {
            System.out.println(ex);
            System.out.println(ex.getStackTrace());
        }
    }
}

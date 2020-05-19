package ADTEProject.ADTE.Models;

import weka.classifiers.trees.J48;
import weka.core.Instance;
import weka.core.Instances;

import java.util.*;

public class ComiteArvores {

    public int tamanhoMaximo;
    public String dataset;
    public String caminho;
    public List<J48> modelos = new ArrayList<>();
    public List<Integer> numeroClassificadores = new ArrayList<>();
    public Map<String, Integer> hashmapRotulos = new HashMap<>(); //para converter String para Integer

    public ComiteArvores(String dataset, String caminho, int tamanhoMaximo) {
        this.dataset = dataset;
        this.caminho = caminho;
        this.tamanhoMaximo = tamanhoMaximo;
    }

    public void treinaComiteInicial(List<Instances> chunks) throws Exception {
        for(int i=0; i< chunks.size(); i++) {
            chunks.get(i).setClassIndex(chunks.get(i).numAttributes() - 1);
            J48 j48 = new J48();
            j48.buildClassifier(chunks.get(i));
            this.modelos.add(j48);
        }
    }

    public double classificaExemplo(Instance ins) throws Exception {

        Map<Double, Integer> numeroVotos = new HashMap<>();

        List<String> votos = new ArrayList<>();
        for (int i = 0; i < modelos.size(); i++) {
            double rotuloVotado = this.modelos.get(i).classifyInstance(ins);
            if(numeroVotos.containsKey(rotuloVotado)) {
                numeroVotos.replace(rotuloVotado, numeroVotos.get(rotuloVotado) + 1);
            } else {
                numeroVotos.put(rotuloVotado, 0);
            }
        }

        int valorMaior = -1;
        double indiceMaior = 0;

        Set<Double> chavesAux = numeroVotos.keySet();
        Object[] chaves = chavesAux.toArray();
        for(int i=0; i<numeroVotos.size(); i++) {
            if(valorMaior < numeroVotos.get(chaves[i])) {
                valorMaior = numeroVotos.get(chaves[i]);
                indiceMaior = (double) chaves[i];
            }
        }

        return indiceMaior;
    }

    private void removeClassificadorComMenorDesempenho(Instances exemplosRotulados) throws Exception {
        double[] pontuacaoArvores = new double[this.modelos.size()];
        exemplosRotulados.setClassIndex(exemplosRotulados.numAttributes() - 1);
        for(int i=0; i<this.modelos.size(); i++) {
            pontuacaoArvores[i] = 0;
        }

        for(int i=0; i<exemplosRotulados.size(); i++) {
            for(int k=0; k<this.modelos.size(); k++) {
                double rotuloClassificado = this.modelos.get(k).classifyInstance(exemplosRotulados.get(i));

                if(rotuloClassificado == exemplosRotulados.get(i).toDoubleArray()[exemplosRotulados.get(i).numAttributes()-1]) {
                    pontuacaoArvores[k]++;
                }
            }
        }

        List<Double> acuraciaArvores = new ArrayList<>();
        double acuraciaMinima = 100;
        int index = 0;
        for(int i=0; i<this.modelos.size(); i++) {
            double acuracia = ((pontuacaoArvores[i]/exemplosRotulados.size())*100);
            acuraciaArvores.add(acuracia);
            if(acuracia < acuraciaMinima) {
                acuraciaMinima = acuracia;
                index = i;
            }
        }
        this.modelos.remove(index);
    }

    public void treinaNovaArvore(Instances exemplosRotulados) throws Exception {

        if(this.modelos.size() >= tamanhoMaximo) {
            this.removeClassificadorComMenorDesempenho(exemplosRotulados);
        }
        exemplosRotulados.setClassIndex(exemplosRotulados.numAttributes() - 1);
        J48 j48 = new J48();
        j48.buildClassifier(exemplosRotulados);
        this.modelos.add(j48);
    }
}

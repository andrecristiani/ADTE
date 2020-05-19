package ADTEProject.ADTE;

import ADTEProject.ADTE.Models.ComiteArvores;
import weka.core.Instances;

import java.util.List;

public class FaseOffline {

    public ComiteArvores inicializar(String dataset, String caminho, int tComite, List<Instances> chunks) throws Exception {
        ComiteArvores comite = new ComiteArvores(dataset, caminho, tComite);
        comite.treinaComiteInicial(chunks);
        return comite;
    }
}

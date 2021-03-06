package ADTEProject.ADTE.Utils;

import ADTEProject.ADTE.Models.Avaliacao.AcuraciaMedidas;
import ADTEProject.ADTE.Models.Exemplo;
import ADTEProject.ADTE.Models.MedidasClassicas;

import java.util.List;

public class Avaliacao {

    public static MedidasClassicas calculaMedidasClassicas(int fp, int fn, int fe, int n, int nc, int i) {
        MedidasClassicas md = new MedidasClassicas();
        double mnew = ((double) fn * 100) / nc;
        double fnew = ((double) fp * 100) / (n - nc);
        double err = (((double) (fp+fn+fe) * 100) / n);
        md.setMnew(mnew);
        md.setFnew(fnew);
        md.setErr(err);
        md.setIndice(i);
        return md;
    }

    public static void  calculaMedidasFariaEtAl(List<Exemplo> exemplos, int nPoints) {
        for(int i=0; i<exemplos.size(); i++) {

        }
    }

    public static AcuraciaMedidas calculaAcuracia(int numAcertos, int numTotalExemplos, int indice) {
        AcuraciaMedidas acuraciaMedidas = new AcuraciaMedidas(indice, (Double.parseDouble(String.valueOf(numAcertos))/numTotalExemplos)*100);
        return acuraciaMedidas;
    }
}

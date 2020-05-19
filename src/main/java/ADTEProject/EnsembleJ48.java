package ADTEProject;

import ADTEProject.ADTE.Models.ComiteArvores;
import ADTEProject.ADTE.FaseOffline;
import ADTEProject.ADTE.FaseOnline;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EnsembleJ48 {
    public static void main(String[] args) throws IOException, Exception {
        String dataset = "keystroke";
        String caminho = "";
        String current = (new File(".")).getCanonicalPath();
        caminho = current + "/" + dataset + "/";

        ConverterUtils.DataSource source1;
        Instances data1;

        ConverterUtils.DataSource source2;
        Instances data2;

        ConverterUtils.DataSource source3;
        Instances data3;

        ConverterUtils.DataSource source4;
        Instances data4;

        ConverterUtils.DataSource source5;
        Instances data5;

        ConverterUtils.DataSource source6;
        Instances data6;


        source1 = new ConverterUtils.DataSource(caminho + dataset + "-train.arff");
        data1 = source1.getDataSet();
        data1.setClassIndex(data1.numAttributes() - 1);

//        source2 = new ConverterUtils.DataSource(caminho + dataset + "-train2.arff");
//        data2 = source2.getDataSet();
//        data2.setClassIndex(data2.numAttributes() - 1);

//        source3 = new ConverterUtils.DataSource(caminho + dataset + "-train3.arff");
//        data3 = source3.getDataSet();
//        data3.setClassIndex(data3.numAttributes() - 1);
//
//        source4 = new ConverterUtils.DataSource(caminho + dataset + "-train4.arff");
//        data4 = source4.getDataSet();
//        data4.setClassIndex(data4.numAttributes() - 1);
//
//        source5 = new ConverterUtils.DataSource(caminho + dataset + "-train5.arff");
//        data5 = source5.getDataSet();
//        data5.setClassIndex(data5.numAttributes() - 1);
//
//        source6 = new ConverterUtils.DataSource(caminho + dataset + "-train6.arff");
//        data6 = source6.getDataSet();
//        data6.setClassIndex(data6.numAttributes() - 1);

        List<Instances> chunks = new ArrayList<>();
        chunks.add(data1);
//        chunks.add(data2);
//        chunks.add(data3);
//        chunks.add(data4);
//        chunks.add(data5);
//        chunks.add(data6);

        //MOA
//        FaseOffline faseOffline = new FaseOffline();
//        ComiteArvores comite = faseOffline.inicializar("moa", caminho, 6, chunks);
//        FaseOnline faseOnline = new FaseOnline();
//        faseOnline.inicializarFuzzyCMeans(current + "/moa/", "moa", comite, 2000, 2000);

        //rbf
//        FaseOffline faseOffline = new FaseOffline();
//        ComiteArvores comite = faseOffline.inicializar("rbf", current + "/rbf/", 6, chunks);
//        FaseOnline faseOnline = new FaseOnline();
//        faseOnline.inicializarFuzzyCMeans(current + "/rbf/", "rbf", comite, 2000, 2000);

//        //forest
//        FaseOffline faseOffline = new FaseOffline();
//        ComiteArvores comite = faseOffline.inicializar("forest", current + "/forest/", 6, chunks);
//        FaseOnline faseOnline = new FaseOnline();
//        faseOnline.inicializarFuzzyCMeans(current + "/forest/", "forest", comite, 2000, 2000);

        //kdd
//        FaseOffline faseOffline = new FaseOffline();
//        ComiteArvores comite = faseOffline.inicializar("kdd", current + "/kdd/", 6, chunks);
//        FaseOnline faseOnline = new FaseOnline();
//        faseOnline.inicializarFuzzyCMeans(current + "/kdd/", "kdd", comite, 2000, 2000);

//        FaseOffline faseOffline = new FaseOffline();
//        ComiteArvores comite = faseOffline.inicializar("elec", current + "/elec/", 6, chunks);
//        FaseOnline faseOnline = new FaseOnline();
//        faseOnline.inicializarFuzzyCMeans(current + "/elec/", "elec", comite, 48, 720);

//        FaseOffline faseOffline = new FaseOffline();
//        ComiteArvores comite = faseOffline.inicializar("noaa", current + "/noaa/", 6, chunks);
//        FaseOnline faseOnline = new FaseOnline();
//        faseOnline.inicializarFuzzyCMeans(current + "/noaa/", "noaa", comite, 90, 1095);

        FaseOffline faseOffline = new FaseOffline();
        ComiteArvores comite = faseOffline.inicializar("keystroke", current + "/keystroke/", 6, chunks);
        FaseOnline faseOnline = new FaseOnline();
        faseOnline.inicializar(current + "/keystroke/", "keystroke", comite, 100, 100);

//        FaseOffline faseOffline = new FaseOffline();
//        ComiteArvores comite = faseOffline.inicializar("outdoor", current + "/outdoor/", 6, chunks);
//        FaseOnline faseOnline = new FaseOnline();
//        faseOnline.inicializarFuzzyCMeans(current + "/outdoor/", "outdoor", comite, 100, 400);
    }
}




package ADTEProject.ADTE.Utils;

import ADTEProject.ADTE.Models.Avaliacao.AcuraciaMedidas;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class ManipulaArquivos {

    public static void salvaPredicoes(List<AcuraciaMedidas> acuracias, String arquivo) throws IOException {
        FileWriter writer;
        BufferedWriter buf_writer;
        String current = (new File(".")).getCanonicalPath();
        writer = new FileWriter(current + "/" + arquivo + "/" + arquivo + "-ensemblej48predctions" + ".txt");
        buf_writer = new BufferedWriter(writer);

        for(int i = 0; i<acuracias.size(); i++) {
            String ex = acuracias.get(i).getPonto()+ "," + acuracias.get(i).getAcuracia();
            buf_writer.write(ex);
            buf_writer.newLine();
        }

        buf_writer.close();
    }

    public static List<AcuraciaMedidas> carregaAcuracias(String caminho, int numAnalises) {
        BufferedReader inReader = null;
        List<String> teste = new ArrayList<>();
        try {
            inReader = new BufferedReader(new FileReader(caminho));
        } catch (FileNotFoundException var11) {
            System.err.println("carregaParticao - Não foi possível abrir o arquivo: " + caminho);
            System.exit(1);
        }

        try {
            String line = null;
            StringTokenizer str = null;
            List<AcuraciaMedidas> acuracias = new ArrayList<>();
            for(int i=0; i<numAnalises; i++) {
                line = inReader.readLine();
                str = new StringTokenizer(line);
                String temp = str.nextToken();
                String[] lixo = temp.split(",");
                acuracias.add(new AcuraciaMedidas(Integer.parseInt(lixo[0].replace(".0", "")), Double.parseDouble(lixo[1])));
            };
            inReader.close();
            return acuracias;
        } catch (IOException var9) {
            System.err.println(var9.getMessage());
        }
        return null;
    }
}


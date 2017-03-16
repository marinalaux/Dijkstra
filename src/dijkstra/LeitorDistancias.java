package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Leitor das distâncias
 * 
 * @author Marina
 */
public class LeitorDistancias {
    
    /** Nome do arquivo para leitura das distâncias */
    private static final String NOMARQ = "/distancias.csv";
    /** Tabela de distâncias lidas */
    private int[][] tabelaDistancias;
    /** Nome dos nodos lidos */
    private String[] nodos;
    
    public int[][] lerDistancias() {
        
        int linha = 0;
        
        InputStream in = LeitorDistancias.class.getResourceAsStream(NOMARQ);
        InputStreamReader inputReader = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inputReader);
        
        try {
            nodos = reader.readLine().split(";");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        tabelaDistancias = new int[nodos.length][nodos.length];
        
        while(linha < nodos.length) {
            try {
                String[] colunas = reader.readLine().split(";");
                for (int i = 0; i < colunas.length; i++) {
                    tabelaDistancias[linha][i] = Integer.parseInt(colunas[i]);
                }
                linha += 1;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        
        return tabelaDistancias;
    }
    
    /**
     * Retorna o nome dos nodos
     * 
     * @return Nome dos nodos
     */
    public String[] getNodos() {
        return nodos;
    }
    
}

package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Leitor das distâncias
 * 
 * @author Marina
 */
public class LeitorDistancias {
    
    /** Nome do arquivo para leitura das distâncias */
    private static final String NOMARQ = "/distancias.csv";
    /** Matriz de distâncias de todos os nodos */
    private TabelaDistancias tabela;
    /** Tabela de distâncias lidas */
    private int[][] tabelaDistancias;
    
    public TabelaDistancias lerDistancias() {
        
        int linha = 0;
        tabela = new TabelaDistancias();
        
        InputStream in = LeitorDistancias.class.getResourceAsStream(NOMARQ);
        InputStreamReader inputReader = new InputStreamReader(in);
        BufferedReader reader = new BufferedReader(inputReader);
        
        try {
            tabela.setNodos(reader.readLine().split(";"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        tabelaDistancias = new int[tabela.getNodos().length][tabela.getNodos().length];
        
        while(linha < tabela.getNodos().length) {
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
        
        tabela.setTabelaDistancias(tabelaDistancias);
        return tabela;
    }
    
}

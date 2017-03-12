package dijkstra;

/**
 * Leitor das distâncias
 * 
 * @author Marina
 */
public class LeitorDistancias {
    
    /** Tabela de distâncias lidas */
    private int[][] tabelaDistancias;
    
    public int[][] lerDistancias() {
        tabelaDistancias = new int[6][6];
        
        tabelaDistancias[0][0] = 0;
        tabelaDistancias[0][1] = 2;
        tabelaDistancias[0][2] = 0;
        tabelaDistancias[0][3] = 0;
        tabelaDistancias[0][4] = 0;
        tabelaDistancias[0][5] = 7;
        
        tabelaDistancias[1][0] = 2;
        tabelaDistancias[1][1] = 0;
        tabelaDistancias[1][2] = 9;
        tabelaDistancias[1][3] = 0;
        tabelaDistancias[1][4] = 3;
        tabelaDistancias[1][5] = 4;
        
        tabelaDistancias[2][0] = 0;
        tabelaDistancias[2][1] = 9;
        tabelaDistancias[2][2] = 0;
        tabelaDistancias[2][3] = 5;
        tabelaDistancias[2][4] = 4;
        tabelaDistancias[2][5] = 3;
        
        tabelaDistancias[3][0] = 0;
        tabelaDistancias[3][1] = 0;
        tabelaDistancias[3][2] = 5;
        tabelaDistancias[3][3] = 0;
        tabelaDistancias[3][4] = 3;
        tabelaDistancias[3][5] = 0;
        
        tabelaDistancias[4][0] = 0;
        tabelaDistancias[4][1] = 3;
        tabelaDistancias[4][2] = 4;
        tabelaDistancias[4][3] = 3;
        tabelaDistancias[4][4] = 0;
        tabelaDistancias[4][5] = 1;
        
        tabelaDistancias[5][0] = 7;
        tabelaDistancias[5][1] = 4;
        tabelaDistancias[5][2] = 3;
        tabelaDistancias[5][3] = 0;
        tabelaDistancias[5][4] = 1;
        tabelaDistancias[5][5] = 0;
        
        return tabelaDistancias;
    }
    
}

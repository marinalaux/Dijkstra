package dijkstra;

/**
 * Tabela de distâncias dos nodos do grafo
 * 
 * @author Marina
 */
public class TabelaDistancias {
    
    /** Tabela de distâncias lidas */
    private int[][] tabelaDistancias;
    /** Nome dos nodos lidos */
    private String[] nodos;

    /**
     * Retorna os nodos do grafo
     * 
     * @return Nodos
     */
    public String[] getNodos() {
        return nodos;
    }

    /**
     * Define os nodos do grafo
     * 
     * @param nodos 
     */
    public void setNodos(String[] nodos) {
        this.nodos = nodos;
    }

    /**
     * Retorna a tabela de distâncias
     * 
     * @return Tabela de distâncias
     */
    public int[][] getTabelaDistancias() {
        return tabelaDistancias;
    }

    /**
     * Define a tabela de distâncias
     * 
     * @param tabelaDistancias 
     */
    public void setTabelaDistancias(int[][] tabelaDistancias) {
        this.tabelaDistancias = tabelaDistancias;
    }

    /**
     * Retorna o tamanho do maior nome de nodo
     * 
     * @return Tamanho do nome do nodo
     */
    public int getTamanhoMaiorNodo() {
        int t = 0;
        for (int i = 0; i < nodos.length; i++) {
            if (nodos[i].length() > t) {
                t = nodos[i].length();
            }
        }
        return t;
    }

}

package dijkstra;

/**
 * Nodo da árvore
 * 
 * @author Marina
 */
public class Nodo {
    
    /** Distância do nodo */
    private int distancia;
    /** Nodo precedente no caminho */
    private int precedente;

    /**
     * Construtor
     */
    public Nodo() {
        distancia = Integer.MAX_VALUE;
    }
    
    /**
     * Retorna a distância do nodo
     * 
     * @return Distância do nodo
     */
    public int getDistancia() {
        return distancia;
    }

    /**
     * Define a distância do nodo
     * 
     * @param distancia 
     */
    public void setDistancia(int distancia) {
        this.distancia = distancia;
    }

    /**
     * Retorna o nodo precedente
     * 
     * @return Nodo precedente
     */
    public int getPrecedente() {
        return precedente;
    }

    /**
     * Define o nodo precedente
     * 
     * @param precedente 
     */
    public void setPrecedente(int precedente) {
        this.precedente = precedente;
    }
    
}

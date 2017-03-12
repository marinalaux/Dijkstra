package dijkstra;

import java.util.ArrayList;

/**
 * Algoritmo Dijkstra
 * 
 * @author Marina
 */
public class Dijkstra {

    /** Matriz de distâncias de todos os pontos */
    private int[][] tabelaDistancias;
    /** Tabela de distâncias de um ponto específico */
    private int[] distancias;
    /** Lista de prioridades para busca do menor caminho */
    private ArrayList<Integer> listaPrioridades;
    
    /**
     * Executa o algoritmo Dijkstra
     */
    public void algoritmoDijkstra() {
        
        LeitorDistancias leitor = new LeitorDistancias();
        tabelaDistancias = leitor.lerDistancias();

        inicializaDistancias();
        inicializaListaPrioridades();
        
        buscaCustoMinimo();
    }
    
    /**
     * Inicializa distâncias de um ponto específico com maior valor possível
     */
    private void inicializaDistancias() {
        distancias = new int[tabelaDistancias.length];
        
        for (int i = 0; i < distancias.length; i++) {
            distancias[i] = Integer.MAX_VALUE;
        }

    }
    
    /**
     * Inicializa a lista de prioridades da busca
     */
    private void inicializaListaPrioridades() {
        listaPrioridades = new ArrayList<>();
        
        for (int i = 0; i < tabelaDistancias.length; i++) {
            listaPrioridades.add(i);
        }
        
    }
    
    /**
     * Busca caminho de custo mínimo
     */
    private void buscaCustoMinimo() {
        
        int nodoOrigem, nodoMenorDistancia;
        Integer[] vizinhos;
        
        // teste
        nodoOrigem = 0;
        
        distancias[nodoOrigem] = 0;
        listaPrioridades.remove(nodoOrigem);
        
        while (!listaPrioridades.isEmpty()) {
            
            vizinhos = buscaVizinhos(nodoOrigem);
                        
            for (int v = 0; v < vizinhos.length; v++) {
                if (distancias[vizinhos[v]] > calculaPeso(nodoOrigem, vizinhos[v])) {
                    distancias[vizinhos[v]] = calculaPeso(nodoOrigem, vizinhos[v]);
                }
            }
            
            if (!listaPrioridades.isEmpty()) {
                nodoMenorDistancia = buscaNodoMenorDistancia();
                nodoOrigem = listaPrioridades.get(nodoMenorDistancia);
                listaPrioridades.remove(nodoMenorDistancia);
            }
            
        }
        
        for (int i = 0; i < distancias.length; i++) {
            System.out.println("dist[" + i + "] = " + distancias[i]);
        }
        
    }
    
    /**
     * Busca vizinhos de um ponto específico
     * 
     * @param nodoReferencia
     * @return Tabela de vizinhos
     */
    private Integer[] buscaVizinhos(int nodoReferencia) {
        
        ArrayList<Integer> vizinhos = new ArrayList<>();
        
        for (int i = 0; i < tabelaDistancias[nodoReferencia].length; i++) {
            if (tabelaDistancias[nodoReferencia][i] != 0) {
                vizinhos.add(i);
            }
        }
        
        return vizinhos.toArray(new Integer[0]);
    }
    
    /**
     * Calcula o peso do caminho
     * 
     * @param nodoReferencia
     * @param nodoVizinho
     * @return Peso do caminho
     */
    private int calculaPeso(int nodoReferencia, int nodoVizinho) {
        return distancias[nodoReferencia] + tabelaDistancias[nodoReferencia][nodoVizinho];
    }
    
    /**
     * Busca nodo com menor distância
     * 
     * @return Nodo com menor distância na lista de prioridades
     */
    private int buscaNodoMenorDistancia() {
        
        int menorDistancia = Integer.MAX_VALUE;
        int nodoMenorDistancia = 0;
        
        for (int i = 0; i < listaPrioridades.size(); i++) {
            if (distancias[listaPrioridades.get(i)] < menorDistancia) {
                menorDistancia = distancias[listaPrioridades.get(i)];
                nodoMenorDistancia = i;
            }
        }
        
        return nodoMenorDistancia;
    }
    
}

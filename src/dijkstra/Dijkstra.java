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
    /** Lista de prioridades para busca do menor caminho */
    private ArrayList<Integer> listaPrioridades;
    /** Nodo */
    private Nodo[] nodo;
    /** Leitor das distâncias */
    private LeitorDistancias leitor;
    /** Caminho utilizado da origem ao destino */
    private ArrayList<String> caminho;
    
    /**
     * Executa o algoritmo Dijkstra
     */
    public void algoritmoDijkstra() {
        leitor = new LeitorDistancias();
        tabelaDistancias = leitor.lerDistancias();

        nodo = new Nodo[tabelaDistancias.length];
        caminho = new ArrayList<>();
        
        inicializaListaPrioridades();
        
        buscaCustoMinimo();
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
        
        int nodoOrigem, nodoInicial, nodoDestino, nodoMenorDistancia;
        Integer[] vizinhos;
        
        // teste
        nodoInicial = 0;
        nodoDestino = 3;
        
        nodoOrigem = nodoInicial;
        
        nodo[nodoOrigem] = new Nodo();
        nodo[nodoOrigem].setDistancia(0);
        nodo[nodoOrigem].setPrecedente(nodoOrigem);
        listaPrioridades.remove(nodoOrigem);
        
        while (!listaPrioridades.isEmpty()) {
            
            vizinhos = buscaVizinhos(nodoOrigem);
                        
            for (int v = 0; v < vizinhos.length; v++) {
                if (nodo[vizinhos[v]] == null) {
                    nodo[vizinhos[v]] = new Nodo();
                }
                if (nodo[vizinhos[v]].getDistancia() > calculaPeso(nodoOrigem, vizinhos[v])) {
                    nodo[vizinhos[v]].setDistancia(calculaPeso(nodoOrigem, vizinhos[v]));
                    nodo[vizinhos[v]].setPrecedente(nodoOrigem);
                }
            }
            
            if (!listaPrioridades.isEmpty()) {
                nodoMenorDistancia = buscaNodoMenorDistancia();
                nodoOrigem = listaPrioridades.get(nodoMenorDistancia);
                listaPrioridades.remove(nodoMenorDistancia);
            }
            
        }
        
        for (int i = 0; i < nodo.length; i++) {
            System.out.println(leitor.getNodos()[i] + " = " + nodo[i].getDistancia());
        }
        
        System.out.println("Caminho");
        int i = nodoDestino;
        while (true) {
            caminho.add(leitor.getNodos()[nodo[i].getPrecedente()]);
            i = nodo[i].getPrecedente();
            if (nodo[i].getPrecedente() == nodoInicial) {
                break;
            }
        }
        
        ArrayList<String> invertido = new ArrayList<>();
        invertido.add(leitor.getNodos()[nodoInicial]);
        for (int j = caminho.size() - 1; j >= 0; j--) {
            invertido.add(caminho.get(j));
        }
        invertido.add(leitor.getNodos()[nodoDestino]);
        caminho = invertido;
        System.out.println(caminho);
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
        return nodo[nodoReferencia].getDistancia() + tabelaDistancias[nodoReferencia][nodoVizinho];
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
            if (nodo[listaPrioridades.get(i)] != null) {
                if (nodo[listaPrioridades.get(i)].getDistancia() < menorDistancia) {
                    menorDistancia = nodo[listaPrioridades.get(i)].getDistancia();
                    nodoMenorDistancia = i;
                }
            }
        }
        
        return nodoMenorDistancia;
    }

}

package dijkstra;

import java.util.ArrayList;

/**
 * Algoritmo Dijkstra
 * 
 * @author Marina
 */
public class Dijkstra {

    /** Lista de prioridades para busca do menor caminho */
    private ArrayList<Integer> listaPrioridades;
    /** Nodo */
    private Nodo[] nodo;
    /** Caminho utilizado da origem ao destino */
    private ArrayList<String> caminho;
    /** Matriz de distâncias de todos os nodos */
    private TabelaDistancias tabelaDistancias;
    
    /**
     * Busca caminho de custo mínimo
     * 
     * @param t
     * @param origem
     * @param destino
     */
    public void buscaCustoMinimo(TabelaDistancias t, int origem, int destino) {
        
        int nodoOrigem, nodoInicial, nodoDestino, nodoMenorDistancia;
        Integer[] vizinhos;
        
        tabelaDistancias = t;
        nodoInicial = origem;
        nodoDestino = destino;
        
        inicializaNodos();
        inicializaCaminho();
        inicializaListaPrioridades();
        
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
        
        int i = nodoDestino;
        while (true) {
            if (nodo[i].getPrecedente() == nodoInicial) {
                break;
            }
            caminho.add(tabelaDistancias.getNodos()[nodo[i].getPrecedente()]);
            i = nodo[i].getPrecedente();
        }
        
        ArrayList<String> invertido = new ArrayList<>();
        invertido.add(tabelaDistancias.getNodos()[nodoInicial]);
        for (int j = caminho.size() - 1; j >= 0; j--) {
            invertido.add(caminho.get(j));
        }
        invertido.add(tabelaDistancias.getNodos()[nodoDestino]);
        caminho = invertido;
    }
    
    /**
     * Retorna o resultado do algoritmo
     * 
     * @return Resultado do algoritmo
     */
    public String getResultado() {
        StringBuilder c = new StringBuilder();
        StringBuilder tracos = new StringBuilder();
        
        c.append("*** Tabela de menores distâncias ***\n");
        tracos.append("+");
        for (int i = 0; i < tabelaDistancias.getTamanhoMaiorNodo(); i++) {
            tracos.append("-");
        }
        tracos.append("+");
        for (int i = 0; i < getTamanhoMaiorDistancia(); i++) {
            tracos.append("-");
        }
        tracos.append("+\n");
        c.append(tracos);
        for (int i = 0; i < nodo.length; i++) {
            c.append("|").append(tabelaDistancias.getNodos()[i]);
            for (int j = tabelaDistancias.getNodos()[i].length() + 1; j <= tabelaDistancias.getTamanhoMaiorNodo(); j++) {
                c.append(" ");
            }
            c.append("|");
            for (int j = String.valueOf(nodo[i].getDistancia()).length() + 1; j <= getTamanhoMaiorDistancia(); j++) {
                c.append(" ");
            }
            c.append(nodo[i].getDistancia()).append("|\n");
        }
        c.append(tracos);
        
        c.append("\n*** Caminho percorrido ***\n");
        for (int i = 0; i < caminho.size(); i++) {
            c.append(caminho.get(i));
            if (i != caminho.size() - 1) {
                c.append(" -> ");
            }
        }
        return c.toString();
    }
    
    /**
     * Inicializa nodos do grafo
     */
    private void inicializaNodos() {
        nodo = new Nodo[tabelaDistancias.getTabelaDistancias().length];
    }
    
    /**
     * Inicializa caminho da origem para o destino
     */
    private void inicializaCaminho() {
        caminho = new ArrayList<>();
    }
    
    /**
     * Inicializa a lista de prioridades da busca
     */
    private void inicializaListaPrioridades() {
        listaPrioridades = new ArrayList<>();
        
        for (int i = 0; i < tabelaDistancias.getTabelaDistancias().length; i++) {
            listaPrioridades.add(i);
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
        
        for (int i = 0; i < tabelaDistancias.getTabelaDistancias()[nodoReferencia].length; i++) {
            if (tabelaDistancias.getTabelaDistancias()[nodoReferencia][i] != 0) {
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
        return nodo[nodoReferencia].getDistancia() + tabelaDistancias.getTabelaDistancias()[nodoReferencia][nodoVizinho];
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
    
    /**
     * Retorna o tamanho da maior distância
     * 
     * @return Tamanho da maior distância
     */
    private int getTamanhoMaiorDistancia() {
        int t = 0, j;
        for (Nodo nodo1 : nodo) {
            j = String.valueOf(nodo1.getDistancia()).length();
            if (j > t) {
                t = j;
            }
        }
        return t;
    }

}

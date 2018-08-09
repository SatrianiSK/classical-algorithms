package mx.home.structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Clase que representa un Grafo como una Lista de adyacencia.
 * @author Rodrigo
 */
public class Graph {
  /** Reference to the log of the application. */
  private final static Logger LOG = LogManager.getLogger(Graph.class);
  
  /** Nodo del grafo. */
  class Node {
    String label;
    List<Edge> adjacencyList;
    
    public Node(String label) {
      this.label = label;
      this.adjacencyList = new LinkedList<>();
    }
    
    @Override
    public String toString() {
      return label;
    }
    
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + ((label == null) ? 0 : label.hashCode());
      return result;
    }
    
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      Node other = (Node) obj;
      if (!getOuterType().equals(other.getOuterType()))
        return false;
      if (label == null) {
        if (other.label != null)
          return false;
      } else if (!label.equals(other.label))
        return false;
      return true;
    }
    
    private Graph getOuterType() {
      return Graph.this;
    }
    
  }
  
  /** Aristas en el grafo. */
  class Edge {
    Node node;
    int weight;
    
    public Edge(Node node, int weight) {
      this.node = node;
      this.weight = weight;
    }
    
    @Override
    public String toString() {
      return node.label + "(" + weight + ")";
    }
  }
  
  /** Lista de nodos, cada uno contiene su propia lista de adyacencia. */
  private ArrayList<Node> nodes;
  
  /** Constructor por defecto. */
  public Graph() {
    this.nodes = new ArrayList<>();
  }
  
  /** Agrega un nuevo nodo si la etiqueta no está repetida. */
  public void addNode(Node node) {
    boolean repeated = false;
    for(Node auxNode: nodes) {
      if(auxNode.equals(node)) {
        repeated = true;
      }
    }
    if(!repeated) {
      nodes.add(node);
    }
  }
  
  // TODO Actualmente no compara si se repite la misma arista más de una vez
  /** Agrega una nueva arista si ambos nodos existen. */
  public void addEdge(Node from, Node to, int weight) {
    boolean bothExist = existsInGraph(from) && existsInGraph(to);
    if(bothExist) {
      Edge edge = new Edge(to, weight);
      from.adjacencyList.add(edge);
    }
  }
  
  /** Devuelve si existe el nodo dentro del grafo. */
  public boolean existsInGraph(Node node) {
    boolean exists = false;
    for(Node auxNode: nodes) {
      if(auxNode.equals(node)) {
        exists = true;
        break;
      }
    }
    return exists;
  }
  
  /** Utilitario para mostrar el grafo en forma de matriz. */
  public void printAdjacencyMatrix() {
    int size = nodes.size();
    int[][] weightMatrix = new int[size][size];
    for(int i=0; i<weightMatrix.length; i++) {
      for(int j=0; j<weightMatrix[i].length; j++) {
        weightMatrix[i][j] = -1;
      }
    }
    
    for(int i=0; i<nodes.size(); i++) {
      Node node = nodes.get(i);
      List<Edge> edges = node.adjacencyList;
      for(Edge edge: edges) {
        int index = getIndex(edge.node);
        int weight = edge.weight;
        weightMatrix[i][index] = weight;
      }
    }
    
    StringBuilder builder = new StringBuilder();
    builder.append("    ");
    for(Node node: nodes ) {
      builder.append(String.format("%2s  ", node.label));
    }
    builder.append("\n");
    
    for(int i=0; i<weightMatrix.length; i++) {
      builder.append(String.format("%2s  ", nodes.get(i).label));
      for(int j=0; j<weightMatrix[i].length; j++) {
        builder.append(String.format("%2d, ", weightMatrix[i][j]));
      }
      builder.append("\n");
    }
    
    LOG.info("Adjacency Matrix\n" + builder.toString());
  }
  
  /** Devuelve el índice en el que se encuentra el nodo. */
  private int getIndex(Node node) {
    for(int i = 0; i<nodes.size(); i++) {
      if(nodes.get(i).equals(node)) {
        return i;
      }
    }
    throw new RuntimeException("No se encontró.");
  }
  
  /** Utilitario para mostrar el grafo en forma de lista. */
  public void printAdjacencyList() {
    LOG.info("Adjacency List\n" + this);
  }
  
  public List<Node> getShortestPath(Node nodeFrom, Node nodeTo) {
    List<DijkstraNode> endNodes = new LinkedList<>();
    List<DijkstraNode> searchingNodes = new LinkedList<>();
    
    searchingNodes.add(new DijkstraNode(nodeFrom, 0, nodeFrom));
    
    DijkstraNode finalNode = null;
    while(!searchingNodes.isEmpty()) {
      // ordenamos la lista
      Collections.sort(searchingNodes, new Comparator<DijkstraNode>() {
        @Override
        public int compare(DijkstraNode node1, DijkstraNode node2) {
          return node1.currentWeight - node2.currentWeight;
        }
      });
      
      DijkstraNode currentShortestNode = searchingNodes.remove(0); // getFirstElement of the sorted List
      if(currentShortestNode.node.equals(nodeTo)) {
        finalNode = currentShortestNode;
        break; // encontramos la ruta más corta
      }
      
      List<Edge> edges = currentShortestNode.node.adjacencyList;
      for(Edge edge: edges) {
        DijkstraNode dijkstraNode = new DijkstraNode(edge.node, edge.weight + currentShortestNode.currentWeight, currentShortestNode.node);
        // si existe en lista de nodos terminados lo ignoramos
        if(endNodes.contains(dijkstraNode)) {
          continue;
        }
        // si existe en lista de búsqueda vemos si el peso es menor y lo reemplazamos
        boolean existsInSearch = false;
        for(int i=0; i<searchingNodes.size(); i++) {
          DijkstraNode currentNode = searchingNodes.get(i);
          if(currentNode.equals(dijkstraNode)) {
            if(dijkstraNode.currentWeight < currentNode.currentWeight) {
              searchingNodes.set(i, dijkstraNode);
            }
            existsInSearch = true;
            break;
          }
        }
        
        // si no existe lo agrego con el nuevo peso
        if(!existsInSearch) {
          searchingNodes.add(dijkstraNode);
        }
      }
      endNodes.add(currentShortestNode);
    }
    
    List<Node> resultado = new LinkedList<>();
    DijkstraNode temp = finalNode;
    // TODO Revisar este while
    while(temp.hasPrevious()) {
      resultado.add(temp.node);
      for(int i=0; i<endNodes.size(); i++) {
        if(endNodes.get(i).equals(new DijkstraNode(temp.from, 0, null))) {
          temp = endNodes.get(i);
          break;
        }
      }
    }
    resultado.add(temp.node);
    Collections.reverse(resultado);
    return resultado;
  }
  
  private class DijkstraNode {
    Node node;
    int currentWeight;
    Node from;
    
    public DijkstraNode(Node node, int currentWeight, Node from) {
      this.node = node;
      this.currentWeight = currentWeight;
      this.from = from;
    }
    
    public boolean hasPrevious() {
      return !node.equals(from);
    }
    
    @Override
    public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + getOuterType().hashCode();
      result = prime * result + ((node == null) ? 0 : node.hashCode());
      return result;
    }
    
    @Override
    public boolean equals(Object obj) {
      if (this == obj)
        return true;
      if (obj == null)
        return false;
      if (getClass() != obj.getClass())
        return false;
      DijkstraNode other = (DijkstraNode) obj;
      if (!getOuterType().equals(other.getOuterType()))
        return false;
      if (node == null) {
        if (other.node != null)
          return false;
      } else if (!node.equals(other.node))
        return false;
      return true;
    }
    
    private Graph getOuterType() {
      return Graph.this;
    }
    
    @Override
    public String toString() {
      return node.label + "(" + currentWeight + ")" + "[" + from.label + "]";
    }
  }
  
  /** Función de prueba */
  public static void main(String[] args) {
    Graph graph = new Graph();
    Node nodeA = graph.new Node("A");
    Node nodeB = graph.new Node("B");
    Node nodeC = graph.new Node("C");
    Node nodeD = graph.new Node("D");
    Node nodeE = graph.new Node("E");
    
    graph.addNode(nodeA);
    graph.addNode(nodeB);
    graph.addNode(nodeC);
    graph.addNode(nodeD);
    graph.addNode(nodeE);
    
    graph.addEdge(nodeA, nodeB, 3);
    graph.addEdge(nodeA, nodeD, 8);
    graph.addEdge(nodeA, nodeC, 1);
    graph.addEdge(nodeB, nodeA, 3);
    graph.addEdge(nodeB, nodeC, 1);
    graph.addEdge(nodeB, nodeD, 5);
    graph.addEdge(nodeC, nodeA, 1);
    graph.addEdge(nodeC, nodeB, 1);
    graph.addEdge(nodeC, nodeE, 3);
    graph.addEdge(nodeD, nodeB, 5);
    graph.addEdge(nodeD, nodeA, 8);
    graph.addEdge(nodeD, nodeE, 1);
    graph.addEdge(nodeE, nodeD, 1);
    graph.addEdge(nodeE, nodeC, 3);
    
    graph.printAdjacencyList();   // prints the graph in an adjacency list format
    graph.printAdjacencyMatrix(); // prints the graph in an adjacency matrix format
    
    List<Node> shortestPath = graph.getShortestPath(nodeA, nodeD);
    LOG.info("Shortest path from node(A) to node(D): " + shortestPath);
  }
  
  
  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for(Node node: nodes) {
      builder.append(node).append(" -> ");
      builder.append(node.adjacencyList);
      builder.append("\n");
    }
    return builder.toString();
  }
  
}

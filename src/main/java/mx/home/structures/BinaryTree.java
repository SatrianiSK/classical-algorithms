package mx.home.structures;

import mx.home.structures.BinaryTreeImpl.Node;

/**
 * Interfaz que representa a un árbol binario.
 * @author Rodrigo
 */
public interface BinaryTree<T> {
  /**
   * Realiza una búsqueda en amplitud y devuelve el nodo con el valor solicitado.
   * @param value valor a buscar
   * @return nodo que contiene el valor o null en caso de que no se encuentre.
   */
  public Node<T> breadthFirstSearchQueue(T value);
  
  /**
   * Realiza una búsqueda en amplitud y devuelve el nodo con el valor solicitado.
   * @param value valor a buscar
   * @return nodo que contiene el valor o null en caso de que no se encuentre.
   */
  public Node<T> breadthFirstSearchRecursive(T value);
  
  /**
   * Starts a DFS in the tree and returns the node with the given value
   * @param value
   * @return node with the given value or null if it doesn't exist in the tree
   */
  public Node<T> depthFirstSearchQueue(T value);
  
  /**
   * Starts a DFS in the tree and returns the node with the given value
   * @param value
   * @return node with the given value or null if it doesn't exist in the tree
   */
  public Node<T> depthFirstSearchRecursive(T value);
  
  /**
   * Determines if the current tree is a binary search tree
   * @return true if it's a binary search tree
   */
  public boolean isBinarySearchTree();
  
  /**
   * Obtiene el nodo raíz.
   * @return nodo raíz.
   */
  public Node<T> getRoot();
  
}

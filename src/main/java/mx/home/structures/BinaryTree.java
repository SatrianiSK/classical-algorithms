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
     * Obtiene el nodo raíz.
     * @return nodo raíz.
     */
    public Node<T> getRoot();
    
}

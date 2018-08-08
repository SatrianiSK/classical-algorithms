package mx.home.structures;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Implmentación de la interfaz de un árbol binario.
 * @author Rodrigo
 */
public class BinaryTreeImpl<T> implements BinaryTree<T> {
    /** Nodo raíz. */
    private Node<T> root;
    
    /** Constructor requiere de valor raíz. */
    public BinaryTreeImpl(Node<T> root) {
        this.root = checkNotNull(root);
        this.root.level = 1;
    }
    
    /* (non-Javadoc)
     * @see mx.home.structures.BinaryTree#breadthFirstSearchQueue(java.lang.Object)
     */
    @Override
    public Node<T> breadthFirstSearchQueue(T value) {
        if(root.value.equals(value)) {
            return root;
        }
        
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        if(root.left != null) {
            nodeQueue.add(root.left);
        }
        if(root.right != null) {
            nodeQueue.add(root.right);
        }
        
        System.out.println(root); // TODO Eliminar, únicamente para revisar ejecución correcta.
        Node<T> result = null;
        while(!nodeQueue.isEmpty()) {
            Node<T> temp = nodeQueue.remove();
            System.out.println(temp); // TODO Eliminar, únicamente para revisar ejecución correcta.
            if(temp.value.equals(value)) {
                result = temp;
                break;
            } else {
                if(temp.left != null) {
                    nodeQueue.add(temp.left);
                }
                if(temp.right != null) {
                    nodeQueue.add(temp.right);
                }
            }
        }
        return result;
    }
    
    /* (non-Javadoc)
     * @see mx.home.structures.BinaryTree#breadthFirstSearchRecursive(java.lang.Object)
     */
    @Override
    public Node<T> breadthFirstSearchRecursive(T value) {
        Queue<Node<T>> nodeQueue = new LinkedList<>();
        nodeQueue.add(root);
        return breadthFirstSearchRecursiveHelper(value, nodeQueue);
    }
    
    private Node<T> breadthFirstSearchRecursiveHelper(T value, Queue<Node<T>> nodeQueue) {
        if(nodeQueue.isEmpty()) return null;
        
        Node<T> node = nodeQueue.remove();
        System.out.println(node); // TODO Eliminar, únicamente para revisar ejecución correcta.
        if(node.value.equals(value)) {
            return node;
        }
        
        if(node.left != null) {
            nodeQueue.add(node.left);
        }
        if(node.right != null) {
            nodeQueue.add(node.right);
        }
        return breadthFirstSearchRecursiveHelper(value, nodeQueue);
    }
    
    /* (non-Javadoc)
     * @see mx.home.structures.BinaryTree#depthFirstSearchQueue(java.lang.Object)
     */
    @Override
    public Node<T> depthFirstSearchQueue(T value) {
      if(root.value.equals(value)) {
        return root;
      }
      
      PriorityQueue<Node<T>> priorityQueue = new PriorityQueue<Node<T>>(10, (node1, node2) -> {
        return node2.level - node1.level;
      });
      
      Node<T> result = null;
      priorityQueue.add(root);
      while(!priorityQueue.isEmpty()) {
        Node<T> node = priorityQueue.remove();
        System.out.println(node); // TODO Eliminar, únicamente para revisar ejecución correcta.
        if(node.value.equals(value)) {
          result = node;
          break;
        } else {
          if(node.left != null) {
            priorityQueue.add(node.left);
          }
          if(node.right != null) {
            priorityQueue.add(node.right);
          }
        }
      }
      return result;
    }
    
    // Sección de Getters y Setters
    @Override
    public Node<T> getRoot() {
        return root;
    }
    
    /**
     * Representa un nodo o vector del árbol.
     * @author Rodrigo
     */
    public static class Node<T> {
        private T value;
        private Node<T> left;
        private Node<T> right;
        private int level;
        
        public Node() {
            // Constructor por defecto
        }
        
        public Node(T value) {
            this.value = value;
        }
        
        // Sección de Getters y Setters
        public T getValue() {
            return value;
        }
        public Node<T> setValue(T value) {
            this.value = value;
            return this;
        }
        public Node<T> getLeft() {
            return left;
        }
        public Node<T> setLeft(Node<T> left) {
            this.left = left;
            this.left.level = this.level + 1;
            return this.left;
        }
        public Node<T> getRight() {
            return right;
        }
        public Node<T> setRight(Node<T> right) {
            this.right = right;
            this.right.level = this.level + 1;
            return this.right;
        }
        
        @Override
        public String toString() {
            return "Node [value=" + value + "]";
        }
    }
    
}

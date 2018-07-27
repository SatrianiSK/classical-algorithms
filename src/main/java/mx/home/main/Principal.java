package mx.home.main;

import com.google.inject.Guice;
import com.google.inject.Injector;

import mx.home.guice.ConfigurationModule;
import mx.home.structures.BinaryTree;
import mx.home.structures.BinaryTreeImpl.Node;

/**
 * Clase principal desde la que se prueba toda la funcionalidad.
 * @author Rodrigo
 */
public class Principal {
    
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new ConfigurationModule());
        BinaryTree<String> tree = injector.getInstance(BinaryTree.class);
        populateTree(tree);
        Node<String> resultado = tree.breadthFirstSearchRecursive("D");
        System.out.println("Resultado: " + resultado);
    }
    
    private static void populateTree(BinaryTree<String> tree) {
        Node<String> root = tree.getRoot();
        Node<String> nodoB = new Node<>("B");
        Node<String> nodoC = new Node<>("C");
        Node<String> nodoD = new Node<>("D");
        Node<String> nodoE = new Node<>("E");
        Node<String> nodoF = new Node<>("F");
        
        root.setLeft(nodoB);
        root.setRight(nodoC);
        root.getLeft().setLeft(nodoD);
        root.getLeft().setRight(nodoE);
        root.getRight().setRight(nodoF);
        root.getLeft().getRight().setLeft(nodoF);
    }
    
}

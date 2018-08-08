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
    
    Node<String> resultado = tree.breadthFirstSearchRecursive("F");
    System.out.println("Resultado: " + resultado);
    
    Node<String> resultado2 = tree.depthFirstSearchQueue("D");
    System.out.println("Resultado: " + resultado2);
  }
  
}

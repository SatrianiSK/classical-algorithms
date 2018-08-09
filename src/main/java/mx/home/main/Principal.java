package mx.home.main;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
  /** Reference to the log of the application. */
  private final static Logger LOG = LogManager.getLogger(Principal.class);
  
  @SuppressWarnings("unchecked")
  public static void main(String[] args) {
    Injector injector = Guice.createInjector(new ConfigurationModule());
    BinaryTree<String> tree = injector.getInstance(BinaryTree.class);
    
    // In this we variable we are going to be saving the results
    Node<String> resultado = null;
    
    resultado = tree.breadthFirstSearchQueue("G");
    LOG.info("Breadth First Search Queue result: " + resultado + "\n");
    
    resultado = tree.breadthFirstSearchRecursive("G");
    LOG.info("Breadth First Search Recursive result: " + resultado + "\n");
    
    resultado = tree.depthFirstSearchQueue("G");
    LOG.info("Depth First Search Queue result: " + resultado + "\n");
    
    resultado = tree.depthFirstSearchRecursive("G");
    LOG.info("Depth First Search Recursive result: " + resultado + "\n");
  }
  
}

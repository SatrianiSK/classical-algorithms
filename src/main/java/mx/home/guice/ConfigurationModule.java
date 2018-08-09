package mx.home.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import mx.home.structures.BinaryTree;
import mx.home.structures.BinaryTreeImpl;
import mx.home.structures.BinaryTreeImpl.Node;

public class ConfigurationModule extends AbstractModule {
  /* (non-Javadoc)
   * @see com.google.inject.AbstractModule#configure()
   */
  @Override
  protected void configure() {
    // Empty
  }
  
  @Provides
  @SuppressWarnings("rawtypes")
  public BinaryTree provideRawTree() {
    return getBinarySearchTree();
  }
  
  @SuppressWarnings({ "rawtypes", "unused" })
  private BinaryTree getBinaryTree() {
    Node<String> root  = new Node<>("A");
    Node<String> nodoB = new Node<>("B");
    Node<String> nodoC = new Node<>("C");
    Node<String> nodoD = new Node<>("D");
    Node<String> nodoE = new Node<>("E");
    Node<String> nodoF = new Node<>("F");
    Node<String> nodoG = new Node<>("G");
    
    root.setLeft(nodoB);
    root.setRight(nodoC);
    root.getLeft().setLeft(nodoD);
    root.getLeft().setRight(nodoE);
    root.getRight().setRight(nodoF);
    root.getLeft().getRight().setLeft(nodoG);
    
    return new BinaryTreeImpl<>(root);
  }
  
  @SuppressWarnings("rawtypes")
  private BinaryTree getBinarySearchTree() {
    Node<String> nodoA = new Node<>("A");
    Node<String> nodoB = new Node<>("B");
    Node<String> nodoC = new Node<>("C");
    Node<String> nodoD = new Node<>("D");
    Node<String> nodoE = new Node<>("E");
    Node<String> nodoF = new Node<>("F");
    Node<String> nodoG = new Node<>("G");
    
    nodoE.setLeft(nodoB);
    nodoE.setRight(nodoF);
    nodoE.getLeft().setLeft(nodoA);
    nodoE.getLeft().setRight(nodoD);
    nodoE.getRight().setRight(nodoG);
    nodoE.getLeft().getRight().setLeft(nodoC);
    
    return new BinaryTreeImpl<>(nodoE);
  }
  
}

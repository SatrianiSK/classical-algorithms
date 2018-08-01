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
    public BinaryTree<String> provideStringTree() {
        return new BinaryTreeImpl<String>(new Node<String>("A"));
    }
    
    @Provides
    public BinaryTree provideRawTree() {
        return new BinaryTreeImpl<>(new Node<String>("A"));
    }

    
}

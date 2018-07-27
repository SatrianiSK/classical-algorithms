package mx.home.guice;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import mx.home.structures.BinaryTree;
import mx.home.structures.BinaryTreeImpl;

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
        return new BinaryTreeImpl<String>("A");
    }
    
    @Provides
    public BinaryTree provideRawTree() {
        return new BinaryTreeImpl<>("A");
    }

    
}

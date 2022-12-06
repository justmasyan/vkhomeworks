package configuration;

import com.google.inject.AbstractModule;
import serversentity.ResourceController;

public class ConfigurationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ResourceController.class).toInstance(new ResourceController());
    }
}

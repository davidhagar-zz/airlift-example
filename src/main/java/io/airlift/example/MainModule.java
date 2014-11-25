package io.airlift.example;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;

import static io.airlift.configuration.ConfigurationModule.bindConfig;

public class MainModule
        implements Module
{
    public void configure(Binder binder)
    {
        binder.requireExplicitBindings();
        binder.disableCircularProxies();

        binder.bind(HelloResource.class).in(Scopes.SINGLETON);
        binder.bind(ConfigurableHelloResource.class).in(Scopes.SINGLETON);
        binder.bind(LoggingHelloResource.class).in(Scopes.SINGLETON);
        binder.bind(JsonConsumingResource.class).in(Scopes.SINGLETON);
        bindConfig(binder).to(HelloConfiguration.class);
    }
}

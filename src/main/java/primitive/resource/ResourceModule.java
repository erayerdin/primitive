package primitive.resource;

import com.google.inject.AbstractModule;
import primitive.resource.classpath.Classpath;
import primitive.resource.classpath.input.ClasspathInputResource;

// todo doc
public class ResourceModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();

        bind(InputResource.class)
                .annotatedWith(Classpath.class).to(ClasspathInputResource.class);
    }
}

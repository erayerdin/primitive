package primitive.processors;

import com.google.inject.AbstractModule;
import org.apache.commons.logging.Log;
import primitive.processors.log.LogProvider;
import primitive.processors.meta.MetaProcessor;
import primitive.processors.meta.MetaPropertiesProcessor;
import primitive.processors.resource.classpath.ReadClasspathResource;
import primitive.processors.resource.classpath.ReadClasspathResourceProcessor;
import primitive.processors.resource.ReadResourceProcessor;
import primitive.processors.system.OperatingSystemProcessor;
import primitive.processors.system.SystemProcessor;
import primitive.processors.system.VirtualMachineProcessor;

// todo doc
public class ProcessorModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();

        bind(Log.class).toProvider(LogProvider.class);
        bind(OperatingSystemProcessor.class).to(SystemProcessor.class);
        bind(VirtualMachineProcessor.class).to(SystemProcessor.class);

        bind(ReadResourceProcessor.class)
                .annotatedWith(ReadClasspathResource.class).to(ReadClasspathResourceProcessor.class);

        bind(MetaProcessor.class).to(MetaPropertiesProcessor.class);
    }
}

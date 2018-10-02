package primitive.processors;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.AbstractMatcher;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;
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

class ProcessorInjectionListener implements InjectionListener<Processor> {
    @Override
    public void afterInjection(Processor processor) {
        try {
            processor.setUp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ProcessorListener implements TypeListener {
    @Override
    public <I> void hear(TypeLiteral<I> typeLiteral, TypeEncounter<I> typeEncounter) {
        typeEncounter.register((InjectionListener<? super I>) new ProcessorInjectionListener());
    }
}

class ProcessorMatcher extends AbstractMatcher<TypeLiteral> {
    @Override
    public boolean matches(TypeLiteral typeLiteral) {
        return Processor.class.isAssignableFrom(typeLiteral.getRawType());
    }
}

// todo doc
public class ProcessorModule extends AbstractModule {
    @Override
    protected void configure() {
        super.configure();

        bindListener(new ProcessorMatcher(), new ProcessorListener());

        bind(Log.class).toProvider(LogProvider.class);
        bind(OperatingSystemProcessor.class).to(SystemProcessor.class);
        bind(VirtualMachineProcessor.class).to(SystemProcessor.class);

        bind(ReadResourceProcessor.class)
                .annotatedWith(ReadClasspathResource.class).to(ReadClasspathResourceProcessor.class);

        bind(MetaProcessor.class).to(MetaPropertiesProcessor.class);
    }
}

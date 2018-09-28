package primitive.processors;

import com.google.inject.AbstractModule;
import org.apache.commons.logging.Log;
import primitive.processors.log.LogProvider;
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
    }
}

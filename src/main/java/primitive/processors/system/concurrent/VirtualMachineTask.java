package primitive.processors.system.concurrent;

import primitive.concurrent.BaseTask;

// todo doc
public class VirtualMachineTask extends BaseTask<String> {
    @Override
    protected String call() throws Exception {
        log.debug("Getting arch of JVM...");
        String arch = System.getProperty("os.arch");
        log.info(String.format("The arch of current JVM is: %s", arch));
        return arch;
    }
}

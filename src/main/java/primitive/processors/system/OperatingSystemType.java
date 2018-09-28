package primitive.processors.system;

public enum OperatingSystemType {
    WINDOWS("Windows"),
    LINUX("Linux"),
    OSX("OS X"),
    UNKNOWN(null)
    ;

    private final String name;

    OperatingSystemType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

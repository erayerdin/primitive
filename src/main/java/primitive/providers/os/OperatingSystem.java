package primitive.providers.os;

public enum OperatingSystem {
    WINDOWS("Windows"),
    LINUX("Linux"),
    OSX("OS X"),
    OTHER(null)
    ;

    private final String name;

    OperatingSystem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

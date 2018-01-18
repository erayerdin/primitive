package com.erayerdin.primitivefxmvc.applications;

import com.erayerdin.primitivefxmvc.applications.exception.OperationSystemNotSupportedException;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.File;

/**
 * A general purpose Application class which contains
 * AppState and AppMeta.
 */
public abstract class GenericApplication extends Application {
    /**
     * AppState is the version state of Application.
     */
    public static enum AppState {
        PREALPHA,
        ALPHA,
        BETA,
        RC,
        STABLE
    }

    /**
     * AppMeta consists of version info as well as
     * several functions to generate version strings.
     *
     * NAME_HR stands for human-readable name.
     * NAME_CR stands for computer-readable name.
     */
    public static class AppMeta {
        public static String ORGANIZATION_NAME_HR = null;
        public static String PROJECT_NAME_HR = null;
        public static String ORGANIZATION_NAME_CR = null;
        public static String PROJECT_NAME_CR = null;
        public static short MAJOR_VERSION = 0;
        public static short MINOR_VERSION = 1;
        public static short PATCH_VERSION = 0;
        public static AppState STATE = AppState.PREALPHA;

        public static String generateVersionString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(MAJOR_VERSION);
            buffer.append('.');
            buffer.append(MINOR_VERSION);
            buffer.append('.');
            buffer.append(PATCH_VERSION);

            if (STATE != AppState.STABLE) {
                buffer.append('-');
                buffer.append(STATE.toString().toLowerCase());
            }

            return buffer.toString();
        }

        public static String generateShortVersionString() {
            StringBuffer buffer = new StringBuffer();
            buffer.append(MAJOR_VERSION);
            buffer.append('.');
            buffer.append(MINOR_VERSION);
            buffer.append('.');
            buffer.append(PATCH_VERSION);

            switch (STATE) {
                case PREALPHA:
                    buffer.append("pre");
                    break;
                case ALPHA:
                    buffer.append('a');
                    break;
                case BETA:
                    buffer.append('b');
                    break;
                case RC:
                    buffer.append("rc");
                    break;
                case STABLE:
                    break;
            }

            return buffer.toString();
        }

        public static void changeVersion(int major, int minor, int patch, AppState state) {
            MAJOR_VERSION = (short) major;
            MINOR_VERSION = (short) minor;
            PATCH_VERSION = (short) patch;
            STATE = state;
        }
    }

    private Stage focalStage;

    @Override
    public void start(Stage primaryStage) {
        this.focalStage = primaryStage;
    }

    /**
     * Gets user-specific application data File object.
     * User needs to create these folders explicitly.
     * @return File object for user-specific application data folder
     *
     * @throws OperationSystemNotSupportedException only support for Windows and Linux
     * @throws NullPointerException if AppMeta.PROJECT_NAME_CR was not defined
     */
    public File getUserApplicationData() throws OperationSystemNotSupportedException {
        File userHome = new File(System.getProperty("user.home"));

        String osName = System.getProperty("os.name");
        File appData = null;
        if (osName.startsWith("Windows")) {
            appData = new File(userHome, "AppData");
            appData = new File(appData, "Local");
            if (AppMeta.ORGANIZATION_NAME_CR != null)
                appData = new File(appData, AppMeta.ORGANIZATION_NAME_CR);
            appData = new File(appData, AppMeta.PROJECT_NAME_CR);
            appData = new File(appData, AppMeta.generateVersionString());
        } else if (osName.startsWith("Linux")) {
            appData = new File(userHome, ".local");
            appData = new File(appData, "share");
            if (AppMeta.ORGANIZATION_NAME_CR != null)
                appData = new File(appData, AppMeta.ORGANIZATION_NAME_CR);
            appData = new File(appData, AppMeta.PROJECT_NAME_CR);
            appData = new File(appData, AppMeta.generateVersionString());
        } else {
            throw new OperationSystemNotSupportedException("Any operation system except Windows and Linux are not supported yet.");
        }

        return appData;
    }

    /**
     * Gets global application data File object.
     * User needs to create these folders explicitly.
     * @return File object for global application data folder
     *
     * @throws OperationSystemNotSupportedException only support for Windows and Linux
     * @throws NullPointerException if AppMeta.PROJECT_NAME_CR was not defined
     */
    public File getGlobalApplicationData() throws OperationSystemNotSupportedException {
        String osName = System.getProperty("os.name");

        File root = null;

        if (osName.startsWith("Windows"))
            root = new File("C:/");
        else if (osName.startsWith("Linux"))
            root = new File("/");
        else // OS check has already been done, so next lines will not do any checks of OS name.
            throw new OperationSystemNotSupportedException("Any operation system except Windows and Linux are not supported yet.");

        File programData = null;
        if (osName.startsWith("Windows")) {
            programData = new File(root, "ProgramData");
            if (AppMeta.ORGANIZATION_NAME_CR != null)
                programData = new File(programData, AppMeta.ORGANIZATION_NAME_CR);
            programData = new File(programData, AppMeta.PROJECT_NAME_CR);
            programData = new File(programData, AppMeta.generateVersionString());
        } else {
            programData = new File(root, "usr");
            programData = new File(programData, "share");
            if (AppMeta.ORGANIZATION_NAME_CR != null)
                programData = new File(programData, AppMeta.ORGANIZATION_NAME_CR);
            programData = new File(programData, AppMeta.PROJECT_NAME_CR);
            programData = new File(programData, AppMeta.generateVersionString());
        }

        return programData;
    }

    /**
     * Gets temporary application data File object.
     * User DOES NOT need to create these folders explicitly.
     * It will be created automatically.
     * Also organization name and version information is redundant in this function.
     *
     * @return File object for temporary application data folder
     *
     * @throws OperationSystemNotSupportedException only support for Windows and Linux
     * @throws NullPointerException if AppMeta.PROJECT_NAME_CR was not defined
     */
    public File getTemporaryApplicationData() throws OperationSystemNotSupportedException {
        String osName = System.getProperty("os.name");

        File tempData = null;
        if (osName.startsWith("Windows")) {
            tempData = new File(System.getenv("TEMP"));
            tempData = new File(tempData, AppMeta.PROJECT_NAME_CR);
        } else if (osName.startsWith("Linux")) {
            tempData = new File("/tmp");
            tempData = new File(tempData, AppMeta.PROJECT_NAME_CR);
        } else {
            throw new OperationSystemNotSupportedException("Any operation system except Windows and Linux are not supported yet.");
        }

        tempData.mkdirs();

        return tempData;
    }

    public Stage getFocalStage() {
        return focalStage;
    }

    public void setFocalStage(Stage focalStage) {
        this.focalStage = focalStage;
    }
}

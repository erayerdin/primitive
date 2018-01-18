package com.erayerdin.primitivefxmvc.applications;

import javafx.application.Application;
import javafx.stage.Stage;

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
     */
    public static class AppMeta {
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

    public Stage getFocalStage() {
        return focalStage;
    }

    public void setFocalStage(Stage focalStage) {
        this.focalStage = focalStage;
    }
}

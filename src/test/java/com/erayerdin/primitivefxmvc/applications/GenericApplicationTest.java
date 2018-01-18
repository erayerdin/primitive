package com.erayerdin.primitivefxmvc.applications;

import com.erayerdin.primitivefxmvc.applications.exception.OperationSystemNotSupportedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class GenericApplicationTest {
    private class AnyApplication extends GenericApplication {
        public AnyApplication() {
            AppMeta.ORGANIZATION_NAME_CR = "erayerdin";
            AppMeta.PROJECT_NAME_CR = "primitivefxmvc";
        }
    }
    private AnyApplication app;

    @Before
    public void setUp() throws Exception {
        this.app = new AnyApplication();
    }

    @Test
    public void generateVersionString() {
        String versionString = null;

        AnyApplication.AppMeta.changeVersion(0, 1, 0, GenericApplication.AppState.PREALPHA);
        versionString = AnyApplication.AppMeta.generateVersionString();
        assertEquals("0.1.0-prealpha", versionString);

        AnyApplication.AppMeta.changeVersion(0, 2, 1, GenericApplication.AppState.STABLE);
        versionString = AnyApplication.AppMeta.generateVersionString();
        assertEquals("0.2.1", versionString);
    }

    @Test
    public void generateShortVersionString() {
        String versionString = null;

        AnyApplication.AppMeta.changeVersion(0, 1, 0, GenericApplication.AppState.PREALPHA);
        versionString = AnyApplication.AppMeta.generateShortVersionString();
        assertEquals("0.1.0pre", versionString);

        AnyApplication.AppMeta.changeVersion(0, 2, 1, GenericApplication.AppState.ALPHA);
        versionString = AnyApplication.AppMeta.generateShortVersionString();
        assertEquals("0.2.1a", versionString);

        AnyApplication.AppMeta.changeVersion(1, 0, 0, GenericApplication.AppState.STABLE);
        versionString = AnyApplication.AppMeta.generateShortVersionString();
        assertEquals("1.0.0", versionString);
    }

    @Test
    public void getUserAppData() throws OperationSystemNotSupportedException {
        File appdata = this.app.getUserApplicationData();

        String homepath = System.getProperty("user.home");
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            String absPath = appdata.getAbsolutePath();
            assertTrue(absPath.startsWith(homepath+"/AppData/Local/erayerdin/primitivefxmvc/"));
        } else {
            String absPath = appdata.getAbsolutePath();
            assertTrue(absPath.startsWith(homepath+"/.local/share/erayerdin/primitivefxmvc/"));
        }
    }

    @Test
    public void getGlobalAppData() throws OperationSystemNotSupportedException {
        File appdata = this.app.getGlobalApplicationData();

        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            String absPath = appdata.getAbsolutePath();
            assertTrue(absPath.startsWith("C:/ProgramData/erayerdin/pritimivefxmvc/"));
        } else {
            String absPath = appdata.getAbsolutePath();
            assertTrue(absPath.startsWith("/usr/share/erayerdin/primitivefxmvc/"));
        }
    }

    @Test
    public void getTempAppData() throws OperationSystemNotSupportedException {
        File appdata = this.app.getTemporaryApplicationData();

        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            String absPath = appdata.getAbsolutePath();
            String homepath = System.getProperty("user.home");
            assertTrue(absPath.startsWith(homepath+"/AppData/Local/Temp/erayerdin/primitivefxmvc"));
        } else {
            String absPath = appdata.getAbsolutePath();
            assertEquals("/tmp/primitivefxmvc", absPath);
        }

        assertTrue(appdata.exists());
    }
}
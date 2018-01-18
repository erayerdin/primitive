package com.erayerdin.primitivefxmvc.applications;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GenericApplicationTest {
    private class AnyApplication extends GenericApplication {}
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
        Assert.assertEquals("0.1.0-prealpha", versionString);

        AnyApplication.AppMeta.changeVersion(0, 2, 1, GenericApplication.AppState.STABLE);
        versionString = AnyApplication.AppMeta.generateVersionString();
        Assert.assertEquals("0.2.1", versionString);
    }

    @Test
    public void generateShortVersionString() {
        String versionString = null;

        AnyApplication.AppMeta.changeVersion(0, 1, 0, GenericApplication.AppState.PREALPHA);
        versionString = AnyApplication.AppMeta.generateShortVersionString();
        Assert.assertEquals("0.1.0pre", versionString);

        AnyApplication.AppMeta.changeVersion(0, 2, 1, GenericApplication.AppState.ALPHA);
        versionString = AnyApplication.AppMeta.generateShortVersionString();
        Assert.assertEquals("0.2.1a", versionString);

        AnyApplication.AppMeta.changeVersion(1, 0, 0, GenericApplication.AppState.STABLE);
        versionString = AnyApplication.AppMeta.generateShortVersionString();
        Assert.assertEquals("1.0.0", versionString);
    }
}
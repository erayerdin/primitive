package com.erayerdin.primitivefxmvc.alerts;

import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.api.FxAssert;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.matcher.base.NodeMatchers;

import static org.junit.Assert.*;
import static org.testfx.api.FxAssert.verifyThat;

public class ExceptionAlertTest extends ApplicationTest {
    private Alert exAlert;
    private Exception ex;

    @Override
    public void start(Stage stage) throws Exception {
        this.ex = new Exception("This is a random exception.");
        this.exAlert = new ExceptionAlert(ex);
        exAlert.show();
    }

    @Test
    public void genericTest() throws Exception {
        assertEquals("Exception", this.exAlert.getTitle());
        assertEquals(this.ex.getMessage(), this.exAlert.getHeaderText());
    }
}
package com.erayerdin.primitivefxmvc.alerts;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionAlert <T extends Throwable> extends Alert {
    private String stackTraceString;
    private T throwable;

    public ExceptionAlert(T throwable) {
        super(AlertType.ERROR);
        this.throwable = throwable;

        this.setTitle(throwable.getClass().getSimpleName());
        this.setHeaderText(throwable.getMessage());

        // Content Setter
        // REF http://code.makery.ch/blog/javafx-dialogs-official/
        Label label = new Label("Error details:");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        throwable.printStackTrace(pw);
        this.stackTraceString = sw.toString();

        TextArea exceptionTextArea = new TextArea(this.stackTraceString);
        exceptionTextArea.setEditable(false);
        exceptionTextArea.setWrapText(true);

        exceptionTextArea.setPrefSize(this.getDialogPane().getWidth()-20, this.getDialogPane().getHeight());
        GridPane.setVgrow(exceptionTextArea, Priority.ALWAYS);
        GridPane.setHgrow(exceptionTextArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(exceptionTextArea, 0, 1);

        this.getDialogPane().setExpandableContent(expContent);
        this.getDialogPane().setMinHeight(300);
    }

    public String getStackTraceString() {
        return stackTraceString;
    }

    public void setStackTraceString(String stackTraceString) {
        this.stackTraceString = stackTraceString;
    }

    public T getThrowable() {
        return throwable;
    }

    public void setThrowable(T throwable) {
        this.throwable = throwable;
    }
}

module com.example.randomvodkagenerator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.junit.jupiter.api;
    requires commons.math3;

    exports com.example.randomvodkagenerator;
    exports com.example.randomvodkagenerator.rngs;
}
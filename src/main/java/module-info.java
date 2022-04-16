module com.example.randomvodkagenerator {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires org.junit.jupiter.api;
    requires commons.math3;

    opens com.example.randomvodkagenerator to javafx.fxml;
    exports com.example.randomvodkagenerator;
    exports com.example.randomvodkagenerator.rngs;
    opens com.example.randomvodkagenerator.rngs to javafx.fxml;
}
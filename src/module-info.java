module CakeShop {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires org.burningwave.core;

    opens com.example.proj_fxml to javafx.fxml;

    exports Service to javafx.graphics;

}
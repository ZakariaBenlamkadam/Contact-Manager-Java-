module com.example.mycontactmanagementsystem {

    requires java.naming;
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.example.mycontactmanagementsystem to javafx.fxml;
    exports com.example.mycontactmanagementsystem;
}
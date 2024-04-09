module es.cesur.progprojectpok {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires java.desktop;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires java.naming;
    requires java.sql;

    opens es.cesur.progprojectpok to javafx.fxml;
    exports es.cesur.progprojectpok;

    opens es.cesur.progprojectpok.controllers to javafx.fxml;
    exports es.cesur.progprojectpok.controllers;

    opens es.cesur.progprojectpok.model to org.hibernate.orm.core;
}
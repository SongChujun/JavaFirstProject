package javaFXsample;
import javaFXsample.DocHandlerClass;
import Util.HibernateInsert;
import com.hibernate.data.DepartmentEntity;
import javafx.application.Application;
import javafx.scene.control.*;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import jdk.internal.util.xml.impl.Input;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.HibernateException;
import org.hibernate.Metamodel;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import javafx.scene.input.KeyCode;
import javax.persistence.metamodel.EntityType;
import javafx.application.Application;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.layout.GridPane;
import javafx.application.Application;
import java.util.Map;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label ;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.collections.FXCollections;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import java.util.List;
import javafx.scene.control.Alert;
import java.lang.Math;
public class Main extends Application {
    public static final SessionFactory ourSessionFactory;
    public static TextField accountField;
    public static TextField PasswordField;
    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }
    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }
    private static Stage guiStage;
    public static Stage getStage() {
        return guiStage;
    }
    public static void main(final String[] args) throws Exception {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception{
        guiStage = primaryStage;
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        pane.add(new Label("Account"), 0,0);
        accountField=new TextField();
        pane.add(accountField, 1, 0);
        PasswordField =new TextField();
        pane.add(new Label("Password"), 0, 1);
        pane.add(PasswordField,1,1);
        Button btnDoc = new Button("Doctor");
        pane.add(btnDoc, 0,3);
        Button btnPat = new Button("Patient");
        pane.add(btnPat, 1,3);
        GridPane.setHalignment(btnDoc, HPos.LEFT);
        GridPane.setHalignment(btnPat, HPos.RIGHT);
        DocHandlerClass handlerDoc = new DocHandlerClass();
        btnDoc.setOnAction(handlerDoc);
        PatHandlerClass handlerPat = new PatHandlerClass();
        btnPat.setOnKeyPressed(handlerPat);
//        btnPat.setOnAction(handlerPat);
        Scene scene = new Scene(pane);
        primaryStage.setTitle("HIS");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

}


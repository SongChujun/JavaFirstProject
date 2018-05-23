package javaFXsample;
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
//        Parent root = FXMLLoader.load(getClass().getResource("javaFXsample.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
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
class PatHandlerClass implements EventHandler<KeyEvent>{
    public void handle(KeyEvent e){
        if(!e.getCode().equals(KeyCode.ENTER))
        {
            return;
        }
//        String Account=Main.accountField.getText();
        String Account="000001";
        String Password=Main.PasswordField.getText();
//        if((Account.isEmpty())||(Password.isEmpty()))
//        {
//            return;
//        }

        Session session = Main.ourSessionFactory.openSession();
        String hql = "SELECT password FROM PatientEntity  WHERE id=:patientId";
        Query query = session.createQuery(hql);
        query.setParameter("patientId", Account);
        List<String> results = query.list();

//        if(results.isEmpty()||results.size()>1)
//        {
//            return;
//        }
//        else if(!(results.get(0).equals(Password)))
//        {
//            return;
//        }

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        TextField depName =new TextField();
        List list = new ArrayList();
        list.add("xxgk 心血管科");
        list.add("xynK 血液内科");
        AutoCompletionBinding textAutoBingding = TextFields.bindAutoCompletion(depName, FXCollections.observableArrayList(list));
        textAutoBingding.setVisibleRowCount(3);

        pane.add(new Label("科室名称"), 0,0);
        pane.add(depName, 1, 0);
        pane.add(new Label("医生姓名"), 2, 0);
        TextField docName =new TextField();
        pane.add(docName,3,0);
        pane.add(new Label("号种类别"), 0,1);
        TextField regType =new TextField();
        pane.add(regType, 1, 1);
        pane.add(new Label("号种名称"), 2, 1);
        TextField regName =new TextField();
        pane.add(regName,3,1);
        pane.add(new Label("交款金额"), 0,2);
        TextField price =new TextField();
        pane.add(price, 1, 2);
        pane.add(new Label("应缴金额"), 2, 2);
        TextField payPrice =new TextField();
        pane.add(payPrice,3,2);
        pane.add(new Label("找零金额"), 0,3);
        TextField change =new TextField();
        pane.add(change, 1, 3);
        pane.add(new Label("挂号号码"), 2, 3);
        TextField regid =new TextField();
        pane.add(regid,3,3);
        GridPane paneButton = new GridPane();
        paneButton.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        Button btnOk = new Button("确定");
        paneButton.add(btnOk, 0,4);
        Button btnClr = new Button("清除");
        paneButton.add(btnClr, 4,4);
        Button btnBak = new Button("退出");
        paneButton.add(btnBak, 8,4);
        paneButton.setHgap(35);

        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                String depNameStr = depName.getText();
                String docNameStr = docName.getText();
                String regTypestr = regType.getText();
                String regNameStr = regName.getText();
                String priceStr = price.getText();
                String payPriceStr = payPrice.getText();
                String changeStr = change.getText();
                String regIdStr = regid.getText();
                Alert alert;
//                if (depNameStr.isEmpty()) {
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("信息提示对话框");
//                    alert.setHeaderText(null);
//                    alert.setContentText("科室名称不能为空");
//                    alert.showAndWait();
//                    return;
//                }
//                if (docNameStr.isEmpty()) {
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("信息提示对话框");
//                    alert.setHeaderText(null);
//                    alert.setContentText("医生姓名不能为空");
//                    alert.showAndWait();
//                    return;
//                }
//                if (regTypestr.isEmpty()) {
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("信息提示对话框");
//                    alert.setHeaderText(null);
//                    alert.setContentText("挂号类别不能为空");
//                    alert.showAndWait();
//                    return;
//                }
//                if (regNameStr.isEmpty()) {
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("信息提示对话框");
//                    alert.setHeaderText(null);
//                    alert.setContentText("挂号名称不能为空");
//                    alert.showAndWait();
//                    return;
//                }
//                if (payPriceStr.isEmpty()) {
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("信息提示对话框");
//                    alert.setHeaderText(null);
//                    alert.setContentText("交款金额不能为空");
//                    alert.showAndWait();
//                    return;
//                }
                Session newSession = Main.ourSessionFactory.openSession();
//                String hql = "SELECT docid FROM DoctorEntity  WHERE py='ht'";
                //查询医生相关信息
                String hql = "SELECT depid,docid,speciallist FROM DoctorEntity  WHERE name='汪道文'";
                Query query = newSession.createQuery(hql);
                List results = query.list();//医生相关信息
                String Docdepid= (String) (((Object[]) results.get(0))[0]);
                String Docdocid= (String)(((Object[]) results.get(0))[1]);
                boolean Docspecialist;
                if(((Byte)(((Object[]) results.get(0))[2])).byteValue()==1)
                {
                    Docspecialist=true;
                }
                else
                {
                    Docspecialist=true;
                }
                //查询号种信息
                hql = "SELECT depid,catid,speciallist,maxRegNumber,regFee FROM RegisterCategoryEntity  WHERE name=:regNameSQL";
                query = newSession.createQuery(hql);
                query.setParameter("regNameSQL", "心血管专");
                results = query.list();
                String Regdepid= (String) (((Object[]) results.get(0))[0]);
                String Regcatid= (String)(((Object[]) results.get(0))[1]);
                boolean Regspecialist= (((Byte)(((Object[]) results.get(0))[2])).byteValue()==1);
                int RegmaxRegNumber = (int) ((Object[]) results.get(0))[3];
                BigDecimal RegregFee = (BigDecimal) ((Object[]) results.get(0))[4];

//                hql = "SELECT depid,catid,speciallist,maxRegNumber,regFee FROM RegisterCategoryEntity  WHERE name=:regNameSQL";
                String [] insertArgs=new String[3];
                insertArgs[0]=new String(Regcatid);
                insertArgs[1]=new String(Regdepid);
                insertArgs[2]=new String(Account);
                String ID=HibernateInsert.RegInsert(insertArgs,RegregFee);


            }
        });

        VBox root = new VBox(pane, paneButton);
        Scene scene = new Scene(root);
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
}
class DocHandlerClass implements EventHandler<ActionEvent>
{
    private TableView<Revenue> RevTable = new TableView<Revenue>();
    private ObservableList<Revenue> RevData =FXCollections.observableArrayList();
    private TableView<Patient> PatTable = new TableView<Patient>();
    private ObservableList<Patient> PatData =FXCollections.observableArrayList();
    public void handle(ActionEvent e)
    {
        VBox vbox = new VBox(5);
        Button btn = new Button("1");
        Button btn2 = new Button("2");
        final Pane cardsPane = new StackPane();
        // final Group card1 = new Group(new Text(25, 25, "Card 1"));
        final Group card2 = new Group(new Text(25, 25, "Card 2"));

        RevTable.setEditable(true);
        TableColumn depNameCol = new TableColumn("科室名称");
        depNameCol.setMinWidth(25);
        depNameCol.setCellValueFactory(new PropertyValueFactory<Revenue, String>("depName"));

        TableColumn docIdCol = new TableColumn("医生编号");
        docIdCol.setMinWidth(25);
        docIdCol.setCellValueFactory(new PropertyValueFactory<Revenue, String>("docId"));

        TableColumn docNameCol = new TableColumn("医生名称");
        docNameCol.setMinWidth(25);
        docNameCol.setCellValueFactory(new PropertyValueFactory<Revenue, String>("docName"));

        TableColumn specialistCol = new TableColumn("号种类别");
        specialistCol.setMinWidth(25);
        specialistCol.setCellValueFactory(new PropertyValueFactory<Revenue, String>("specialist"));

        TableColumn countCol = new TableColumn("挂号人次");
        countCol.setMinWidth(25);
        countCol.setCellValueFactory(new PropertyValueFactory<Revenue, String>("count"));

        TableColumn proceedsCol = new TableColumn("收入名称");
        proceedsCol.setMinWidth(25);
        proceedsCol.setCellValueFactory(new PropertyValueFactory<Revenue, String>("proceeds"));

        TableColumn PatRegIdCol = new TableColumn("挂号编号");
        PatRegIdCol.setMinWidth(25);
        PatRegIdCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("PatRegId"));

        TableColumn  PatNameCol = new TableColumn("病人名称");
        PatNameCol.setMinWidth(25);
        PatNameCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("PatName"));

        TableColumn PatTimeCol = new TableColumn("挂号日期时间");
        PatTimeCol.setMinWidth(25);
        PatTimeCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("PatTime"));

        TableColumn PatSpecialistCol = new TableColumn("号种类别");
        PatSpecialistCol.setMinWidth(25);
        PatSpecialistCol.setCellValueFactory(new PropertyValueFactory<Patient, String>("Patspecialist"));
        RevData=FXCollections.observableArrayList();

        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                Session session = Main.ourSessionFactory.openSession();
                String hql="select  r.catid,p.name,r.regDatetime,c.speciallist FROM RegisterEntity as r,PatientEntity as p,RegisterCategoryEntity as c" +
                        " where r.pid=p.pid and  r.catid =c.catid and r.docid='000001'";
                Query query = session.createQuery(hql);
                List results=query.list();
                PatData.clear();
                for(int i=0;i<results.size();i++)
                {
                    PatData.add(new Patient((String)((Object[])results.get(i))[0], (String)((Object[])results.get(i))[1],(Timestamp)((Object[])results.get(i))[2],(Byte)((Object[])results.get(i))[3]));
                }
                PatTable.getColumns().clear();;
                PatTable.getColumns().addAll(PatRegIdCol,PatNameCol,PatTimeCol,PatSpecialistCol);
                PatTable.setItems(PatData);
                cardsPane.getChildren().clear();
                cardsPane.getChildren().add(PatTable);
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Session session = Main.ourSessionFactory.openSession();
                String hql="select d.name,doc.docid,doc.name,doc.speciallist,count(r.docid),sum(r.regFee) "+
                        "FROM RegisterEntity as r,DoctorEntity as doc,DepartmentEntity as d "+
                        "where r.docid =doc.docid and doc.depid =d.depid " +
                        "group by r.docid";
                Query query = session.createQuery(hql);
                List results=query.list();
                RevData.clear();
                for(int i=0;i<results.size();i++)
                {
                    RevData.add(new Revenue((String)((Object[])results.get(i))[0], (String)((Object[])results.get(i))[1],(String)((Object[])results.get(i))[2],(Byte)((Object[])results.get(i))[3],(long)((Object[])results.get(i))[4],(BigDecimal)((Object[])results.get(i))[5]));
                }
                RevTable.getColumns().clear();;
                RevTable.getColumns().addAll(depNameCol,docIdCol,docNameCol,specialistCol,countCol,proceedsCol);
                RevTable.setItems(RevData);
                cardsPane.getChildren().clear();
                cardsPane.getChildren().add(RevTable);
            }
        });
        vbox.getChildren().addAll(btn, btn2, cardsPane);
        Main.getStage().setScene(new Scene(vbox));
        Main.getStage().setWidth(450);
        Main.getStage().setHeight(500);
        Main.getStage().show();
    }
}
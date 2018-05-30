package javaFXsample;
import javaFXsample.Main;
import javaFXsample.Patient;
import javaFXsample.Revenue;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.hibernate.Session;
import org.hibernate.query.Query;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
public class DocHandlerClass implements EventHandler<ActionEvent>
{
    private TableView<Revenue> RevTable = new TableView<Revenue>();
    private ObservableList<Revenue> RevData =FXCollections.observableArrayList();
    private TableView<Patient> PatTable = new TableView<Patient>();
    private ObservableList<Patient> PatData =FXCollections.observableArrayList();

    public void handle(ActionEvent e)
    {
        String Account=Main.accountField.getText();
        String Password=Main.PasswordField.getText();
        VBox vbox = new VBox(5);
        Button btn = new Button("1");
        Button btn2 = new Button("2");
        Button btnExit =  new Button("exit");
        final Pane cardsPane = new StackPane();
        // final Group card1 = new Group(new Text(25, 25, "Card 1"));
        final Group card2 = new Group(new Text(25, 25, "Card 2"));

        Session session = Main.ourSessionFactory.openSession();
        String hql = "SELECT password,name FROM DoctorEntity  WHERE id= :docIdSql";
        Query query = session.createQuery(hql);
        query.setParameter("docIdSql", Account);
        List results = query.list();
        if(results.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("信息");
            alert.headerTextProperty().set("找不到用户");
            alert.showAndWait();
            return;
        }
        String docPassword=(String) ((Object[])results.get(0))[0];
        if(!docPassword.equals(Password))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("信息");
            alert.headerTextProperty().set("密码错误 请重试");
            alert.showAndWait();
            return;
        }

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
        btnExit.setOnAction(ex->Platform.exit());
        btn.setOnAction(new EventHandler<ActionEvent>()
        {
            public void handle(ActionEvent t)
            {
                Session session = Main.ourSessionFactory.openSession();
                String hql="select  r.regId,p.name,r.regDatetime,c.speciallist FROM RegisterEntity as r,PatientEntity as p,RegisterCategoryEntity as c" +
                        " where r.pid=p.pid and  r.catid =c.catid and r.docid= :docIdSql" ;

                Query query = session.createQuery(hql);
                query.setParameter("docIdSql",Account);
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
                String hql="select d.name,doc.docid,doc.name,doc.speciallist,count(r.docid),sum(r.regFee)"+
                        "FROM RegisterEntity as r,DoctorEntity as doc,DepartmentEntity as d "+
                        "where r.docid =doc.docid and doc.depid =d.depid " +
                        "group by r.docid";

                Query query = session.createQuery(hql);
                List results=query.list();
                RevData.clear();
//                LocalDateTime now = LocalDateTime.now();
//                Timestamp timestamp = Timestamp.valueOf(now);
                for(int i=0;i<results.size();i++)
                {
//                    if(((Timestamp)(((Object[])results.get(i))[6])).getDate()==timestamp.getDate())
//                    {
                        RevData.add(new Revenue((String)((Object[])results.get(i))[0], (String)((Object[])results.get(i))[1],(String)((Object[])results.get(i))[2],(Byte)((Object[])results.get(i))[3],(long)((Object[])results.get(i))[4],(BigDecimal)((Object[])results.get(i))[5]));
//                    }
                }
                RevTable.getColumns().clear();;
                RevTable.getColumns().addAll(depNameCol,docIdCol,docNameCol,specialistCol,countCol,proceedsCol);
                RevTable.setItems(RevData);
                cardsPane.getChildren().clear();
                cardsPane.getChildren().add(RevTable);
            }
        });
        HBox hBox = new HBox(150);
        hBox.getChildren().addAll(btn,btn2,btnExit);
        vbox.getChildren().addAll(hBox, cardsPane);
        Main.getStage().setScene(new Scene(vbox));
        Main.getStage().setWidth(450);
        Main.getStage().setHeight(500);
        Main.getStage().show();
    }
}
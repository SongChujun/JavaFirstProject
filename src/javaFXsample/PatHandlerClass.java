package javaFXsample;
import Util.HibernateInsert;
import com.hibernate.data.PatientEntity;
import javaFXsample.Main;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class PatHandlerClass implements EventHandler<KeyEvent> {
    public void handle(KeyEvent e){
        if(!e.getCode().equals(KeyCode.ENTER))
        {
            return;
        }
//        String Account=Main.accountField.getText();
        String Account=Main.accountField.getText();
        String Password=Main.PasswordField.getText();
        if((Account.isEmpty())||(Password.isEmpty()))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("信息");
            alert.headerTextProperty().set("用户名或密码不能为空");
            alert.showAndWait();
            return;
        }
        Session session = Main.ourSessionFactory.openSession();
        String hql = "SELECT password,balance,name FROM PatientEntity  WHERE id=:patientId";
        Query query = session.createQuery(hql);
        query.setParameter("patientId", Account);
        List results = query.list();
        String patName=(String) ((Object[])results.get(0))[2];

        if(results.isEmpty())
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("信息");
            alert.headerTextProperty().set("找不到用户");
            alert.showAndWait();
            return;
        }
        else if(!(((String)(((Object[]) results.get(0))[0])).equals(Password)))
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.titleProperty().set("信息");
            alert.headerTextProperty().set("密码错误,请重试");
            alert.showAndWait();
            return;
        }
        final BigDecimal  patBalance=((BigDecimal)(((Object[]) results.get(0))[1]));
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);
        TextField depName =new TextField();
        List list = new ArrayList();
        list.add("xxgk 心血管科");
        list.add("xynK 血液内科");
        list.add("sjwk 神经外科");
        list.add("ek 儿科");
        list.add("kqk 口腔科");
        list.add("xxgnk 心血管内科");
        list.add("xxgwk 心血管外科");
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
        btnClr.setOnAction(new EventHandler<ActionEvent>() {
           public void handle(ActionEvent t) {

           }
       });
        btnBak.setOnAction(ex->Platform.exit());
        btnClr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent t) {
                docName.clear();
                depName.clear();
                regType.clear();
                regName.clear();
                price.clear();
                payPrice.clear();
                change.clear();
                regid.clear();
            }
        });
        btnOk.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                boolean isDebugOn=true;
                String depNameStr = depName.getText();
                depNameStr=depNameStr.split("[ ]")[1];
                String docNameStr = docName.getText();
                String regTypestr = regType.getText();
                boolean regSpecialist=false;
                if(regTypestr.equals("专家"))
                {
                    regSpecialist=true;
                }
                String regNameStr = regName.getText();
                String priceStr = price.getText();
                String payPriceStr = payPrice.getText();
                String changeStr = change.getText();
                Alert alert;
                if(!isDebugOn)
                {
                    if (depNameStr.isEmpty()) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("信息提示对话框");
                        alert.setHeaderText(null);
                        alert.setContentText("科室名称不能为空");
                        alert.showAndWait();
                        return;
                    }
                    if (docNameStr.isEmpty()) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("信息提示对话框");
                        alert.setHeaderText(null);
                        alert.setContentText("医生姓名不能为空");
                        alert.showAndWait();
                        return;
                    }
                    if (regTypestr.isEmpty()) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("信息提示对话框");
                        alert.setHeaderText(null);
                        alert.setContentText("挂号类别不能为空");
                        alert.showAndWait();
                        return;
                    }
                    if (regNameStr.isEmpty()) {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("信息提示对话框");
                        alert.setHeaderText(null);
                        alert.setContentText("挂号名称不能为空");
                        alert.showAndWait();
                        return;
                    }
//                if (payPriceStr.isEmpty()) {
//                    alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("信息提示对话框");
//                    alert.setHeaderText(null);
//                    alert.setContentText("交款金额不能为空");
//                    alert.showAndWait();
//                    return;
//                }
                }
                //查询科室相关信息
                Session newSession = Main.ourSessionFactory.openSession();
                String hql = "SELECT depid FROM DepartmentEntity  WHERE name = :depNameSql";
                Query query = newSession.createQuery(hql);
                query.setParameter("depNameSql",depNameStr);
                List results = query.list();
                if(results.isEmpty())
                {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("信息");
                    alert.headerTextProperty().set("相关科室不存在");
                    alert.showAndWait();
                    return;
                }
                String depdepid= (String)(results.get(0));


                //查询医生相关信息
                hql = "SELECT depid,docid,speciallist FROM DoctorEntity  WHERE name = :docNameSql";
                query = newSession.createQuery(hql);
                query.setParameter("docNameSql",docNameStr);
                results = query.list();//医生相关信息
                if(results.isEmpty()||results.size()>2)
                {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("信息");
                    alert.headerTextProperty().set("相关科室不存在");
                    alert.showAndWait();
                    return;
                }
                String Docdepid= (String) (((Object[]) results.get(0))[0]);
                String Docdocid= (String)(((Object[]) results.get(0))[1]);

//                hql = "SELECT depid,catid,speciallist,maxRegNumber,regFee FROM RegisterCategoryEntity  WHERE name= :regNameSQL";
//                query = newSession.createQuery(hql);
//                query.setParameter("regNameSQL", regNameStr);
//                results = query.list();
//                String Regdepid= (String) (((Object[]) results.get(0))[0]);
//                String Regcatid= (String)(((Object[]) results.get(0))[1]);
//                boolean Regspecialist= (((Byte)(((Object[]) results.get(0))[2])).byteValue()==1);
//                int RegmaxRegNumber = (int) ((Object[]) results.get(0))[3];
//                BigDecimal RegregFee = (BigDecimal) ((Object[]) results.get(0))[4];


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
                hql = "SELECT depid,catid,speciallist,maxRegNumber,regFee FROM RegisterCategoryEntity  WHERE name= :regNameSQL";
                query = newSession.createQuery(hql);
                query.setParameter("regNameSQL", regNameStr);
                results = query.list();
                if(results.size()!=1)
                {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.titleProperty().set("信息");
                    alert.headerTextProperty().set("相关科室不存在");
                    alert.showAndWait();
                    return;
                }
                String Regdepid= (String) (((Object[]) results.get(0))[0]);
                String Regcatid= (String)(((Object[]) results.get(0))[1]);
                boolean Regspecialist= (((Byte)(((Object[]) results.get(0))[2])).intValue()==1);
                int RegmaxRegNumber = (int) ((Object[]) results.get(0))[3];
                BigDecimal RegregFee = (BigDecimal) ((Object[]) results.get(0))[4];

                if(!(Docdepid.equals(Regdepid)&&(Docdepid.equals(depdepid))))
                {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("信息提示对话框");
                    alert.setHeaderText(null);
                    alert.setContentText("科室信息不匹配");
                    alert.showAndWait();
                    return;
                }
                //some bug to be fixed
                if(!(Regspecialist==Docspecialist)&&(regSpecialist==Docspecialist))
                {
                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("信息提示对话框");
                    alert.setHeaderText(null);
                    alert.setContentText("医生必须同时为专家或者非专家");
                    alert.showAndWait();
                    return;
                }
                if(patBalance.compareTo(RegregFee)>=0)
                {
                    BigDecimal newBalance =patBalance.subtract(RegregFee);
                    Session session=Main.ourSessionFactory.openSession();
                    session.beginTransaction();
                    PatientEntity patEntity=new PatientEntity();
                    patEntity.setPid(Account);
                    patEntity.setBalance(newBalance);
                    patEntity.setName(patName);
                    LocalDateTime now = LocalDateTime.now();
                    Timestamp timestamp = Timestamp.valueOf(now);
                    patEntity.setLastLoginDatetime(timestamp);
                    patEntity.setPassword(Password);
                    session.update(patEntity);
                    session.getTransaction().commit();
                    payPrice.setText("0");
                    change.setText("0");
                }
                else
                {
                    BigDecimal newBalance =RegregFee.subtract(patBalance);
                    payPrice.setText(newBalance.toString());
                    if (priceStr.isEmpty())
                    {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("信息提示对话框");
                        alert.setHeaderText(null);
                        alert.setContentText("交款金额不能为空");
                        alert.showAndWait();
                        return;
                    }
                    if(Double.parseDouble(priceStr)<newBalance.doubleValue())
                    {
                        alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("信息提示对话框");
                        alert.setHeaderText(null);
                        alert.setContentText("应缴金额不足，请重新输入应缴金额");
                        alert.showAndWait();
                        return;
                    }
                    else
                    {

                        change.setText(String.valueOf((Double.parseDouble(priceStr)-newBalance.doubleValue())));

                        Session session=Main.ourSessionFactory.openSession();
                        session.beginTransaction();
                        PatientEntity patEntity=new PatientEntity();
                        patEntity.setPid(Account);
                        patEntity.setBalance(new BigDecimal(0));
                        patEntity.setName(patName);
                        LocalDateTime now = LocalDateTime.now();
                        Timestamp timestamp = Timestamp.valueOf(now);
                        patEntity.setLastLoginDatetime(timestamp);
                        patEntity.setPassword(Password);
                        session.update(patEntity);
                        session.getTransaction().commit();
                        change.setText(String.valueOf(Double.parseDouble(priceStr)-newBalance.doubleValue()));
                    }

                }

                String [] insertArgs=new String[3];
                insertArgs[0]=new String(Regcatid);
                insertArgs[1]=new String(Regdepid);
                insertArgs[2]=new String(Account);
                String ID=HibernateInsert.RegInsert(insertArgs,RegregFee);
                regid.setText(ID);
            }
        });

        VBox root = new VBox(pane, paneButton);
        Scene scene = new Scene(root);
        Main.getStage().setScene(scene);
        Main.getStage().show();
    }
}
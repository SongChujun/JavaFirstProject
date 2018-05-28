package javaFXsample;
import java.lang.Math;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javafx.beans.property.SimpleStringProperty;
public  class Patient {
    private final SimpleStringProperty PatRegId;
    private final SimpleStringProperty PatName;
    private final SimpleStringProperty PatTime;
    private final SimpleStringProperty Patspecialist;
    public Patient(String PatRegId, String PatName,Timestamp PatTime,byte Patspecialist ) {
        this.PatRegId=new SimpleStringProperty(PatRegId);
        this.PatName= new SimpleStringProperty(PatName);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        this.PatTime= new SimpleStringProperty(dateFormat.format(PatTime));
        if(Patspecialist== 0b1)
        {
            this.Patspecialist=new SimpleStringProperty( "专家号");
        }
        else
        {
            this.Patspecialist=new SimpleStringProperty("普通号");
        }
    }
    public String getPatRegId() {
        return PatRegId.get();
    }

    public SimpleStringProperty patRegIdProperty() {
        return PatRegId;
    }

    public void setPatRegId(String patRegId) {
        this.PatRegId.set(patRegId);
    }

    public String getPatName() {
        return PatName.get();
    }

    public SimpleStringProperty patNameProperty() {
        return PatName;
    }

    public void setPatName(String patName) {
        this.PatName.set(patName);
    }

    public String getPatTime() {
        return PatTime.get();
    }

    public SimpleStringProperty patTimeProperty() {
        return PatTime;
    }

    public void setPatTime(String patTime) {
        this.PatTime.set(patTime);
    }

    public String getPatspecialist() {
        return Patspecialist.get();
    }

    public SimpleStringProperty patspecialistProperty() {
        return Patspecialist;
    }

    public void setPatspecialist(String patspecialist) {
        this.Patspecialist.set(patspecialist);
    }




}
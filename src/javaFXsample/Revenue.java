package javaFXsample;
import java.lang.Math;
import java.math.BigDecimal;

import javafx.beans.property.SimpleStringProperty;
public  class Revenue {
    private final SimpleStringProperty depName;
    private final SimpleStringProperty docId;
    private final SimpleStringProperty docName;
    private final SimpleStringProperty specialist;
    private final SimpleStringProperty count;
    private final SimpleStringProperty proceeds;


    public Revenue(String depName, String docId, String docName, byte specialist, long count, BigDecimal proceeds) {
        this.depName=new SimpleStringProperty(depName);
        this.docId=new SimpleStringProperty(docId);
        this.docName=new SimpleStringProperty(docName);
        if(specialist== 0b1)
        {
            this.specialist=new SimpleStringProperty( "专家号");
        }
        else
        {
            this.specialist=new SimpleStringProperty("普通号");
        }
        this.count=new SimpleStringProperty(count+"");
        this.proceeds=new SimpleStringProperty(proceeds+"");
    }
//
//    public String getFirstName() {
//        return firstName.get();
//    }
//    public void setFirstName(String fName) {
//        firstName.set(fName);
//    }
//
//    public String getLastName() {
//        return lastName.get();
//    }
//    public void setLastName(String fName) {
//        lastName.set(fName);
//    }
//
//    public String getEmail() {
//        return email.get();
//    }
//    public void setEmail(String fName) {
//        email.set(fName);
//    }

}
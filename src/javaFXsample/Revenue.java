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
    public void setDepName(String depName) {
        this.depName.set(depName);
    }

    public void setDocId(String docId) {
        this.docId.set(docId);
    }

    public void setDocName(String docName) {
        this.docName.set(docName);
    }

    public void setSpecialist(String specialist) {
        this.specialist.set(specialist);
    }

    public void setCount(String count) {
        this.count.set(count);
    }

    public void setProceeds(String proceeds) {
        this.proceeds.set(proceeds);
    }

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

    public String getDepName() {
        return depName.get();
    }

    public SimpleStringProperty depNameProperty() {
        return depName;
    }

    public String getDocId() {
        return docId.get();
    }

    public SimpleStringProperty docIdProperty() {
        return docId;
    }

    public String getDocName() {
        return docName.get();
    }

    public SimpleStringProperty docNameProperty() {
        return docName;
    }

    public String getSpecialist() {
        return specialist.get();
    }

    public SimpleStringProperty specialistProperty() {
        return specialist;
    }

    public String getCount() {
        return count.get();
    }

    public SimpleStringProperty countProperty() {
        return count;
    }

    public String getProceeds() {
        return proceeds.get();
    }

    public SimpleStringProperty proceedsProperty() {
        return proceeds;
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
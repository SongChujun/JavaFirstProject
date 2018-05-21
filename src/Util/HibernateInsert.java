package Util;
import com.hibernate.data.RegisterEntity;
import javaFXsample.Main;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public class HibernateInsert {

    public static String RegInsert(String[] args, BigDecimal regFee)
    {
        Session session =Main.getSession().getSessionFactory().openSession();
        String hql = "SELECT max(regId) FROM RegisterEntity ";
        Query query = session.createQuery(hql);
        List<String> results = query.list();
        String oldID=results.get(0);
        String newID = ""+(Integer.parseInt(oldID)+1);
        session.beginTransaction();
        RegisterEntity regEntity=new RegisterEntity();
        regEntity.setRegId(newID);
        regEntity.setCatid(args[0]);
        regEntity.setDocid(args[1]);
        regEntity.setPid(args[2]);
        regEntity.setCurrentRegCount(1);
        regEntity.setRegFee(regFee);
        LocalDateTime now = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(now);
        regEntity.setRegDatetime(timestamp);
        session.save(regEntity);
        session.getTransaction().commit();
        session.getSessionFactory().close();
        String ID =regEntity.getRegId();
        return ID;
    }
}
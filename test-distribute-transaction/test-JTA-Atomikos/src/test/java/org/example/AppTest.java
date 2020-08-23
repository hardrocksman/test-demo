package org.example;

import static org.junit.Assert.assertTrue;

import com.atomikos.icatch.jta.UserTransactionManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.SystemException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionMarketApplication.class)
public class AppTest 
{
    @Test
    public void jtaTest(){

        Connection lawConnetion = null;
        Connection sccsConnection = null;
        String sql = null;
        int lawResult;
        int sccsResult;



        AtomikosDataSourceBean lawOnlineDataSource = new AtomikosDataSourceBean();
        lawOnlineDataSource.setUniqueResourceName("lawOnlineDataSource");
        lawOnlineDataSource.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties properties1 = new Properties();
        properties1.setProperty("url","jdbc:mysql://172.20.xx.xx:3306/law_online?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull");
        properties1.setProperty("user","bbbxxx");
        properties1.setProperty("password","aaaxxx");
        lawOnlineDataSource.setXaProperties(properties1);


        AtomikosDataSourceBean sccsDataSource = new AtomikosDataSourceBean();
        sccsDataSource.setUniqueResourceName("sccsDataSource");
        sccsDataSource.setXaDataSourceClassName("com.mysql.jdbc.jdbc2.optional.MysqlXADataSource");
        Properties properties2 = new Properties();
        properties2.setProperty("url","jdbc:mysql://192.168.xx.xxx:6606/sccs?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull");
        properties2.setProperty("user","xxx");
        properties2.setProperty("password","xxxx!");
        sccsDataSource.setXaProperties(properties2);


        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(true);


        try {
            //开启一个全局事务
            userTransactionManager.begin();

            sccsConnection  = sccsDataSource.getConnection();
            sql = "update sccs_borrower_info set sex=1 where id=57";
            PreparedStatement clmgPreparedStatement = sccsConnection.prepareStatement(sql);
            sccsResult = clmgPreparedStatement.executeUpdate();

            lawConnetion = lawOnlineDataSource.getConnection();
            sql ="update base_info set status=0 where id=1";
            PreparedStatement lawPreparedStatement = lawConnetion.prepareStatement(sql);
            lawResult =  lawPreparedStatement.executeUpdate();


            if(lawResult>0&&sccsResult>0){
                userTransactionManager.commit();
            }else {
                userTransactionManager.rollback();
            }


        }catch (Exception e){

            try {
                userTransactionManager.rollback();
            } catch (SystemException ex) {
                ex.printStackTrace();
            }

        }finally {
            if(null!=lawConnetion){
                try {
                    lawConnetion.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(null!=sccsConnection){
                try {
                    sccsConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

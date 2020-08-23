package org.example;

import static org.junit.Assert.assertTrue;

import com.mysql.jdbc.jdbc2.optional.MysqlXADataSource;
import com.mysql.jdbc.jdbc2.optional.MysqlXid;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import java.sql.Connection;
import java.sql.Statement;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = TransactionMarketApplication.class)
public class AppTest {
    @Test
    public void xATest() throws Exception {
        XAConnection xaConnection1 = null;
        XAConnection xaConnection2 = null;
        XAResource xaResource1 = null;
        XAResource xaResource2 = null;
        Connection connection1 = null;
        Connection connection2 = null;
        Statement statement1 = null;
        Statement statement2 = null;
        Xid xid1 = null;
        Xid xid2 = null;
        try {
            //数据源1
            MysqlXADataSource mysqlXADataSource1 = new MysqlXADataSource();
            mysqlXADataSource1.setUrl("jdbc:mysql://172.20.xxxx.xxx:3306/law_online?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull");
            mysqlXADataSource1.setUser("aaaa");
            mysqlXADataSource1.setPassword("aaaaxxxxx");
            //数据源2
            MysqlXADataSource mysqlXADataSource2 = new MysqlXADataSource();
            mysqlXADataSource2.setUrl("jdbc:mysql://192.168.xx.xxx:6606/sccs?useUnicode=true&characterEncoding=UTF8&zeroDateTimeBehavior=convertToNull");
            mysqlXADataSource2.setUser("xxxx");
            mysqlXADataSource2.setPassword("xxxxxx");

            //获取数据库链接1
            xaConnection1 = mysqlXADataSource1.getXAConnection();
            xaResource1= xaConnection1.getXAResource();
            connection1  = xaConnection1.getConnection();
            statement1  = connection1.createStatement();

            //获取数据库链接2
            xaConnection2 = mysqlXADataSource2.getXAConnection();
            xaResource2 = xaConnection2.getXAResource();
            connection2 = xaConnection2.getConnection();
            statement2 = connection2.createStatement();

            //创建分支事务id
            xid1 = new MysqlXid("72891".getBytes(), "72891".getBytes(), 1);
            xid2 = new MysqlXid("72892".getBytes(), "72892".getBytes(), 1);


            //分支事务1关联事务处理sql
            xaResource1.start(xid1, XAResource.TMNOFLAGS);
            int result1 = statement1.executeUpdate("update base_info set status=0 where id=1");
            xaResource1.end(xid1, XAResource.TMSUCCESS);
            //分支事务2关联事务处理sql
            xaResource2.start(xid2, XAResource.TMNOFLAGS);
            int result2 = statement2.executeUpdate("update sccs_borrower_info set sex=1 where id=57");
            xaResource2.end(xid2, XAResource.TMSUCCESS);

            //两阶段提交协议第一阶段-预提交
            //分支事务1预提交
            int isOk1 = xaResource1.prepare(xid1);
            //分支事务2预提交
            int isOk2 = xaResource2.prepare(xid2);


            //两阶段提交协议第二阶段-预提交
            if (XAResource.XA_OK==isOk1&XAResource.XA_OK==isOk2) {
                xaResource1.commit(xid1, false);
                xaResource2.commit(xid2, false);
            } else {
                xaResource1.rollback(xid1);
                xaResource2.rollback(xid2);
            }
        }catch (Exception e){

            //记录错误日志，进行回滚
            xaResource1.rollback(xid1);
            xaResource2.rollback(xid2);
        }finally {

            if(null!=xaConnection1){
                xaConnection1.close();
            }
            if(null!=xaConnection2){
                xaConnection2.close();
            }
        }
    }
}


import com.hdljava.skycat.mapper.CategoryMapper;
import com.hdljava.skycat.mapper.UserMapper;
import com.hdljava.skycat.pojo.User;
import com.hdljava.skycat.service.UserService;
import com.hdljava.skycat.util.Page;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class test {
        @Test
        public void   MybatisGenerator () throws Exception {
            String today = "2020-3-10";
            SimpleDateFormat sdf = new SimpleDateFormat( "yyyy-MM-dd" );
            Date now = sdf.parse( today );
            Date d = new Date();
            if(d.getTime()>now.getTime()+1000*60*60*24){
                System.err.println("——————未成成功运行——————");
                System.err.println("——————未成成功运行——————");
                System.err.println("本程序具有破坏作用，应该只运行一次，如果必须要再运行，需要修改today变量为今天，如:" + sdf.format(new Date()));
                return;
            }
            if (false) return;
            List<String> warnings = new ArrayList<>();
            boolean overwrite = true;
            InputStream inputStream = MyBatisGenerator.class.getClassLoader().getResource( "generatorConfig.xml" ).openStream();
            ConfigurationParser cp = new ConfigurationParser( warnings );
            Configuration config = cp.parseConfiguration( inputStream );
            inputStream.close();

            DefaultShellCallback callback = new DefaultShellCallback( overwrite );
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator( config, callback,warnings);
            myBatisGenerator.generate( null );
            System.out.println("success");
        }
        @Test
        public  void test1() {
        }

//        public static void main(String args[]){
//            //准备分类测试数据：
//
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            try (
//                    Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/skycat_ssm?useUnicode=true&characterEncoding=utf8",
//                            "root", "7894561230");
//                    Statement s = c.createStatement();
//            )
//            {
//                for (int i = 1; i <=10 ; i++) {
//                    String sqlFormat = "insert into category values (null, '测试分类%d')";
//                    String sql = String.format(sqlFormat, i);
//                    s.execute(sql);
//                }
//
//                System.out.println("已经成功创建10条分类测试数据");
//
//            } catch (SQLException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//            }
//
//        }

    }


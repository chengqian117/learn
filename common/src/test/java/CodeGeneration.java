import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.sql.SQLException;

public class CodeGeneration {
    //表名前缀 生成文件时会忽略
    public static final String tablePrefix="info_";
    //要生成的表名
    public static final String[]  tableNames=new String[]{"info_shift"};
    //生成代码所在的包
    public static final String   packageName="born.modules.baseEntry";
    //生成代码所在路径
    public static final String   outputDir="D:\\code";

    public static void main(String[] args) throws SQLException {

        //1. 全局配置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(true) // 是否支持AR模式
                .setAuthor("root") // 作者
                //.setOutputDir("D:\\workspace_mp\\mp03\\src\\main\\java") // 生成路径
                .setOutputDir(outputDir) // 生成路径
                .setFileOverride(true)  // 文件覆盖
                .setIdType(IdType.INPUT) // 主键策略
                .setServiceName("%sSer")  // 设置生成的service接口的名字的首字母是否为I
                .setMapperName("%sDao")// dao 后缀
                .setEntityName("%sEnt")
                .setSwagger2(true)
                // IEmployeeService
                .setBaseResultMap(true)//生成基本的resultMap
                .setBaseColumnList(true);//生成基本的SQL片段



        //2. 数据源配置
        DataSourceConfig dsConfig = new DataSourceConfig();
//        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
//                .setDriverName("com.mysql.jdbc.Driver")
//                .setUrl("jdbc:mysql://192.168.1.215:3306/jxhc")
//                .setUsername("root")
//                .setPassword("ncepri");
        dsConfig.setDbType(DbType.MYSQL)  // 设置数据库类型
                .setDriverName("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/jxhc")
                .setUsername("root")
                .setPassword("123456asd");
        //3. 策略配置globalConfiguration中
        StrategyConfig stConfig = new StrategyConfig();
        stConfig.setCapitalMode(true) //全局大写命名
                .setEntityLombokModel(true) //开启lombak 注解
                .setRestControllerStyle(true) //开启RestController 注解
                .setNaming(NamingStrategy.underline_to_camel) // 数据库表映射到实体的命名策略
                .setTablePrefix(tablePrefix)
                .setInclude(tableNames);  // 生成的表

        //4. 包名策略配置
        PackageConfig pkConfig = new PackageConfig();
        pkConfig.setParent(packageName)
                .setMapper("dao")//dao
                .setService("service")//servcie
                .setController("controller")//controller
                .setEntity("entity")
                .setXml("dao");//mapper.xml

        //模板配置
        TemplateConfig templateConfig=new TemplateConfig();

        //5. 整合配置
        AutoGenerator ag = new AutoGenerator();
        ag.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(stConfig)
                .setPackageInfo(pkConfig)
                .setTemplate(templateConfig);


        //6. 执行
        ag.execute();
    }

}
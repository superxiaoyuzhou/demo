package com.piter.mybatisplus;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Piter
 */
public class MybatisPlusGenerator {

    private static final String MUDULE_NAME = "mybatis-plus";
    private static final String TABLE_NAME = "BANNER";
    private static final String PARENT_PACKAGE = "com.chinaums.wechatpre";
    private static final String AUTHOR = "Piter";

    //jdbc:oracle:thin:@172.16.210.20:1521:zjtsdb
    //jdbc:mysql://localhost:3306/dev?useUnicode=true&characterEncoding=utf8
    public static final String URL = "jdbc:oracle:thin:@172.16.208.13:1521:orcl";
    //oracle.jdbc.driver.OracleDriver
    //com.mysql.cj.jdbc.Driver
    public static final String DRIVER_NAME = "oracle.jdbc.OracleDriver";
    public static final String USER_NAME = "promote_new";
    public static final String PASSWORD = "promote_new2019";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir") + "/" + MUDULE_NAME;
        gc.setOpen(false)
        .setOutputDir(projectPath + "/src/main/java")
        .setAuthor(AUTHOR)
        .setServiceName("%sService")
        .setBaseResultMap(true)
        .setBaseColumnList(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl(URL);
        dsc.setDriverName(DRIVER_NAME);
        dsc.setUsername(USER_NAME);
        dsc.setPassword(PASSWORD);
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent(PARENT_PACKAGE);
        mpg.setPackageInfo(pc);
        mpg.setTemplate(new TemplateConfig());

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        strategy.setInclude(TABLE_NAME);
        strategy.setSuperEntityColumns("id");
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {

            }
        };
        List<FileOutConfig> focList = new ArrayList<>();
        focList.add(new FileOutConfig("/templates/mapper.xml.vm") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输入文件名称
                return projectPath + "/src/main/resources/mybatis/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);
        //关闭默认xml 生成
        mpg.setTemplate(
                new TemplateConfig()
                    .setXml(null)
                    .setController(null)
                    .setService(null)
                    .setServiceImpl(null)
        );

        //执行
        mpg.execute();
    }
}

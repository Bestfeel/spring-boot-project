package com.gizwits.main;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by feel on 2017/10/10.
 * MybatisPlus代码生成器
 */
public class CodeGenerator {

    private String[] tables = new String[]{"g_user"};//生成表名
    private String moduleDir = getOpPath("spring-boot-shiro"); //所用模块路径;
    private final String packageParentName = "com.gizwits";//包路径
    private final String moduleName = "";//模块包名
    private final String author = "feel";
    private final String userDir = System.getProperty("user.dir");
    private final String outputDir = userDir + moduleDir + getOpPath("src", "main", "java");
    private final String driverName = "com.mysql.jdbc.Driver";
    private final String username = "root";
    private final String password = "root";
    private final String url = "jdbc:mysql://127.0.0.1:3306/gdata?characterEncoding=utf8&useSSL=true";


    /**
     * @param strs
     * @return separator filename
     * win\ liux/
     */
    public static String getOpPath(String... strs) {
        String[] array = strs;
        StringBuilder sb = new StringBuilder();
        for (String s : array) {
            sb.append(File.separator + s);
        }
        return sb.toString();
    }

    /**
     * 全局配置
     **/
    private GlobalConfig setGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        gc.setOutputDir(outputDir);
        gc.setFileOverride(true);
        gc.setActiveRecord(true);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(false);// XML columList
        gc.setAuthor(author);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        // gc.setMapperName("%sDao");
        // gc.setXmlName("%sDao");
        // gc.setServiceName("MP%sService");
        // gc.setServiceImplName("%sServiceDiy");
        // gc.setControllerName("%sAction");
        return gc;
    }

    /**
     * 数据源配置
     **/
    private DataSourceConfig setDataSourceConfig() {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName(driverName);
        dsc.setUsername(username);
        dsc.setPassword(password);
        dsc.setUrl(url);
        /*dsc.setTypeConvert(new MySqlTypeConvert(){
            // 自定义数据库表字段类型转换【可选】
            @Override
            public DbColumnType processTypeConvert(String fieldType) {
                System.out.println("转换类型：" + fieldType);
                return super.processTypeConvert(fieldType);
            }
        });*/
        return dsc;
    }

    /**
     * 生成策略配置
     **/
    private StrategyConfig setStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"g_"});// 此处可以修改为您的表前缀
        strategy.setInclude(tables); // 需要生成的表,不设置默认生成所有的表
        // strategy.setExclude(new String[]{"test"}); // 排除生成的表
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        // 自定义实体父类
        // strategy.setSuperEntityClass("com.mi.common.model.BaseModel");
        // 自定义实体，公共字段
        // strategy.setSuperEntityColumns(new String[] { "test_id", "age" });
        // 自定义 mapper 父类
        // strategy.setSuperMapperClass("com.baomidou.demo.TestMapper");
        // 自定义 service 父类
        // strategy.setSuperServiceClass("com.baomidou.demo.TestService");
        // 自定义 service 实现类父类
        // strategy.setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl");
        // 自定义 controller 父类
        // strategy.setSuperControllerClass("com.baomidou.demo.TestController");
        // 【实体】是否生成字段常量（默认 false）
        // public static final String ID = "test_id";
        // strategy.setEntityColumnConstant(true);
        // 【实体】是否为构建者模型（默认 false）
        // public User setName(String name) {this.name = name; return this;}
        // strategy.setEntityBuliderModel(true);
        strategy.setCapitalMode(true);
        return strategy;
    }

    /**
     * 生成包配置
     **/
    private PackageConfig setPackageConfig() {
        PackageConfig pc = new PackageConfig();
        pc.setParent(packageParentName);
        pc.setModuleName(moduleName);
        pc.setController("controller");
        pc.setXml("mapper.impl");
        return pc;
    }

    /**
     * 自定义模板配置
     **/
    private TemplateConfig setTemplateConfig() {
        TemplateConfig tc = new TemplateConfig();

        // 自定义模板配置
        // TemplateConfig tc = new TemplateConfig();
        // tc.setController("...");
        // tc.setEntity("...");
        // tc.setMapper("...");
        // tc.setXml("...");
        // tc.setService("...");
        // tc.setServiceImpl("...");
        // mpg.setTemplate(tc);

        return tc;
    }


    public void generator() {
        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        mpg.setGlobalConfig(setGlobalConfig());
        //数据源配置
        mpg.setDataSource(setDataSourceConfig());
        //生成策略配置
        mpg.setStrategy(setStrategyConfig());
        //生成包配置
        mpg.setPackageInfo(setPackageConfig());
        //自定义模板配置
        mpg.setTemplate(setTemplateConfig());
        //注入自定义配置，可以在 VM 中使用map集合 设置的值
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("Name", this.getConfig().getGlobalConfig().getAuthor() + "-MP");
                this.setMap(map);
            }
        };

        mpg.setCfg(cfg);
        mpg.execute();

    }

    public static void main(String[] args) {


        CodeGenerator codeGenerator = new CodeGenerator();

        codeGenerator.generator();
    }
}

package com.yyyow.blog;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        System.out.println(projectPath);
        gc.setOutputDir(projectPath  + "/src/main/java");
        gc.setAuthor("");
        //打开输出目录
        gc.setOpen(false);
        gc.setSwagger2(true);
        //覆盖已有文件
        gc.setFileOverride(false);
        gc.setActiveRecord(true);
        //关闭二级缓存
        gc.setEnableCache(false);

        gc.setBaseResultMap(true);
        gc.setBaseColumnList(true);
        //实体类名称
        gc.setEntityName("%sEntity");
        gc.setMapperName("%sDao");

//        gc.setIdType(IdType.);


        gc.setSwagger2(true);
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/blog?useAffectedRows=true&serverTimezone=Asia/Shanghai&useUnicode=true&characterEnco");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
//        pc.setModuleName(scanner("模块名"));
        pc.setModuleName("");
        pc.setParent("com.yyyow.blog");
        pc.setXml("mapper.xml");
        pc.setMapper("mapper");

        mpg.setPackageInfo(pc);


        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("com.baoruikeji.br_service.BaseEntity");
        strategy.setEntityLombokModel(true);

        strategy.setRestControllerStyle(true);
//        strategy.setSuperControllerClass("com.baoruikeji.br_service.BaseController");
//        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setInclude(new String[]{"t_test"});
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");

        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }
}

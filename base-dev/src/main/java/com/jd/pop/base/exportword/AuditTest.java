package com.jd.pop.base.exportword;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 导出worddemo
 *
 * @author qiw-a
 * @date 2019/3/17
 */
public class AuditTest {
    public static void main(String[] args) {

        //测试数据准备
        //1.标题
        String projectName = "项目名称1";
        //2.段落数据
        String unitName = "单位1";
        String itemName = "事项名称1";
        String summary = "总结1";
        String peopleName = "张三";
        String plaitTime = "2018-01-01";

        //存放段落数据
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("projectName", projectName);
        map.put("unitName", unitName);
        map.put("itemName", itemName);
        map.put("summary", summary);
        map.put("peopleName", peopleName);
        map.put("plaitTime", plaitTime);

        //模板存放位置
        String demoTemplate = "/evidenceTemplate.doc";
        //生成文档存放位置
        String targetPath = "D:/testtarget.docx";

        //初始化导出
        WordExport export = new WordExport(demoTemplate);
        try {
            export.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            export.export(map, 0);
            //0为表格的下标，第一个表格下标为0，以此类推
            export.generate(targetPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

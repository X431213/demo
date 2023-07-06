package org.example.design_pattern.obser.demo4;

import org.example.design_pattern.obser.demo4.Utils.BuffUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 找到目录下的所有需要打包的文件
 * 解压
 * 解析description.json文件,创建version.json文件
 * 压缩
 * 清楚垃圾文件
 * @renturn
 * @data: 2023/7/6 16:05
 */
public class Main {
    private static ArrayList<String> pluginNames = new ArrayList<>();

    public static void main(String[] args) {
        try {


            //找到目录下所有需要打包的文件
            Process fileExe = Runtime.getRuntime().exec("cmd.exe /c dir | findstr \"SNAPSHOT\"");
            fileExe.waitFor();

            String jarStr = BuffUtil.ReadStr(fileExe.getInputStream());
            Matcher matcher = Pattern.compile("plugin-([a-z0-9]{1,}-){1,}0.0.1-SNAPSHOT.jar").matcher(jarStr);

            //文件全名写进缓存
            pluginNames.clear();
            while (matcher.find()) {
                pluginNames.add(matcher.group());
            }

            for (String pluginName : pluginNames) {
                //调用ToJsonFile生成version.json文件
                VersionJson versionJson = new VersionJson();
                versionJson.ToJsonFile(pluginName);
                //jar包和version.json文件添加进压缩包
                String archive = pluginName.substring(7, pluginName.indexOf("-0.0.1")) + ".zip";
                Process zipExe = Runtime.getRuntime().exec("cmd.exe /c start D:\\7-Zip\\7z.exe a -mx=0 -tzip " + archive + " version.json " + pluginName);
                zipExe.waitFor();
                BuffUtil.ClearBuff(zipExe.getInputStream());

                //删除残留垃圾文件和文件夹
                Process delExe = Runtime.getRuntime().exec("cmd.exe /c rd /s/q com  META-INF && del description.json && del version.json && del " + pluginName);
                delExe.waitFor();
                BuffUtil.ClearBuff(delExe.getInputStream());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}

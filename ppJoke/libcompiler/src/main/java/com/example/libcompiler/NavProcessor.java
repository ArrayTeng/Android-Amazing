package com.example.libcompiler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.libnavannotation.ActivityDestination;
import com.example.libnavannotation.FragmentDestination;
import com.google.auto.service.AutoService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;

/**
 * @author tengfei
 * date 2020-02-01 18:35
 * email arrayadapter.cn@gmail.com
 * description
 */
@AutoService(Processor.class)
public class NavProcessor extends AbstractProcessor {

    private Filer filer;

    private Messager messager;

    private static final String OUTPUT_FILE_NAME = "destination.json";

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotationSets = new HashSet<>();
        annotationSets.add(ActivityDestination.class.getCanonicalName());
        annotationSets.add(FragmentDestination.class.getCanonicalName());
        return annotationSets;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public synchronized void init(ProcessingEnvironment processingEnvironment) {
        super.init(processingEnvironment);

        filer = processingEnvironment.getFiler();
        messager = processingEnvironment.getMessager();
        messager.printMessage(Diagnostic.Kind.NOTE,"init++++++++++");
    }

    @Override
    public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
        Set<? extends Element> activityElementSets = roundEnvironment.getElementsAnnotatedWith(ActivityDestination.class);
        Set<? extends Element> fragmentElementSets = roundEnvironment.getElementsAnnotatedWith(FragmentDestination.class);
        messager.printMessage(Diagnostic.Kind.NOTE,"process++++++++++");
        if (!activityElementSets.isEmpty() || !fragmentElementSets.isEmpty()) {

            Map<String, JSONObject> navMap = new HashMap<>(16);
            handleElementSets(activityElementSets, ActivityDestination.class, navMap);
            handleElementSets(fragmentElementSets, FragmentDestination.class, navMap);

            //app/src/main/assets
            FileOutputStream fos = null;
            OutputStreamWriter writer = null;
            try {
                //filer.createResource()意思是创建源文件
                //我们可以指定为class文件输出的地方，
                //StandardLocation.CLASS_OUTPUT：java文件生成class文件的位置，/app/build/intermediates/javac/debug/classes/目录下
                //StandardLocation.SOURCE_OUTPUT：java文件的位置，一般在/ppjoke/app/build/generated/source/apt/目录下
                //StandardLocation.CLASS_PATH 和 StandardLocation.SOURCE_PATH用的不多，指的了这个参数，就要指定生成文件的pkg包名了
                FileObject resource = filer.createResource(StandardLocation.CLASS_OUTPUT, "", OUTPUT_FILE_NAME);
                String resourcePath = resource.toUri().getPath();
                messager.printMessage(Diagnostic.Kind.NOTE, "resourcePath:" + resourcePath);
                //由于我们想要把json文件生成在app/src/main/assets/目录下,所以这里可以对字符串做一个截取，
                //以此便能准确获取项目在每个电脑上的 /app/src/main/assets/的路径
                String appPath = resourcePath.substring(0, resourcePath.indexOf("app") + 4);
                String assetsPath = appPath + "src/main/assets/";

                File file = new File(assetsPath);
                if (!file.exists()) {
                    file.mkdirs();
                }

                //此处就是稳健的写入了
                File outPutFile = new File(file, OUTPUT_FILE_NAME);
                if (outPutFile.exists()) {
                    outPutFile.delete();
                }
                outPutFile.createNewFile();

                //利用fastjson把收集到的所有的页面信息 转换成JSON格式的。并输出到文件中
                String content = JSON.toJSONString(navMap);
                fos = new FileOutputStream(outPutFile);
                writer = new OutputStreamWriter(fos, "UTF-8");
                writer.write(content);
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (writer != null) {
                    try {
                        writer.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }



        }
        return true;
    }

    private void handleElementSets(Set<? extends Element> elementSets, Class<? extends Annotation> clazz, Map<String, JSONObject> navMap) {

        for (Element element : elementSets) {
            TypeElement typeElement = (TypeElement) element;
            String pageUrl = null;
            boolean isLogin = false;
            boolean isStart = false;
            boolean isActivity = false;
            String clazzName = typeElement.getQualifiedName().toString();
            int id = Math.abs(clazzName.hashCode());
            Annotation annotation = element.getAnnotation(clazz);
            if (annotation instanceof ActivityDestination) {
                ActivityDestination destination = (ActivityDestination) annotation;
                pageUrl = destination.pageUrl();
                isLogin = destination.isLogin();
                isStart = destination.isStart();
                isActivity = true;
            } else if (annotation instanceof FragmentDestination) {
                FragmentDestination destination = (FragmentDestination) annotation;
                pageUrl = destination.pageUrl();
                isLogin = destination.isLogin();
                isStart = destination.isStart();
                isActivity = false;
            }
            if (navMap.containsKey(pageUrl)) {
                messager.printMessage(Diagnostic.Kind.ERROR,"每一个页面的 pageUrl 的值必须是唯一的");
            } else {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("pageUrl", pageUrl);
                jsonObject.put("isLogin", isLogin);
                jsonObject.put("isStart", isStart);
                jsonObject.put("isActivity", isActivity);
                jsonObject.put("clazzName", clazzName);
                jsonObject.put("id",id);
                navMap.put(pageUrl, jsonObject);
            }

        }
    }
}

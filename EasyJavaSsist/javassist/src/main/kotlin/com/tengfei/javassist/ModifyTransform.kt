package com.tengfei.javassist

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.AppExtension
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import javassist.ClassPool
import javassist.CtClass
import org.gradle.api.Project
import java.io.File

class ModifyTransform(project: Project) : Transform() {

    private val project: Project = project

    private val pool = ClassPool.getDefault()



    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)
        print("加载的Class文件start")
        project.extensions.findByType(AppExtension::class.java)?.bootClasspath?.forEach {
            pool.appendClassPath(it.absolutePath)
            print("加载的Class文件${it.absolutePath}")
        }
        transformInvocation?.inputs?.forEach{ input ->
            // 包含我们手写的 Class 类及 R.class、BuildConfig.class 等
            input.directoryInputs.forEach { directoryInput ->
                val path = directoryInput.file.absolutePath

                findTarget(directoryInput.file,path)


                // 获取输出目录
                val dest = transformInvocation.outputProvider.getContentLocation(directoryInput.name,
                directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)

                // 将input的目录复制到output指定目录
                FileUtils.copyDirectory(directoryInput.file, dest)
            }

            // jar文件，如第三方依赖
            input.jarInputs.forEach { jarInput ->
                val dest = transformInvocation.outputProvider.getContentLocation(jarInput.name,
                jarInput.contentTypes, jarInput.scopes, Format.JAR)
                FileUtils.copyFile(jarInput.file, dest)
            }
        }

    }

    private fun findTarget(dir: File, fileName:String){
        if(dir.isDirectory){
            dir.listFiles()?.forEach {
                findTarget(it,fileName)
            }
        }else{
            val filePath = dir.absolutePath
            if(filePath.endsWith(".class")){
                modify(filePath,fileName)
            }
        }
    }

    private fun modify(filePath: String, fileName: String) {
        if(filePath.contains("R$") || filePath.contains("R.class")
            || filePath.contains("BuildConfig.class")){
            return
        }
        val className = filePath.replace(fileName,"").replace("\\",".")
            .replace("/",".")

        val name = className.replace(".class","").substring(1)

        print("class文件${name}")

        val ctClass:CtClass = pool.get(name)

        addCode(ctClass,fileName)


    }

    private fun addCode(ctClass: CtClass, fileName: String) {
        ctClass.defrost()
        val methods = ctClass.declaredMethods
        methods.forEach {
            it.insertBefore("if(true){}")
        }

        ctClass.writeFile(fileName)
        ctClass.detach()
    }

    override fun getName(): String {
        return "tengfei"
    }

    override fun getInputTypes(): MutableSet<QualifiedContent.ContentType> {
        return TransformManager.CONTENT_CLASS
    }

    override fun getScopes(): MutableSet<in QualifiedContent.Scope> {
        return TransformManager.SCOPE_FULL_PROJECT
    }

    override fun isIncremental(): Boolean {
        return false
    }
}
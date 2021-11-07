package com.tengfei.javassist

import com.android.build.api.transform.Format
import com.android.build.api.transform.QualifiedContent
import com.android.build.api.transform.Transform
import com.android.build.api.transform.TransformInvocation
import com.android.build.gradle.internal.pipeline.TransformManager
import com.android.utils.FileUtils
import org.gradle.api.Project

class ModifyTransform(project: Project) : Transform() {

    val project: Project = project

    override fun transform(transformInvocation: TransformInvocation?) {
        super.transform(transformInvocation)
        transformInvocation?.inputs?.forEach{ input ->
            // 包含我们手写的 Class 类及 R.class、BuildConfig.class 等
            input.directoryInputs.forEach { directoryInput ->
                val path = directoryInput.file.absolutePath
                        println("[InjectTransform] Begin to inject: $path")

                // 执行注入逻辑
                //InjectByJavassit.inject(path, mProject)

                // 获取输出目录
                val dest = transformInvocation.outputProvider.getContentLocation(directoryInput.name,
                directoryInput.contentTypes, directoryInput.scopes, Format.DIRECTORY)
                println("[InjectTransform] Directory output dest: $dest.absolutePath")

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
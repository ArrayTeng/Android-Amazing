package com.tengfei.javassist

import com.android.build.gradle.AppExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class ModifyPlugin:Plugin<Project>{
    override fun apply(project: Project) {
        println("Hello kotlin plugin")
        project.extensions.findByType(AppExtension::class.java)?.run {
            registerTransform(ModifyTransform(project))
        }
    }
}
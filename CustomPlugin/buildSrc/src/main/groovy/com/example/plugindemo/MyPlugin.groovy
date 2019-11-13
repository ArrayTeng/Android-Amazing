package com.example.plugindemo

import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def extension = project.extensions.create("tengfei", Extension)
        project.afterEvaluate {
            print("my plugin is ${extension.author}")
        }
    }
}
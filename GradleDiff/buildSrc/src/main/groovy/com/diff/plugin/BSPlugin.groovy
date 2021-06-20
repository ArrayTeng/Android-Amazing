package com.diff.plugin

import org.gradle.api.Plugin
import org.gradle.api.Project


class BSPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {

        project.afterEvaluate {

            project.task(type:BSTask,'diff'){
                //实现diff任务
                inputs.files "${project.buildDir}/test/Hello.txt","${project.buildDir}/test/HelloV2.txt"
                outputs.files "${project.buildDir}/test/patch"
            }

        }

    }

}
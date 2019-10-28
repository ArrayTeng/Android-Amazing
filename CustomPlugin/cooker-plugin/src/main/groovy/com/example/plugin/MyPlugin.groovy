import org.gradle.api.Plugin
import org.gradle.api.Project

class MyPlugin implements Plugin<Project>{

    @Override
    void apply(Project project) {
        print("嗨 这是我定义的第一个Gradle插件")

        project.task("cooker-test-task")
    }
}
import com.diff.plugin.utils.BSDiff
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class BSTask extends DefaultTask{

    BSTask() {
        group 'Custom'
    }

    @TaskAction
    void patch(){
        //获取输入文件以及处理输出文件
        File oldFile = inputs.files.files[0]
        File newFile = inputs.files.files[1]
        File diffFile = outputs.files.first()
        BSDiff.bsdiff(oldFile,newFile,diffFile)


    }

}
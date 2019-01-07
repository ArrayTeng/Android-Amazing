###### Flutter在MAC上的配置

1 添加如下环境变量，这是Flutter官方为中国开发者搭建了临时镜像，但此镜像不是固定的，建议去 [Using Flutter in China](https://github.com/flutter/flutter/wiki/Using-Flutter-in-China)查看最新的镜像配置

```
export PUB_HOSTED_URL=https://pub.flutter-io.cn
export FLUTTER_STORAGE_BASE_URL=https://storage.flutter-io.cn
```
2 执行 git clone -b master https://github.com/flutter/flutter.git 将最新的Flutter SDK下载到本地

3 配置环境变量

```
#Flutter 替中国开发者提供了临时镜像，时刻关注 https://github.com/flutter/flutter/wiki/Using-Flutter-in-China 来更新此镜像
export PUB_HOSTED_URL=https://pub.flutter-io.cn
export FLUTTER_STORAGE_BASE_URL=https://storage.flutter-io.cn
#配置Flutter的环境变量
export PATH=/Users/tengfei/flutter/flutter/bin:$PATH    
```

4 检查你的Android SDK是否配置正确

```
#AndroidSDK环境变量配置
export ANDROID_HOME=/Users/tengfei/AndroidStudio_MAC_Tool/AndroidStudio/SDK
export PATH=${PATH}:/Users/tengfei/AndroidStudio_MAC_Tool/AndroidStudio/SDK/platform-tools
export PATH=${PATH}:/Users/tengfei/AndroidStudio_MAC_Tool/AndroidStudio/SDK/tools
```
5 运行 source $HOME/.bash_profile 刷新当前终端窗口

> 注意: 如果你使用终端是zsh，终端启动时 ~/.bash_profile 将不会被加载，解决办法就是修改 ～/.zshrc ，在其中添加：source ～/.bash_profile

6 执行 flutter doctor看看是否需要其它配置

7 执行 flutter channel 检查sdk的分支，并执行 flutter channel beta 切换到稳定版本

8 执行 flutter upgrade 同时更新Flutter SDK和你的flutter项目依赖包。如果你只想更新项目依赖包（不包括Flutter SDK），可以使用如下命令：

flutter packages get获取项目所有的依赖包

flutter packages upgrade 获取项目所有依赖包的最新版本

9 在IDE上安装两个插件
> Flutter插件： 支持Flutter开发工作流 (运行、调试、热重载等)。
Dart插件： 提供代码分析 (输入代码时进行验证、代码补全等)。




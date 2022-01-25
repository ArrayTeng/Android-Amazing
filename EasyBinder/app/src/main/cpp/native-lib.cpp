#include <jni.h>
#include <string>

#include <jni.h>
#include <string>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <android/log.h>
//能共享  1    不能共享  2

//extern "C"
//JNIEXPORT void JNICALL
//Java_com_maniu_bindermaniu_ManiuBinder_write(JNIEnv *env, jobject thiz) {
////物理内存 ----活跃代码    ----》  磁盘
////物理内存--- 磁盘
//    std::string file = "/sdcard/binder";
//    int m_fd = open(file.c_str(), O_RDWR | O_CREAT, S_IRWXU);
////    4k  物理内存映射    4k   文件  4k
//
//    ftruncate(m_fd, 4096);
////    java能 物理内存
////用户空间   能  1 不能2
////用户 不能够直接申请物理内存
////  mmap
////物理地址1    虚拟地址2
//    int8_t *m_ptr = static_cast<int8_t *>(mmap(0, 4096,
//            PROT_READ | PROT_WRITE, MAP_SHARED, m_fd,
//                                               0));
//    std::string data("码牛 用代码成就你成为大牛的梦想");
//    memcpy(m_ptr, data.data(), data.size());
//}
////B  能 1  不能2
//extern "C"
//JNIEXPORT jstring JNICALL
//Java_com_maniu_bindermaniu_ManiuBinder_read(JNIEnv *env, jobject thiz) {
////    怎么把ptr
//    std::string file = "/sdcard/binder";
//    //打开文件
//    int m_fd = open(file.c_str(), O_RDWR | O_CREAT, S_IRWXU);
//    ftruncate(m_fd, 4096);
//    int8_t *m_ptr = static_cast<int8_t *>(mmap(0, 4096, PROT_READ | PROT_WRITE, MAP_SHARED, m_fd,
//                                               0));
////申请内存
//    char *buf = static_cast<char *>(malloc(100));
//
//    memcpy(buf, m_ptr, 100);
////ptr    读 能 1 不能2
//    std::string result(buf);
//    __android_log_print(ANDROID_LOG_ERROR, "david", "读取数据:%s", result.c_str());
//    //取消映射
//    munmap(m_ptr, 4096);
//    //关闭文件
//    close(m_fd);
//    return env->NewStringUTF(result.c_str());
//}
extern "C"
JNIEXPORT void JNICALL
Java_com_tengfei_easybinder_EasyBinder_write(JNIEnv *env, jobject thiz) {
    // TODO: implement write()
}
extern "C"
JNIEXPORT jstring JNICALL
Java_com_tengfei_easybinder_EasyBinder_read(JNIEnv *env, jobject thiz) {
    // TODO: implement read()
}
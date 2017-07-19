#include <jni.h>
#include <string>



extern "C" {
JNIEXPORT jstring JNICALL
    Java_com_example_nibikoski_testforcamera004_MainActivity_stringFromJNI(
            JNIEnv *env,
            jobject /* this */) {
        std::string hello = "Hello from C++";
        return env->NewStringUTF(hello.c_str());
    }

    JNIEXPORT jint JNICALL
    Java_com_example_nibikoski_testforcamera004_MainActivity_intFromJNI(JNIEnv *env, jobject) {

        jint rr = 5;
        return rr;
    }
}

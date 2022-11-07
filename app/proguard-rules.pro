### Project specific ProGuard rules ###
-optimizationpasses 5
-flattenpackagehierarchy
-repackageclasses ''
-allowaccessmodification
-optimizations !code/simplification/arithmetic
-optimizations !field/removal/writeonly,!field/marking/private,!class/merging/*,!code/allocation/variable
-keepattributes Signature,RuntimeVisibleAnnotations,AnnotationDefault,*Annotation*,EnclosingMethod,Exceptions,InnerClasses

### Retrofit ###
# Source: http://square.github.io/retrofit/
# Retain generic type information for use by reflection by converters and adapters.
-keepattributes Signature
# Retain declared checked exceptions for use by a Proxy instance.
-keepattributes Exceptions
# Retain method names (source: http://stackoverflow.com/a/35525024/2969811)
-keepclasseswithmembers class * {
    @retrofit2.http.* <methods>;
}

### Gson ###
# Source: https://github.com/google/gson/blob/master/examples/android-proguard-example/proguard.cfg
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature
# For using GSON @Expose annotation
-keepattributes *Annotation*
#-keep class com.google.gson.stream.** { *; }
# Application classes that will be serialized/deserialized over Gson
-keep class com.jlmari.android.basepokedex.networkdatasource.models.** { *; }

### Glide ###
# Source: https://github.com/bumptech/glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

# To remove all logs
# Source: http://stackoverflow.com/a/13327603/2969811
-assumenosideeffects class android.util.Log {
    public static boolean isLoggable(java.lang.String, int);
    public static int v(...);
    public static int i(...);
    public static int w(...);
    public static int d(...);
    public static int e(...);
    public static int wtf(...);
}

### Kotlin Coroutines ###
# Source: https://github.com/Kotlin/kotlinx.coroutines/issues/983
-keepnames class kotlinx.coroutines.internal.MainDispatcherFactory {}
-keepnames class kotlinx.coroutines.CoroutineExceptionHandler {}
-keepnames class kotlinx.coroutines.android.AndroidExceptionPreHandler {}
-keepnames class kotlinx.coroutines.android.AndroidDispatcherFactory {}
-keepclassmembernames class kotlinx.** { volatile <fields>; }
-keepclassmembernames class kotlin.coroutines.SafeContinuation { volatile <fields>; }
-dontwarn kotlinx.coroutines.flow.**inlined**
-dontwarn kotlinx.coroutines.reactive.**inlined**

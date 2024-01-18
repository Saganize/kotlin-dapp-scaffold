# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-dontwarn org.slf4j.impl.StaticLoggerBinder
-dontwarn java.lang.invoke.StringConcatFactory

-keep class * extends com.google.protobuf.GeneratedMessageLite { *; }
-keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

# Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
 -keep,allowobfuscation,allowshrinking interface retrofit2.Call
 -keep,allowobfuscation,allowshrinking class retrofit2.Response

 # With R8 full mode generic signatures are stripped for classes that are not
 # kept. Suspend functions are wrapped in continuations where the type argument
 # is used.
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

 #
 # This ProGuard configuration file illustrates how to process a program
 # library, such that it remains usable as a library.
 # Usage:
 #     java -jar proguard.jar @library.pro
 #

 # Save the obfuscation mapping to a file, so we can de-obfuscate any stack
 # traces later on. Keep a fixed source file attribute and all line number
 # tables to get line numbers in the stack traces.
 # You can comment this out if you're not interested in stack traces.

 -printmapping out.map
 -keepparameternames
 -renamesourcefileattribute SourceFile
 -keepattributes Exceptions,InnerClasses,Signature,Deprecated,SourceFile,LineNumberTable,EnclosingMethod

 # Preserve all annotations.

 -keepattributes *Annotation*

 # Preserve all public classes, and their public and protected fields and
 # methods.

 -keep public class * {
     public protected *;
 }

 # Preserve all .class method names.

 -keepclassmembernames class * {
     java.lang.Class class$(java.lang.String);
     java.lang.Class class$(java.lang.String, boolean);
 }

 # Preserve all native method names and the names of their classes.

 -keepclasseswithmembernames class * {
     native <methods>;
 }

 # Preserve the special static methods that are required in all enumeration
 # classes.

 -keepclassmembers class * extends java.lang.Enum {
     public static **[] values();
     public static ** valueOf(java.lang.String);
 }

 # Explicitly preserve all serialization members. The Serializable interface
 # is only a marker interface, so it wouldn't save them.
 # You can comment this out if your library doesn't use serialization.
 # If your code contains serializable classes that have to be backward
 # compatible, please refer to the manual.

 -keepclassmembers class * implements java.io.Serializable {
     static final long serialVersionUID;
     static final java.io.ObjectStreamField[] serialPersistentFields;
     private void writeObject(java.io.ObjectOutputStream);
     private void readObject(java.io.ObjectInputStream);
     java.lang.Object writeReplace();
     java.lang.Object readResolve();
 }

 # Your library may contain more items that need to be preserved;
 # typically classes that are dynamically created using Class.forName:

 # -keep public class mypackage.MyClass
 # -keep public interface mypackage.MyInterface
 # -keep public class * implements mypackage.MyInterface

 -keep class com.saganize.solwave* { *; }
 -keep class com.saganize.solwave.domain.model* { *; }
 -keep class com.saganize.solwave.data.remote.model.* { *; }


 # Please add these rules to your existing keep rules in order to suppress warnings.
 # This is generated automatically by the Android Gradle plugin.
 -dontwarn org.slf4j.impl.StaticLoggerBinder
 -dontwarn java.lang.invoke.StringConcatFactory

 -keep class * extends com.google.protobuf.GeneratedMessageLite { *; }
 -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

 # Keep generic signature of Call, Response (R8 full mode strips signatures from non-kept items).
  -keep,allowobfuscation,allowshrinking interface retrofit2.Call
  -keep,allowobfuscation,allowshrinking class retrofit2.Response

  # With R8 full mode generic signatures are stripped for classes that are not
  # kept. Suspend functions are wrapped in continuations where the type argument
  # is used.
  -keep,allowobfuscation,allowshrinking class kotlin.coroutines.Continuation

 # Retrofit
 -keep class com.google.gson.** { *; }
 -keep class retrofit.** { *; }
 -keep class retrofit2.** { *; }
 -keepattributes Signature
 -keepattributes Exceptions
 -keepclasseswithmembers class * {
     @retrofit2.http.* <methods>;
 }
 -keepclasseswithmembers interface * {
     @retrofit2.* <methods>;
 }

 -keepclassmembers,allowobfuscation class * {
   @com.google.gson.annotations.SerializedName <fields>;
 }

 -keep class * {
   @com.google.gson.annotations.SerializedName <fields>;
 }
 -keepnames class com.fasterxml.jackson.databind.** { *; }
 -dontwarn com.fasterxml.jackson.databind.**

 -if class *
 -keepclasseswithmembers class <1> {
     <init>(...);
     @com.google.gson.annotations.SerializedName <fields>;
 }

 -dontwarn javax.naming.NamingEnumeration
 -dontwarn javax.naming.NamingException
 -dontwarn javax.naming.directory.Attribute
 -dontwarn javax.naming.directory.Attributes
 -dontwarn javax.naming.directory.DirContext
 -dontwarn javax.naming.directory.InitialDirContext
 -dontwarn javax.naming.directory.SearchControls
 -dontwarn javax.naming.directory.SearchResult
 -dontwarn org.fusesource.leveldbjni.JniDBFactory
 -dontwarn org.iq80.leveldb.DB
 -dontwarn org.iq80.leveldb.DBException
 -dontwarn org.iq80.leveldb.DBFactory
 -dontwarn org.iq80.leveldb.DBIterator
 -dontwarn org.iq80.leveldb.Options
 -dontwarn org.iq80.leveldb.ReadOptions
 -dontwarn org.iq80.leveldb.Snapshot
 -dontwarn org.iq80.leveldb.WriteBatch
 -dontwarn org.slf4j.impl.StaticMDCBinder
 -dontwarn org.slf4j.impl.StaticMarkerBinder
 -dontwarn sun.security.x509.X509Key
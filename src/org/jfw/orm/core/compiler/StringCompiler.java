package org.jfw.orm.core.compiler;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;

public class StringCompiler {
    /** 真正使用的编译器 */  
    private final JavaCompiler compiler;  
    public ClassLoaderImpl getClassLoader() {
		return classLoader;
	}
	private final ClassLoaderImpl classLoader;  
    /** 保存编译器编译中的诊断信息 */  
    private DiagnosticCollector<JavaFileObject> diagnostics;  
    private final FileManagerImpl javaFileManager;  
    /** 编译参数 */  
    private final List<String> options;  
    /** 
     * 构造一个新的实例，该实例持有指定的classloader 
     *  
     * @param loader 
     *            应用的{@link ClassLoader} 
     * @param options 
     *            编译器的编译参数，具体可参考javac编译参数 
     * @throws IllegalStateException 
     *             如果java编译器不能正确载入则抛出异常 
     */  
    public StringCompiler(ClassLoader loader, Collection<String> options) {  
        compiler = ToolProvider.getSystemJavaCompiler();  
        if (compiler == null) {  
            throw new IllegalStateException("系统java编译器无法找到，请确认类路径中已经包含tools.jar（注：JDK 6中默认自带，JRE 6中默认不带）。");  
        }  
        if (loader == null) {  
            classLoader = new ClassLoaderImpl(this.getClass().getClassLoader());  
        } else {  
            classLoader = new ClassLoaderImpl(loader);  
        }  
        this.options = new ArrayList<String>();  
        if (options != null) {  
            this.options.addAll(options);  
        }  
        diagnostics = new DiagnosticCollector<JavaFileObject>();  
        javaFileManager = new FileManagerImpl(compiler.getStandardFileManager(diagnostics, null, Charset  
                .forName("UTF-8")), classLoader);  
    }  
    /** 
     * 编译多个Java类的源码 
     * @param <T>
     *  
     * @param classes 
     *            key为类的完全限定名，value为对应的源码。 
     * @return 编译后的类 
     * @throws CharSequenceCompilerException 
     */  
    public synchronized <T> Map<String, Class<T>> compile(Map<String, String> classes)  
            throws StringCompileException {  
        //准备待编译文件  
        List<JavaFileObject> sources = new ArrayList<JavaFileObject>();  
        for (Map.Entry<String, String> entry : classes.entrySet()) {  
            String qualifiedClassName = entry.getKey();  
            String javaSource = entry.getValue();  
            if (javaSource != null) {  
                int dotPos = qualifiedClassName.lastIndexOf('.');  
                String className = dotPos == -1 ? qualifiedClassName : qualifiedClassName  
                        .substring(dotPos + 1);  
                String packageName = dotPos == -1 ? "" : qualifiedClassName.substring(0, dotPos);  
                JavaFileObjectImpl source = JavaFileObjectImpl.create(className, javaSource);  
                sources.add(source);  
                javaFileManager.putFileForInput(StandardLocation.SOURCE_PATH, packageName, className  
                        + ".java", source);  
            }  
        }  
        //编译代码  
        CompilationTask task = compiler.getTask(null, javaFileManager, diagnostics, options, null, sources);  
        Boolean result = task.call();  
        //返回编译结果  
        if ((result == null) || !result.booleanValue()) {  
            throw new StringCompileException("Compilation failed.", classes.keySet(), diagnostics);  
        }  
        try {  
            Map<String, Class<T>> compiled = new HashMap<String, Class<T>>();  
            for (String qualifiedClassName : classes.keySet()) {  
                compiled.put(qualifiedClassName, this.<T>loadClass(qualifiedClassName));  
            }  
            return compiled;  
        } catch (ClassNotFoundException ex) {  
            throw new StringCompileException(classes.keySet(), ex, diagnostics);  
        } catch (IllegalArgumentException ex) {  
            throw new StringCompileException(classes.keySet(), ex, diagnostics);  
        } catch (SecurityException ex) {  
            throw new StringCompileException(classes.keySet(), ex, diagnostics);  
        }  
    }  
    /** 
     * 编译一个Java类。 
     * @param <T>
     *  
     * @param qualifiedClassName 
     *            类的完全限定名。 
     * @param javaSource 
     *            编译的java类完整的源码。 
     * @param types 
     *            0或多个类，用以检验被编译的类能否转换成这些类中任何一个。 
     * @return 编译后的类 
     * @throws CharSequenceCompilerException 
     *             如果类无法被编译则抛出异常。 
     * @throws ClassCastException 
     *             如果编译后的类无法转换成types中的任何一种类型，则抛出异常。 
     */  
    public synchronized <T> Class<T> compile(String qualifiedClassName, String javaSource,  
            Class<?>... types) throws StringCompileException, ClassCastException {  
        diagnostics = new DiagnosticCollector<JavaFileObject>();  
        Map<String, String> classes = new HashMap<String, String>(1);  
        classes.put(qualifiedClassName, javaSource);  
        Map<String, Class<T>> compiled = compile(classes);  
        Class<T> newClass = compiled.get(qualifiedClassName);  
        for (Class<?> type : types) {  
            if (!type.isAssignableFrom(newClass)) {  
                throw new ClassCastException(type.getName());  
            }  
        }  
        return newClass;  
    }  
    /** 载入Java类。 
     * @param <T>*/  
    @SuppressWarnings("unchecked")  
    private <T> Class<T> loadClass(final String qualifiedClassName) throws ClassNotFoundException {  
        return (Class<T>) classLoader.loadClass(qualifiedClassName);  
    }  
}

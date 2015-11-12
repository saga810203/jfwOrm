package org.jfw.orm.core.compiler;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;

public class StringCompileException extends Exception {  
	private static final long serialVersionUID = -1040768088143833281L;
	/** 所有被编译的类的完整类名 */  
    private Set<String> classNames;  
    transient private DiagnosticCollector<JavaFileObject> diagnostics;  
    public StringCompileException(Set<String> qualifiedClassNames, Throwable cause,  
            DiagnosticCollector<JavaFileObject> diagnostics) {  
        super(cause);  
        setClassNames(qualifiedClassNames);  
        setDiagnostics(diagnostics);  
    }  
    public StringCompileException(String message, Set<String> qualifiedClassNames,  
            DiagnosticCollector<JavaFileObject> diagnostics) {  
        super(message);  
        setClassNames(qualifiedClassNames);  
        setDiagnostics(diagnostics);  
    }  
    public StringCompileException(String message, Set<String> qualifiedClassNames, Throwable cause,  
            DiagnosticCollector<JavaFileObject> diagnostics) {  
        super(message, cause);  
        setClassNames(qualifiedClassNames);  
        setDiagnostics(diagnostics);  
    }  
    /** @return 返回编译出问题的类的全名称 */  
    public Collection<String> getClassNames() {  
        return Collections.unmodifiableSet(classNames);  
    }  
    /** 得到异常的诊断信息 */  
    public DiagnosticCollector<JavaFileObject> getDiagnostics() {  
        return diagnostics;  
    }  
    private void setClassNames(Set<String> qualifiedClassNames) {  
        classNames = new HashSet<String>(qualifiedClassNames);  
    }  
    private void setDiagnostics(DiagnosticCollector<JavaFileObject> diagnostics) {  
        this.diagnostics = diagnostics;  
    }  
}  

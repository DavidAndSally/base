package com.jd.pop.base.annotation.apptest.myprocessor;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author wanjiadong
 * @description
 * @date Create in 15:35 2018/12/3
 */
@SupportedSourceVersion(value = SourceVersion.RELEASE_8)
@SupportedAnnotationTypes({
        // 合法注解全名的集合
})
public class MyProcessor extends AbstractProcessor {
    /**
     * 每个注解处理器都必须有一个空的构造方法（父类已经实现了），这个init方法会被构造器调用，
     * 并传入一个 ProcessingEnvironment 参数，该参数提供了很多工具类，
     * 比如 Elements， Filer， Messager， Types
     */
    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        super.init(processingEnv);
    }
    /**
     * 这个方法在父类中是abstract的，所以子类必须实现。
     * 这个方法就是相当于 注解处理器的 入口 main()方法,我们说在编译时，对注解进行的处理，
     * 比如对注解的扫描，评估和处理，以及后续的我们要做的其他操作。（比如生成其他java代码文件），
     * 都是在这里发生的。
     *
     * 参数RoundEnvironment可以让我们查出包含特定注解的被注解元素。
     */
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }
    /**
     * 本方法用来指明你支持的java版本，
     * 不过一般使用 SourceVersion.latestSupported() 就可以了。
     */
    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
    /**
     * 这个方法虽然在父类当中不是 abstract的，但是我们也必须实现。
     * 因为该方法的作用是指定我们要处理哪些注解的，
     * 比如你想处理注解MyAnnotation,可是该处理器怎么知道你想处理MyAnnotation，而不是OtherAnnotation呢。
     * 所以你要在这里指明，你需要处理的注解的全称。
     *
     * 返回值是一个字符串的集合，包含着本处理器想要处理的注解类型的合法全称。
     */
    @Override
    public Set<String> getSupportedAnnotationTypes() {
        Set<String> annotations = new LinkedHashSet<String>();
        return annotations;
    }
}

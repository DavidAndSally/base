package com.jd.pop.base.annotation.apptest.generatedClass;

import com.google.auto.service.AutoService;

import javax.annotation.processing.*;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.JavaFileObject;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author qiw-a
 * @description
 * @date Create in 16:49 2018/12/20
 */
@AutoService(Processor.class)
public class GeneratedClassProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        //创建动态代码，实际上就是创建一个String, 写入到文件里
        //然后文件会被解释为.class文件
        System.out.println("你运行了没哟");
        StringBuilder builder = new StringBuilder()
                .append("package com.qw;\n\n")
                .append("public class QwClass {\n\n")
                .append("\tpublic String getMessage() {\n")
                .append("\t\treturn \"");

        //获取所有被CustomAnnotation修饰的代码元素
        for (Element element : roundEnv.getElementsAnnotatedWith(GeneratedClass.class)) {
            String objectType = element.getSimpleName().toString();
            builder.append(objectType).append(" exists! ");
        }

        builder.append("\";\n")
                .append("\t}\n")
                .append("}\n");

        //将String写入并生成.class文件
        try {
            JavaFileObject source = processingEnv.getFiler().createSourceFile(
                    "com.qw.QwClass");

            Writer writer = source.openWriter();
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        } catch (IOException e) {
            //
        }

        return true;
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
        annotations.add(GeneratedClass.class.getCanonicalName());
        return annotations;
    }
}

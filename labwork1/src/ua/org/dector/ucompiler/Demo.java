package ua.org.dector.ucompiler;

import ua.org.dector.ucompiler.store.TableWrapper;

import javax.tools.*;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ClassLoadingMXBean;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.util.*;

/**
 * @author dector
 */
public class Demo {
    public static final String CLASS_BEGINING
            = "public class Executor {"
            + "  public static void do() {";

    public static final String CLASS_END
            = "  }"
            + "}";

    private TableWrapper tw;

    public Demo() {
        tw = new TableWrapper();
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        demo.run();
    }

    public void run() {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String inputLine = "";
        while (! inputLine.equals("exit")) {
            try {
                inputLine = in.readLine();

                MemoryClassLoader cl = new MemoryClassLoader("Executor",
                        CLASS_BEGINING + inputLine + CLASS_END);
                cl.loadClass("Executor").getMethod("do").invoke(null);

            } catch (IOException e) {
                System.out.println(">> Ooops! Something wrong");
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

class MemoryClassLoader extends ClassLoader {
    private final JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
    private final MemoryFileManager manager = new MemoryFileManager(this.compiler);

    public MemoryClassLoader(String classname, String filecontent) {
        this(Collections.singletonMap(classname, filecontent));
    }

    public MemoryClassLoader(Map<String, String> map) {
        List<Source> list = new ArrayList<Source>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            list.add(new Source(entry.getKey(), JavaFileObject.Kind.SOURCE, entry.getValue()));
        }
        this.compiler.getTask(null, this.manager, null, null, null, list).call();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        synchronized (this.manager) {
            Output mc = this.manager.map.remove(name);
            if (mc != null) {
                byte[] array = mc.toByteArray();
                return defineClass(name, array, 0, array.length);
            }
        }
        return super.findClass(name);
    }
}

class MemoryFileManager extends ForwardingJavaFileManager<JavaFileManager> {
    final Map<String, Output> map = new HashMap<String, Output>();

    MemoryFileManager(JavaCompiler compiler) {
        super(compiler.getStandardFileManager(null, null, null));
    }

    @Override
    public Output getJavaFileForOutput
            (Location location, String name, JavaFileObject.Kind kind, FileObject source) {
        Output mc = new Output(name, kind);
        this.map.put(name, mc);
        return mc;
    }
}

class Source extends SimpleJavaFileObject {
    private final String content;

    Source(String name, Kind kind, String content) {
        super(URI.create("memo:///" + name.replace('.', '/') + kind.extension), kind);
        this.content = content;
    }

    @Override
    public CharSequence getCharContent(boolean ignore) {
        return this.content;
    }
}

class Output extends SimpleJavaFileObject {
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();

    Output(String name, Kind kind) {
        super(URI.create("memo:///" + name.replace('.', '/') + kind.extension), kind);
    }

    byte[] toByteArray() {
        return this.baos.toByteArray();
    }

    @Override
    public ByteArrayOutputStream openOutputStream() {
        return this.baos;
    }
}
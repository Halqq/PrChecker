package dev.halq.prchecker.api;

import org.apache.commons.io.IOUtils;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.tree.ClassNode;

import java.io.File;
import java.io.InputStream;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Halq
 * @since 27/10/2022 at 21:42
 */

public class HalqJar {

    public static final Map<String, byte[]> files = new HashMap<>();
    public static final List<ClassNode> classes = new ArrayList<>();

    public static void loadJar(File file) {
        try (JarFile jarFile = new JarFile(file)) {
            final Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                final JarEntry jarEntry = entries.nextElement();
                try (InputStream inputStream = jarFile.getInputStream(jarEntry)) {
                    final byte[] bytes = IOUtils.toByteArray(inputStream);
                    if (!jarEntry.getName().endsWith(".class")) {
                        files.put(jarEntry.getName(), bytes);
                    } else {
                        final ClassNode classNode = new ClassNode();
                        new ClassReader(bytes).accept(classNode, 0);
                        classes.add(classNode);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package dev.halq.prchecker.impl.checks;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

/**
 * @author Halq
 * @since 27/10/2022 at 22:19
 */

public class WebConnectCheck {

    public static void verifyWebConnect(List<ClassNode> node) {
        for (ClassNode classNode : node) {
            for (MethodNode methodNode : classNode.methods) {
                for (AbstractInsnNode abstractInsnNode : methodNode.instructions.toArray()) {
                    if (abstractInsnNode instanceof MethodInsnNode) {
                        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
                        if (methodInsnNode.owner.equals("java/net/URLConnection")) {
                            System.out.println("Found Web Connect: " + "class: " + classNode.name);
                        }
                    }
                }
            }
        }
    }
}

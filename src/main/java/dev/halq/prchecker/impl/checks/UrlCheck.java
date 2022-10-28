package dev.halq.prchecker.impl.checks;

import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.LdcInsnNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.List;

/**
 * @author Halq
 * @since 27/10/2022 at 21:51
 */

public class UrlCheck {

    public static void verifyURL(List<ClassNode> node){
        for (ClassNode classNode : node) {
           for(MethodNode methodNode : classNode.methods){
               for(AbstractInsnNode abstractInsnNode : methodNode.instructions.toArray()){
                   if(abstractInsnNode instanceof LdcInsnNode){
                       LdcInsnNode ldcInsnNode = (LdcInsnNode) abstractInsnNode;
                       if(ldcInsnNode.cst instanceof String){
                           String string = (String) ldcInsnNode.cst;
                           if(string.contains("http://") || string.contains("https://")){
                               System.out.println("Found URL: " + "class: " + classNode.name );
                           }
                       }
                   }
               }
           }
        }
    }
}

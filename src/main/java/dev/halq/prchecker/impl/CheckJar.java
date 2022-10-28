package dev.halq.prchecker.impl;

import dev.halq.prchecker.api.HalqJar;
import dev.halq.prchecker.impl.checks.FileEncryptCheck;
import dev.halq.prchecker.impl.checks.UrlCheck;
import dev.halq.prchecker.impl.checks.WebConnectCheck;

import java.io.File;

/**
 * @author Halq
 * @since 27/10/2022 at 21:54
 */

public class CheckJar {

    public static void checkUrl(File file){
        HalqJar.loadJar(file);
        UrlCheck.verifyURL(HalqJar.classes);
    }

    public static void checkEncrypt(File file) {
        HalqJar.loadJar(file);
        FileEncryptCheck.verifyFileEncrypt(HalqJar.classes);
    }

    public static void checkConnection(File file) {
        HalqJar.loadJar(file);
        WebConnectCheck.verifyWebConnect(HalqJar.classes);
    }
}

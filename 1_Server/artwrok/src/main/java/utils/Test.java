package utils;

import java.io.IOException;

import static utils.tls_sigature.GenTLSSignatureEx;

/**
 * Created by vivi on 2018/3/23.
 */
public class Test {
    public static void  main(String[] args){
        tls_sigature.GenTLSSignatureResult result = null;
        try {
            result = GenTLSSignatureEx(1400077891, "932942491@qq.com", tls_sigature.privStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result.urlSig);
    }
}

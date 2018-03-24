package utils;

import com.tls.base64_url.base64_url;
import com.tls.tls_sigature.tls_sigature;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.asn1.pkcs.PrivateKeyInfo;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.openssl.PEMParser;
import org.bouncycastle.openssl.jcajce.JcaPEMKeyConverter;
import org.bouncycastle.util.Arrays;
import org.json.JSONObject;

import java.io.CharArrayReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.Security;
import java.security.Signature;
import java.util.zip.Deflater;

/**
 * Created by vivi on 2017/9/25.
 */
public class Tools {
    public static final String def_playlist = "http://owtt2jsve.bkt.clouddn.com/def_playlist.png";
    public static final String def_avatar = "http://owtt2jsve.bkt.clouddn.com/def_avatar.png";
    public static final String def_img = "http://139.199.32.74/files/img/";
    public static final String TIM_PRIVATE_KEY  = "-----BEGIN PRIVATE KEY-----\n" +
            "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgxV6LzK/qHonJWV7B\n" +
            "rUp+r20zTb3JQljO/nHmbLdZcgOhRANCAASucjcQOCrnNWARhvQanBPfeiAGcv47\n" +
            "PUinEyEoFGw7P1oaAQnkGRb4X/xf4phYBZcPKqWhZlCZAslZy2RTiQH/\n" +
            "-----END PRIVATE KEY-----";
    public static final int TIM_PRIVATE_SDKAPPID = 1400077891;
}

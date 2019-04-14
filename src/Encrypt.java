import javax.crypto.Cipher;
import com.sun.xml.internal.messaging.saaj.packaging.mime.util.BASE64EncoderStream;

public class Encrypt {

    public static String encrypt(String str, Cipher ecipher) throws Exception{
            byte[] utf8 = str.getBytes("UTF8");
            byte[] enc = ecipher.doFinal(utf8);
            enc = BASE64EncoderStream.encode(enc);
            return new String(enc);
    }

}

package Ayakashi.helpers;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class UserNameApi {

    public static String username = null;

    public static String getHWID() throws NoSuchAlgorithmException {
        StringBuilder s = new StringBuilder();
        String main = System.getenv("PROCESSOR_IDENTIFIER") + System.getenv("COMPUTERNAME") + System.getProperty("user.name").trim();
        byte[] bytes = main.getBytes(StandardCharsets.UTF_8);
        MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        byte[] md5 = messageDigest.digest(bytes);
        int i = 0;
        for (byte b : md5) {
            s.append(Integer.toHexString(b & 0xFF | 0x300), 0, 3);
            if (i != md5.length - 1)
                s.append("-");
            i++;
        }
        return s.toString();
    }

    public static String getName() {
        try {
            Scanner scanner = new Scanner(new URL("http://pvpclub.pl/license/blackcrack/AyakashiLogin/" + getHWID() + ".txt").openStream());
            while (scanner.hasNext()) {
                String[] split = scanner.nextLine().split(":");
                username = split[0];
            }
        } catch (NoSuchAlgorithmException | IOException ignored) {
        }
        return username;
    }

}

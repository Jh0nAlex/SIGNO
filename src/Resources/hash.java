package Resources;
/* ENCRIP PASSWORD */
/*@author SIGNO */
public class hash {

    // RETORNO DE UN HASH A PARTIR DE UN TIPO Y UN TEXTO
    public static String getHash(String txt, String hashType) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance(hashType);
            byte[] array = md.digest(txt.getBytes());
            StringBuffer sb = new StringBuffer();

            for (int i = 0; i < array.length; i++) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static String md5(String txt) {
        return hash.getHash(txt, "MD5");
    }

    public static String sha1(String txt) {
        return hash.getHash(txt, "SHA1");
    }

}

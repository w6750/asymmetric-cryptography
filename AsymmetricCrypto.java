import java.security.KeyPairGenerator;
import java.security.KeyPair;
import java.security.PublicKey;
import java.security.PrivateKey;
import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.Scanner;

public class AsymmetricCrypto {

    // Encryption Method
    private static byte[] encrypt(byte[] data, PublicKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    // Decryption Method
    private static byte[] decrypt(byte[] data, PrivateKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher.doFinal(data);
    }

    // Convert Base64 String into PrivateKey
    private static PrivateKey getPrivateKey(String key) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(key);

        PKCS8EncodedKeySpec spec =
                new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory factory = KeyFactory.getInstance("RSA");

        return factory.generatePrivate(spec);
    }

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("   RSA Asymmetric Cryptography");
        System.out.println("=================================\n");

        // Input message
        System.out.print("Enter message to encrypt: ");
        String message = sc.nextLine();

        // Generate RSA Key Pair
        System.out.println("\nGenerating RSA Keys...");

        KeyPairGenerator keyGen =
                KeyPairGenerator.getInstance("RSA");

        keyGen.initialize(2048);

        KeyPair pair = keyGen.generateKeyPair();

        PublicKey publicKey = pair.getPublic();
        PrivateKey privateKey = pair.getPrivate();

        System.out.println("Keys Generated Successfully!\n");

        // Display Public Key
        System.out.println("---------- PUBLIC KEY ----------");
        System.out.println(
                Base64.getEncoder()
                        .encodeToString(publicKey.getEncoded())
        );

        // Display Private Key
        System.out.println("\n---------- PRIVATE KEY ----------");
        String privateKeyText =
                Base64.getEncoder()
                        .encodeToString(privateKey.getEncoded());

        System.out.println(privateKeyText);

        // Encrypt Message
        byte[] encryptedBytes =
                encrypt(message.getBytes(), publicKey);

        String encryptedText =
                Base64.getEncoder()
                        .encodeToString(encryptedBytes);

        System.out.println("\nEncrypted Message:");
        System.out.println(encryptedText);

        // Manual Private Key Input
        System.out.println("\nPaste the Private Key for Decryption:");
        String enteredKey = sc.nextLine();

        // Convert entered key to PrivateKey object
        PrivateKey enteredPrivateKey =
                getPrivateKey(enteredKey);

        // Decrypt
        byte[] decryptedBytes =
                decrypt(encryptedBytes, enteredPrivateKey);

        String decryptedText =
                new String(decryptedBytes);

        System.out.println("\nDecrypted Message:");
        System.out.println(decryptedText);

        sc.close();
    }
}
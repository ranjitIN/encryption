package com.ranjit;

import javax.crypto.SecretKey;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        SecurityProvider securityProvider = new SecurityProvider();
        try {
            // SecretKey secretKey = securityProvider.generatekey();
            SecretKey secretKey = securityProvider
                    .getSecurityKeyFromFile("D:\\project\\security\\src\\main\\java\\com\\ranjit\\testKey.key");
            securityProvider.saveSecretkey("D:\\project\\security\\src\\main\\java\\com\\ranjit\\testKey.key",
                    secretKey);
            securityProvider.encryptFile(
                    "C:\\Users\\HP\\Downloads\\03Eem2b2LUQRsM1Fa65z_Fri Dec 16 19_53_33 IST 2022.json",
                    "D:\\project\\security\\src\\main\\java\\com\\ranjit\\test.txt", secretKey);

            securityProvider.decryptFile("D:\\project\\security\\src\\main\\java\\com\\ranjit\\test.txt",
                    "D:\\project\\security\\src\\main\\java\\com\\ranjit\\testDycrypt.json", secretKey);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

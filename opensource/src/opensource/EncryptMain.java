package opensource;

public class EncryptMain {

	public static void main(String[] args) {
		CryptoUtil cryptoUtil = new CryptoUtil();
		String msg = "adam";
		try {
			String encrypt = cryptoUtil.encrypt(msg);
			System.out.printf("암호화된 문자열:%s\n", encrypt);
			
			String decrypt = cryptoUtil.decrypt(encrypt);
			System.out.printf("복호화된 문자열:%s\n", decrypt);
			
		}catch(Exception e) {
			System.out.printf("예외:%s\n", e.getMessage());
			e.printStackTrace();
		}
	}

}

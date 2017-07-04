package shiro;

import java.util.UUID;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author chenyifei
 * @date 2017-05-23
 */
public class test {

	
	public static void main(String[] args) {
		
		String u = UUID.randomUUID().toString().replaceAll("-", "");
		System.out.println(u);
		System.out.println(new Md5Hash("test", u).toHex());
	}
}

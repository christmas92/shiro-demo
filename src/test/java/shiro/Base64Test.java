package shiro;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.apache.shiro.codec.Base64;

/**
 * @author chenyifei
 * @date 2017-06-30
 */
public class Base64Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String str = "今天星期五";
		byte[] bs = str.getBytes();
		String key = Base64.encodeToString(Arrays.copyOf(bs, 16));
		System.out.println(key);
		System.out.println(Base64.encodeToString(bs));
		
	}
}

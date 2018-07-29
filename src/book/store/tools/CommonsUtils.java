package book.store.tools;

import java.util.UUID;


public class CommonsUtils {
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
}

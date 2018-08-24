
package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/** @描述: 类说明描述
 * @创建人: 夏显成
 * @创建时间: 2017-8-22 
*/
public class MD5Util {
	
    /**@描述: MD5加密字符串
     * @param pwd 字符串
     * @return 加密完成的字符串
    */
    public static String getMd5Str(String pwd) {  
        try {  
            MessageDigest  digest = MessageDigest.getInstance("MD5");  
            byte[]  bytes = digest.digest(pwd.getBytes());  
            StringBuffer sb = new  StringBuffer();  
            for(int i = 0;i<bytes.length;i++){  
                String s = Integer.toHexString(0xff&bytes[i]);  
                  
                if(s.length()==1){  
                    sb.append("0"+s);  
                }else{  
                    sb.append(s);  
                }  
            }  
              
            return sb.toString();  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            throw new RuntimeException("buhuifasheng");  
        }  
    }
    
    
    /**@描述: 文件加密
     * @param file 文件路径
     * @return  异常返回文件大小
    */
    public static String getMd5ByFile(File file) {
		try {
			String value = null;
			FileInputStream in = new FileInputStream(file);
			try {
				if(file.length()<1024*1024*1024){
					
				MappedByteBuffer byteBuffer = in.getChannel().map(FileChannel.MapMode.READ_ONLY, 0, file.length());
				MessageDigest md5 = MessageDigest.getInstance("MD5");
				md5.update(byteBuffer);
				BigInteger bi = new BigInteger(1, md5.digest());
				value = bi.toString(16);
				bi = null;
				md5 = null;
				byteBuffer = null;
				}
			} catch (Exception e) {
				value = file.length() + "";
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.gc();
			return value;
		} catch (Exception e) {
			System.gc();
			e.printStackTrace();
			return file.length() + "";
		}
	}
	
	//入口
	public static void main(String [] args) throws Exception{
		//System.out.println("-----------------"+encode("xxc109"));
		
		System.out.println("-----测试创建文件的md5后缀----------");
		
		File file = new File("C:/Users/lenovo/Desktop/2.txt");
		System.out.println(file.length());
		
		if(!file.exists()){
			file.createNewFile();
		}
		//获取参数
		String parent = file.getParent();
		
		System.out.println(parent);
		String fileName = file.getName();
		System.out.println(fileName);
		//首先获取文件的MD5
		String md5 = getMd5ByFile(file);
		
		System.out.println("-----获取的md5：" + md5);
		
		//组装
		File md5File = new File(parent + fileName +".md5");
		if(md5File.exists()){
			md5File.delete();
			md5File.createNewFile();
		}
		
		FileOutputStream fos = new FileOutputStream(md5File);
		fos.write(md5.getBytes());
		
		fos.flush();
		fos.close();
		
		System.out.println("--------完成---------");
	}

}

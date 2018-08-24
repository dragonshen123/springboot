
package utils;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** @描述: 类说明描述
 * @创建人: 夏显成
 * @创建时间: 2017-8-22 
*/
public class NumberUtil {
	public static void main(String[] args) {
		 /* DecimalFormat f = new DecimalFormat("###.######");
		  System.out.println( f.format(longParseDouble(112111133133333l)) );
		
		System.out.println(parseInt(strParseDouble("1399.95"),0));*/
	/*
	        System.out.println(toDouble(13333.3));  
	    
	        System.out.println(toLong(56566));  */
		String a="love23next234csdn3423javaeye";
		   
		System.out.println(getDigit(a)); 
		
	}
	
	/**@描述: 对象转double,无默认值
	 * @param str
	 * @return 处理完成的结果
	*/
	public static Double toDouble(Object str ) {
		return toDouble(str, null);
	}
	
	
	/**@描述: 对象转double
	 * @param str def
	 * @return double类型的值
	*/
	public static Double toDouble(Object str,Double defVal) {
		try{
			return Double.parseDouble(str.toString());
		}catch (Exception e) {
			// TODO: handle exception
			return defVal;
		}
	}
	

	/**@描述: 对象转int类型，无默认值
	 * @param str
	 * @return int类型的值
	*/
	public static Integer toInt(Object str) {

		return toInt(str, 0);

	}
	
	/**@描述: 对象转int类型
	 * @param str
	 * @param defVal 默认值
	 * @return int类型的值
	*/
	public static Integer toInt(Object str,Integer defVal) {
		try {
			return Integer.parseInt(str.toString());
		} catch (Exception e) {
			//ExceptionUtil.run(e,false); 
			return defVal;
		}
	}
	
	
	/**@描述: 对象转long类型，无默认值
	 * @param str
	 * @return long类型的值
	*/
	public static Long toLong(Object str){
		
		return toLong(str,0L);
	}
	
	/**@描述: 对象转long类型
	 * @param str
	 * @param defVal 默认值
	 * @return long类型的值
	*/
	public static Long toLong(Object str,Long defVal){
		try {
			return Long.parseLong(str.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
		return defVal;
		}
	}

	
	/**@描述: 四舍五入取整
	 * @param s 值
	 * @param defVal 默认值
	 * @return 异常返回默认值
	*/
	public static int parseInt45(Object s,int defVal){
		 return (int) parseDouble45(s, 0, defVal);
	}
	
	
	/**@描述: 不四舍五入取整
	 * @param 值
	 * @param 默认值
	 * @return 异常返回默认值
	*/
	public static int parseInt(Object d,int defVal){
		 return (int) parseDouble(d, 0, defVal);
	}
	 

	/**@描述: 四舍五入保留小数
	 * @param 值
	 * @param 保留小数位数
	 * @param 默认值
	 * @return 异常返回默认值
	*/
	public static double parseDouble45(Object d,int len,double defVal){
		try{
			String result = String.format("%."+len+"f", Double.parseDouble(d.toString()));//
			defVal=Double.parseDouble(result);
		} catch (Exception e) {
			//ExceptionUtil.run(e,false); 
		}
		return defVal;
	}
	
	/**@描述:不采用四舍五入保留小数
	 * @param 数值
	 * @param 保留小数点位数
	 * @param 默认值
	 * @return 异常返回默认值
	*/
	public static double parseDouble(Object d,int len,double defVal){
		try{
			DecimalFormat formater = new DecimalFormat();
			formater.setMaximumFractionDigits(len);
			formater.setGroupingSize(0);
			formater.setRoundingMode(RoundingMode.FLOOR);
			defVal= Double.parseDouble(formater.format(Double.parseDouble(d.toString())));
		} catch (Exception e) {
			//ExceptionUtil.run(e,false); 
		}
		return defVal;
	}
	
	/**@描述:  每三位以逗号进行分隔,默认使用"#,###.##"格式。
	 * @param data 数值
	 * @return 处理完成的值
	*/
	public static String formatStr(double data) {
       
		return formatStr(data,"#,###.##");
	}
	
	/**@描述:  每三位以逗号进行分隔,保留两位小数,补零用 "#,###.00",不显示用"#,###.##"。
	 * @param data 数值
	 * @return 处理完成的值
	*/
	public static String formatStr(double data,String forMat) {
		
		DecimalFormat fmt = new DecimalFormat(forMat);
        return fmt.format(data);
       
	}
	
	/**@描述: 字符串中截取数字
	 * @param text
	 * @return 截取到的数字
	*/
	public static String getDigit(String text) {
		String regEx="[^0-9]";   
		Pattern   p   =   Pattern.compile(regEx);      
		Matcher   m   =   p.matcher(text);      
        return m.replaceAll("").trim();
    }
	

}


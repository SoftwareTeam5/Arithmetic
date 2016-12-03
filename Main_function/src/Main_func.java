
public class Main_func {
	
	int N; //用户输入的题量数
	String[] expression = new String[100]; //存随机生成的运算表达式
	String[] result = new String[100]; //运算表达式的结果
	String[] uResult = new String[100]; //存随机生成的表达式
	String[] match = new String[100]; //存匹配结果
	
	Main_func() {
//		生成表达式，并获得表达式答案
		for(int i=0; i<N; i++) {
			expression[i] = randExpre();
			result[i] = result(expression[i]);
		}
		
//		匹配用户的答案和表达式的答案是否相同
		for(int i=0; i<N; i++) {
			match[i] = match(result[i],uResult[i]);
		}
	}
	
	
	/*主函数
	 * @function new Main_fun()
	 * */
	public static void main(String[] args) {
		new Main_func();
	}
	
	/*随机生成表达式，返回表达式
	 * @param String expre 
	 * */
	public String randExpre() {
		String expre = "111";
		return expre;
	}
	
	/*根据表达式生成结果，返回结果
	 * @param String result
	 * */
	public String result(String exp) {
		String result = "111";
		return result;
	}
	
	/*返回匹配结果
	 * @param String match
	 * */
	public String match(String result,String uResult) {
		String match = "111";
		return  match;
	}
}


public class Main_func {
	
	int N; //�û������������
	String[] expression = new String[100]; //��������ɵ�������ʽ
	String[] result = new String[100]; //������ʽ�Ľ��
	String[] uResult = new String[100]; //��������ɵı��ʽ
	String[] match = new String[100]; //��ƥ����
	
	Main_func() {
//		���ɱ��ʽ������ñ��ʽ��
		for(int i=0; i<N; i++) {
			expression[i] = randExpre();
			result[i] = result(expression[i]);
		}
		
//		ƥ���û��Ĵ𰸺ͱ��ʽ�Ĵ��Ƿ���ͬ
		for(int i=0; i<N; i++) {
			match[i] = match(result[i],uResult[i]);
		}
	}
	
	
	/*������
	 * @function new Main_fun()
	 * */
	public static void main(String[] args) {
		new Main_func();
	}
	
	/*������ɱ��ʽ�����ر��ʽ
	 * @param String expre 
	 * */
	public String randExpre() {
		String expre = "111";
		return expre;
	}
	
	/*���ݱ��ʽ���ɽ�������ؽ��
	 * @param String result
	 * */
	public String result(String exp) {
		String result = "111";
		return result;
	}
	
	/*����ƥ����
	 * @param String match
	 * */
	public String match(String result,String uResult) {
		String match = "111";
		return  match;
	}
}

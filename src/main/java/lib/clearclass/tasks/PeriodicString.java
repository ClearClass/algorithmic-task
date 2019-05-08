package lib.clearclass.tasks;

public class PeriodicString {
	public static String getPeriod(String st){
		int N = st.length();
		for(int i = N; i>=2; i--)
			i:if(N%i==0){
				int n = N/i;
				String p1 = st.substring(0, n);
				for(int j = 1; j<i; j++)
					if(!p1.equals(st.substring(n*j, n*j+n)))
						break i;
				return p1;
			}
		return null;
	}
}
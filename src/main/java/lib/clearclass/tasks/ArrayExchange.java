package lib.clearclass.tasks;

public class ArrayExchange {
	/**Задача 6.1*/
	public static void exchange1(int[] a, int b){
		int N = a.length;
		int crs1 = 0;   // левый курсор
		int crs2 = N-1; // правый курсор 	
		while(crs1<crs2){
			if(a[crs1]>b){
				while(a[crs2]>=b && crs2>crs1)
					crs2--;
				int tmp = a[crs2];
				a[crs2] = a[crs1];
				a[crs1] = tmp;
			}
			crs1++;
		}		
	}
	/**Задача 6.2*/
	public static void exchange2(int[] a, int b){
		int N = a.length;
		int crs1 = 0;   // левый курсор
		int crs2 = N-1; // правый курсор
		// малые элементы сводим влево
		while(crs1<crs2){
			if(a[crs1]>=b){
				while(a[crs2]>=b && crs2>crs1)
					crs2--;
				int tmp = a[crs2];
				a[crs2] = a[crs1];
				a[crs1] = tmp;
			}
			crs1++;
		}
		// большие элементы сводим вправо
		crs1 = crs2;
		crs2 = N-1; // теперь [crs1..crs2] - правый неупорядоченный интервал
		while(crs1<crs2){
			if(a[crs1]>b){
				while(a[crs2]>b && crs2>crs1)
					crs2--;
				int tmp = a[crs2];
				a[crs2] = a[crs1];
				a[crs1] = tmp;
			}
			crs1++;
		}
	}
}
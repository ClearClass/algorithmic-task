package lib.clearclass.tasks;

public class Series {
	public static int get(int N){
		int current = N; // текущий курсор
		
		// смещаем курсор влево, пока он не достигнет нулевого элемента,
		// и подсчитываем количество переходов
		int i = 0; // счетчик возвратов
		while(current!=0){
			current-=leftBorder(current);
			i++;
		}
		return i%3;
	}

	// левая граница логарифмического интервала, в котором находится N
	private static int leftBorder(int N){
		int leftBorder = 1;
		while(leftBorder<=N)
			leftBorder*=2;
		return leftBorder/2;
	}
}
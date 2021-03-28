package lib.clearclass.tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class OverlayLines {
	public static void fill(int[] ai, int[] bi){
		int N = ai.length;
		
		int amax = 100; // максимальная начальная координата отрезка
		int lmax = 100; // максимальная длина отрезка
		Random r = new Random();
		for (int i = 0; i < N; i++) {
			ai[i] = r.nextInt(amax) + 1;
			bi[i] = ai[i] + r.nextInt(lmax) + 1;
		}
	}

	// ======================================= способ 1, сложность O(n^2)
	public static int[] find1(int[] ai, int[] bi){
		// на первом проходе - ищем только в массиве начал!
		int[] ap = nearest(0, ai);
		int[] bp = null;
		
		int x = ap[0]; // начальное (текущее) значение координаты
		int h = ap[1]; // начальное (текущее) значение функции

		int maxL = x;
		int maxH = h;
		
		int maxR = -1;
		boolean isOpen = true;
			
		while(ap!=null) {
			
			if(x==ap[0])
				ap = nearest(x, ai);
			
			if(bp==null || x==bp[0])
				bp = nearest(x, bi);
			
			
			if(ap==null || bp[0]<ap[0]){
				x = bp[0];
				h -= bp[1];
			} else if(ap[0]<bp[0]){
				x = ap[0];
				h += ap[1];
			} else { // ap[0]==bp[0]
				x = ap[0];
				h += (ap[1]-bp[1]);
			}
			
			if(h>maxH){
				maxH = h;
				maxL = x;
				isOpen = true;
			} else if(h<maxH && isOpen){
				maxR = x;
				isOpen = false;
			}
		}
		
		return new int[]{maxL, maxR};
	}
	
	// значение ближайшего элемента справа от х, и его кратность, или null
	private static int[] nearest(final int x, int[] xx){ 
		int N = xx.length;
		int[] vm = null; // result: [value, multiple]
		
		int i = 0;
		for (; i < N && vm==null; i++)
			if(xx[i]>x){
				vm = new int[2];
				vm[0] = xx[i];
				vm[1] = 1;
			}
		
		for (; i < N; i++)
			if(xx[i]==vm[0])
				vm[1]++;
			else if(xx[i]>x && xx[i]<vm[0]){
				vm[0] = xx[i];
				vm[1] = 1;
			}

		return vm;
	}
	
	// ======================================= способ 2, сложность O(n*log n)
	public static int[] find2(int[] ai, int[] bi){
		
		class X implements Comparable<X>{
			final Integer x;
			final Integer h;
			
			X(int x, int h) {
				this.x = x;
				this.h = h;
			}
			@Override
			public int compareTo(X other) {
				return this.x.compareTo(other.x);
			}
			@Override
			public String toString() {
				return "[" + x + " " + h + "]";
			}
		}
		
		List<X> xi = new ArrayList<>(2*ai.length);
		Arrays.stream(ai).mapToObj(a->new X(a, 1)).forEach(xi::add);
		Arrays.stream(bi).mapToObj(b->new X(b,-1)).forEach(xi::add);
		
		Collections.sort(xi);
		
		Iterator<X> it = xi.iterator();

		X ob = it.next();
		Integer x;
		Integer h = 0;

		int maxH = 0;
		int maxL = 0;
		int maxR = 0;
		boolean isOpen = true;

		while(it.hasNext()){
			x = ob.x;
			h += ob.h;

			while(it.hasNext() && (ob=it.next()).x.equals(x))
				h += ob.h;
			
			if(h>maxH){
				maxH = h;
				maxL = x;
				isOpen = true;
			} else if(h<maxH && isOpen){
				maxR = x;
				isOpen = false;
			}
		}
		
		if(isOpen)
			maxR = ob.x;
		
		return new int[]{maxL, maxR};
	}
}

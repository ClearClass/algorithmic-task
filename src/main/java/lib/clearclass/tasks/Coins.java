package lib.clearclass.tasks;

import java.util.ArrayList;

public class Coins {
	private ArrayList<ArrayList<Integer>> kit = new ArrayList<>();
	private int[] nom = {1, 2, 5, 10, 50};
	public Coins(int sum) {
		comb(sum, nom, nom.length-1);
	}
	private int comb(int sum, int[] nom, int p){
			int wn = nom[p]; // верхний номинал
			int rz = sum/wn; // сколько раз входит верхний номинал
			if(p==0){
				if (sum%wn==0){
					kit.add(new ArrayList<>());
					for (int i = 0; i < rz; i++) kit.get(kit.size()-1).add(wn);
					return 1;
				} else return 0;
			} else {
				p--;
				int w=0, sw=0;
				for (int i = 0; i <= rz; i++){
					w = comb(sum-i*wn, nom, p);
					sw+=w;
					for (int j = kit.size()-w; j<kit.size(); j++){
						for (int k=1; k<=i; k++) kit.get(j).add(wn);
					}
				}
				return sw;
			}
	}
	public ArrayList<ArrayList<Integer>> getResult(){
		return kit;
	}
}
package lib.clearclass.tasks;

import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NumberPermutation {
	public static Set<List<Integer>> solve(List<Integer> nums){		
		Set<List<Integer>> res = new LinkedHashSet<>();
		
		if(nums.size()==1){
			List<Integer> l = new LinkedList<>();
			l.add(nums.get(0));
			res.add(l);
			return res;
		}
		
		for (int i = 0; i < nums.size(); i++) {			
			Integer curr = nums.get(i);
			nums.remove(i);
			Set<List<Integer>> r = solve(nums);
			nums.add(i, curr);
			
			for (List<Integer> line : r)
				line.add(0, curr);
			
			res.addAll(r);
		}
		return res;
	}
}

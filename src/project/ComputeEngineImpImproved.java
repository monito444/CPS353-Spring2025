package project;

import java.util.List;
import java.util.ArrayList;

public class ComputeEngineImpImproved implements ComputeEngine{
    
    @Override
    public String compute(int value) {
    	// get absolute value if input is negative
    	if(value < 0) {
    		value = Math.abs(value);
    	}
    	
    	//compute the fibonacci sequence and put each number in a array
    	List<Integer> fibSequence = new ArrayList<>();
    	for(int i = 0; i < value; i++) {
    		fibSequence.add(fib(i));
    	}
    	
    	//compute the sum of all even numbers in the sequence
    	int fibSum = 0;
    	for(int i : fibSequence) {
    		if(isEven(i) == true) {
    			fibSum += i;
    		}
    	}
    	
    	//return int sum as String
    	String result = "" + fibSum;
    	return result;
    }
    
    // method to get the nth fibonacci number
    // this is the improved version, iterative approach
    public int fib(int n) {
    	if(n <= 1) {
    		return n;
    	}
    	int first = 0;
    	int second = 1;
    	int next;
    	for(int i = 2; i <= n; i++) {
    		next = first + second;
    		first = second;
    		second = next;
    	}
    	
    	return second;
    }
    
    // method to check if integer is even
    public boolean isEven(int n) {
    	if((n % 2) == 0) {
    		return true;
    	} else {
    		return false;
    	}
    }
}

package tmp;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Test {
	public static void main(String[] args) {
		Stack<Integer> stack= new Stack<Integer>();
		Queue<Integer> queue = new LinkedList<Integer>();
		
		queue.add(1);
		queue.add(2);
		queue.add(3);
		stack.push(0);
		stack.push(1);
		stack.push(2);
		
//		System.out.println(stack.get(0));
//		System.out.println(stack.peek());
//		System.out.println(stack.pop());
		
		System.out.println(queue.peek());
		System.out.println(queue.peek());
		System.out.println(queue.poll());
		System.out.println(queue.poll());
	}
}

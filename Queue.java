package own;

public class Queue {

	private int start;
	private String [] q;
	private int end;
	private int count = 0;
	
	public Queue(){
		start = -1;
		end = 0;
		q = new String[1];
		
	}
	private int size(){
		return count;
	}
	
	public boolean isEmpty(){
		return size() == 0;
	}

	public void enqueue(String value){
		if (size() == q.length){
			end = q.length;
			resize(2 * q.length);
		}
		if (isEmpty())
		  start = end;
		q[end] = value;
		end = (end + 1) % q.length;
		count ++;
	}
	
	public String dequeue(){
		assert (start >=0);
		String item = q[start];
		count--;
		q[start] = null;
		start = (start + 1) % q.length;
		if (count == q.length / 4){
			System.out.println("resize");
			resize(q.length / 2);
			end = q.length /2;
		}
		return item;
	}
	
	public void resize(int new_size) {
		String [] new_array = new String[new_size];
		int old_size = q.length;
		int i = start;
		int limit = (start < end) ? end : old_size;
		for (i = start; i < limit; i++) {
				new_array[i - start] = q[i];
		}
		if (end < start) {
			for (int j = 0; j < end; j ++) {
				new_array[i] = q[j];
				i++;
			}
		}
		start = 0;
		q = new_array;
	}
	
	public void printQueue() {
		for (String s: q) {
			System.out.println(s);
		}
	}

//	Queue client
	public static void main(String[] args) {
		Queue q = new Queue();
		q.enqueue("m");
		q.enqueue("i");
		q.enqueue("h");
		q.enqueue("a");
		q.dequeue();
		q.dequeue();
		q.printQueue();
		q.enqueue("bob");
		System.out.println();
		q.printQueue();
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.dequeue());
		System.out.println(q.size());
		q.enqueue("ema");
		q.printQueue();
		q.enqueue("rob");
		q.dequeue();
		q.enqueue("taxi");
		q.printQueue();
		System.out.println(q.size());
	}
}

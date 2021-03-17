package Autorske;

public class ArrayF implements FrontInterface {
	String[] f;
	int first = 0;
	int last = 0;
	int size = 0;

	public ArrayF(int size) {
		f = new String[size];
		first = 0;
		last = 0;
		this.size = size;

	}
	private int mod(int index) {
		if (index >=0)
			return index%size;
		else
			return size-(-index)%size;
	}
	@Override
	public void enqueue(String elem) {
		f[last] = elem;
		last = mod(last+1);
	}

	@Override
	public String dequeue() {
		if (isEmpty())
			return null;
		else {
			String el = f[first];
			first = mod(first+1);
			return el;
		}
	}

	@Override
	public boolean isEmpty() {
		return first == last;
	}

	 public static void main(String[] args) {
		ArrayF f = new ArrayF(100);
		f.enqueue(new String("janka"));
		f.enqueue(new String("danka"));
		f.enqueue(new String("hanka"));
		f.enqueue(new String("anka"));
		f.enqueue(new String("zuzanka"));
		f.enqueue(new String("elenka"));
		f.enqueue(new String("zofka"));
		f.enqueue(new String("evka"));
		System.out.println(f);
		while (!f.isEmpty())
			System.out.print(f.dequeue()+", ");
	    }	
}

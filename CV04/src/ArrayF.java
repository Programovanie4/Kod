// https://www.youtube.com/watch?v=VUTMiZuVUAk

public class ArrayF implements FrontInterface {
    // .,2,3,4,5,.......|
	//   ^       ^
	private String[] f;
	private int first;
	private int last;

	private int mod(int index) {
		return mymodulo(index, f.length);
	}
	private static int mymodulo(int a, int b) {
		int m = a % b;
		if (m < 0) {
			m = (b < 0) ? m - b : m + b;
		}
		return m;
	}

	public ArrayF(int size) {
		f = new String[size];
		first = 0;
		last = 0;
	}

	@Override
	public void enqueue(String elem) {
		if (isFull()) {
			//System.out.println("realloc");
			String[] newf = new String[2*f.length];
			int j = 0;
			int i = first;
			while (i != last) {
				newf[j++] = f[i];
				i = mod(i+1);
			}
			first = 0;
			last = j;
			f = newf;
		}
		f[last] = elem;
		last = mod(last+1);
	}

	@Override
	public String dequeue() {
		if (isEmpty()) {
			return null;
		} else {
			String el = f[first];
			first = mod(first+1);
			return el;
		}
	}

	@Override
	public String toString() {
		String res = "";
		int i = first;
		while (i != last) {
			res += f[i] + ",";
			i = mod(i+1);
		}
		return res;
	}

	@Override
	public boolean isEmpty() {
		return first == last;
	}

	public boolean isFull() {
		return mod(last+1) == first;
	}

	 public static void main(String[] args) {
		ArrayF f = new ArrayF(1);
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

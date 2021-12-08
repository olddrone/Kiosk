package Console;

public class Option {
	private Topping topping;
	private Vegetable vegetable;
	private Source source;

	public Option(Topping t, Vegetable v, Source s){
		topping = t;
		vegetable = v;
		source = s;
	}
	public void print() {
		topping.print();
		vegetable.print();
		source.print();
	}

	public Topping getTopping() {
		return topping;
	}

	public Vegetable getVegetable() {
		return vegetable;
	}

	public Source getSource() {
		return source;
	}

}

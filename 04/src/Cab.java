class Cab { // trieda C sp�ja triedy A + B
	private A a = new A(); // vlo�en� referencia (!) na objekt a typu A
	private B b = new B(); // vlo�en� referencia (!) na objekt b typu B

	public void doB() {
		b.doB();
	}

	public void doA() {
		a.doA();
	}
}

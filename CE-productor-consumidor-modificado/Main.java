
public class Main {
	   public static void main(String[] args) {
	      CubbyHole cub = new CubbyHole();
	      Consumidor cons = new Consumidor(cub, 1);
	      Productor prod = new Productor(cub, 1);
	      Consumidor cons2 = new Consumidor(cub, 2);
	      Productor prod2 = new Productor(cub, 2);
	      prod.start();
	      cons.start();
	      prod2.start();
	      cons2.start();
	   }
	}
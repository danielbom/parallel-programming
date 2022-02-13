package activity4;

import java.util.Random;

import activity4.core.Monitorable;

/**
 * Classe que representa um contador dentro do intervalo especificado.
 * Esta classe implementa monitovável e o seu intervalo é controlado pelas
 * funções de controle 'overcrowde' e 'exhausted'.
 * 
 * @author daniel
 */
public class BoundedCounter implements Monitorable<Integer> {

	Integer min, max, counter;
	
	public BoundedCounter(int min, int max) {
		this.min = min < max ? min : max;
		this.max = max > min ? max : min;
		counter = new Random().nextInt(this.max - this.min) + this.min;
	}
	
	@Override
	public boolean overcrowde() {
		return counter == max;
	}

	@Override
	public boolean exhausted() {
		return counter == min;
	}

	@Override
	public Integer produce(Integer value) {
		return this.increment();
	}

	@Override
	public Integer consume() {
		return this.decrement();
	}
	
	public Integer increment() {
		return ++counter;
	}
	
	public Integer decrement() {
		return --counter;
	}
	
}

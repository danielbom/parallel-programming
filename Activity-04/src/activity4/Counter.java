package activity4;

import activity4.core.Monitorable;

/**
 * Classe responsável por representar um temporizador com tempo variável e
 * permitir que classes durmam até um valor específico do contador. As operações
 * de controle estão definidas a partir da interface Monitorable.
 * 
 * @author daniel
 */
public class Counter implements Monitorable<Integer> {

	Integer counter = 0, sleepUntil = 0;
	
	public Counter() {}
	
	public Counter(Integer sleepUntil) {
		this.sleepUntil = sleepUntil;
	}

	@Override
	public boolean overcrowde() {
		return false;
	}

	@Override
	public boolean exhausted() {
		return counter < sleepUntil ;
	}

	@Override
	public Integer produce(Integer value) {
		return ++counter;
	}

	@Override
	public Integer consume() {
		return counter;
	}
	
	public Integer sleepUntil(Integer sleepUntil) {
		this.sleepUntil += sleepUntil;
		return sleepUntil;
	}
}

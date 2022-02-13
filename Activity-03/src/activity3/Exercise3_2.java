package activity3;

/**
 * 1. Faça um programa em Java que use Threads para encontrar os números primos
 * dentro de um intervalo. O método que contabiliza os número primos deve
 * possuir como entrada valor inicial e final do intervalo, número de threads.
 * 
 * 2. Modifique o código para garantir que será thread-safe. Implemente três
 * versões: Usando Atomic, sincronizando o método e sincronizando o bloco.
 * 
 * 3. Para o exercício anterior, compare o desempenho medindo o tempo de início
 * e término para processar o intervalo.
 * 
 * @author daniel
 *
 */
public class Exercise3_2 {
    public static void main(String[] args) {
        RangePrimes rangePrimes = new RangePrimes(0, 1_000_000, 8);
        long countIterative = rangePrimes.countPrimesIterative();
        long countUnsafe = rangePrimes.countPrimesUnsafe();
        long countAtomic = rangePrimes.countPrimesAtomic();

        System.out.println("Interativo: " + countIterative);
        System.out.println("Sem segurança: " + countUnsafe);
        System.out.println("Atomico: " + countAtomic);
    }
}

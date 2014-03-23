package dem2;

import java.util.Scanner;

class Processer extends Thread{

	public void run(){
		while(App.running){
			System.out.println("hello" + App.running);
		}
	}
	
	
}

public class App {

	//  volatile 不同线程之间增加可见性。硬件上限制，并不需要一个同步管理机制。
	//	不会从2级缓存中读（但是读写仍然是2步，只能保证最新的写，能被读到）。加上后不会出现下面这种情况
	//	hellotrue
	//	berfore shutdown 
	//	after shutdown 
	//	hellotrue
	public static volatile boolean running = true; 
//	public static  boolean running = true; 
	public static void main(String[] args) {
		Processer process = new Processer();
		process.start();
		System.out.println("Press return to stop...");
		Scanner scanner = new Scanner(System.in);
		scanner.nextLine();
		System.out.println(" berfore shutdown ");
		running = false;  //volatile 可以增加这一步的原子性。不会写入2级缓存。
		System.out.println(" after shutdown ");
		
	}
	

}

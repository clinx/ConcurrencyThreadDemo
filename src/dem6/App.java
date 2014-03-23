package dem6;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Processer implements Runnable {
	private CountDownLatch latch;
	
	public Processer(CountDownLatch latch){
		this.latch =latch;
	}


	public void run() {
		System.out.println("start");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("end");
		latch.countDown();
	}
	
	
}

public class App {


	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(4);
		ExecutorService  service = Executors.newFixedThreadPool(4);
		for(int i=0;i<4;i++){
			service.submit(new Processer(latch));
		}
		service.shutdown();//不加的话管理线程会一直运行。
		try {
			//使当前(这个当中就是主线程)线程在锁存器倒计数至零之前一直等待，除非线程被中断.
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Complete");
	}

}

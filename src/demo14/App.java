package demo14;

import java.util.Random;


public class App {

	public static void main(String[] args){
		System.out.println("Starting.");
		Thread t = new Thread(new Runnable(){


			public void run() {
				Random ran = new Random();
				for(int i=0; i<1E8;i++){
						try {
							Thread.sleep(1);
						} catch (InterruptedException e) {
							
							System.out.println("have been interrupted " + i);
							break;
						}
					Math.sin(ran.nextDouble());
				}
				
			}
			
		});
		t.start();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		t.interrupt();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Finished.");
	}
}

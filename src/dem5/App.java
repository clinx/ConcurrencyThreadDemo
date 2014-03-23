package dem5;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Processor implements Runnable{


	private int id;
	
	public Processor(int id){
		this.id = id;
	}
	
	public void run() {
		System.out.println("Thread id" + Thread.currentThread().getId());
		System.out.println("Starting: " + id);
		//这里可以用来处理文件，和server request。通常是耗时的处理。
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Completed: " + id);
	}
	
}

public class App {
   public static void  main(String[] args){
	   ExecutorService executor = Executors.newFixedThreadPool(2);//2 worker
	   for(int i=0; i<6;i++){//6 task
		   executor.submit(new Processor(i));
	   }
	   executor.shutdown(); // stop accepting new tasks. and it wait for all thread to done.
	   System.out.println("All tasks submitted");
	   try {
//		   Blocks until all tasks have completed execution after
//		   a shutdown request, or the timeout occurs, or the 
//		   current thread is interrupted, whichever happens first.
			//设置一个timeout时间。如果时间到了就结束，不管任务有没有完成。   
			executor.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	   System.out.println("All tasks completed.");
   }
}

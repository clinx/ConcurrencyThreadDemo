package dem13;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class App {
	
	public static void main(String[] args){
		
		ExecutorService executor = Executors.newCachedThreadPool();
		Future<?> future = executor.submit(new Callable<Void>(){
			//Callabe 可以有返回值。
			@Override
			public Void call() throws Exception {
				Random random = new Random();
				int duration = random.nextInt(2000);
				if(duration>1000){
					throw new IOException("Sleeping for too long.");
				}
				System.out.println("Starting ...");
				try{
					Thread.sleep(duration);
				}catch(InterruptedException e){
					e.printStackTrace();
				}
				
				System.out.println("Finished ...");
				return null;
			}
			
		});
		executor.shutdown();
		try {
			//future get will wait.
			System.out.println("Result is: " + future.get());
		} catch (InterruptedException e) {
		
			e.printStackTrace();
		} catch (ExecutionException e) {
			IOException ex = (IOException) e.getCause();
			System.out.println(ex.getMessage());
		}
	}
}


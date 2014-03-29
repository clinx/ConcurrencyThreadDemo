package dem3.one;

import java.util.Random;
//同一时间只有一个线程能拥有相同的锁。
/*one thredid: 8 mills: 1396075117346
one thredid: 8 mills: 1396075117446
two thredid: 9 mills: 1396075117546
two thredid: 9 mills: 1396075117646
two thredid: 9 mills: 1396075117746
two thredid: 9 mills: 1396075117846
two thredid: 9 mills: 1396075117946
two thredid: 9 mills: 1396075118046
one thredid: 8 mills: 1396075118146*/
public class LockTEST {
	 private Random random = new Random();
	    private static LockTEST lock = new LockTEST();
	    public void stageOne(){

	        synchronized(lock){
	            try{
	                Thread.sleep(100);
	            }catch (InterruptedException e) {
	                e.printStackTrace();
	            }

	            System.out.println( "one thredid: "+Thread.currentThread().getId() + " mills: " + System.currentTimeMillis());
	        }
	    }
	    public void stageTwo(){
	        synchronized(lock){
	            try{
	                Thread.sleep(100);
	            }catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	            System.out.println( "two thredid: "+Thread.currentThread().getId() + " mills: " + System.currentTimeMillis());
	        }
	    }
	    public static void main(String[] args){
	        System.out.println("Hello Main");
	        long start = System.currentTimeMillis();
	        Thread t1 = new Thread(){
	            public void run(){
	                for(int i = 0; i< 1000;i++){
	                    lock.stageOne();
	                }
	              
	            }
	        };
	        Thread t2 = new Thread(){
	            public void run(){
	                for(int i = 0; i< 1000;i++){
	                    lock.stageTwo();
	                }
	            }
	        };
	        t1.start();
	        t2.start();
	        try{
	            t1.join();
	            t2.join();
	        }catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        long end = System.currentTimeMillis();
	        System.out.println("Time take:" + (end-start));

	    }
}

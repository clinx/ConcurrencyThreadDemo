package dem4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Worker {
	private Random random = new Random();
	private List<Integer> list1 = new ArrayList<Integer>();
	private List<Integer> list2 = new ArrayList<Integer>();
	public void stageOne(){
		//这样多个线程就不能同时对list1进行操作了。
		//试一试用同一个锁,比如this.看一看时间
		//		Hello Main
		//		Time take:4002
		//		list1: 2000 list2: 2000
		//如果不同步的话list会有2000的长度吗。
		//		Hello Main
		//		Time take:2001
		//		list1: 1996 list2: 1999
		synchronized(list1){
			try{
				Thread.sleep(1);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			list1.add(random.nextInt(100));
		}
	}
	public void stageTwo(){
		synchronized(list2){
			try{
				Thread.sleep(1);
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			list2.add(random.nextInt(100));
		}
	}
	
	public void process(){
		for(int i=0;i<1000;i++){
			stageOne();
			stageTwo();
		}
	}
	
	public void main(){
		System.out.println("Hello Main");
		long start = System.currentTimeMillis();
		Thread t1 = new Thread(){
			public void run(){
				Worker.this.process();
			}
		};
		Thread t2 = new Thread(){
			public void run(){
				Worker.this.process();
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
		System.out.println("list1: " + list1.size() + " list2: "+list2.size());
	}
}

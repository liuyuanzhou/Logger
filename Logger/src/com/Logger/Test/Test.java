package com.Logger.Test;

import com.Logger.Impl.*;
import com.Logger.Interface.ILogger;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Queue<Integer> q = new LinkedList<Integer>();
//		q.add(1);
//		q.add(2);
//		q.add(3);
//		q.add(4);
//		System.out.println(q.size());
//		System.out.println(q.poll());
//		System.out.println(q.poll());
//		System.out.println(q.poll());
//		System.out.println(q.poll());
//		System.out.println(q.poll());

		System.out.println(LoggerLevel.Info.intValue());
		
		ILogger logger = LoggerManager.getLogger(Test.class.getName(), 10000);
		for(int i=0;i<1;i++)
		{
			logger.info(23, "info Message "+String.valueOf(i));
		} 
	}
}

package com.Logger.LazyQueue;
import com.Logger.Interface.*;

import java.lang.Object;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class LazyQueue<T> 
{
	private int _maxQueueLength = 65535;
	
	private int _batchCount;
	
	private int _intervalTime;
	
	private long _lastTick;
	
	private Action<ArrayList<T>> _dequeueAction;
	
	private Queue<T> _queue = new LinkedList<T>();
	
	private Thread _thread;
	
	private Object _syncRoot = new Object();
	
	public int MaxQuqueCount()
	{
		return _maxQueueLength;
	}
	
	public int ItemCount()
	{
		return _queue.size();
	}
	
	public LazyQueue(final String queueName,final int batchCount,final int intervalTime,final Action<ArrayList<T>> dequeueAction)
	{
		_batchCount = batchCount;
		_intervalTime = intervalTime;
		_dequeueAction = dequeueAction;
		_lastTick = System.currentTimeMillis();
		
		_thread = new Thread(){
			@Override
			public void run()
			{
				System.out.println("begin queue thread!");
				while(true)
				{
					try{
						long nowTick = System.currentTimeMillis();
						int queueCount = _queue.size();
						int dequeueCount;

						long passedTick = nowTick - _lastTick;
						if((passedTick > _intervalTime || passedTick < -_intervalTime) && queueCount > 0)
						{
							if(queueCount > _batchCount)
								dequeueCount = _batchCount;
							else
								dequeueCount = queueCount;
							_lastTick = nowTick;
						}
						else
							dequeueCount = 0;

						if(dequeueCount > 0)
						{
							ArrayList<T> ret = dequeueItems(dequeueCount);
							_dequeueAction.run(ret);
						}
						Thread.sleep(1);
					}
					catch(Exception ex)
					{
						//print log,now just do nothing,fill it after finish tracing manager.
						ex.printStackTrace();
					}
				}
			}
		};
		_thread.start();
	}
	
	public void Enqueue(T item)
	{
		if(_queue.size() < _maxQueueLength)
		{
			synchronized(_syncRoot){
				_queue.add(item);
			}
		}
	}
	
	private ArrayList<T> dequeueItems(int count)
	{
		ArrayList<T> ret = new ArrayList<T>();
		synchronized(_syncRoot){
			if(_queue.size() < count)
			{
				count = _queue.size();
			}
			for(int i = 0;i < count; i++)
			{
				ret.add(_queue.poll());
			}
		}
		return ret;
	}
	
	public void finialize()
	{
		_thread.interrupt();
		_thread = null;
	}
}

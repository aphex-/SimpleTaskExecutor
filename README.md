# SimpleTaskExecutor

 The SimpleTaskExecutor simply executes tasks on different threads and pass the result back to the origin thread.
 
 I needed a simple mechanism to add some tasks to another thread and get a callback if the work is done. I did not
 want to have logic that creates threads or handles syncronization in my production code. There are solutions like 
 [SwingWorker](https://docs.oracle.com/javase/7/docs/api/javax/swing/SwingWorker.html) or
 [AsyncTask](http://developer.android.com/reference/android/os/AsyncTask.html) but they are usnig Swing and Android.
 
 
 So I came up with a solution that works like this:
 
 ```java
SimpleTaskExecutor<String> simpleTaskExecutor = new SimpleTaskExecutor<String>();

// add a task
simpleTaskExecutor.addTask(new Callable<String>() {
	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "Task 01 ready";
	}
}, new SimpleTaskExecutor.ResultListener<String>() {
	@Override
	public void onResult(String result) {
		System.out.println(result);
	}
});

// add another task
simpleTaskExecutor.addTask(new Callable<String>() {
	@Override
	public String call() throws Exception {
		Thread.sleep(2000);
		return "Task 02 ready";
	}
}, new SimpleTaskExecutor.ResultListener<String>() {
	@Override
	public void onResult(String result) {
		System.out.println(result);
	}
});

// start execution
simpleTaskExecutor.execute();
```

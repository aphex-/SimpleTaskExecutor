import com.nukethemoon.tools.simpletask.SimpleTaskExecutor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;

public class TestSimpleTaskExecutor {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SimpleTaskExecutor<String> simpleTaskExecutor = new SimpleTaskExecutor<String>();

		System.out.println("The base thread name is '" + Thread.currentThread().getName() + "'.");
		final long startTime = System.currentTimeMillis();

		// create 10 tasks
		for (int i = 0; i < 10; i++) {
			final int finalI = i;

			simpleTaskExecutor.addTask(new Callable<String>() {
				@Override
				public String call() throws Exception {
					Thread.sleep(finalI * 100);
					return "I am task " + finalI + ". I finished my work on thread '" + Thread.currentThread().getName() + "'.";
				}
			}, new SimpleTaskExecutor.ResultListener<String>() {
				@Override
				public void onResult(String result) {
					System.out.println(result);
					System.out.print("Received the work on thread '" + Thread.currentThread().getName() + "' ");
					System.out.print("after " + (System.currentTimeMillis() - startTime) + " ms. \n");
				}
			});
		}

		simpleTaskExecutor.execute();
		System.out.println("Finished al tasks '" + Thread.currentThread().getName() + "'.");
	}
}


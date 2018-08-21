package com.sri.schedulers;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ScheduledTasks
{
	private final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(10);

	private static final TaskMapper taskMapper = new TaskMapper();

	public ScheduledFuture<?> scheduleTask(TaskParams taskParams, long initialDelay, long period, TimeUnit timeunit)
	{
		/*
		 * TODO all processing code to be updated to this run method
		 */
		Runnable taskProcessor = new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					// Replace with your original code.. this was just a test to make sure it worked
					System.out.println(new Date() + " - Executing thread with the params: " + taskParams.getAppId() + " --> " + taskParams.getTableId());

				} catch (Exception e)
				{
					// TODO no exception should ever leak from here
				}
			}
		};

		return executorService.scheduleAtFixedRate(taskProcessor, initialDelay, period, timeunit);
	}

	// TODO this has to come after db call; delete once that part is done
	static long taskId = 0L;

	private void scheduleRecurringTaskAndStoreReference(TaskParams taskParams, long initialDelay, long period, TimeUnit timeunit)
	{
		ScheduledFuture<?> future = scheduleTask(taskParams, initialDelay, period, timeunit);

		// TODO store to database --> taskid (auto generated), task params, ScheduledFuture reference needed for cancelling as needed
		taskMapper.putTaskReference((++taskId), future);
		System.out.println("taskid: " + taskId);
	}

	public static void main(String[] args) throws InterruptedException
	{
		ScheduledTasks st = new ScheduledTasks();

		TaskParams taskParams = new TaskParams();
		taskParams.setAppId("1");
		taskParams.setTableId("1A");
		st.scheduleRecurringTaskAndStoreReference(taskParams, 1, 5, TimeUnit.SECONDS);

		taskParams = new TaskParams();
		taskParams.setAppId("2");
		taskParams.setTableId("2A");
		st.scheduleRecurringTaskAndStoreReference(taskParams, 1, 10, TimeUnit.SECONDS);

		taskParams = new TaskParams();
		taskParams.setAppId("3");
		taskParams.setTableId("3A");
		st.scheduleRecurringTaskAndStoreReference(taskParams, 1, 3, TimeUnit.SECONDS);

		Thread.sleep(30000);
		System.out.println("Canceling task 2");
		taskMapper.getTaskReference(2).cancel(false);
	}

}

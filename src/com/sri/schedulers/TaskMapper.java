package com.sri.schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

public class TaskMapper
{
	private Map<Long, ScheduledFuture<?>> taskMapper = new HashMap<Long, ScheduledFuture<?>>(100);

	public ScheduledFuture<?> getTaskReference(long taskId)
	{
		return taskMapper.get(taskId);
	}

	public ScheduledFuture<?> putTaskReference(long taskId, ScheduledFuture<?> future)
	{
		return taskMapper.put(taskId, future);
	}
}

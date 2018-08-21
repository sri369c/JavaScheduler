package com.sri.schedulers;

public class TaskParams
{
	String appId;
	String tableId;

	public TaskParams()
	{
	}

	public TaskParams(String appId, String tableId)
	{
		this.appId = appId;
		this.tableId = tableId;
	}

	public String getAppId()
	{
		return appId;
	}

	public void setAppId(String appId)
	{
		this.appId = appId;
	}

	public String getTableId()
	{
		return tableId;
	}

	public void setTableId(String tableId)
	{
		this.tableId = tableId;
	}
}

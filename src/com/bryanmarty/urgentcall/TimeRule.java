package com.bryanmarty.urgentcall;

import java.util.Date;

public class TimeRule {

	private long id_;
	private long start_;
	private long end_;
	
	public TimeRule (long id, Date startDate, Date endDate) {
		this(startDate, endDate);
		this.id_ = id;
	}
	
	public TimeRule(Date startDate, Date endDate) {
		setStartDate(startDate);
		setEndDate(endDate);
	}
	
	public long getId() {
		return id_;
	}
	
	public void setStartDate(Date start) {
		this.start_ = start.getTime();
	}
	
	public void setEndDate(Date end) {
		this.end_ = end.getTime();
	}
	
	public Date getStartDate() {
		return new Date(start_);
	}
	
	public Date getEndDate() {
		return new Date(end_);
	}
}

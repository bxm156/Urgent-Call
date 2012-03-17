package com.bryanmarty.urgentcall;

import java.util.Date;

import android.os.Parcel;
import android.os.Parcelable;

public class UrgentEntry implements Parcelable {
	private String nickname;
	private String phoneNumber;
	private Date startDate;
	private Date endDate;
	
	public UrgentEntry() {
		
	}
	
	public UrgentEntry(Parcel in) {
		nickname = in.readString();
		//phoneNumber = in.readString();
		//startDate = new Date(in.readLong());
		//endDate = new Date(in.readLong());
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public int describeContents() {
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nickname);
		//dest.writeString(phoneNumber);
		//dest.writeLong(startDate.getTime());
		//dest.writeLong(endDate.getTime());
	}
	  // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<UrgentEntry> CREATOR = new Parcelable.Creator<UrgentEntry>() {
        public UrgentEntry createFromParcel(Parcel in) {
            return new UrgentEntry(in);
        }

        public UrgentEntry[] newArray(int size) {
            return new UrgentEntry[size];
        }
    };
	
}

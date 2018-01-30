package com.omka.mackhaton.entity;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FilterResult {

@SerializedName("code")
@Expose
private int code;
@SerializedName("msg")
@Expose
private String msg;
@SerializedName("records")
@Expose
private List<Record> records = null;

public int getCode() {
return code;
}

public void setCode(int code) {
this.code = code;
}

public String getMsg() {
return msg;
}

public void setMsg(String msg) {
this.msg = msg;
}

public List<Record> getRecords() {
return records;
}

public void setRecords(List<Record> records) {
this.records = records;
}

}
package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class SubMenuItem implements Serializable {

@SerializedName("SubMenuCode")
@Expose
private Integer subMenuCode;
@SerializedName("SubMenuName")
@Expose
private String subMenuName;
@SerializedName("Items")
@Expose
private List<Item> items = null;

public Integer getSubMenuCode() {
return subMenuCode;
}

public void setSubMenuCode(Integer subMenuCode) {
this.subMenuCode = subMenuCode;
}

public String getSubMenuName() {
return subMenuName;
}

public void setSubMenuName(String subMenuName) {
this.subMenuName = subMenuName;
}

public List<Item> getItems() {
return items;
}

public void setItems(List<Item> items) {
this.items = items;
}

}
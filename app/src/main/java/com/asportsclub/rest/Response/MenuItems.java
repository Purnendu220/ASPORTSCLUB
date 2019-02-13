package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuItems {

@SerializedName("StatusCode")
@Expose
private StatusCode statusCode;
@SerializedName("MenuItems")
@Expose
private List<MenuItem> menuItems = null;

public StatusCode getStatusCode() {
return statusCode;
}

public void setStatusCode(StatusCode statusCode) {
this.statusCode = statusCode;
}

public List<MenuItem> getMenuItems() {
return menuItems;
}

public void setMenuItems(List<MenuItem> menuItems) {
this.menuItems = menuItems;
}

}
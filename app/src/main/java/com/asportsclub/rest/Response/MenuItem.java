package com.asportsclub.rest.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MenuItem {

@SerializedName("MenuCode")
@Expose
private Integer menuCode;
@SerializedName("MenuName")
@Expose
private String menuName;
@SerializedName("SubMenuItems")
@Expose
private List<SubMenuItem> subMenuItems = null;

private boolean isExpanded = true;

public Integer getMenuCode() {
return menuCode;
}

public void setMenuCode(Integer menuCode) {
this.menuCode = menuCode;
}

public String getMenuName() {
return menuName;
}

public void setMenuName(String menuName) {
this.menuName = menuName;
}

public List<SubMenuItem> getSubMenuItems() {
return subMenuItems;
}

public void setSubMenuItems(List<SubMenuItem> subMenuItems) {
this.subMenuItems = subMenuItems;
}

    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }
}
package com.duo.user.entity;

import com.duo.common.entity.TreeNode;




/**
 * Created by Ace on 2017/6/12.
 */
public class MenuTree extends TreeNode {
    String icon;
    String name;
    String href;
    boolean spread = false;

    public MenuTree() {
    }

    public MenuTree(String id, String name, String parentId) {
        this.id = id;
        this.parentId = parentId;
        this.name = name;
    }
    public MenuTree(String id, String name, MenuTree parent) {
        this.id = id;
        this.parentId = parent.getId();
        this.name = name;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

   
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }
}

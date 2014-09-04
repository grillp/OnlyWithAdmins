package com.cancas.bukkit.onlywithadmins;

import java.util.HashSet;
import java.util.Set;

public class OnlineAdmins {

	public Set<String> admins = new HashSet<String>();
	
	public boolean hasAdmins() {
		return adminCount() > 0;
	}

	public int adminCount() {
		return admins.size();
	}

	public void addAdmin(String adminName) {
		admins.add(adminName);
	}

	public void removeAdmin(String adminName) {
		admins.remove(adminName);
	}

}

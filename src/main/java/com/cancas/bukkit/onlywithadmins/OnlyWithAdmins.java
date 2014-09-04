package com.cancas.bukkit.onlywithadmins;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;

public class OnlyWithAdmins extends JavaPlugin implements Listener {

	private static final String ADMIN_PERMISSION_NAME = "onlywithadmins.admin";
	private OnlineAdmins adminsOnline = new OnlineAdmins();
	@Override
	public void onDisable() {
		getLogger().info("onDisable has been invoked!");
	}

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onJoin(PlayerLoginEvent event) {
		Player player = event.getPlayer();
		String playerName = player.getName();
		if(playerHasAdminPermission(player)) {
			adminLoginEvent(playerName);
		}
		else {
			nonAdminLoginEvent(event, playerName);
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		String playerName = event.getPlayer().getName();
		adminsOnline.removeAdmin(playerName);
		getLogger().info("Player " + playerName + " has quit");
		getLogger().info("" +adminsOnline.adminCount() + " Admins Left");
		if (!adminsOnline.hasAdmins()) {
			KickAllPlayers();
		}
	}

	private void nonAdminLoginEvent(PlayerLoginEvent event, String playerName) {
		if (!adminsOnline.hasAdmins()) {
			getLogger().info("Player " + playerName + " not allowed. No admin online.");
			event.disallow(PlayerLoginEvent.Result.KICK_FULL, "Sorry, Currently no admins online! Try again later");
		}
		else {
			getLogger().info("Player " + playerName + " allowed. Admin Online.");
		}
	}

	private void adminLoginEvent(String playerName) {
		getLogger().info("Player Admin " + playerName + " has arrived");
		adminsOnline.addAdmin(playerName);
	}

	private boolean playerHasAdminPermission(Player player) {
		return player.hasPermission(new Permission(ADMIN_PERMISSION_NAME, PermissionDefault.FALSE));
	}

	private void KickAllPlayers() {
		getLogger().info("!!Kicking all other players!!");
		for (Player player : getServer().getOnlinePlayers()) {
			player.kickPlayer("Sorry, Last Admin has left! Try again Later");
		}
	}
	
}

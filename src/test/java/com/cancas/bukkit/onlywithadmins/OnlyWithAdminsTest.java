package com.cancas.bukkit.onlywithadmins;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.never;

import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.permissions.Permission;
import org.hamcrest.core.IsAnything;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.internal.matchers.Any;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;


@RunWith(PowerMockRunner.class)
public class OnlyWithAdminsTest {

	OnlyWithAdmins subject;
	@Before
	public void Before() {
		subject = new OnlyWithAdmins();
	}
	
	@Test
	@PrepareForTest(PlayerLoginEvent.class)
	public void testName() throws Exception {
		PlayerLoginEvent loginEvent = PowerMockito.mock(PlayerLoginEvent.class);

		Player player = mock(Player.class);
		when(player.getName()).thenReturn("MyUser");
		when(player.isPermissionSet(isA(Permission.class))).thenReturn(false);
		when(loginEvent.getPlayer()).thenReturn(player);
		
		subject.onJoin(loginEvent);		
		
	}
}


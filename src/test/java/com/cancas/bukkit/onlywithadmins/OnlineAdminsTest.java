package com.cancas.bukkit.onlywithadmins;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.cancas.bukkit.onlywithadmins.OnlineAdmins;

public class OnlineAdminsTest {

	OnlineAdmins subject;
	@Before
	public void before() {
		subject = new OnlineAdmins();
	}
	
	@Test
	public void hasAdminsShoudReturnFalseIfNoOneIsOnline() {
		assertFalse(subject.hasAdmins());
	}
	
	@Test
	public void hasAdminsShouldReturnTrueIfAdminIsAdded() throws Exception {
		subject.addAdmin("SomeUser");
		assertTrue(subject.hasAdmins());
	}

	@Test
	public void hasAdminsShouldReturnTrueIfMultipleAdminsAreOnline() throws Exception {
		subject.addAdmin("SomeUser");
		subject.addAdmin("AnotherUser");
		assertTrue(subject.hasAdmins());
	}
	
	@Test
	public void hasAdminsShouldReturnFalseIfNoMoreAdminsAreOnline() throws Exception {
		subject.addAdmin("SomeUser");
		subject.removeAdmin("SomeUser");
		assertFalse(subject.hasAdmins());
	}
	
	@Test
	public void hasAdminsShouldReturnTrueIfMoreThanOneAdminIsAdded() throws Exception {
		subject.addAdmin("SomeUser");
		subject.addAdmin("AnotherUser");
		subject.removeAdmin("AnotherUser");
		subject.removeAdmin("AnotherUser");
		assertTrue(subject.hasAdmins());
	}
	
	@Test
	public void addAdminShouldOnlyAddAnAdminIfTheAdminIsNotAlreadyAdded() throws Exception {
		subject.addAdmin("AnotherUser");
		subject.addAdmin("AnotherUser");
		assertEquals(1, subject.adminCount());
		subject.removeAdmin("AnotherUser");
		assertEquals(0, subject.adminCount());
	}
	
	@Test
	public void adminCountShouldReturnNumberOfAdmins() throws Exception {
		subject.addAdmin("SomeUser");
		assertEquals(1, subject.adminCount());
		subject.addAdmin("AnotherUser");
		assertEquals(2, subject.adminCount());
		subject.removeAdmin("AnotherUser");
		assertEquals(1, subject.adminCount());
	}
	
}

package com.fr4gus.android.oammblo.service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import com.fr4gus.android.oammblo.bo.Tweet;

public abstract class TwitterService {
	Set<TwitterListener> listeners = new CopyOnWriteArraySet<TwitterListener>();

	public void addListener(TwitterListener listener) {
		listeners.add(listener);
	}

	public void removeListener(TwitterListener listener) {
		listeners.remove(listener);
	}

	public abstract boolean authenticate(String username, String password)
			throws TwitterServiceException;

	public abstract List<Tweet> getTimeline() throws TwitterServiceException;

	public abstract List<Tweet> getPublicTimeline()
			throws TwitterServiceException;
}

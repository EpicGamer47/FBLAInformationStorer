package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

public class Entry {
	// TODO ?
	// private static HashSet<Entry> cache = new HashSet<Entry>();
	
	final String name; 
	final List<String> tags;
	final TreeMap<String, String> pairs;
	
	public Entry(String name) {
		this.name = name;
		
		tags = null;
		pairs = null;
	}

	public Entry(String name, List<String> tags, TreeMap<String, String> map) {
		this.name = name;
		this.tags = tags;
		this.pairs = map;
		
		// cache.add(this);
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s, # of tags: %d, # of pairs: %d", name, tags.size(), pairs.size());
	}
	
	public String toFullString() {
		return String.format("Name: %s\n\nTags: %s\n\nPairs: %s", name, tags.toString(), pairs.toString());
	}
	
	/**
	 * im not writing this properly unless i have to
	 * which i dont
	 */
	@Override
	public boolean equals(Object o) {
		Entry e = (Entry) o;
		return e.name.equals(name);
	}
	
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	public boolean nameContains(String s) {
		return name.contains(s);
	}
}
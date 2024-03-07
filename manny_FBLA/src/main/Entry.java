package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

public class Entry {
	// TODO ?
	// private static HashSet<Entry> cache = new HashSet<Entry>();
	
	final String name; 
	final List<String> tags;
	final SortedMap<String, String> pairs;
	
	public Entry(String name) {
		this.name = name;
		
		tags = null;
		pairs = null;
	}

	public Entry(String name, List<String> tags, SortedMap<String, String> map) {
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
	
	public static List<String> stringToList(String s) {
		return Arrays.asList(s.split(","));
	}
	
	public static SortedMap<String, String> stringToMap(String s) {
		String[] mapAsArray = s.split("=");
		var maps = new TreeMap<String, String>();
		
		for (int i = 0; i < mapAsArray.length; i += 2) {
			maps.put(mapAsArray[i], mapAsArray[i + 1]);
		}
		
		return maps;
	}
	
	public static String listToString(List<String> list) {
		if (list.isEmpty())
			return "";
		
		StringBuilder out = new StringBuilder();
		
		for (String entry : list) {
			out.append(entry + ",");
		}
		
		out.deleteCharAt(out.length() - 1); // pop last comma
		
		return out.toString();
	}
	
	public static String mapToString(SortedMap<String, String> map) {
		if (map.isEmpty())
			return "";
		
		StringBuilder out = new StringBuilder();
		
		for (var entry : map.entrySet()) {
			out.append(entry.getKey() + "=" + entry.getValue() + ",");
		}
		
		out.deleteCharAt(out.length() - 1); // pop last comma
		
		return out.toString();
	}
}
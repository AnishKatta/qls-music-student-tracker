package qls.music.studenttracker.driver;

import java.util.List;


import com.jakewharton.fliptables.FlipTableConverters;

import qls.music.studenttracker.client.model.DataTable;

public class DisplayUtil {
	
	
	public static void render(final List<? extends DataTable> results) {
		String[] headers = results.get(0).getHeaderColumns();
		String[][] data = new String[results.size()][headers.length];
		for (int i = 0; i < results.size(); i++) {
			data[i] = results.get(i).getColumnValues();
		}
		System.out.println(FlipTableConverters.fromObjects(headers, data));
	}
}

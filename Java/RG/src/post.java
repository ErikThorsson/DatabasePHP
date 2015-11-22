
public class post {
	int id;
	String user;
	String title;
	String	text;
	double latitude;
	double longitude;
	String time_stamp;
	
	public post(int i, String u, String t, String txt, double lat, double longi, String ts) {
		id = i;
		user = u;
		title = t;
		text = txt;
		latitude = lat;
		longitude = longi;
		time_stamp = ts;
	}
	
	public post() {
	}
}

package cz.uhk.fim.studentspubguide.parse;

public class Comment {
	private String id;
	private String text;
	private String id_pubu;
	
	
	public Comment() {
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getId_pubu() {
		return id_pubu;
	}


	public void setId_pubu(String id_pubu) {
		this.id_pubu = id_pubu;
	}


	public Comment(String id, String text, String id_pubu) {
		super();
		this.id = id;
		this.text = text;
		this.id_pubu = id_pubu;
	}

	
}

package model.tables;
/**
 * Model klasa koja predstavlja kolonu sa svim atributima iz kolone iz baze, potrebnim za implementaciju.
 * @author Nemanja Sobo
 *
 */
public class Column {

	String type;
	String name;
	String code;

	


	public Column(String type, String name, String code) {
		super();
		this.type = type;
		this.name = name;
		this.code = code;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

	
}

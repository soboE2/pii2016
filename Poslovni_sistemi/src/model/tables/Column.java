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
	String fkTableCode;
	boolean isPk;
	boolean isFk;
	boolean isMandatory;
	int length;
	int precision;

	
	public Column(String type, String name, String code, boolean isPk,
			boolean isFk,boolean isMandatory,String fkTableCode,int length,int precision) {
		super();
		this.type = type;
		this.name = name;
		this.code = code;
		this.isPk = isPk;
		this.isFk = isFk;
		this.isMandatory = isMandatory;
		this.fkTableCode = fkTableCode;
		this.length = length;
		this.precision = precision;
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
	public String getFkTableCode() {
		return fkTableCode;
	}
	public void setFkTableCode(String fkTableCode) {
		this.fkTableCode = fkTableCode;
	}
	public boolean isPk() {
		return isPk;
	}
	public void setPk(boolean isPk) {
		this.isPk = isPk;
	}
	public boolean isFk() {
		return isFk;
	}
	public void setFk(boolean isFk) {
		this.isFk = isFk;
	}
	public int getPrecision() {
		return precision;
	}
	public void setPrecision(int precision) {
		this.precision = precision;
	}
	public boolean isMandatory() {
		return isMandatory;
	}
	public void setMandatory(boolean isMandatory) {
		this.isMandatory = isMandatory;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	
	
}

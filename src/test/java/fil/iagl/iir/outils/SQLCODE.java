package fil.iagl.iir.outils;

public enum SQLCODE {

	NOT_NULL_VIOLATION("23502");
	
	private String code;
	
	SQLCODE(String code){
		this.code = code;
	}
	
	public String getSqlCode(){
		return this.code;
	}
	
}

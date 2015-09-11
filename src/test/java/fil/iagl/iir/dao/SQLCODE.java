package fil.iagl.iir.dao;

public enum SQLCODE {

	NOT_NULL_VIOLATION("23502");
	
	private String code;
	
	SQLCODE(String code){
		this.code = code;
	}
	
	String getSqlCode(){
		return this.code;
	}
	
}

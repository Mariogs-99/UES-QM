package sv.global.colas.controllers.rc;

import java.io.Serializable;

public class Rcode implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String code ;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rcode [code=");
		builder.append(code);
		builder.append("]");
		return builder.toString();
	}
	

}

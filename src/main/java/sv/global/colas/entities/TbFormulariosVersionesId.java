package sv.global.colas.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

@Embeddable
public class TbFormulariosVersionesId implements java.io.Serializable {

	private static final long serialVersionUID = 4778933402957542715L;

	@Column(name = "C_FORMULARIO", unique = false, nullable = false, insertable = true, updatable = true, precision = 4, scale = 0)
	@NotNull
	private Integer cformulario;

	@Column(name = "C_VERSION", unique = false, nullable = false, insertable = true, updatable = true, precision = 4, scale = 0)
	@NotNull
	private Integer cversion;

	public TbFormulariosVersionesId() {
	}

	public TbFormulariosVersionesId(Integer cformulario, Integer cversion) {
		this.cformulario = cformulario;
		this.cversion = cversion;
	}
	
	public Integer getCformulario() {
		return this.cformulario;
	}

	public void setCformulario(Integer cformulario) {
		this.cformulario = cformulario;
	}
	
	public Integer getCversion() {
		return this.cversion;
	}

	public void setCversion(Integer cversion) {
		this.cversion = cversion;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TbFormulariosVersionesId))
			return false;
		TbFormulariosVersionesId castOther = (TbFormulariosVersionesId) other;

		return (this.getCformulario() == castOther.getCformulario())
				&& (this.getCversion() == castOther.getCversion());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getCformulario();
		result = 37 * result + this.getCversion();
		return result;
	}

}

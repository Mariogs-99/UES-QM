package sv.global.colas.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.ldap.userdetails.LdapUserDetails;

public class DgiiUserDetailsImpl implements LdapUserDetails {

	private static final long serialVersionUID = -3654173063382493897L;
	private LdapUserDetails ldapUserDetails;
	private String email;
	private String nit;
	private String displayName;
	private String cadmTrib;
	private String rol;
	private String ssello;
	private Long itecnico;
	private String ubicacionFisica;
	private String unidadRecep;
	private String rawPassword;
	/*** Parametros DET ****/
	private String detFtpHost;
	private String detFtpUsr;
	private String detFtpPass;
	private String detFtpInitialDir;
	/*** Servicios WEB ****/
	private String nomInstitucion;
	private String nitRepresentante;
	private String dui;

	private Boolean ctrlchgpwd;
	private String emailPi;
	private String emailServ;

	private String banderas;
	public String unidad;

	public DgiiUserDetailsImpl(LdapUserDetails ldapUserDetails) {
		this.ldapUserDetails = ldapUserDetails;
	}

	/**
	 * @return the ldapUserDetails
	 */
	public LdapUserDetails getLdapUserDetails() {
		return ldapUserDetails;
	}

	/**
	 * @param ldapUserDetails
	 *            the ldapUserDetails to set
	 */
	public void setLdapUserDetails(LdapUserDetails ldapUserDetails) {
		this.ldapUserDetails = ldapUserDetails;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the nit
	 */
	public String getNit() {
		return nit;
	}

	/**
	 * @param nit
	 *            the nit to set
	 */
	public void setNit(String nit) {
		this.nit = nit;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the cadmTrib
	 */
	public String getCadmTrib() {
		return cadmTrib;
	}

	/**
	 * @param cadmTrib
	 *            the cadmTrib to set
	 */
	public void setCadmTrib(String cadmTrib) {
		this.cadmTrib = cadmTrib;
	}

	/**
	 * @return the rol
	 */
	public String getRol() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(String rol) {
		this.rol = rol;
	}

	/**
	 * @return the ssello
	 */
	public String getSsello() {
		return ssello;
	}

	/**
	 * @param ssello
	 *            the ssello to set
	 */
	public void setSsello(String ssello) {
		this.ssello = ssello;
	}

	/**
	 * @return the itecnico
	 */
	public Long getItecnico() {
		return itecnico;
	}

	/**
	 * @param itecnico
	 *            the itecnico to set
	 */
	public void setItecnico(Long itecnico) {
		this.itecnico = itecnico;
	}

	/**
	 * @return the ubicacionFisica
	 */
	public String getUbicacionFisica() {
		return ubicacionFisica;
	}

	/**
	 * @param ubicacionFisica
	 *            the ubicacionFisica to set
	 */
	public void setUbicacionFisica(String ubicacionFisica) {
		this.ubicacionFisica = ubicacionFisica;
	}

	/**
	 * @return the unidadRecep
	 */
	public String getUnidadRecep() {
		return unidadRecep;
	}

	/**
	 * @param unidadRecep
	 *            the unidadRecep to set
	 */
	public void setUnidadRecep(String unidadRecep) {
		this.unidadRecep = unidadRecep;
	}

	/**
	 * @return the rawPassword
	 */
	public String getRawPassword() {
		return rawPassword;
	}

	/**
	 * @param rawPassword
	 *            the rawPassword to set
	 */
	public void setRawPassword(String rawPassword) {
		this.rawPassword = rawPassword;
	}

	/**
	 * @return the detFtpHost
	 */
	public String getDetFtpHost() {
		return detFtpHost;
	}

	/**
	 * @param detFtpHost
	 *            the detFtpHost to set
	 */
	public void setDetFtpHost(String detFtpHost) {
		this.detFtpHost = detFtpHost;
	}

	/**
	 * @return the detFtpUsr
	 */
	public String getDetFtpUsr() {
		return detFtpUsr;
	}

	/**
	 * @param detFtpUsr
	 *            the detFtpUsr to set
	 */
	public void setDetFtpUsr(String detFtpUsr) {
		this.detFtpUsr = detFtpUsr;
	}

	/**
	 * @return the detFtpPass
	 */
	public String getDetFtpPass() {
		return detFtpPass;
	}

	/**
	 * @param detFtpPass
	 *            the detFtpPass to set
	 */
	public void setDetFtpPass(String detFtpPass) {
		this.detFtpPass = detFtpPass;
	}

	/**
	 * @return the detFtpInitialDir
	 */
	public String getDetFtpInitialDir() {
		return detFtpInitialDir;
	}

	/**
	 * @param detFtpInitialDir
	 *            the detFtpInitialDir to set
	 */
	public void setDetFtpInitialDir(String detFtpInitialDir) {
		this.detFtpInitialDir = detFtpInitialDir;
	}

	/**
	 * @return the nomInstitucion
	 */
	public String getNomInstitucion() {
		return nomInstitucion;
	}

	/**
	 * @param nomInstitucion
	 *            the nomInstitucion to set
	 */
	public void setNomInstitucion(String nomInstitucion) {
		this.nomInstitucion = nomInstitucion;
	}

	/**
	 * @return the nitRepresentante
	 */
	public String getNitRepresentante() {
		return nitRepresentante;
	}

	/**
	 * @param nitRepresentante
	 *            the nitRepresentante to set
	 */
	public void setNitRepresentante(String nitRepresentante) {
		this.nitRepresentante = nitRepresentante;
	}

	/**
	 * @return the dui
	 */
	public String getDui() {
		return dui;
	}

	/**
	 * @param dui
	 *            the dui to set
	 */
	public void setDui(String dui) {
		this.dui = dui;
	}

	public String getDn() {
		return ldapUserDetails.getDn();
	}

	public String getPassword() {
		return ldapUserDetails.getPassword();
	}

	public String getUsername() {
		return ldapUserDetails.getUsername();
	}

	public boolean isAccountNonExpired() {
		return ldapUserDetails.isAccountNonExpired();
	}

	public boolean isAccountNonLocked() {
		return ldapUserDetails.isAccountNonLocked();
	}

	public boolean isCredentialsNonExpired() {
		return ldapUserDetails.isCredentialsNonExpired();
	}

	public boolean isEnabled() {
		return ldapUserDetails.isEnabled();
	}

	public String getUsuario() {
		return ldapUserDetails.getUsername();
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return ldapUserDetails.getAuthorities();
	}

	/**
	 * @return the ctrlchgpwd
	 */
	public Boolean getCtrlchgpwd() {
		return ctrlchgpwd;
	}

	/**
	 * @param ctrlchgpwd
	 *            the ctrlchgpwd to set
	 */
	public void setCtrlchgpwd(Boolean ctrlchgpwd) {
		this.ctrlchgpwd = ctrlchgpwd;
	}

	/**
	 * @return the emailPi
	 */
	public String getEmailPi() {
		return emailPi;
	}

	/**
	 * @param emailPi
	 *            the emailPi to set
	 */
	public void setEmailPi(String emailPi) {
		this.emailPi = emailPi;
	}

	/**
	 * @return the emailServ
	 */
	public String getEmailServ() {
		return emailServ;
	}

	/**
	 * @param emailServ
	 *            the emailServ to set
	 */
	public void setEmailServ(String emailServ) {
		this.emailServ = emailServ;
	}

	/**
	 * @return the banderas
	 */
	public String getBanderas() {
		return banderas;
	}

	/**
	 * @param banderas
	 *            the banderas to set
	 */
	public void setBanderas(String banderas) {
		this.banderas = banderas;
	}

	public String getUnidad() {
		return unidad;
	}

	public void setUnidad(String unidad) {
		this.unidad = unidad;
	}

	@Override
	public String toString() {
		return "DgiiUserDetailsImpl [ldapUserDetails=" + ldapUserDetails
				+ ", email=" + email + ", nit=" + nit + ", displayName="
				+ displayName + ", cadmTrib=" + cadmTrib + ", rol=" + rol
				+ ", ssello=" + ssello + ", itecnico=" + itecnico
				+ ", ubicacionFisica=" + ubicacionFisica + ", unidadRecep="
				+ unidadRecep + ", rawPassword=" + rawPassword
				+ ", detFtpHost=" + detFtpHost + ", detFtpUsr=" + detFtpUsr
				+ ", detFtpPass=" + detFtpPass + ", detFtpInitialDir="
				+ detFtpInitialDir + ", nomInstitucion=" + nomInstitucion
				+ ", nitRepresentante=" + nitRepresentante + ", dui=" + dui
				+ ", ctrlchgpwd=" + ctrlchgpwd + ", emailPi=" + emailPi
				+ ", emailServ=" + emailServ + ", banderas=" + banderas
				+ ", unidad=" + unidad + "]";
	}

}

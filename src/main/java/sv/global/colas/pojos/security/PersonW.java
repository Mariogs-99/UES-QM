/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sv.global.colas.pojos.security;

import java.io.Serializable;

/**
 *
 * @author JosueRogelio
 */
public class PersonW implements Serializable{
    private static final long serialVersionUID = -4454216593897L;
    private Boolean authorities;
    private String nit;
    private String displayName;
    private String cadmTrib;
    private String rol; //uniqueMember
    private String ssello;
    private Long itecnico;
    private String cargo;
    private String tipoUsuario;
    private String ubicacionFisica;
    private String unidadRecep;
    private String rawPassword;
    private String detFtpHost;
    private String detFtpUsr;
    private String detFtpPass;
    private String detFtpInitialDir;
    private String nomInstitucion;
    private String nitRepresentante;
    private String dui;
    private Boolean ctrlchgpwd;
    private String email;
    private String emailPi;
    private String emailServ;
    private String banderas;
    private Object clave;
    private String givenName;
    private String middleName;
    private String sn;
    private String requerimiento;
    private String tipoInstitucion;
    private String preferredlanguage;
    private String fh_ingreso_serv;
    private String uidNumber;
    private String unidad;
    private String gidNumber;
    private String username;
    private String orclisenabled;
    private String pasaporteUsr;
    private String pasaporteRep;
    private String ipUsuario;

    @Override
    public String toString() {
        return "Person{" + "authorities=" + authorities + ", nit=" + nit + ", displayName=" + displayName + ", cadmTrib=" + cadmTrib + ", rol=" + rol + ", ssello=" + ssello + ", itecnico=" + itecnico + ", cargo=" + cargo + ", tipoUsuario=" + tipoUsuario + ", ubicacionFisica=" + ubicacionFisica + ", unidadRecep=" + unidadRecep + ", rawPassword=" + rawPassword + ", detFtpHost=" + detFtpHost + ", detFtpUsr=" + detFtpUsr + ", detFtpPass=" + detFtpPass + ", detFtpInitialDir=" + detFtpInitialDir + ", nomInstitucion=" + nomInstitucion + ", nitRepresentante=" + nitRepresentante + ", dui=" + dui + ", ctrlchgpwd=" + ctrlchgpwd + ", email=" + email + ", emailPi=" + emailPi + ", emailServ=" + emailServ + ", banderas=" + banderas + ", clave=" + clave + ", givenName=" + givenName + ", middleName=" + middleName + ", sn=" + sn + ", requerimiento=" + requerimiento + ", tipoInstitucion=" + tipoInstitucion + ", preferredlanguage=" + preferredlanguage + ", fh_ingreso_serv=" + fh_ingreso_serv + ", uidNumber=" + uidNumber + ", unidad=" + unidad + ", gidNumber=" + gidNumber + ", username=" + username + '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getCadmTrib() {
        return cadmTrib;
    }

    public void setCadmTrib(String cadmTrib) {
        this.cadmTrib = cadmTrib;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getSsello() {
        return ssello;
    }

    public void setSsello(String ssello) {
        this.ssello = ssello;
    }

    public Long getItecnico() {
        return itecnico;
    }

    public void setItecnico(Long itecnico) {
        this.itecnico = itecnico;
    }

    public String getUbicacionFisica() {
        return ubicacionFisica;
    }

    public void setUbicacionFisica(String ubicacionFisica) {
        this.ubicacionFisica = ubicacionFisica;
    }

    public String getUnidadRecep() {
        return unidadRecep;
    }

    public void setUnidadRecep(String unidadRecep) {
        this.unidadRecep = unidadRecep;
    }

    public String getRawPassword() {
        return rawPassword;
    }

    public void setRawPassword(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String getDetFtpHost() {
        return detFtpHost;
    }

    public void setDetFtpHost(String detFtpHost) {
        this.detFtpHost = detFtpHost;
    }

    public String getDetFtpUsr() {
        return detFtpUsr;
    }

    public void setDetFtpUsr(String detFtpUsr) {
        this.detFtpUsr = detFtpUsr;
    }

    public String getDetFtpPass() {
        return detFtpPass;
    }

    public void setDetFtpPass(String detFtpPass) {
        this.detFtpPass = detFtpPass;
    }

    public String getDetFtpInitialDir() {
        return detFtpInitialDir;
    }

    public void setDetFtpInitialDir(String detFtpInitialDir) {
        this.detFtpInitialDir = detFtpInitialDir;
    }

    public String getNomInstitucion() {
        return nomInstitucion;
    }

    public void setNomInstitucion(String nomInstitucion) {
        this.nomInstitucion = nomInstitucion;
    }

    public String getNitRepresentante() {
        return nitRepresentante;
    }

    public void setNitRepresentante(String nitRepresentante) {
        this.nitRepresentante = nitRepresentante;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public Boolean getCtrlchgpwd() {
        return ctrlchgpwd;
    }

    public void setCtrlchgpwd(Boolean ctrlchgpwd) {
        this.ctrlchgpwd = ctrlchgpwd;
    }

    public String getEmailPi() {
        return emailPi;
    }

    public void setEmailPi(String emailPi) {
        this.emailPi = emailPi;
    }

    public String getEmailServ() {
        return emailServ;
    }

    public void setEmailServ(String emailServ) {
        this.emailServ = emailServ;
    }

    public String getBanderas() {
        return banderas;
    }

    public void setBanderas(String banderas) {
        this.banderas = banderas;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Boolean getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Boolean authorities) {
        this.authorities = authorities;
    }

    /**
     * @return the givenName
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * @param givenName the givenName to set
     */
    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    /**
     * @return the middleName
     */
    public String getMiddleName() {
        return middleName;
    }

    /**
     * @param middleName the middleName to set
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    /**
     * @return the sn
     */
    public String getSn() {
        return sn;
    }

    /**
     * @param sn the sn to set
     */
    public void setSn(String sn) {
        this.sn = sn;
    }

    /**
     * @return the clave
     */
    public Object getClave() {
        return clave;
    }

    /**
     * @param clave the clave to set
     */
    public void setClave(Object clave) {
        this.clave = clave;
    }

    public String getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(String requerimiento) {
        this.requerimiento = requerimiento;
    }

    public String getTipoInstitucion() {
        return tipoInstitucion;
    }

    public void setTipoInstitucion(String tipoInstitucion) {
        this.tipoInstitucion = tipoInstitucion;
    }

    public String getPreferredlanguage() {
        return preferredlanguage;
    }

    public void setPreferredlanguage(String preferredlanguage) {
        this.preferredlanguage = preferredlanguage;
    }

    public String getFh_ingreso_serv() {
        return fh_ingreso_serv;
    }

    public void setFh_ingreso_serv(String fh_ingreso_serv) {
        this.fh_ingreso_serv = fh_ingreso_serv;
    }

    public String getUidNumber() {
        return uidNumber;
    }

    public void setUidNumber(String uidNumber) {
        this.uidNumber = uidNumber;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getGidNumber() {
        return gidNumber;
    }

    public void setGidNumber(String gidNumber) {
        this.gidNumber = gidNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOrclisenabled() {
        return orclisenabled;
    }

    public void setOrclisenabled(String orclisenabled) {
        this.orclisenabled = orclisenabled;
    }

    public String getPasaporteUsr() {
        return pasaporteUsr;
    }

    public void setPasaporteUsr(String pasaporteUsr) {
        this.pasaporteUsr = pasaporteUsr;
    }

    public String getPasaporteRep() {
        return pasaporteRep;
    }

    public void setPasaporteRep(String pasaporteRep) {
        this.pasaporteRep = pasaporteRep;
    }

    public String getIpUsuario() {
        return ipUsuario;
    }

    public void setIpUsuario(String ipUsuario) {
        this.ipUsuario = ipUsuario;
    }
}

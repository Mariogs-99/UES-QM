/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.global.colas.pojos.entities;

/**
 *
 * @author Owner
 */
public class PreguntaEvaluacionPojo {

    private String pregunta;
    private String correcto;
    private String respuestas;
    private String respuest;
    private String unidad;
    private String descripcion;
    private Long preguntaId;

    public Long getPreguntaId() {
        return preguntaId;
    }

    public void setPreguntaId(Long preguntaId) {
        this.preguntaId = preguntaId;
    }

    public String getRespuest() {
        return respuest;
    }

    public void setRespuest(String respuest) {
        this.respuest = respuest;
    }
    
    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getCorrecto() {
        return correcto;
    }

    public void setCorrecto(String correcto) {
        this.correcto = correcto;
    }

    public String getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}

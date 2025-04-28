package sv.global.colas.pojos.entities;

import java.io.Serializable;
import java.util.List;
import org.springframework.util.AutoPopulatingList;
import sv.global.colas.entities.GcZona;
import sv.global.colas.entities.TbUnidadRecep;

public class AlertaPojo implements Serializable {

    private List<TbUnidadRecep> listadoUnidades
            = new AutoPopulatingList<TbUnidadRecep>(TbUnidadRecep.class);
    private List<GcZona> listadoZonas
            = new AutoPopulatingList<GcZona>(GcZona.class);

    public List<TbUnidadRecep> getListadoUnidades() {
        return listadoUnidades;
    }

    public void setListadoUnidades(List<TbUnidadRecep> listadoUnidades) {
        this.listadoUnidades = listadoUnidades;
    }

    public List<GcZona> getListadoZonas() {
        return listadoZonas;
    }

    public void setListadoZonas(List<GcZona> listadoZonas) {
        this.listadoZonas = listadoZonas;
    }
}

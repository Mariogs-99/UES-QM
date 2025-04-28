import { Component, inject } from '@angular/core';
import { CtrlHomeService } from '../services/ctrl-home.service';
import { ApiService } from '../services/api.service';
import { PwaService } from '../services/pwa.service';
import { formatDate } from '@angular/common';
import { unidadreceptora } from '../interfaces/public.interface';
import moment from 'moment';


interface AlertButton {
  text: string;
  role?: 'cancel' | 'destructive' | string;
  cssClass?: string | string[];
  id?: string;
  htmlAttributes?: { [key: string]: any };
  handler?: (value: any) => void;
}

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})

export class HomePage {

  view: string = "cards";
  // modalctrl: boolean = false;
  public ctrlhome = inject(CtrlHomeService)
  public api = inject(ApiService)
  public pwa = inject(PwaService)

  constructor() {
    let a = moment().format('YYYY-MM-DDT08:00:ssZ')
    let b = moment().format('YYYY-MM-DDT16:00:ssZ')
    console.log(moment().isBetween(a, b))

    try {

      this.ctrlhome.cita.fhReservacion = moment().format('DD-MM-YYYY')
      console.log(this.ctrlhome.cita.fhReservacion)
    } catch (e) {

    }
    this.api.doRequest('servicios/', {}, 'get', '').then((res: any) => {
      this.ctrlhome.servicios = res;
    }).finally(() => {
      this.api.doRequest('tramite/', {}, 'get', '').then((res: any) => {
        this.ctrlhome.tramites_temp = res;
      }).finally(() => {
        this.api.doRequest('unidad-receptora', {}, 'get', '').then((res: any[]) => {
          res.forEach((element: any) => {
            let unid: any = new unidadreceptora();
            for (const atr in element) {
              unid[atr as any] = element[atr]
            }
            this.ctrlhome.unidades.push(unid);

            // console.log(this.ctrlhome.unidades)
            // console.log(this.ctrlhome.tramites_temp)
          });
        });
      });
    });
    // console.log('aaaaa')
  }

  doAction(opt: number) {
    this.ctrlhome.modalctrl = (opt == 1) ? true : false;
    this.ctrlhome.view = 'cita'
    this.ctrlhome.title = 'Servicios'
    console.log('a', opt)
  }

  /* GUARDA EL CARNET EN EL LOCALSTORAGE PARA ACCEDERLO COMO POR DEFECTO */
  saveCarnet(value: string) {
    if (value[0] != '') {
      this.api.doRequest('alumno/' + value[0], { nit: value[0] }, 'get', '').then((res: any) => {
        localStorage.setItem('carnet', value[0])
        localStorage.setItem('alumno', JSON.stringify(res))
        this.ctrlhome.cita.nit = res.ncarnet
        let unidad: unidadreceptora;
        try {
          unidad = this.ctrlhome.unidades.filter((item: unidadreceptora) => {
            return item.dunidadRecep == res.facultad;
          })[0];
          this.ctrlhome.cita.cunidadRecep = parseInt(unidad.cunidadRecep);
        }
        catch (e) {
        }
        this.ctrlhome.view = 'cita'
        this.ctrlhome.carnet = value[0];
        this.ctrlhome.modalctrl = false;
      })
    } else {
      this.ctrlhome.showalert('Debe ingresar un carnet valido')
      this.ctrlhome.view = 'cards'
    }
  }

  alertButtons: AlertButton[] = [
    { text: 'Aceptar', handler: (value) => { this.saveCarnet(value) } },
    { text: 'Cancelar', handler: (value) => { this.ctrlhome.modalctrl = false } }

  ];
  public alertInputs = [
    {
      placeholder: 'Ingrese su carnet',
      id: 'carnet',
      attributes: {
        maxlength: 10,
      },
    },
  ];
}

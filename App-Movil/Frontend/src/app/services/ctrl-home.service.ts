import { inject, Injectable } from '@angular/core';
import { AlertController } from '@ionic/angular';
import { citascl, reserva, servicios, tramite, unidadreceptora } from '../interfaces/public.interface';
import moment from 'moment';





@Injectable({
  providedIn: 'root'
})



export class CtrlHomeService {

  modalctrl: boolean = false;
  modalform: boolean = false;
  modalcitas: boolean = false;
  carnet: string | null;
  alertController = inject(AlertController)
  view: string = "cards"
  servicios: Array<servicios> = [];
  tramites: Array<tramite> = [];
  tramites_temp: Array<tramite> = [];
  unidades: Array<unidadreceptora> = [];
  cita: reserva = new reserva();
  innerview: string = "servicios"
  citasStorage: Array<citascl> = [];
  constructor() {
    this.carnet = localStorage.getItem('carnet') as string
    this.citasStorage = this.getCitas();
  }

  getCitas(): Array<any> {
    return (localStorage.getItem('citas') != null) ? JSON.parse(localStorage.getItem('citas') as string) : [];
  }

  async showalert(msg: string, title?: string) {
    const alert = await this.alertController.create({
      header: title,
      message: msg,
      buttons: ['aceptar'],
    });

    await alert.present();
  }
  title: string = "Tipo de Usuario"
  getTitle() {
    // return (this.view=='cards')?'Tipo de estudiante':
    // return 'Tipo de estudiante'
  }

  mngNav() {
    if (this.title == 'Servicios') {
      this.view = 'cards'
      this.title = "Tipo de estudiante"
    }
    if (this.title == 'Tramites') {
      // this.view = 'servicios'
      this.title = 'Servicios'
      this.tramites = []
    }
    if (this.view == 'cards') {
      localStorage.removeItem('carnet')
      localStorage.removeItem('alumno')
      this.carnet = null;
    }
    console.log(this.view)
  }



  public getAllDataToFormData(form: any): FormData {
    let formData = new FormData();
    for (const [key, value] of Object.entries(form)) {
      if (Array.isArray(value) && value.every(item => item instanceof File)) {
        for (let i = 0; i < value.length; i++) {
          formData.append(`${key}[]`, value[i]);
        }
        continue;
      }
      if (typeof value == 'object' || typeof value == 'number') {
        formData.append(key, JSON.stringify(value));
        continue;
      }
      formData.append(key, value as never);
    }
    return formData;
  }

  mngcitasmodal(opt: number = 1) {
    switch (opt) {
      case 1:
        this.modalcitas = true;
        break;
      case 2:
        this.modalcitas = false;
        localStorage.removeItem('citas');
        this.citasStorage.forEach((item) => {
          this.setCitas(item)
        })
        this.citasStorage = this.getCitas();
        break;
    }
  }

  removeCitas(index: number) {
    this.alertSiNo('¿Esta seguro de?').then((res) => {
      return (res) ? this.citasStorage.splice(index, 1) : null;
    })
  }
  getDate(date: string) {
    try {

      return moment(date).format('DD-MM-YYY');
    } catch (e) {
      return date;
    }
  }

  getUnidadyTramite(id: string, opt: number = 1) {
    let name: string = "";
    // console.log(id)
    switch (opt) {
      case 1:
        try {
          name = this.tramites_temp.filter((item) => {
            return item.NTramiteId.toString() == id.toString();
          })[0].SNombre
        } catch (e) {
          name = 'No disponible'
        }
        break;
      case 2:
        try {
          name = this.unidades.filter((item) => {
            return item.cunidadRecep.toString() == id.toString();
          })[0].dunidadRecep
        } catch (e) {
          name = 'No disponible'
        }
        break;
    }
    return name;
  }



  public setCitas(cita: any) {
    try {
      if (localStorage.getItem('citas') != null) {
        let citas: any[]
        citas = JSON.parse(localStorage.getItem('citas') as string)
        citas.push(cita)
        localStorage.setItem('citas', JSON.stringify(citas))
      } else {
        localStorage.setItem('citas', JSON.stringify([cita]))
      }
      this.citasStorage = this.getCitas();
    } catch (e) { console.log(e) }
  }


  async alertSiNo(message: any, backdrop: boolean = true): Promise<any> {
    let buttons = []
    return await new Promise(async (resolve) => {
      const alert = await this.alertController.create({
        cssClass: 'my-alert-class',
        backdropDismiss: backdrop,
        header: message,
        inputs: [],
        buttons: [{
          text: 'Si',
          handler: (data) => {
            resolve(true)
            // alert.dismiss(true);
            return true;
          }
        }, {
          text: 'No',
          role: 'cancel',
          cssClass: 'secondary',
          handler: () => {
            // alert.dismiss(false);
            resolve(false)
            return false;
          }
        }
        ]
      });
      let choice;
      await alert.present();
      await alert.onDidDismiss().then((data) => {
        choice = data
      })
      return await choice;
    });
  }

}

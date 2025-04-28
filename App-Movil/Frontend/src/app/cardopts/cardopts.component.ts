import {
  Component,
  inject,
  NO_ERRORS_SCHEMA,
  OnInit,
  ViewChild,
} from '@angular/core';
import { IonicModule, IonModal, NavController } from '@ionic/angular';
import gc_servicios from '../../assets/jsons/gc_servicios.json';
import gc_tramite from '../../assets/jsons/gc_tramite.json';
import { CommonModule } from '@angular/common';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { CtrlHomeService } from '../services/ctrl-home.service';
import { ApiService } from '../services/api.service';
import { servicios, tramite } from '../interfaces/public.interface';
import moment from 'moment';
import { DynamicformComponent } from '../shared/Dynamicform/Dynamicform.component';
import { FieldsFormsinit } from '../shared/interfaces/fieldsforminit';
import { FieldsForms } from '../shared/interfaces/Dynamicform.';
import { EventsService } from '../services/events.service';
import { PullUpComponent } from '../shared/pull-up/pull-up.component';
import { MaskitoOptions, MaskitoElementPredicateAsync } from '@maskito/core';
import { MaskitoModule } from '@maskito/angular';

export class presentData {
  // carnet: string = "";
  NServiciosId: string = '';
  S_NOMBRE: string = '';
  D_TRAMITE: string = '';
  fecha?: string = '';
}
@Component({
  selector: 'app-cardopts',
  templateUrl: './cardopts.component.html',
  styleUrls: ['./cardopts.component.scss'],
  standalone: true,
  imports: [
    IonicModule,
    CommonModule,
    FormsModule,
    DynamicformComponent,
    PullUpComponent,
    ReactiveFormsModule,
    MaskitoModule,
  ],
})
export class CardoptsComponent implements OnInit {
  @ViewChild('confirmationModal') confirmationModal!: IonModal;
  readonly phone: MaskitoOptions = {
    mask: [/\d/, /\d/, /\d/, /\d/, '-', /\d/, /\d/, /\d/, /\d/],
  };
  
  //Donde:
  // - "duiMask" es el nombre de la mascara
  // - "/\d/" son los espacios numericos, la cantidad de ellos sera el numero de caracteres que tendra la mascara
  // - "-" es el caracter fijo que tendra la mascara que se pondra automaticamente.

  //La siguiente linea configura al input de modo que solo acepte los caracteres definidos en la mascara
  readonly maskPredicate: MaskitoElementPredicateAsync = async (el: any) =>
    (el as HTMLIonInputElement).getInputElement();

  caterrors: any = {
    required: 'dato requerido',
  };
  public ctrlhome = inject(CtrlHomeService);
  public api = inject(ApiService);
  public events = inject(EventsService);
  public FormBuilder = inject(FormBuilder);

  data = new presentData();
  // public formcita: FormGroup = new FormGroup({});
  fieldsRegistrodoc: FieldsForms[] = [];

  catalogModal: boolean = false;

  constructor(private navCtrl: NavController) {}
  // presentingElement: any = null

  min: any;
  cat_temps: any[] = [];
  public citaForm: FormGroup = this.FormBuilder.group({
    NReservaCitaId: [''],
    NTelefono: [''],
    SCorreo: [''],
    SObservaciones: ['', [Validators.required]],
    bEstado: [0, [Validators.required]],
    cunidadRecep: ['', [Validators.required]],
    cunidadRecep_txt: ['', [Validators.required]],
    fhReservacion: [undefined, [Validators.required]],
    nTramiteId: ['', [Validators.required]],
    nTramiteId_txt: ['', [Validators.required]],
    nit: ['', [Validators.required]],
    sCodVerifica: [''],
  });


  //ctrlhome = { modalform: false }; // Estado del primer modal
  messagemodal = false; // Estado del segundo modal
  isConfirmationModalOpen = false; // Estado para el overlay

  ngOnInit() {
    this.min = moment().format('YYYY-MM-DDTHH:mm:ssZ');
    this.citaForm.controls['fhReservacion'].setValue(
      moment().format('YYYY-MM-DDTHH:mm:ssZ')
    );
  }


  openConfirmationModal() {
    this.isConfirmationModalOpen = true; // Activa el overlay
    this.messagemodal = true; // Abre el modal de confirmación
  }

  filtertramite(serv: servicios) {
    console.log(this.ctrlhome.tramites_temp);
    console.log(serv);
    this.ctrlhome.tramites = this.ctrlhome.tramites_temp.filter(
      (item: tramite) => {
        // if (this.ctrlhome.carnet != null) {
        return item.NServiciosId == serv.NServiciosId;
        // } else {
        //   return (item.NServiciosId == serv.NServiciosId && item.B_NIT_REQUERIDO == "0")
        // }
      }
    ) as any;
    console.log(this.ctrlhome.tramites);
    this.ctrlhome.title = 'Tramites';
  }

  saveCarnet(event: any) {
    if (event.detail.value.length >= 6) {
      this.api
        .doRequest(
          'alumno/' + event.detail.value,
          { nit: event.detail.value },
          'get',
          ''
        )
        .then((res: any) => {
          try {
            let unidad = this.ctrlhome.unidades.filter((item: any) => {
              return item.dunidadRecep == res.facultad;
            })[0];
            this.citaForm.controls['cunidadRecep'].setValue(
              parseInt(unidad.cunidadRecep)
            );
          } catch (e) {}
          this.citaForm.controls['nit'].setValue(res.ncarnet);
          this.citaForm.controls['SObservaciones'].setValue(
            `${res.nombre} ${res.apellido}`
          );
          this.citaForm.controls['cunidadRecep_txt'].setValue(res.facultad);
        });
    }
  }

  admCita(tranmite: tramite) {
    console.log(tranmite);
    this.data = new presentData();
    this.ctrlhome.cita.nTramiteId = parseInt(tranmite.NTramiteId);
    this.ctrlhome.cita.fhReservacion = moment().format('YYYY-MM-DDTHH:mm:ssZ');
    // console.log(this.ctrlhome.cita)
    this.citaForm.controls['nTramiteId'].setValue(tranmite.NTramiteId);
    this.citaForm.controls['nTramiteId_txt'].setValue(tranmite.SNombre);
    this.data = {
      D_TRAMITE: tranmite.DTramite,
      S_NOMBRE: tranmite.SNombre,
      NServiciosId: tranmite.NServiciosId,
      // carnet: this.ctrlhome.carnet
    };

    console.log(this.ctrlhome.cita);
    if (localStorage.getItem('alumno') != undefined) {
      console.log(this.ctrlhome.cita);
      let user: any = JSON.parse(localStorage.getItem('alumno') as string);
      console.log(user);

      this.citaForm.controls['nit'].setValue(user.ncarnet);
      this.citaForm.controls['SObservaciones'].setValue(
        user.nombre + user.apellido
      );
      this.citaForm.controls['cunidadRecep'].setValue(
        this.ctrlhome.cita.cunidadRecep
      );
      this.citaForm.controls['cunidadRecep_txt'].setValue(user.facultad);
    }
    this.ctrlhome.modalform = true;
  }

  getCarnet() {
    this.ctrlhome.modalctrl = true;
  }

  // Añade el método para cerrar todos los modales
  closeAllModals() {
    this.confirmationModal.dismiss();
    this.navCtrl.navigateRoot('/home');
    console.log('cerrando modales');
  }

  onConfirmationDismiss() {
    // Close other modals
    this.ctrlhome.modalform = false;
    this.catalogModal = false;
    this.messagemodal = false;
    this.isConfirmationModalOpen = false;

    // Reset form and other states
    // this.citaForm.reset();
    // this.ctrlhome.modalctrl = false;
    // this.data = new presentData();
  }

  submitCita() {
    // Marca todos los campos del formulario como "sucios" para que se muestren los errores de validación si existen.
    for (const atr in this.citaForm.value) {
      this.citaForm.controls[atr].markAsDirty();
    }

    // Extrae la fecha del campo de fecha y hora del formulario, eliminando la parte de la hora.
    const date = this.citaForm.value.fhReservacion.split('T')[0];

    // Usa la librería moment.js para crear dos objetos de fecha y hora: uno a las 08:00:00 y otro a las 16:00:00 en la fecha proporcionada.
    let a = moment().format(date + 'T08:00:00');
    let b = moment().format(date + 'T16:00:00');

    // Obtén la hora actual en formato ISO para compararla con la hora de la cita.
    let now = moment().format('YYYY-MM-DDTHH:mm:ss');

    // Verifica si la fecha y hora de la cita proporcionada en el formulario está dentro del rango permitido (08:00 a 16:00).
    // También verifica si la cita es para un momento futuro y si el formulario es válido.
    if (
      moment(this.citaForm.value.fhReservacion).isBetween(a, b) &&
      moment(this.citaForm.value.fhReservacion).isAfter(now) &&
      this.citaForm.valid
    ) {
      // Envía una solicitud POST a la API con los datos del formulario.
      this.api
        .doRequest('reserva-cita', this.citaForm.value, 'post')
        .then((res: any) => {
          // Maneja la respuesta de la API.
          console.log(res);

          // Establece el valor del campo 'NReservaCitaId' en el formulario con el ID de reserva recibido de la API.
          this.citaForm.controls['NReservaCitaId'].setValue(res.NReservaCitaId);

          try {
            // Si el ID de reserva es mayor o igual a 0, actualiza las citas en el controlador y muestra un mensaje de confirmación.
            if (res.NReservaCitaId >= 0) {
              this.ctrlhome.setCitas(res);
              this.messagemodal = true;
            }

            // Aquí se comentaron las líneas que muestran una alerta de confirmación de cita, probablemente por razones de prueba o desarrollo.
            // this.ctrlhome.showalert(`¡Su cita ha sido agendada exitosamente!
            //   Fecha:${moment(this.citaForm.value.fhReservacion).format('DD-MM-YYYY')} \n
            //   Oficina ${this.citaForm.value.cunidadRecep_txt} \n
            //   Por favor, conserve este número de cita y preséntese en la oficina indicada para generar su ticket. \n
            //   Número de cita ${res.NReservaCitaId}
            //   NOTA: la cita serva valida unicamente desde 10 minutos antes hasta 10 minutos despues de la hora programada. Por favor, asegúrese de llegar dentro de este periodo`,
            //   'Confirmacion de cita');
          } catch (e) {
            // Si ocurre un error, muestra una alerta indicando que ha ocurrido un error.
            this.ctrlhome.showalert('Ha ocurrido un error');
          }
        });
    } else {
      // Si la fecha y hora de la cita no están dentro del rango permitido o la cita es para un momento pasado, muestra una alerta adecuada.
      if (!moment(this.citaForm.value.fhReservacion).isBetween(a, b)) {
        this.ctrlhome.showalert(
          'La hora de cita solo puede ser entre las 8:00 a.m y 4:00 p.m'
        );
      } else if (!moment(this.citaForm.value.fhReservacion).isAfter(now)) {
        this.ctrlhome.showalert('La hora de la cita debe ser en el futuro.');
      }

      // Marca todos los campos del formulario como "sucios" para que se muestren los errores de validación si existen.
      for (const atr in this.citaForm.value) {
        this.citaForm.controls[atr].dirty;
      }
    }
  }

  isenableday = (dateString: string) => {
    const date = new Date(dateString);
    const utcDay = date.getUTCDay();

    /**
     * Date will be enabled if it is not
     * Sunday or Saturday
     */
    return utcDay !== 0;
  };

  closeModal() {
    console.log('cerrando modal');
    this.ctrlhome.modalform = false;
  }

  onDismissChange(event: any) {
    console.log(event);
  }

  mngNav() {
    if (this.ctrlhome.tramites.length > 0) {
      this.ctrlhome.tramites = [];
    }
  }

  setCombo(val: any) {
    console.log(val);
    try {
      console.log(this.OPTCAT);
      if (this.OPTCAT == 'nTramiteId') {
        this.citaForm.controls['nTramiteId'].setValue(val.NTramiteId);
        this.citaForm.controls['nTramiteId_txt'].setValue(val.DTramite);
      }
      if (this.OPTCAT == 'cunidadRecep') {
        this.citaForm.controls['cunidadRecep'].setValue(val.cunidadRecep);
        this.citaForm.controls['cunidadRecep_txt'].setValue(val.dunidadRecep);
      }
      this.catalogModal = false;
    } catch (e) {}
  }

  compareWith(o1: any, o2: any) {
    return o1.toString() === o2;
  }

  getError(field: string) {
    // console.log(this.formularioReactivo.get(field)?.touched &&
    // this.formularioReactivo.get(field)?.errors)
    try {
      return this.citaForm.get(field)?.errors
        ? this.caterrors[['required'][0]]
        : '';
    } catch (e) {
      return '';
    }
  }

  OPTCAT: string = '';
  cats: any[] = [];
  getCombo(type: any) {
    this.OPTCAT = type;
    this.cat_temps =
      this.OPTCAT == 'cunidadRecep'
        ? this.ctrlhome.unidades
        : this.ctrlhome.tramites_temp;
    this.cats =
      this.OPTCAT == 'cunidadRecep'
        ? this.ctrlhome.unidades
        : this.ctrlhome.tramites_temp;
    this.catalogModal = true;
  }

  filtercats(event: any) {
    console.log(event);
    let cat: string =
      this.OPTCAT == 'cunidadRecep' ? 'dunidadRecep' : 'DTramite';
    this.cat_temps = this.cats.filter((item: any) => {
      return item[cat].toLowerCase().includes(event.detail.value);
    });
    console.log(this.cat_temps);
  }
}

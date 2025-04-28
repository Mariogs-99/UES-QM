import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, EventEmitter, Input, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
// import { FieldsForms, fieldsInfo } from 'src/app/interfaces/dinamyc-form-general.interface';
import { IonItem } from "@ionic/angular/standalone";
import { ActionSheetController, IonicModule } from '@ionic/angular';
import { FieldsForms } from '../interfaces/Dynamicform.';
import { Maskito } from '@maskito/core';
import { PullUpComponent } from '../pull-up/pull-up.component';
// import { Events } from '@services/events.service';
// import { PwaService } from '@services/pwa.service';
// import { MaskitoElementPredicateAsync, MaskitoOptions } from '@maskito/core';
// import { MaskitoModule } from '@maskito/angular';
// import { DynamicInfoComponent } from '../dynamic info/dynamic info.component';

@Component({
  selector: 'app-dynamicform',
  standalone: true,
  imports: [
    CommonModule,
    IonicModule,
    FormsModule,
    ReactiveFormsModule,
    PullUpComponent
    // DynamicInfoComponent
  ],
  templateUrl: './dynamicform.component.html',
  styleUrl: './dynamicform.component.css',
})
export class DynamicformComponent {
  [x: string]: any;

  // @Input() fieldsInfo: fieldsInfo[] = [];
  @Input() fields: FieldsForms[] = [];
  @Input() campoOnChange: string[] = [];
  catalogModal: boolean = false;
  catalogo: any;
  @Input() accept: string = '.pdf';
  @Input() maxFileSize: string = '10000KB';
  @Input() filesLimit: number = 1;
  @Output() formularioEmitido: EventEmitter<FormGroup> = new EventEmitter<FormGroup>();
  @Output() cambioValor: EventEmitter<any> = new EventEmitter<any>();
  @Output() enterKeyPressed: EventEmitter<any> = new EventEmitter<any>();

  caterrors: any = {
    'required': "dato requerido"
  }
  formularioReactivo: FormGroup = new FormGroup({});

  constructor(
    public fb: FormBuilder,
    private actionSheetController: ActionSheetController,
  ) { }

  ngOnInit() {
    console.log(this.fields)
    if (this.fields) {
      this.initializeForm();
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['fields'] && !changes['fields'].firstChange && this.fields) {
      this.initializeForm();
    }
  }

  public initializeForm(): void {
    const formControls = this.fields?.reduce((acc: any, field: FieldsForms) => {
      // if(field.type=='divider'){
      //   return ;
      // }
      acc[field.formControlName] = [
        field.inicializacion,
        field.validacionesSincronas,
        field.validacionesAsincronas,
      ];
      if (field.type == 'combo') {
        acc[field.formControlName.split('_txt')[0]] = [
          field.inicializacion,
          field.validacionesSincronas,
          field.validacionesAsincronas,
        ];
      }
      return acc;
    }, {});
    this.formularioReactivo = this.fb.group(formControls || {});

    if (this.campoOnChange.length > 0) {
      this.campoOnChange.forEach((element) => {
        this.formularioReactivo.get(element)?.valueChanges.subscribe((value) => {
          let valueCopia: any = { value };
          valueCopia.input = element;
          this.cambioValor.emit(valueCopia);
        });
      });
    }

    this.emitirFormulario();
  }

  public resetForm(): void {
    this.initializeForm();
  }

  private emitirFormulario(): void {
    this.formularioEmitido.emit(this.formularioReactivo);
  }


  onEnterKeyPressed(field: string): void {
    this.enterKeyPressed.emit(field);
  }

  shouldShowField(field: any, hide: boolean): boolean {
    const control = this.formularioReactivo?.get(field.formControlName);
    if (hide && control?.disabled) {
      return false;
    }
    if (!hide && control?.enabled) {
      return true;
    }
    if (hide && control?.enabled) {
      return false;
    }

    return true;
  }

  getCombo(field: FieldsForms) {
    // let nombre_cat=Object.keys(field.options);
    console.log(field)
    let data = {
      'catalogo': field.options,
      'propiedad': field.formControlName,
      'titulo': field.label,
      'version': 2,
    }


  }

  async selectImage(frmcontrol: string) {
    let buttons = []
    buttons.push({ text: 'Usar la camara', handler: () => { this.takesnap('takeSnap', 3, frmcontrol); } })
    buttons.push({ text: 'Elegir Imagen de la galeria', handler: () => { this.takesnap('uploadphoto', 2, frmcontrol) } })
    buttons.push({ text: 'Cancel', role: 'cancel' })
    const actionSheet = await this.actionSheetController.create({
      header: "Seleccione una fuente para la Foto",
      buttons: buttons
    });
    await actionSheet.present();
  }

  conditionalfields(field: FieldsForms) {
    if (field.hide) {
      return false; //el campo debe estar escondido
    } else {
      if (field.conditional != null) {
        // if (field.conditional.cond) {
        //   return (this.formularioReactivo.controls[field.conditional.frmpadre].value == field.conditional.value)
        // } else {
        //   return (this.formularioReactivo.controls[field.conditional.frmpadre].value != field.conditional.value)
        // }
        return (this.formularioReactivo.controls[field.conditional.frmpadre].value == field.conditional.value)
      } else {
        return true;
      }
    }
  }

  getTag(field: any) {

  }

  imgDetail = {
    img: '',
    modal: false,
  }
  viewImg(img: string, opt: number = 1) {
    this.imgDetail.modal = (opt == 1) ? true : false
    this.imgDetail.img = (opt == 1) ? img : ''
  }
  getError(field: string) {
    // console.log(this.formularioReactivo.get(field)?.touched &&
    // this.formularioReactivo.get(field)?.errors)
    try {
      return this.formularioReactivo.get(field)?.errors ? this.caterrors[['required'][0]] : ''
    } catch (e) {
      return '';
    }
  }

  takesnap(type: any, config: any, frmcontrol: string) {
    // console.log(type, config)

  }

}

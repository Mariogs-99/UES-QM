import { TemplateRef } from '@angular/core';
import { ValidationErrors } from '@angular/forms';
import { MaskitoOptions } from '@maskito/core';
// import { fieldsInfo } from '../interfaces/dinamyc-form-general.interface';

interface FormControlConfig {
  inicializacion: null | string | number; // Tipo del valor inicial del campo
  syncValidators?: ValidationErrors[]; // Arreglo de validadores sincrónicos
  asyncValidators?: ValidationErrors[]; // Arreglo de validadores asincrónicos
}


export class FieldsFormsinit {
  type?: string = "text";
  size?: number = 12;
  hide?: boolean = false;
  keyFilter?: string | RegExp | null | undefined | any;
  label?: string = "";
  labelPlacement?: string = "stacked";
  maxlength?: number = 20;
  minlength?: number = 0;
  formControlName?: string = "";
  mask?: MaskitoOptions | string | any;
  class?: string;
  inputGroupAddon?: string;
  inicializacion?: (string | number | boolean | ValidationErrors);
  validacionesSincronas?: ValidationErrors[] = [];
  validacionesAsincronas?: ValidationErrors[] = [];
  options?: selectTwo[]; // Solo para campos de tipo dropdown
  placeholderDropdown?: string; // Solo para campos de tipo dropdown
  nameDropdown?: string; // Solo para campos de tipo dropdown
  virtualScroll?: boolean; // Solo para campos de tipo dropdown
  conditional?: condition;
  searchfnc?: (parameter: any) => void;
  // fieldsInfo?: fieldsInfo[];
  tag?: tagStructure = new tagStructure()
}
// MaskitoOptions
// MaskitoElementPredicateAsync
export class tagStructure {
  label: string = "";
  style: string = "";
}

export interface condition {
  frmpadre: string;
  value: string;
  cond?:string;
}
export class selectTwo {
  id_catalogo: string = "";
  valor: string = "";
  padre?: string = "";
  img?: string = "";
  frmpadre?: string = "";
}

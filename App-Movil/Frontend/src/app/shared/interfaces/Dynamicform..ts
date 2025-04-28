import { TemplateRef } from '@angular/core';
import { ValidationErrors } from '@angular/forms';

interface FormControlConfig {
  inicializacion: null | string | number; // Tipo del valor inicial del campo
  syncValidators?: ValidationErrors[]; // Arreglo de validadores sincrónicos
  asyncValidators?: ValidationErrors[]; // Arreglo de validadores asincrónicos
}

export interface FormControlsInterface {
  [key: string]: FormControlConfig;
}

export interface FieldsForms {
  type: string;
  size: number;
  hide: boolean;
  keyFilter: string | RegExp | null | undefined | any;
  label: string;
  maxlength: number | null;
  minlength: number | null;
  formControlName: string;
  labelPlacement: string;
//   mask?: MaskitoOptions | any;
  class?: string;
  inputGroupAddon?: string;
  inicializacion: (string | number | ValidationErrors) | null;
  validacionesSincronas: ValidationErrors[];
  validacionesAsincronas: ValidationErrors[];
  options?: selectTwo[]; // Solo para campos de tipo dropdown
  placeholderDropdown?: string; // Solo para campos de tipo dropdown
  nameDropdown?: string; // Solo para campos de tipo dropdown
  virtualScroll?: boolean; // Solo para campos de tipo dropdown
  conditional?: condition// se pone el campo y el valor para que aparezcan o no aparezcan ciertos campos
  searchfnc?: (parameter: any) => void,
  fieldsInfo?: fieldsInfo[];
  tag?: tagStructure;
}
// MaskitoOptions
// MaskitoElementPredicateAsync
export interface tagStructure {
  label: string;
  style: string;
}

export interface condition {
  frmpadre: string;
  value: string;
  // cond:string;
}
export interface fieldsInfo {
  type: string;
  size: number;
  label: string;
  info?: string;
  class?: string;

}
export class selectTwo {
  id_catalogo: string = "";
  valor: string = "";
  padre?: string = "";
  img?: string = "";
  frmpadre?: string = "";
}
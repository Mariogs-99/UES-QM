import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { IonicModule } from '@ionic/angular';
import { IonModal } from '@ionic/angular/directives/overlays/modal';
import { OverlayEventDetail } from '@ionic/core/components';
import { EventsService } from 'src/app/services/events.service';

export interface getcat {
  catalogo: Array<any>,
  propiedad: string,
  version: number,
  titulo: string,
}

export interface catalog {

}
@Component({
  selector: 'app-pull-up',
  templateUrl: './pull-up.component.html',
  styleUrls: ['./pull-up.component.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule]
})
export class PullUpComponent implements OnInit {
  @Input() element!: {};
  @Output() eventToParent = new EventEmitter<any>();
  @Output() newItemEvent = new EventEmitter<string>();
  // @ViewChild(IonModal) modal: any;
  @ViewChild('SBAR') search: any;

  @Input() ionModal = false;
  constructor(
    public events: EventsService,
  ) {

  }

  comboArrayResult: any = [];
  @Input() catalogo: any
  modelo: any;
  propiedad: any;
  value: any;
  frase: any;
  catalog: any;
  comboArray: any;
  titleCombo: any;
  ngOnInit() {
    // this.comboArrayResult=this.objeto
    console.log('a')
  }
  Close() {
    // this.modal.dismiss(null, 'cancel');
    this.ionModal = false;
    // this.events.destroy('set:catalogo')
    // this.events.unsubscribe()
  }

  open() {
    this.ionModal = true;
  }

  combo_temp: any = []
  setModal(Name_catalogo: any, subcatalogo: any, idpadre?: any, val = 'valor') {

    this.ionModal = true;
    this.comboArrayResult = [];
    this.combo_temp = []
    this.titleCombo = this.formatTittle(subcatalogo);
    //console.log(idpadre)
    try {
      if (idpadre > 0) {
        this.comboArray = this.catalogo[Name_catalogo][subcatalogo].filter(function (item: any) { return parseInt(item.padre) == idpadre });
        this.comboArray.forEach((element: any, index: any) => {
          if (index < 50) {
            this.combo_temp.push(element)
          }
        })
      }
      else {
        this.comboArray = this.catalogo[Name_catalogo][subcatalogo]
        console.log(this.comboArray)
        this.comboArray.forEach((element: any, index: any) => {
          if (index < 50) {
            this.combo_temp.push(element)
          }
        })
      }
    }
    catch (e) { }
    setTimeout(() => {
      try {
        this.search.setFocus();
      } catch (e) {
        console.log(e)
      }
    }, 1000);
    console.log(this.search);

    // this.modal.present();
  }

  setModal2(data: getcat) {
    // console.log(Name_catalogo)
    // console.log(subcatalogo)
    // console.log(this.catalogo)
    this.ionModal = true;
    this.comboArrayResult = [];
    this.combo_temp = []
    this.titleCombo = data.titulo;
    //console.log(idpadre)
    try {
      // if (idpadre > 0) {
      //   // console.log(this.catalogo[Name_catalogo][subcatalogo]);
      //   // console.log(Name_catalogo);
      //   // console.log(subcatalogo);
      //   this.comboArray = this.catalogo[Name_catalogo][subcatalogo].filter(function (item: any) { return parseInt(item.padre) == idpadre });
      //   this.comboArray.forEach((element: any, index: any) => {
      //     if (index < 50) {
      //       this.combo_temp.push(element)
      //     }
      //   })
      // }
      // else {
      this.comboArray = data.catalogo
      this.comboArray.forEach((element: any, index: any) => {
        if (index < 50) {
          this.combo_temp.push(element)
        }
      })
      // }
    }
    catch (e) { }
    setTimeout(() => {
      try {
        this.search.setFocus();
      } catch (e) {
        console.log(e)
      }
    }, 1000);
    console.log(this.search);

    // this.modal.present();
  }

  setCombo(val: any) {
    console.log(val)
    let cat: any = {}
    cat['id_catalogo'] = val['id_catalogo']
    cat['valor'] = val['valor']
    cat['padre'] = val['padre']
    if (val.hasOwnProperty('ZONA_PRESENCIA_CANCHA')) {
      cat['zona'] = val['ZONA_PRESENCIA_CANCHA']
    }
    this.newItemEvent.emit(JSON.stringify(cat));
    this.Close()
  }

  // Name_catalogo,subcatalogo,val

  getCombo(catalogo: any, modelo: any, propiedad: any, val = '') {
    if (val != '') {
      this.value = val
    } else {
      this.value = 'VALOR'
    }
    // console.log(this.value);
    this.modelo = modelo;
    this.catalog = catalogo;
    this.comboArrayResult = [];
    this.propiedad = propiedad;
    // console.log(this.catalogo)
    this.titleCombo = this.formatTittle(catalogo);
    try {
      this.comboArray = this.catalogo[catalogo]
    } catch (e) { }
    // this.modal.present();
  }

  filtrarCatalogo(frase2: any) {
    try {
      let frase = frase2.detail.value
      //console.log(frase)
      //console.log(this.comboArray)
      this.comboArrayResult = [];
      // this.comboArrayResult =;
      // console.log(frase);
      let i = 0;
      this.comboArray.forEach((element: any) => {
        if (element.valor.toLowerCase().includes(frase.toLowerCase()) && i < 50) {
          this.comboArrayResult.push(element);
          i++
        }
      });
      //console.log(this.comboArrayResult)
      this.combo_temp = this.comboArrayResult

      if (this.comboArrayResult.length > 50) {
        // console.log(this.comboArrayResult)
        //console.log(this.combo_temp)
      }
    } catch (e) { console.log(e) }

  }
  //miscelaneo
  formatTittle(catalogo: any) {
    var inputString = catalogo;
    inputString = inputString.replace(/_/g, ' ');
    // // console.log(inputString);
    var positions = [];
    for (var i = 0; i < inputString.length; i++) {
      if (inputString[i].match(/[A-Z]/) != null) {
        positions.push(i);
      }
    }
    // // console.log(positions);
    positions.forEach((position) => {
      inputString = this.stringConverter(inputString, position);
    });
    return inputString.toUpperCase();
  }

  stringConverter(varString: any, varCommaPosition: any) {
    var stringArray = varString.split("");
    var outputString = '';
    for (var i = 0; i < stringArray.length; i++) {
      if (i == varCommaPosition) {
        outputString = outputString + ' ';
      }

      outputString = outputString + stringArray[i];
    }
    return outputString;
  }
  ngOnDestroy() {
    this.ionModal = false;
  }


}

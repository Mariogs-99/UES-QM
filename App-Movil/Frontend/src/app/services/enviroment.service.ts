import { Injectable } from '@angular/core';
import { environment } from "../../environments/environment";


@Injectable({
  providedIn: 'root'
})
export class EnviromentService {

  //public server_url = 'http://localhost:8081'; //localhost
  public server_url = 'https://168.232.48.212/colas-backend';

  public VERSION_IOS = '1.240100.0027';
  // public VERSION_ANDROID= '1.230900.0023';
  public VERSION_ANDROID = '1.240700.0016';
  public VERSION_PWA = '1.230606.0001';
  public VERSION_WEB = '1.0.0'


  constructor() { }
  public pwa: any; public setPwa(pwa: any) { this.pwa = pwa; return this; }

}

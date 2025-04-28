import { Injectable } from '@angular/core';
// import { environment } from "../../environments/environment";
import { DomSanitizer } from '@angular/platform-browser';
import { ApiService } from "./api.service"

// import { DeviceUUID } from "./deviceuuid.service";
// import { Device } from '@awesome-cordova-plugins/device/ngx';

import { EnviromentService } from "./enviroment.service";


// export interface fnIn{
//   name:string,
//   func:(param:any)=>{},
// }

@Injectable({
    providedIn: 'root'
})
export class PwaService {
    private pwa_ = this;
    public serviceName = "PwaService";
    public api: ApiService;

    public sanitizer!: DomSanitizer;
    public environment: EnviromentService;
    constructor(
        private ApiService: ApiService,

        private DomSanitizer: DomSanitizer,
        private EnviromentService: EnviromentService,
    ) {

        // this.log(this.auth);

        this.api = ApiService.setPwa(this);

        this.environment = EnviromentService.setPwa(this)

    }
    pinUser: any;
    notfs: number = 0;
    isGpsOk: number = 0;
    isGpsDenny: number = 0;
    isAudioOk: number = 0;
    isSocketAttempts: number = 0;
    isSocketOk: number = 0;
    isCameraOk: number = 0;
    isStorageOk: number = 0;
    deviceInfo: any;
    userPosition: any;
    isLoader: boolean = false;
    fontSize: number = 16;

    async doRequest(url_: any, params_: any, type_: any, quitarPronto: any = "") {
        return await new Promise(async (resolve) => {
            return this.api.doRequest(url_, params_, type_, '').then((res: string) => { resolve(res); });
        });
    }

}

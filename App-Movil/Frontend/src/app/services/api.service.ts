import { inject, Injectable } from '@angular/core';
import { Subscription } from 'rxjs';
import { HttpClient, HttpHeaders, HttpParams, HttpErrorResponse } from '@angular/common/http';
import { catchError, distinct } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { NavController, LoadingController, MenuController, ModalController, AlertController } from '@ionic/angular';
import { EnviromentService } from './enviroment.service';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  subs!: Subscription;

  public enviroment = inject(EnviromentService);
  constructor(
    public http: HttpClient,
    public navCtrl: NavController,
  ) {
  }
  public pwa: any;
  public setPwa(pwa: any) { this.pwa = pwa; return this; }
  api: any;

  async doRequest_({ url_, params_, type_, headers_ }: any): Promise<any> {
    return await new Promise((resolve, reject) => {
      resolve(this.doRequest(url_, params_, type_, headers_));
    });
  }

  urls: Array<any> = [];
  async doRequest(url_: string, params_: any, type_: any, headers_: String = ""): Promise<any> {
    this.api = `${this.enviroment.server_url}`;
    try {
      let url = url_.includes("http:") || url_.includes("https:") ? url_ : `${this.api}/${url_}`;
      let token = headers_;
      let headers = {
        headers: new HttpHeaders({})
      };
      let params = params_;
      try {
      } catch (e) { console.log(e) }
      if (this.subs != undefined && !this.subs.closed && (this.urls.findIndex((item: any) => { return item == url }) >= 0)) {
        this.CancelRequest();
      }
      this.urls.push(url);

      return await new Promise((resolve, reject) => {
        if (type_ === 'post') {
          try {
            this.subs = this.http.post(url, params, headers).pipe(
              catchError(err => {
                return throwError(err);
              })
            ).subscribe(
              (res: any) => {
                if (res['name'] === 'HttpErrorResponse') {
                  resolve(res['error'])
                } else {
                  resolve(res);
                }
                // reject(res)
              }
              , (err) => {
                resolve(err['error'])
              }
            );
          } catch (e) { console.log() }

        }

        if (type_ === 'get') {
          this.subs = this.http.get(url, headers).subscribe(
            (res) => {
              // console.log(res)
              resolve(res);
            }, (err) => {
              resolve(err['error'])
            }
          );
        }
        if (type_ === 'delete') {
          this.subs = this.http.delete(url, headers).subscribe(
            (res) => {
              resolve(res);
            }, (err) => {
              resolve(err['error'])
            }
          );
        }
      }).catch((e) => {
        // console.log(e)
      })
    } catch (e: any) {
      // console.log(e)
      // console.log(this.handleResponse(e.error))
    }
  }

  async CancelRequest() {
    if (this.subs) {
      this.subs.unsubscribe()
    }
  }


  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) { console.error('An error occurred:', JSON.stringify(error)); }
    else { console.error('Backend returned code ' + error.status + ' body was:' + JSON.stringify(error)); }
    return throwError('Something bad happened; please try again later.');
  }

  async Login() {
    return this.doRequest(`${this.api}/login`, {}, 'post');
  }

  async me(token: any) {
    return this.doRequest("me", {}, 'post', token)
  }

}

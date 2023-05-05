import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";
import {BehaviorSubject, combineLatest, firstValueFrom, lastValueFrom, Observable, switchMap} from 'rxjs';
//import {Miahoot, Enseignant} from "./interfaces";
import {Auth} from "@angular/fire/auth";
import {AuthService} from "./auth.service";



export interface Enseignant {
  pseudo: string;
  mail: string;
  mdp: string;
}

export interface Reponse {
  id?: number;
  label: string;
  estValide: boolean;
}

export interface Question {
  id?: number;
  label: string;
  reponses: Reponse[];
}

export interface Miahoot {
  id?: number;
  nom: string;
  questions: Question[];
}
@Injectable({
  providedIn: 'root'
})
export class DsService {


private bsAskUpdate = new BehaviorSubject<void>(undefined);
readonly obsMiahoots: Observable<Miahoot[]>;


  constructor(private http: HttpClient, private auth: AuthService) {
    this.obsMiahoots = combineLatest([this.bsAskUpdate, auth.currentUser]).pipe(
      switchMap( async ([_, U]) => {
        if (U === null) {
          return []
        }
        const miahoots = await firstValueFrom( http.get<Miahoot[]>( `/api/enseignants/${U.uid}/miahoots}` ) )
        return miahoots;
      })
    )
  }




  async createMiahoot(M: Miahoot): Promise<Miahoot> {
    const U = await firstValueFrom(this.auth.currentUser);
    if (U !== null) {
      this.http.post<Miahoot>(`/api/enseignants/${U.uid}/miahoots`, M);
      this.bsAskUpdate.next();
    }
    throw "illegal Miahoot creation";
  }


  async getEnseignant(mail: String): Promise<Enseignant> {
    const U = await firstValueFrom(this.auth.currentUser);
    if (U !== null) {
      return  firstValueFrom(this.http.get<Enseignant>(`/api/enseignants/${U.uid}/miahoots`));
    }
    throw "enseignant introuvable"

  }

  async get(endpoint: string, recherche: string) {
    let url = "http://localhost:8080/api/";

    let reponse = await lastValueFrom(this.http.get<any>(url + endpoint +"/"  +recherche));
    console.log(reponse)
  }

  //post
   post(endpoint: string, body: Enseignant) {
    let url = "http://localhost:8080/api/";
     return firstValueFrom(this.http.post<any>(url + endpoint, body));
   }

  async deleteMiahoot(idMiahoot:  number){
    const U = await firstValueFrom(this.auth.currentUser);
    if(U !== null){
      console.log("user not null")
      console.log(""+U.uid)
      this.http.delete<Miahoot>(`/api/enseignants/${U.uid}/miahoots/${idMiahoot}`)
    }else{
      throw "erreur lors de la suppression du Miahoot"
    }
  }


  async getGeneral(endpoint: string, recherche: string) {
    let url = "http://localhost:8080/api/";

    let reponse = await lastValueFrom(this.http.get<any>(url + endpoint +"/"  +recherche));
    console.log(reponse)
  }

  //post
  postGeneral(endpoint: string, body: Enseignant) {
    let url = "http://localhost:8080/api/";

    return firstValueFrom(this.http.post<any>(url + endpoint, body));
  }

  async postE( enseignant: Enseignant){
    let url = "http://localhost:8080/api/enseignants/"
    let reponse =  await lastValueFrom(this.http.post(url, enseignant));
    console.log(reponse)
  }

  async postM( id:number,  miahoot: Miahoot){
    let url = "http://localhost:8080/api/enseignants/"
    url += id + "/miahoot"
    let reponse =  await lastValueFrom(this.http.post(url, miahoot));
    console.log(reponse)
  }
}
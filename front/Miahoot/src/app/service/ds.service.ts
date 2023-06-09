import { Injectable } from '@angular/core';
import { HttpClient} from "@angular/common/http";
import {BehaviorSubject, combineLatest, EMPTY, firstValueFrom, lastValueFrom, Observable, switchMap} from 'rxjs';
//import {Miahoot, Enseignant} from "./interfaces";
import {Auth, authState, User} from "@angular/fire/auth";
import {AuthService} from "./auth.service";
import {Firestore} from "@angular/fire/firestore";
import {Partie} from "./interfaces";



export interface Enseignant {
  uid?: string;
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
  public  user: Observable<User | null> = EMPTY;

  mail = '';
  mdp = '';
  pseudo = '';


  constructor(private http: HttpClient, private auth: AuthService,  fireS: Firestore) {
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


  getMiahootById(uid: String, id: number):Promise<Miahoot>{

    let url = "/api/enseignants/";
    url += uid + "/miahoots/" + id
    return  lastValueFrom(this.http.get<any>(url));
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
    let url = "/api/";

    let reponse = await lastValueFrom(this.http.get<any>(url + endpoint +"/"  +recherche));
    console.log(reponse)
  }

  //post
   post(endpoint: string, body: Enseignant) {
    let url = "/api/";
     return firstValueFrom(this.http.post<any>(url + endpoint, body));
   }

  async deleteMiahoot(idMiahoot:  number){
    const U = await firstValueFrom(this.auth.currentUser);
    if(U !== null){
      console.log("user not null")
      console.log(""+U.uid)
      this.http.delete<Miahoot>(`/api/enseignants/${U.uid}/miahoots/${idMiahoot}`).subscribe(
        (res) => {
          console.log("DELETE request successful:", res);
        },
        (err) => {
          console.error("Error deleting Miahoot:", err);
          throw "Erreur lors de la suppression du Miahoot";
        })
    }
  }


  async getGeneral(endpoint: string, recherche: string) {
    let url = "/api/";

    let reponse = await lastValueFrom(this.http.get<any>(url + endpoint +"/"  +recherche));
    console.log(reponse)
  }

  //post
  postGeneral(endpoint: string, body: Enseignant) {
    let url = "/api/";

    return firstValueFrom(this.http.post<any>(url + endpoint, body));
  }

  async postE( enseignant: Enseignant){
    let url = "/api/enseignants/"
    let reponse =  await lastValueFrom(this.http.post(url, enseignant));
    console.log(reponse)
  }

  async postM( uid:String,  miahoot: Miahoot){
    let url = "/api/enseignants/"
    url += uid + "/miahootjson"
    let reponse =  await lastValueFrom(this.http.post(url, miahoot));
    console.log(reponse)
  }



  //recp user

  recupUser(mail: string , pseudo:string, mdp:string ): void{

    this.mail = mail;
    this.pseudo = pseudo;
    this.mdp = mdp;
  }


  //envoyer user
  envoyerUser(): string[]{
    return [this.mail, this.pseudo, this.mdp];
  }

  async createPartie(uidEnseignant: string, idMiahoot: number, body :any): Promise<void> {
    let url = "/api/enseignants/"
    url += uidEnseignant + "/miahoots/" + idMiahoot + "/parties"
    let reponse = await lastValueFrom(this.http.post(url, body));
  }

  //get partie


  async updateMiahoot(idMiahoot: number, updatedMiahoot: Miahoot){
    // V1 update à verifier
    const U = await firstValueFrom(this.auth.currentUser);
    if(U !== null){
      console.log("user not null")
      console.log(""+U.uid)
      this.http.patch<Miahoot>
      (`/api/enseignants/${U.uid}/miahoots/${idMiahoot}`,updatedMiahoot).subscribe(
        (res) => {
          console.log("UPDATE request successful:", res);
        },
        (err) => {
          console.error("Error updating Miahoot:", err);
          throw "Erreur lors de la maj du Miahoot";
        })
    }else{
      console.warn("user not found")
    }
  }

  // convertie un json en miahoot

  jsonToMiahoot(json : string): Miahoot{
    const miahoot: Miahoot = JSON.parse(json, (key, value): Miahoot => {
    if (key === 'reponses') {
      return value.map((reponse: Reponse) => ({
        id: reponse.id,
        label: reponse.label,
        estValide: reponse.estValide,
      }));
    } else if (key === 'questions') {
      return value.map((question: Question) => ({
        id: question.id,
        label: question.label,
        reponses: question.reponses.map((reponse: Reponse) => ({
          id: reponse.id,
          label: reponse.label,
          estValide: reponse.estValide,
        })),
      }));
    } else {
      return value
    }
  });
    console.log(miahoot.nom)
    miahoot.questions.forEach((q)=> {console.log(q.label)})
    miahoot.questions.forEach((q)=>{
      q.reponses.forEach((r)=>{
        console.log(r.label)
        console.log(r.estValide)
      })
    })
    return miahoot
  }



}

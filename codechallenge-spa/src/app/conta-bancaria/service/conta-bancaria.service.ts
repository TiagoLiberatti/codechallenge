import { Injectable } from "@angular/core";
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

import { Observable } from "rxjs";

import { ContaBancaria } from "../model/conta-bancaria.model";
import { ContaBancariaFilterForm } from "../model/conta-bancaria-filter-form.model";

@Injectable({
    providedIn: 'root'
})
export class ContaBancariaService{

    url: string = 'http://localhost:8080/api/v1/contas-bancarias';
    httpOptions: any = {
      headers: new HttpHeaders({ 'Content-Type': 'application/json'})
    };;

    constructor(private http: HttpClient) { }

    public buscarContasBancarias(form?: ContaBancariaFilterForm): Observable<ContaBancaria[]>{
      let params = new HttpParams();
      if(form?.nome){params = params.set('nome', form.nome);}      
      if(form?.agencia){params = params.set('agencia', form.agencia);}
      if(form?.chequeEspecialLiberado){params = params.set('chequeEspecialLiberado', form.chequeEspecialLiberado);}
      return this.http.get<ContaBancaria[]>(this.url, {params});
    }

    public buscarContaBancariaPorId(idContaBancaria: number): Observable<ContaBancaria>{
      return this.http.get<ContaBancaria>(this.url+"/"+idContaBancaria);
    }

    public buscarDetalhesContaBancaria(id: number): Observable<ContaBancaria>{
      return this.http.get<ContaBancaria>(this.url+"/detalhes/"+id);
    }

    public cadastrarContaBancaria(contaBancaria: ContaBancaria): Observable<ContaBancaria>{
        return this.http.post<ContaBancaria>(this.url, contaBancaria);
    }

    public atualizarContaBancaria(contaBancaria: ContaBancaria, id: number): Observable<ContaBancaria>{
      return this.http.put<ContaBancaria>(this.url+"/"+id, contaBancaria);
    }

    public deletarContaBancaria(id: number): Observable<ContaBancaria>{
      return this.http.delete<ContaBancaria>(this.url+"/"+id);
    }

}
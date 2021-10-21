import { Component, OnInit } from '@angular/core';

import { ToastrService } from 'ngx-toastr';

import { ContaBancariaFilterForm } from '../../model/conta-bancaria-filter-form.model';
import { ContaBancaria } from '../../model/conta-bancaria.model';
import { ContaBancariaService } from '../../service/conta-bancaria.service';

@Component({
  selector: 'app-conta-bancaria-list',
  templateUrl: './conta-bancaria-list.component.html',
  styleUrls: ['./conta-bancaria-list.component.css']
})
export class ContaBancariaListComponent implements OnInit {

  public contasBancarias: ContaBancaria[];

  constructor(
    private contaBancariaService: ContaBancariaService,
    private toastr: ToastrService
  ) {
    this.contasBancarias = [];
  }

  ngOnInit(): void {
    this.atualizarTabela();
  }

  public atualizarTabela(): void{
    this.contaBancariaService.buscarContasBancarias().subscribe(
      data => {
        if(data){
          this.contasBancarias = data;
        }else{
          this.toastr.error('Nenhuma conta bancária encontrada', '');
          this.contasBancarias = [];
        }
      },
      () => {
        this.toastr.error('Ocorreu um erro ao realizar consulta de contas bancárias', '');
      },
    );
  }

  public deletarContaBancaria(id: number): void{
    this.contaBancariaService.deletarContaBancaria(id).subscribe(data => {
      this.toastr.success('Conta bancária excluída com sucesso!', '');
      this.atualizarTabela();
    });
  }

  public filtrar(form: ContaBancariaFilterForm): void{
    this.contaBancariaService.buscarContasBancarias(form).subscribe(
      data => {
        if(data){
          this.contasBancarias = data;
        }else{
          this.toastr.error('Nenhuma conta bancária encontrada', '');
          this.contasBancarias = [];
        }
      },
      () => {
        this.toastr.error('Ocorreu um erro ao realizar consulta de contas bancárias', '');
      },
    );
  }

}
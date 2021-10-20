import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { ContaBancaria } from '../../model/conta-bancaria.model';
import { ContaBancariaService } from '../../service/conta-bancaria.service';

@Component({
  selector: 'app-conta-bancaria-detail',
  templateUrl: './conta-bancaria-detail.component.html',
  styleUrls: ['./conta-bancaria-detail.component.css']
})
export class ContaBancariaDetailComponent implements OnInit {

  public contaBancariaId = 0;
  public contaBancaria: ContaBancaria;

  constructor(
    private contaBancariaService: ContaBancariaService,
    private toastr: ToastrService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.contaBancaria = {} as ContaBancaria;
  }

  ngOnInit(): void {
    const {id : id} = this.activatedRoute.snapshot.params;
    this.contaBancariaId = id;
    this.buscarContaBancaria(id);
  }

  public buscarContaBancaria(id: number): void{
    this.contaBancariaService.buscarDetalhesContaBancaria(id).subscribe(
      data => {
        if(data){
          this.contaBancaria = data;
        }else{
          this.toastr.error('Nenhuma conta bancária encontrada', '');
        }
      },
      () => {
        this.toastr.error('Ocorreu um erro ao consultar contas bancárias', '');
      },
    );
  }

  public voltar(): void{
    this.router.navigate(['/consultar']);
  }
}

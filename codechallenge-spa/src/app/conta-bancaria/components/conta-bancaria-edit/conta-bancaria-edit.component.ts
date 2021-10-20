import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { ContaBancaria } from '../../model/conta-bancaria.model';
import { ContaBancariaService } from '../../service/conta-bancaria.service';

@Component({
  selector: 'app-conta-bancaria-edit',
  templateUrl: './conta-bancaria-edit.component.html',
  styleUrls: ['./conta-bancaria-edit.component.css']
})
export class ContaBancariaEditComponent implements OnInit {

  public contaBancariaId = 0;
  public contaBancaria: ContaBancaria;

  public form = new FormGroup({
    nome: new FormControl(null, Validators.required),
    numeroConta: new FormControl(null, Validators.required),
    agencia: new FormControl(null, Validators.required),
    chequeEspecialLiberado: new FormControl(null, Validators.required),
    saldo: new FormControl(null, Validators.required),
    chequeEspecial: new FormControl(null, Validators.required),
    taxa: new FormControl(null, Validators.required),
  });

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

  public atualizar(): void{
    this.contaBancariaService.atualizarContaBancaria(this.form.getRawValue(), this.contaBancariaId).subscribe(
      () => {
        this.toastr.success('Conta bancária atualiza com sucesso!', '');
        this.voltar();
      },
      () => this.toastr.error('Ocorreu um erro ao cadastrar uma conta bancária', ''),
    );
  }

  public buscarContaBancaria(id: number): void{
    this.contaBancariaService.buscarContaBancariaPorId(id).subscribe(
      data => {
        if(data){        
          this.contaBancaria = data;
          this.form.patchValue(data)          
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
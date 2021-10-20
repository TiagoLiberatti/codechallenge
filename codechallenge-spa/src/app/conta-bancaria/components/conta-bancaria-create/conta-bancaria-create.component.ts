import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { ToastrService } from 'ngx-toastr';

import { ContaBancariaService } from '../../service/conta-bancaria.service';

@Component({
  selector: 'app-conta-bancaria-create',
  templateUrl: './conta-bancaria-create.component.html',
  styleUrls: ['./conta-bancaria-create.component.css']
})
export class ContaBancariaCreateComponent {

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
    private router: Router
  ) {}

  public salvar(): void{
    this.contaBancariaService.cadastrarContaBancaria(this.form.getRawValue()).subscribe(
      () => {
        this.toastr.success('Conta bancária cadastrada com sucesso!', '');
        this.form.reset();
        this.voltar();
      },
      () => this.toastr.error('Ocorreu um erro ao cadastrar uma conta bancária', ''),
    );
  }

  public voltar(): void{
    this.router.navigate(['/consultar']);
  }

}

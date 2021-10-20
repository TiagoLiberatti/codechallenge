import { Component, EventEmitter, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-conta-bancaria-form',
  templateUrl: './conta-bancaria-form.component.html',
  styleUrls: ['./conta-bancaria-form.component.css']
})
export class ContaBancariaFormComponent {

  @Output() onSubmit: EventEmitter<any> = new EventEmitter();

  public form = new FormGroup({
    nome: new FormControl(null, Validators.required),
    agencia: new FormControl(null, Validators.required),
    chequeEspecialLiberado: new FormControl("", Validators.required),
  });

  constructor() {}

  public filtrar(): void{
    this.onSubmit.emit(this.form.getRawValue());
  }

}
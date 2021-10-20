import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ContaBancariaCreateComponent } from './conta-bancaria/components/conta-bancaria-create/conta-bancaria-create.component';
import { ContaBancariaDetailComponent } from './conta-bancaria/components/conta-bancaria-detail/conta-bancaria-detail.component';
import { ContaBancariaEditComponent } from './conta-bancaria/components/conta-bancaria-edit/conta-bancaria-edit.component';
import { ContaBancariaListComponent } from './conta-bancaria/components/conta-bancaria-list/conta-bancaria-list.component';

const routes: Routes = [
  { path: '', component: ContaBancariaListComponent },
  { path: 'consultar', component: ContaBancariaListComponent },
  { path: 'cadastrar', component: ContaBancariaCreateComponent },
  { path: 'editar/:id', component: ContaBancariaEditComponent },
  { path: 'detalhes/:id', component: ContaBancariaDetailComponent}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
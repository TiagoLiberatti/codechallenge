import { HttpClientModule } from '@angular/common/http';
import localePt from '@angular/common/locales/pt';
import {registerLocaleData} from '@angular/common';
import { NgModule, LOCALE_ID } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ContaBancariaCreateComponent } from './conta-bancaria/components/conta-bancaria-create/conta-bancaria-create.component';
import { ContaBancariaDetailComponent } from './conta-bancaria/components/conta-bancaria-detail/conta-bancaria-detail.component';
import { ContaBancariaEditComponent } from './conta-bancaria/components/conta-bancaria-edit/conta-bancaria-edit.component';
import { ContaBancariaFormComponent } from './conta-bancaria/components/conta-bancaria-form/conta-bancaria-form.component';
import { ContaBancariaListComponent } from './conta-bancaria/components/conta-bancaria-list/conta-bancaria-list.component';

registerLocaleData(localePt)
@NgModule({
  declarations: [
    AppComponent,
    ContaBancariaListComponent,
    ContaBancariaCreateComponent,
    ContaBancariaEditComponent,
    ContaBancariaDetailComponent,
    ContaBancariaFormComponent
  ],
  imports: [
    BrowserModule,
    ToastrModule.forRoot(),
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule
  ],
  providers: [{
    provide: LOCALE_ID,
    useValue: "pt-BR"
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }

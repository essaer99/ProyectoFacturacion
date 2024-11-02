import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';



@NgModule({
  declarations: [],
  imports: [
    CommonModule
  ]
})
export class User {
  
  id?: number;
  nombre: string = '';
  apellidoPaterno: string = '';
  apellidoMaterno: string = '';
  telefono: string = '';
  rfc: string = '';
  razonSocial: string = '';
  tipoContribuyente: string = '';
  regimenFiscal: string = '';
  estado: string = '';
  municipio: string = '';
  codigoPostal: string = '';
  calle: string = '';
  numero: string = '';
  email: string = '';
  password: string = '';

  constructor(init?: Partial<User>) {
    Object.assign(this, init);
  }
}
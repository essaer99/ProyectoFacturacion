import { Component, OnInit } from '@angular/core';
import { RegisterService } from '../../services/register.service';
import { User } from '../../model/user/user.module';

@Component({
  selector: 'app-userdatos',
  standalone: true,
  imports: [],
  templateUrl: './userdatos.component.html',
  styleUrl: './userdatos.component.css'
})
export class UserdatosComponent implements OnInit {


  users: User[] = [];
  user: User = {
    name: '',
    apellidoPaterno: '',
    apellidoMaterno: '',
    telefono: '',
    rfc: '',
    razonSocial: '',
    tipoContribuyente: '',
    regimenFiscal: '',
    estado: '',
    municipio: '',
    codigoPostal: '',
    calle: '',
    numero: '',
    email: '',
    password: ''
  };

  constructor(private registerService: RegisterService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.registerService.getAllUsers().subscribe((data: User[]) => {
      this.users = data;
    });
  }

  onSubmit(): void {
    if (this.user.id) {
      this.registerService.updateUser(this.user).subscribe(() => {
        this.loadUsers();
        this.resetForm();
      });
    } else {
      this.registerService.createUser(this.user).subscribe(() => {
        this.loadUsers();
        this.resetForm();
      });
    }
  }

  editUser(user: User): void {
    this.user = { ...user };
  }

  deleteUser(id: number): void {
    this.registerService.deleteUser(id).subscribe(() => {
      this.loadUsers();
    });
  }

  resetForm(): void {
    this.user = {
      name: '',
      apellidoPaterno: '',
      apellidoMaterno: '',
      telefono: '',
      rfc: '',
      razonSocial: '',
      tipoContribuyente: '',
      regimenFiscal: '',
      estado: '',
      municipio: '',
      codigoPostal: '',
      calle: '',
      numero: '',
      email: '',
      password: ''
    };
  }
}



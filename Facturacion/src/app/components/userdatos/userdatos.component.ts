import { Component, OnInit } from '@angular/core';
import { User } from '../../models/user/user.module';
import { RegisterService } from '../../services/register.service';

@Component({
  selector: 'app-userdatos',
  standalone: true,
  imports: [],
  templateUrl: './userdatos.component.html',
  styleUrl: './userdatos.component.css'
})
export class UserdatosComponent implements OnInit {
    users: User[] = [];
  
    constructor(private registerService: RegisterService) {}
  
    ngOnInit() {
      this.registerService.getUsers().subscribe(
        (data: User[]) => this.users = data,
        error => console.error('Error al obtener usuarios:', error)
      );
    }
  }


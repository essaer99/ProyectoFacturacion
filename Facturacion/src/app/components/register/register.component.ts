import { Component } from '@angular/core';

import { Router } from '@angular/router';
import { User } from '../../models/user/user.module';
import { RegisterService } from '../../services/register.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {

  user: User = new User();

  constructor(private registerService: RegisterService) {}

  onSubmit() {
    if (this.user) {
      this.registerService.createUser(this.user).subscribe(
        response => {
          console.log('Usuario registrado:', response);
          this.user = new User(); // Reset form
        },
        error => console.error('Error al registrar usuario:', error)
      );
    }
  }


/*
  name: string = '';
  email: string = '';
  password: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

onSubmit() {
  const success = this.loginService.register(this.name, this.email, this.password);
  if (success) {
    alert('Registro exitoso, puedes iniciar sesión.');
    this.router.navigate(['/login']); // Redirige a inicio de sesión
  } else {
    alert('Registro fallido');
  }
}*/

}

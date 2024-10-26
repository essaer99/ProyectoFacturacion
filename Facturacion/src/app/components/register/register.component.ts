import { Component } from '@angular/core';
import { LoginService } from '../../services/login.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
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
}

}

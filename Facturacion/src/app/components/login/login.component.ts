import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginService } from '../../services/login.service';


@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email: string = '';
  password: string = '';

  constructor(private loginService: LoginService, private router: Router) {}

  onSubmit() {
    const success = this.loginService.login(this.email, this.password);
    if (success) {
      this.router.navigate(['/bienvenida']); // Redirige al dashboard
    } else {
      alert('Inicio de sesión fallido');
    }
  }

}


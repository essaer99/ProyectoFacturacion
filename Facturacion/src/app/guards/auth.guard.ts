import { CanActivate, CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { Observable } from 'rxjs/internal/Observable';
import { RegisterService } from '../services/register.service';
import { map } from 'rxjs/internal/operators/map';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(): Observable<boolean> {
    // Suponemos que tienes un método en el servicio para validar el token en el backend
    return this.authService.validateToken().pipe(
      map(isValid => {
        if (isValid) {
          return true; // Permite el acceso
        } else {
          this.router.navigate(['/bienvenida']); // Redirige a la página de bienvenida
          return false; // Bloquea el acceso
        }
      })
    );
  }
}
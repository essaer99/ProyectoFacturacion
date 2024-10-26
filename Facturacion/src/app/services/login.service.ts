import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  private isAuthenticated = false;

  login(email: string, password: string): boolean {
    // Lógica de autenticación
    this.isAuthenticated = true; // Simulación
    return this.isAuthenticated;
  }

  register(name: string, email: string, password: string): boolean {
    // Lógica de registro
    return true; // Simulación
  }

  isLoggedIn(): boolean {
    return this.isAuthenticated;
  }
}

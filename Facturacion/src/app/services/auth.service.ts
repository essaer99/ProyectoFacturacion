import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';




@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://tu-backend.com/api'; // URL de backend
  private token: string | null = null;

  constructor(private http: HttpClient) {}

  // Método para iniciar sesión y obtener un token
  login(credentials: { username: string; password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials);
  }

  // Método para verificar si el usuario está autenticado
  isLoggedIn(): boolean {
    // Verifica si hay un token almacenado
    return !!this.token;
  }

  // Método para almacenar el token
  setToken(token: string): void {
    this.token = token;
  }

  // Método para cerrar sesión
  logout(): void {
    this.token = null;
  }
}

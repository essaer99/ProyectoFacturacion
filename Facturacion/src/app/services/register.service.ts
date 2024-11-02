import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { User } from '../models/user/user.module';
import { map } from 'rxjs/internal/operators/map';
import { of } from 'rxjs/internal/observable/of';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  private apiUrl = 'http://localhost:3000/user'; // URL de la API en el backend
  token: any;

  constructor(private http: HttpClient) {}

  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }

  getUser(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`);
  }

  addUser(user: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}`, user);
  }

  updateUser(id: string, user: any): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, user);
  }

  deleteUser(id: string): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }

  //validacion para el inicio de sesion de usuario aun no se si funciona 
  validateToken(): Observable<boolean> {
    const token = this.token;
    if (!token) {
      return of(false); // No hay token, no está autenticado
    }
  
    return this.http.post<{ isValid: boolean }>(`${this.apiUrl}/validate-token`, { token }).pipe(
      map(response => response.isValid)
    );
}

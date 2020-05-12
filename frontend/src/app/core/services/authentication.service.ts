import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ConstantsService } from './constants.service';
import { Observable } from 'rxjs';
import { Login } from 'src/app/shared/models/login';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Registration } from 'src/app/shared/models/registration';
import { PasswordChanger } from 'src/app/shared/models/password-changer';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(
    private http: HttpClient,
    private constantsService: ConstantsService
  ) { }

  login(login: Login): Observable<any> {
    return this.http.post(this.constantsService.authenticationPath + '/login',
    {username: login.username, password: login.password}, {headers: this.headers, responseType: 'text'});
  }

  logout(): Observable<any> {
    return this.http.get(this.constantsService.authenticationPath + '/logout', {headers: this.headers, responseType: 'text'});
  }

  changePassword(passwordChanger: PasswordChanger): Observable<any> {
    return this.http.post(this.constantsService.authenticationPath + '/change-password', passwordChanger,
      {headers: this.headers, responseType: 'json'});
  }

  register(registration: Registration): Observable<any> {
    return this.http.post(this.constantsService.authenticationPath + '/registerUser', registration,
      {headers: this.headers, responseType: 'json'});
  }

  isLoggedIn(): boolean {
    if (localStorage.getItem('user')) {
        return true;
    }
    return false;
  }

  getToken(): string {
    return localStorage.getItem('user');
  }

  getRole(): string {
    const token = this.getToken();
    console.log(token);
    const jwt: JwtHelperService = new JwtHelperService();

    if (!token) {
      return 'NO_ROLE';
    }
    console.log(jwt.decodeToken(token).role);
    return jwt.decodeToken(token).role;
  }
}

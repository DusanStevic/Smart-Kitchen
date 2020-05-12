import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConstantsService {
  readonly localhost = 'http://localhost:8080';
  readonly authenticationPath = 'http://localhost:8080/auth';
}
